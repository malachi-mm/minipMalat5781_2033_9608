package geometries;

import primitives.BoundingBox;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * A class to represent a cylinder with Tube and the height of the cylinder
 */
public class Cylinder extends Tube {

    double height;

    /**
     * the main constructor of the cylinder
     * that gets everything necessary for a tube-axis ray and radius
     * with additional double for height
     *
     * @param axisRay the axis ray
     * @param radius  the radius of the cylinder
     * @param height  the height of the cylinder
     * @throws IllegalArgumentException if the  radius or height are not positive
     */
    public Cylinder(Ray axisRay, double radius, double height) {
        super(axisRay, radius);
        if (alignZero(height) <= 0)
            throw new IllegalArgumentException("radius must be positive");
        this.height = height;
        this.boundingBox = this.findBoundingBox();
    }

    /**
     * @return the height of the cylinder
     */
    public double getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return "Cylinder{" +
                ", axisRay=" + axisRay +
                ", radius=" + radius +
                "height=" + height +
                '}';
    }

    @Override
    public Vector getNormal(Point3D point) {
        Vector norm = axisRay.getDir();//the normal
        Point3D upPoint = axisRay.getP0().add(axisRay.getDir().scale(height));//the center of the upper base

        if (point.equals(axisRay.getP0())) {//if its the center of the lower base
            norm = norm.scale(-1);
        } else if (!point.equals(upPoint)) {//if it is not the center of either bases

            Vector upvec = upPoint.subtract(point);//the vector between the point and the center of the upper base
            Vector lowVec = axisRay.getP0().subtract(point);//the Vector between the point and the center of the lower base

            if (isZero(lowVec.dotProduct(axisRay.getDir()))) {//if the axis ray and the lowvec are perpendicular(the point is in the lower bse)
                norm = norm.scale(-1);//returns Vector of the axis ray scaled by -1
            } else if (!isZero(upvec.dotProduct(axisRay.getDir()))) {//if the point is not on the upper(and lower bases)
                norm = super.getNormal(point);//it is the same as Tube
            }
        }

        return norm;
    }

    @Override
    public List<Point3D> findIntersections(Ray ray) {
        List<Point3D> points = new ArrayList<Point3D>();

        Point3D upPoint = axisRay.getP0().add(axisRay.getDir().scale(height));
        List<Point3D> pointsT = super.findIntersections(ray);

        if (pointsT != null)
            for (Point3D point : pointsT) {
                double A = point.distanceSquared(axisRay.getP0());
                double R = radius * radius;
                Vector v = axisRay.getP0().subtract(point);
                if (Math.sqrt(A - R) <= height && v.dotProduct(axisRay.getDir()) <= 0)
                    points.add(point);
            }

        Plane p1 = new Plane(axisRay.getP0(), axisRay.getDir().scale(-1));
        Plane p2 = new Plane(upPoint, axisRay.getDir());

        List<Point3D> point1 = p1.findIntersections(ray);
        List<Point3D> point2 = p2.findIntersections(ray);

        if (point1 != null && alignZero(point1.get(0).distance(axisRay.getP0()) - radius) <= 0)
            points.add(point1.get(0));
        if (point2 != null && alignZero(point2.get(0).distance(upPoint) - radius) <= 0)
            points.add(point2.get(0));

        if (points.isEmpty())
            return null;
        return points;
    }

    @Override
    protected BoundingBox findBoundingBox() {
        Point3D a = this.axisRay.getP0(), b = this.axisRay.getPoint(height);
        Vector R = new Vector(radius, radius, radius);
        Point3D max = a.add(R).maxPoint(b.add(R));
        R = R.scale(-1);
        Point3D min = a.add(R).minPoint(b.add(R));
        return new BoundingBox(max, min);
    }

}
