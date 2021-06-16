package primitives;

import geometries.Geometry;
import geometries.Sphere;

import java.util.LinkedList;

import static primitives.Util.isZero;

public class BoundingBox {
    Point3D pointMax, pointMin;
    Point3D center;

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
        calcCenter();
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
        calcCenter();
    }

    public BoundingBox addBoundingBox(BoundingBox boundingBox) {
        pointMax = pointMax.maxPoint(boundingBox.pointMax);
        pointMin = pointMin.minPoint(boundingBox.pointMin);
        calcCenter();
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
            if (pointMax.getX() >= point.getX() && pointMin.getX() <= point.getX()) {
                xMax = Double.MAX_VALUE;
                xMin = Double.MIN_VALUE;
            } else
                return false;

        } else {
            double t1 = (pointMax.getX() - point.getX()) / vector.getX();
            double t2 = (pointMin.getX() - point.getX()) / vector.getX();
            if(t1 < t2){
                xMin = t1; xMax = t2;
            } else{
                xMin = t2; xMax = t1;
            }
        }

        if (isZero(vector.getY())) {
            if (pointMax.getX() >= point.getY() && pointMin.getY() <= point.getY()) {
                yMax = Double.MAX_VALUE;
                yMin = Double.MIN_VALUE;
            } else
                return false;

        } else {
            double t1 = (pointMax.getY() - point.getY()) / vector.getY();
            double t2 = (pointMin.getY() - point.getY()) / vector.getY();
            if(t1 < t2){
                yMin = t1; yMax = t2;
            } else{
                yMin = t2; yMax = t1;
            }
        }

        if (isZero(vector.getZ())) {
            if (pointMax.getZ() >= point.getZ() && pointMin.getZ() <= point.getZ()) {
                zMax = Double.MAX_VALUE;
                zMin = Double.MIN_VALUE;
            } else
                return false;

        } else {
            double t1 = (pointMax.getZ() - point.getZ()) / vector.getZ();
            double t2 = (pointMin.getZ() - point.getZ()) / vector.getZ();
            zMin=Math.min(t1,t2);
            zMax=Math.max(t1,t2);
        }

        return !(xMin > yMax || xMin > zMax ||
                yMin > xMax || yMin > zMax ||
                zMin > yMax || zMin > xMax);

    }
    private void calcCenter(){
        center=new Point3D((pointMax.getX()+pointMin.getX())/2,
                (pointMax.getY()+pointMin.getY())/2,
                (pointMax.getZ()+pointMin.getZ())/2);
    }

    public Point3D getCenter() {
        return center;
    }

    public int findBiggestEdge(){
        double x=Math.abs(pointMax.getX()-pointMin.getX());
        double y=Math.abs(pointMax.getY()-pointMin.getY());
        double z=Math.abs(pointMax.getZ()-pointMin.getZ());
        if (x>=y&&x>=z)
            return 0;
        if (y>=x&&y>=z)
            return 1;
        return 2;
    }

    public Point3D getPointMax() {
        return pointMax;
    }
}
