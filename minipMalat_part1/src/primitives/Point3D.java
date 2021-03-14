package primitives;

import java.util.Objects;

import static primitives.Util.*;

/**
 *Point in 3D
 *
 * @author Achiya Danziger Malachi Mahpod
 * @version 1
 */
public class Point3D {
    /**
     * the coordinations
     */
    Coordinate x;
    Coordinate y;
    Coordinate z;

    static Point3D ZERO = new Point3D(0,0,0);

    public Point3D(Coordinate x, Coordinate y, Coordinate z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Point3D(double x, double y, double z) {
        this.x = new Coordinate(x);
        this.y = new Coordinate(y);
        this.z = new Coordinate(z);
    }

    /**
     *
     * @param point
     * @return
     * @exception
     */
    public Vector subtract(Point3D point){
        return new Vector(point.x.coord-this.x.coord,point.y.coord-this.y.coord,point.z.coord-this.z.coord);
    }
    public Point3D add(Vector vec){
        return new Point3D(vec.head.x.coord+this.x.coord,vec.head.y.coord+this.y.coord,vec.head.z.coord+this.z.coord);
    }
    public double distanceSquared(Point3D point){
        Vector vec  = subtract(point);
        return vec.lengthSquared();
    }
    public double distance(Point3D point){
        return Math.sqrt(distanceSquared(point));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point3D point3D = (Point3D) o;
        return Objects.equals(x, point3D.x) && Objects.equals(y, point3D.y) && Objects.equals(z, point3D.z);
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Point3D{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}