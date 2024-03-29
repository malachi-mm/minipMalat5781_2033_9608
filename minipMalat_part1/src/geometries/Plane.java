
package geometries;

import primitives.BoundingBox;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;

import static primitives.Util.alignZero;

/**
 * A class that represents a plain
 * using a point and a perpendicular vector
 */
public class Plane extends Geometry{
    Point3D q0;
    Vector normal;

    /**
     *A constructor that gets three points
     * @param p1 the first point
     * @param p2 the second point
     * @param p3 the first point
     */
    public Plane(Point3D p1,Point3D p2,Point3D p3){
        q0 = p1;
        //note: if have 2 equals points. the constructor will throw an error. and if p2 and p3 equals it's will figure out in the second test
        Vector v1 = p3.subtract(p1);
        Vector v2 = p2.subtract(p1);

        if(v1.normalize().equals(v2.normalize()))
            throw new IllegalArgumentException("all the 3 points on the same vector");

        normal = v1.crossProduct(v2).normalize();//temporary
        this.boundingBox = findBoundingBox();
    }

    /**
     *A constructor that gets a Vecor and a point
     * @param q0 a point in the plane
     * @param normal the normal of the vector
     */
    public Plane(Point3D q0, Vector normal) {
        this.q0 = q0;
        this.normal = normal;
    }

    @Override
    public Vector getNormal(Point3D point) {
        return getNormal();
    }

    /**
     * returns the normal of the plain
     * without getting a point
     * because in a plain the normal is the same in every point
     *
     * @return the normal
     */
    public Vector getNormal() {
        return normal;
    }


    @Override
    protected BoundingBox findBoundingBox() {
        return new BoundingBox(new Point3D
                (Double.MAX_VALUE,Double.MAX_VALUE,Double.MAX_VALUE)
                ,new Point3D
                (-Double.MAX_VALUE,-Double.MAX_VALUE,-Double.MAX_VALUE));
    }

    @Override
    public List<Point3D> findIntersections(Ray ray) {

        try
        {
            Vector vec = q0.subtract(ray.getP0());
            double n = vec.dotProduct(normal);
            if(n == 0)
                return null;

            double t = n/(ray.getDir().dotProduct(normal));

            if(t <= 0)
                return null;

            List<Point3D> points = new ArrayList<Point3D>();
            points.add(ray.getPoint(t));
            return points;
        }
        catch (IllegalArgumentException ex) {
            return null;
        }
    }

    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance) {

            List<Point3D> listPoints= findIntersections(ray);
            if(listPoints==null)
                return null;
            double t =ray.getP0().distance(listPoints.get(0));
            if(alignZero(t - maxDistance) <= 0) {
                List<GeoPoint> listGeoPoints = new ArrayList<GeoPoint>();
                listGeoPoints.add(new GeoPoint(this, listPoints.get(0)));
                return listGeoPoints;
            }
            return null;
    }
}
