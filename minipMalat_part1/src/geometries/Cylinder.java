package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static primitives.Util.alignZero;

/**
 * A class to represent a cylinder with Tube and the height of the cylinder
 */
public class Cylinder extends Tube{

    double height;

    /**
     * the main constructor of the cylinder
     * that gets everything necessary for a tube-axis ray and radius
     * with additional double for height
     * @param axisRay the axis ray
     * @param radius the radius of the cylinder
     * @param height the height of the cylinder
     * @exception  IllegalArgumentException if the  radius or height are not positive
     */
    public Cylinder(Ray axisRay, double radius, double height) {
        super(axisRay, radius);
        if(alignZero(height) <= 0)
            throw new IllegalArgumentException("radius must be positive");
        this.height = height;
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
        Vector norm=axisRay.getDir();//the normal
        Point3D upPoint=axisRay.getP0().add(axisRay.getDir().scale(height));//the center of the upper base
        Vector upvec=upPoint.subtract(point);
        Vector lowVec=axisRay.getP0().subtract(point);
        if (point.equals(axisRay.getP0())) {
            norm.scale(-1);
        }


        return null;
    }


}
