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

public class Tube implements Geometry{

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
        Vector Dp = ray.getP0().subtract(axisRay.getP0());

        Vector vOnVa = axisRay.getDir().scale(ray.getDir().dotProduct(axisRay.getDir()));
        Vector dpOnVa = axisRay.getDir().scale(Dp.dotProduct(axisRay.getDir()));

        Vector a1 = vOnVa.subtract(ray.getDir());
        Vector c1 = dpOnVa.subtract(Dp);

        double A = a1.lengthSquared();
        double B = (a1.dotProduct(c1))*2;
        double C = c1.lengthSquared() - radius*radius;

        double disc = B*B - 4*A*C;

        if(disc < 0)
            return null;
        else if (disc == 0)
        {
            List<Point3D> point = null;
            double t = -B / 2*A;
            if(t >= 0){
                point = new ArrayList<Point3D>();
                point.add(ray.getPoint(t));
            }
            return point;
        }
        else //disc < 0
        {
            List<Point3D> points = null;
            disc = Math.sqrt(disc);
            double t1 = alignZero((-B + disc) / (2*A));
            double t2 = alignZero((-B - disc) / (2*A));

            if(t1 >= 0) {
                points =  new ArrayList<Point3D>();
                points.add(ray.getPoint(t1));
            }
            if(t2 >= 0) {
                if(points == null)
                    points =  new ArrayList<Point3D>();
                points.add(ray.getPoint(t2));
            }

            return points;
        }
    }
}
