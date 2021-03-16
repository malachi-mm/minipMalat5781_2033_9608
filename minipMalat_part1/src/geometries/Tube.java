package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 *
 */

public class Tube implements Geometry{
    Ray axisRay;
    double radius;

    public Tube(Ray axisRay, double radius) {
        this.axisRay = axisRay;
        if(radius <= 0)
            throw new IllegalArgumentException("radius must be positive");
        this.radius = radius;
    }

    @Override
    public Vector getNormal(Point3D point) {
        return null;
    }
}
