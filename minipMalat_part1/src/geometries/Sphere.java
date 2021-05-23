package geometries;

import primitives.Ray;
import primitives.Util;
import primitives.Point3D;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;

import static primitives.Util.alignZero;

/**
 * A class that represents a sphere
 * using the center point of the sphere and the radius of the sphere
 */
public class Sphere extends Geometry{

    Point3D center;
    double radius;

    /**
     * A constructor that gets a point and double
     * @param center the center of the sphere
     * @param radius the radius of the sphere
     */
    public Sphere(Point3D center, double radius) {
        this.center = center;
        if(alignZero(radius) <= 0)
            throw new IllegalArgumentException("radius must be positive");
        this.radius = radius;
    }

    @Override
    public String toString() {
        return "Sphere{" +
                "center=" + center +
                ", radius=" + radius +
                '}';
    }

    /**
     * @return A point- the center if the sphere
     */
    public Point3D getCenter() {
        return center;
    }

    /**
     * @return double-the radius of the dphere
     */
    public double getRadius() {
        return radius;
    }


    @Override
    public Vector getNormal(Point3D point) {
        return point.subtract(center).normalize();
    }


    @Override
    public List<Point3D> findIntersections(Ray ray) {
        try {
            Vector u = center.subtract(ray.getP0());
            double tm = u.dotProduct(ray.getDir());
            double d = Math.sqrt(u.lengthSquared()-tm*tm);

            if(d >= radius)
                return null;

            double th = Math.sqrt(radius*radius - d*d);
            double t1 = tm + th, t2 = tm - th;

            if(t1 <= 0 && t2 <= 0)
                return null;

            List<Point3D> listPoints = new ArrayList<Point3D>();
            if(t1 > 0)
                listPoints.add(ray.getPoint(t1));
            if(t2 > 0)
                listPoints.add(ray.getPoint(t2));

            return listPoints;
        }
        catch (IllegalArgumentException ex){
            List<Point3D> listPoints = new ArrayList<Point3D>();
            listPoints.add(ray.getPoint(radius));
            return listPoints;
        }




    }

    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray,double maxDistance) {
        List<Point3D> listPoints= findIntersections(ray);
        if(listPoints==null)
            return null;
        List<GeoPoint> listGeoPoints= new ArrayList<GeoPoint>();

        for (Point3D inter:listPoints){
            double t =ray.getP0().distance(inter);
            if(alignZero(t - maxDistance) <= 0)
                listGeoPoints.add(new GeoPoint(this, inter));
        }
        if(listGeoPoints.isEmpty())
            return null;
        return listGeoPoints;
    }
}
