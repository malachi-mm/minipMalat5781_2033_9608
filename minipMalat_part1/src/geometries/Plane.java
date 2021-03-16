
package geometries;

import primitives.Point3D;
import primitives.Vector;

/**
 * A class that represents a plain
 * using a point and a perpendicular vector
 */
public class Plane implements Geometry{
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
        normal = null;//temporary
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
}
