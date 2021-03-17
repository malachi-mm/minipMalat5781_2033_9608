package geometries;

import primitives.Util;
import primitives.Point3D;
import primitives.Vector;

import static primitives.Util.alignZero;

/**
 * A class that represents a sphere
 * using the center point of the sphere and the radius of the sphere
 */
public class Sphere implements Geometry{

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
        return null;
    }
}
