package elements;

import geometries.Intersectable;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;

import static primitives.Util.*;

/**
 * this class is implementation of camera.
 * using 3 (actually 2) to the direction of the camera
 * and point3D to the position
 * It also has data for view plane
 *
 * @author Achiya Danziger Malachi Mahpod
 * @version 1
 */
public class Camera {

    final Point3D p0;//the location of the camera
    final Vector vTo;//the Direction of the camera
    final Vector vUp;//the up direction-represents the upper side of the picture
    final Vector vRight;//the right direction

    double width;//the width of the view plane
    double height;//the height of the view plane
    double distance;//the distance between the camera and the view plane

    /**
     * the size of the aperture in the view plane
     */
    double apertureRadius = 1;
    /**
     * the distance of the aperture plane from the view plane
     */
    double apertureDistance = 1;
    /**
     * how many rays we will use for DOF
     */
    int sizeSuperSamplingDOF=1;


    /**
     * how many rays we will use for anti aliasing
     */
    int sizeSuperSamplingAntiAliasing=1;

    /**
     * a constructor for camera which calculates vright
     * @param p0 the location of the camera
     * @param vto the Direction of the camera
     * @param vup the up direction-represents the upper side of the picture
     */
    public Camera(Point3D p0, Vector vto, Vector vup) {
        this.p0 = p0;
        if (!isZero(vto.dotProduct(vup)))//if the are not orthogonal
            throw new IllegalArgumentException("yup and yto should be orthogonal");
        this.vTo = vto.normalized();
        this.vUp = vup.normalized();
        vRight = vup.crossProduct(vto).normalized();
    }

    /**
     * sets the size of the view plane
     * @param width the width of the view plane
     * @param height the height of the view plane
     * @return this object
     */
    public Camera setViewPlaneSize(double width, double height) {
        this.width = width;
        this.height = height;
        return this;
    }

    /**
     * sets the size of the aperture
     * @param apertureRadius the new size of the aperture
     */
    public void setApertureRadius(double apertureRadius) {
        if (apertureRadius <= 0)
            throw new IllegalArgumentException("the distance should be bigger then 0");
        this.apertureRadius = apertureRadius;
    }

    /**
     * sets the distance from the view plane to the  aperture plane
     * @param apertureDistance the new  distance from the view plane to the  aperture plane
     */
    public void setApertureDistance(double apertureDistance) {
        if (apertureDistance <= 0)
            throw new IllegalArgumentException("the distance should be bigger then 0");
        this.apertureDistance = apertureDistance;
    }

    /**
     *sets the number of rays that DOF will use
     * @param sizeSuperSamplingDOF the new number of DOF rays
     */
    public void setSizeSuperSamplingDOF(int sizeSuperSamplingDOF) {
        this.sizeSuperSamplingDOF = sizeSuperSamplingDOF;
    }


    /**
     * sets the distance between the camera and the view plane
     * @param distance the new distance between the camera and the view plane
     * @return this object
     */
    public Camera setDistance(double distance) {
        if (distance <= 0)
            throw new IllegalArgumentException("the distance should be bigger then 0");
        this.distance = distance;
        return this;
    }


    /**
     *sets the number of rays that antialiasing will use
     * @param sizeSuperSamplingAntiAliasing the new number of antialiasing rays
     */
    public Camera setSizeSuperSamplingAntiAliasing(int sizeSuperSamplingAntiAliasing) {
        this.sizeSuperSamplingAntiAliasing = sizeSuperSamplingAntiAliasing;
        return this;
    }


    /**
     * @param nX  the number of pixels in the x axis
     * @param nY  the number of pixels in the y axis
     * @param j,i the indexes of the pixel
     * @return the point in the designated place on the viewplane
     */
    public Point3D calcPointOnPixel(int nX, int nY, double j, double i) {
        double xJ = (this.width / nX) * ((j - ((double) (nX - 1)) / 2));
        double yI = (this.height / nY) * (-(i - (double) (nY - 1) / 2));
        Point3D Pc = p0.add(vTo.scale(distance));
        Point3D pIJ = Pc;
        if (!isZero(xJ)) pIJ = pIJ.add(vRight.scale(xJ));
        if (!isZero(yI)) pIJ = pIJ.add(vUp.scale(yI));

        return pIJ;
    }

    /**
     * @param nX  the number of pixels in the x axis
     * @param nY  the number of pixels in the y axis
     * @param j,i the indexes of the pixel
     * @return the Ray that starts from the camera and passes through the pixel
     */
    public Ray constructRayThroughPixel(int nX, int nY, int j, int i) {
        Vector vIJ = calcPointOnPixel(nX, nY, j, i).subtract(this.p0);
        return new Ray(this.p0, vIJ);
    }

    /**
     * @param nX  the number of pixels in the x axis
     * @param nY  the number of pixels in the y axis
     * @param j,i the indexes of the pixel
     * @return
     */
    public List<Ray> calcApertureRays(int nX, int nY, int j, int i) {
        Point3D q0 = p0.add(vTo.scale(distance + apertureDistance));
        Ray ray = constructRayThroughPixel(nX, nY, j, i);
        Vector vec = q0.subtract(ray.getP0());
        double n = vec.dotProduct(vTo);
        double t = n / (ray.getDir().dotProduct(vTo));

        Point3D focalPoint = ray.getPoint(t);
        List<Point3D> points = SuperSampling.superSampling(calcPointOnPixel(nX, nY, j, i),vTo,sizeSuperSamplingDOF,apertureRadius);// superSampling(nX, nY, j, i, sizeSuperSamplingDOF, apertureRadius);

        List<Ray> rays = new ArrayList<Ray>();
        for (Point3D point : points) {
            rays.add(new Ray(point, focalPoint.subtract(point)));
        }
        return rays;
    }

    /**
     * calculates the rays from the pixel for antialiasing
     * @param nX  the number of pixels in the x axis
     * @param nY  the number of pixels in the y axis
     * @param j,i the indexes of the pixel
     * @return list of the Rays we will use for antialiasing
     */
    public List<Ray> calcAntiAliasingRays(int nX, int nY, int j, int i) {

        List<Point3D> points = SuperSampling.superSampling
                (calcPointOnPixel(nX, nY, j, i), vRight,vUp,sizeSuperSamplingAntiAliasing,width/nX,height/nY);// superSampling(nX, nY, j, i, sizeSuperSamplingDOF, apertureRadius);
        List<Ray> rays = new ArrayList<Ray>();
        for (Point3D point : points) {
            rays.add(new Ray(p0, point.subtract(p0)));
        }
        return rays;
    }

    /**
     * @param nX  the number of pixels in the x axis
     * @param nY  the number of pixels in the y axis
     * @param j,i the indexes of the pixel
     * @return the ray in the pixel
     */

    public Ray calcMainRay(int nX, int nY, int j, int i) {
        return constructRayThroughPixel(nX, nY, j, i);
    }


    /*
     * calculates the point of the superSampling
     *
     * @param nX   the number of pixels in the x axis
     * @param nY   the number of pixels in the y axis
     * @param j,i  the indexes of the pixel
     * @param SIZE the number of samples
     * @return list of the points to use in superSampling
     */
    /*
    private List<Point3D> superSampling(int nX, int nY, int j, int i, int SIZE, double radius) {
        List<Point3D> points = new ArrayList<Point3D>();
        double newSize = Math.sqrt((SIZE - 1) / 4);
        for (int k = 1; k < newSize + 1; k++) {
            for (int t = 1; t < newSize + 1; t++) {
                //might be change because now it is a rectangular but not a circle
                points.add(calcPointOnPixel(nX, nY, j + radius / k, i + radius / t));
                points.add(calcPointOnPixel(nX, nY, j - radius / k, i + radius / t));
                points.add(calcPointOnPixel(nX, nY, j + radius / k, i - radius / t));
                points.add(calcPointOnPixel(nX, nY, j - radius / k, i - radius / t));
            }
        }
        points.add(calcPointOnPixel(nX, nY, j, i));
        return points;
    }*/

    public Point3D getP0() {
        return p0;
    }

    public Vector getvTo() {
        return vTo;
    }

    public Vector getvUp() {
        return vUp;
    }

    public Vector getvRight() {
        return vRight;
    }
}
