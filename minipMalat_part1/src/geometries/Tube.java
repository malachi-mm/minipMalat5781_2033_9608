package geometries;
import primitives.Util;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;

import static primitives.Util.alignZero;

/**
 *A class that represents a Tube
 * using A ray for the center ray nd double for the distance
 */

public class Tube extends Geometry{

    Ray axisRay;
    double radius;

    /**
     * The primary constructor getting the axis ray and the radius
     * @param axisRay the axis ray
     * @param radius the radius of the tube
     * @exception  IllegalArgumentException if the  radius is not positive
     */
    public Tube(Ray axisRay, double radius) {
        this.axisRay = axisRay;
        if(alignZero(radius) <= 0)
            throw new IllegalArgumentException("radius must be positive");
        this.radius = radius;
    }

    /**
     * @return the axis ray of the tube
     */
    public Ray getAxisRay() {
        return axisRay;
    }

    /**
     * @return the radius of the tube
     */
    public double getRadius() {
        return radius;
    }

    @Override
    public String toString() {
        return "Tube{" +
                "axisRay=" + axisRay +
                ", radius=" + radius +
                '}';
    }

    @Override
    public Vector getNormal(Point3D point) {
        double t = axisRay.getDir().dotProduct(point.subtract(axisRay.getP0()));
        Point3D O = axisRay.getP0();
        if (t!=0)
            O = axisRay.getP0().add(axisRay.getDir().scale(t));
        Vector n = point.subtract(O).normalize();
        return n;
    }

    @Override
    public List<Point3D> findIntersections(Ray ray) {
        if(ray.getDir().equals(axisRay.getDir()))
            return null;


            Vector Dp = ray.getP0().subtract(axisRay.getP0());

            Vector a1, c1;

            double n1 = ray.getDir().dotProduct(axisRay.getDir());
            if(n1 != 0) {
                Vector vOnVa = axisRay.getDir().scale(n1);
                a1 = vOnVa.subtract(ray.getDir());
            }
            else
                a1 = ray.getDir();

            double n2 = Dp.dotProduct(axisRay.getDir());
            if (n2 != 0) {
                Vector dpOnVa = axisRay.getDir().scale(n2);
                c1 = dpOnVa.subtract(Dp);
            }
            else
                c1 = Dp;


            double A = a1.lengthSquared();
            double B = (a1.dotProduct(c1)) * 2;
            double C = c1.lengthSquared() - radius * radius;

            double disc = B * B - 4 * A * C;

            if (disc < 0)
                return null;
            else if (disc == 0) {
                /*List<Point3D> point = null;
                double t = -B / 2 * A;
                if (t > 0) {
                    point = new ArrayList<Point3D>();
                    point.add(ray.getPoint(t));
                }
                return point;*/
                return null;
            } else //disc < 0
            {
                List<Point3D> points = null;
                disc = Math.sqrt(disc);
                double t1 = alignZero((-B + disc) / (2 * A));
                double t2 = alignZero((-B - disc) / (2 * A));

                if (t1 > 0) {
                    points = new ArrayList<Point3D>();
                    points.add(ray.getPoint(t1));
                }
                if (t2 > 0) {
                    if (points == null)
                        points = new ArrayList<Point3D>();
                    points.add(ray.getPoint(t2));
                }

                return points;
            }
    }
    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray,double maxDistance) {
        List<Point3D> listPoints= findIntersections(ray);
        if(listPoints==null)
            return null;
        List<GeoPoint> listGeoPoints= new ArrayList<GeoPoint>();

        for (Point3D inter:listPoints) {
            double t =ray.getP0().distance(inter);
            if(alignZero(t - maxDistance) <= 0)
                listGeoPoints.add(new GeoPoint(this, inter));
        }
        if(listGeoPoints.isEmpty())
            return null;
        return listGeoPoints;
    }
}
