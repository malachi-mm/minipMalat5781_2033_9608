package geometries;

import primitives.Point3D;
import primitives.Vector;

public class Plane implements Geometry{
    Point3D q0;
    Vector normal;

    public Plane(Point3D p1,Point3D p2,Point3D p3){
        q0 = p1;
        normal = null;
    }
    public Plane(Point3D q0, Vector normal) {
        this.q0 = q0;
        this.normal = normal;
    }

    @Override
    public Vector getNormal(Point3D point) {
        return getNormal();
    }
    public Vector getNormal() {
        return normal;
    }
}
