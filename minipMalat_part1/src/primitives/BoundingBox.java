package primitives;

import geometries.Geometry;
import geometries.Sphere;

import java.util.LinkedList;

import static primitives.Util.isZero;

public class BoundingBox {
    Point3D pointMax, pointMin;


    Geometry geo;

    /**
     * constructor that gets two points
     *
     * @param pointMax the max points of the box
     * @param pointMin the min point of the box
     */
    public BoundingBox(Point3D pointMax, Point3D pointMin) {
        this.pointMax = pointMax;
        this.pointMin = pointMin;
    }


    /**
     * constructor that gets other BoundingBox
     * and adds it to the list in the BoundingBox
     *
     * @param boundingBox
     */
    public BoundingBox(BoundingBox boundingBox) {

        pointMax = boundingBox.pointMax;
        pointMin = boundingBox.pointMin;
    }

    public BoundingBox addBoundingBox(BoundingBox boundingBox) {
        pointMax = pointMax.maxPoint(boundingBox.pointMax);
        pointMin = pointMin.minPoint(boundingBox.pointMin);
        return this;
    }

    /**
     * @param ray the ray we want to check for
     * @return if the ray intersects the box
     */
    public boolean hasIntersection(Ray ray) {
        Point3D vector = ray.getDir().getHead();
        Point3D point = ray.getP0();
        double xMax, yMax, zMax, xMin, yMin, zMin;

        if (isZero(vector.getX())) {
            if (pointMax.getX() > point.getX() && pointMin.getX() < point.getX()) {
                xMax = Double.MAX_VALUE;
                xMin = 0;
            } else
                return false;

        } else {
            xMax = (pointMax.getX() - point.getX()) / vector.getX();
            xMin = (pointMin.getX() - point.getX()) / vector.getX();
        }

        if (isZero(vector.getY())) {
            if (pointMax.getX() > point.getY() && pointMin.getY() < point.getY()) {
                yMax = Double.MAX_VALUE;
                yMin = 0;
            } else
                return false;

        } else {
            yMax = (pointMax.getY() - point.getY()) / vector.getY();
            yMin = (pointMin.getY() - point.getY()) / vector.getY();
        }

        if (isZero(vector.getZ())) {
            if (pointMax.getZ() > point.getZ() && pointMin.getZ() < point.getZ()) {
                zMax = Double.MAX_VALUE;
                zMin = 0;
            } else
                return false;

        } else {
            zMax = (pointMax.getZ() - point.getZ()) / vector.getZ();
            zMin = (pointMin.getZ() - point.getZ()) / vector.getZ();
        }

        return !(xMin > yMax || xMin > zMax ||
                yMin > xMax || yMin > zMax ||
                zMin > yMax || zMin > xMax);

    }


}
