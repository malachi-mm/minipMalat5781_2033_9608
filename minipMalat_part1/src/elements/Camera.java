package elements;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static primitives.Util.*;

/**
 *
 * this class is implementaion of camera.
 * using 3 (actually 2) to the direction of the camera
 * and point3D to the position
 *
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

    public Camera(Point3D p0, Vector vto, Vector vup) {
        this.p0 = p0;
        if(!isZero(vto.dotProduct(vup)))
            throw new IllegalArgumentException("yup and yto should be orthogonal");
        this.vTo = vto.normalized();
        this.vUp = vup.normalized();
        vRight=vto.crossProduct(vup).normalized();
    }
    public Camera setViewPlaneSize(double width, double height) {
        this.width=width;
        this.height=height;
        return this;
    }

    public Camera setDistance(double distance){
        if(distance<=0)
            throw new IllegalArgumentException("the distance should be bigger then 0");
        this.distance=distance;
        return this;
    }

    /**
     *
     * @param nX the number of pixels in the x axis
     * @param nY the number of pixels in the y axis
     * @param j,i the indexes of the pixel
     * @return the Ray that starts from the camera and passes through the pixel
     */
    public Ray constructRayThroughPixel(int nX, int nY, int j, int i){
        double xJ=(this.width/nX)*(-(j-((double)(nX-1))/2));
        double yI=(this.height/nY)*(-(i-(double)(nY-1)/2));
        Point3D Pc=p0.add(vTo.scale(distance));
        Point3D pIJ=Pc;
        if (xJ!= 0) pIJ= pIJ.add(vRight.scale(xJ));
        if (yI!= 0) pIJ= pIJ.add(vUp.scale(yI));
        Vector vIJ=pIJ.subtract(this.p0);
        return new Ray(this.p0,vIJ);
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
