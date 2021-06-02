package elements;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;

import static primitives.Util.*;

/**
 * this class is implementaion of camera.
 * using 3 (actually 2) to the direction of the camera
 * and point3D to the position
 * <p>
 * It also has data for view plane
 *
 * @author Achiya Danziger Malachi Mahpod
 * @version 1
 */
public class Camera {
    final Point3D p0;
    final Vector vTo;
    final Vector vUp;
    final Vector vRight;
    double width;
    double height;
    double distance;

    /**
     * the size of the aperture in the view plane
     */
    double apertureRadius = 1;
    /**
     * the distance of the aperture plane from the view plane
     */
    double apertureDistance = 1;

    int sizeSuperSamplingDOF=1;

    public Camera(Point3D p0, Vector vto, Vector vup) {
        this.p0 = p0;
        if (!isZero(vto.dotProduct(vup)))
            throw new IllegalArgumentException("yup and yto should be orthogonal");
        this.vTo = vto.normalized();
        this.vUp = vup.normalized();
        vRight = vup.crossProduct(vto).normalized();
    }

    public Camera setViewPlaneSize(double width, double height) {
        this.width = width;
        this.height = height;
        return this;
    }

    public void setApertureRadius(double apertureRadius) {
        if (apertureRadius <= 0)
            throw new IllegalArgumentException("the distance should be bigger then 0");
        this.apertureRadius = apertureRadius;
    }

    public void setApertureDistance(double apertureDistance) {
        if (apertureDistance <= 0)
            throw new IllegalArgumentException("the distance should be bigger then 0");
        this.apertureDistance = apertureDistance;
    }

    public void setSizeSuperSamplingDOF(int sizeSuperSamplingDOF) {
        this.sizeSuperSamplingDOF = sizeSuperSamplingDOF;
    }

    public Camera setDistance(double distance) {
        if (distance <= 0)
            throw new IllegalArgumentException("the distance should be bigger then 0");
        this.distance = distance;
        return this;
    }

    /**
     * @param nX  the number of pixels in the x axis
     * @param nY  the number of pixels in the y axis
     * @param j,i the indexes of the pixel
     * @return
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
        List<Point3D> points = superSampling(nX, nY, j, i, sizeSuperSamplingDOF, apertureRadius);

        List<Ray> rays = new ArrayList<Ray>();
        for (Point3D point : points) {
            rays.add(new Ray(point, focalPoint.subtract(point)));
        }
        return rays;
    }

    /**
     * calculates the point of the superSampling
     *
     * @param nX   the number of pixels in the x axis
     * @param nY   the number of pixels in the y axis
     * @param j,i  the indexes of the pixel
     * @param SIZE the number of samples
     * @return list of the points to use in superSampling
     */
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
    }

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
