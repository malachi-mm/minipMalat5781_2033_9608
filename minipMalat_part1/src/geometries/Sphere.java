package geometries;

import primitives.Point3D;
import primitives.Vector;

public class Sphere implements Geometry{

    Point3D center;
    double radius;

    public Sphere(Point3D center, double radius) {
        this.center = center;
        if(radius <= 0)
            throw new IllegalArgumentException("radius must be positive");
        this.radius = radius;
    }

    @Override
    public Vector getNormal(Point3D point) {
        return null;
    }
}
