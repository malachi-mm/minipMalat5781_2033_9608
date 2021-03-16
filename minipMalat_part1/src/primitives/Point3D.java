package primitives;

import java.util.Objects;

import static primitives.Util.*;

/**
 *this class represents Point in 3D
 *The class is based on Util controlling the accuracy.
 *
 * @author Achiya Danziger Malachi Mahpod
 * @version 1
 */
public class Point3D {
    /**
     * the coordination's of the points
     */
    Coordinate x;
    Coordinate y;
    Coordinate z;

    public static Point3D ZERO = new Point3D(0,0,0);

    /**
     * A constructor that gets three coordinates
     *
     * @param x the x coordinate
     * @param y the y coordinate
     * @param z the z coordinate
     */
    public Point3D(Coordinate x, Coordinate y, Coordinate z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * A constructor that gets three doubles
     *
     * @param x the value of the x coordinate
     * @param y the value of the y coordinate
     * @param z the value of the z coordinate
     */
    public Point3D(double x, double y, double z) {
        this.x = new Coordinate(x);
        this.y = new Coordinate(y);
        this.z = new Coordinate(z);
    }

    /**
     *
     * @param point the start point of the vector
     * @return the vector between the points
     */
    public Vector subtract(Point3D point){
        return new Vector(this.x.coord-point.x.coord,this.y.coord-point.y.coord,this.z.coord-point.z.coord);
    }

    /**
     * @param vec the vector we add to this point
     * @return the point we get from adding the vector to this point
     */
    public Point3D add(Vector vec){
        return new Point3D(vec.head.x.coord+this.x.coord,vec.head.y.coord+this.y.coord,vec.head.z.coord+this.z.coord);
    }

    /**
     * this function calculates the distance Squared between two points
     *
     * @param point the second point
     * @return the distance between the points squared
     */
    public double distanceSquared(Point3D point){
       double x=this.x.coord-point.x.coord;
        double y=this.y.coord-point.y.coord;
        double z=this.z.coord-point.z.coord;
        return (x*x+y*y+z*z);//the distance between two points is the square root sum of the square of the difference between the coordination's
    }

    /**
     * returns the distance between two points
     * @param point the second point
     * @return the distance between this point and the point we get as parameter
     */
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