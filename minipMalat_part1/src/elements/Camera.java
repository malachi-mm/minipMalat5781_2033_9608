package elements;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static primitives.Util.*;

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
        if(isZero(vto.dotProduct(vup)))
            throw new IllegalArgumentException("yup and yt should be orthogonal");
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
        return null;
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
