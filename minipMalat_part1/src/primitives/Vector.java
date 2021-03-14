package primitives;

import java.util.Objects;

import static primitives.Util.*;
/**
 * Vector in 3D
 *
 *@author Achiya Danziger Malachi Mahpod
 * @version 1
 */
public class Vector {


    /**
     * the vector
     */
    Point3D head;

    public Point3D getHead() {
        return head;
    }

    public Vector(Point3D head) {
        if (head.equals(Point3D.ZERO))
                throw new IllegalArgumentException("the vector can't be Zero");
        this.head = head;
    }

    public Vector(double x, double y, double z) {
        Point3D point = new Point3D(x, y, z);
        if (head.equals(Point3D.ZERO))
            throw new IllegalArgumentException("the vector can't be Zero");
        this.head = head;
    }
    public Vector(Coordinate x, Coordinate y, Coordinate z) {
        Point3D point = new Point3D(x, y, z);
        if (head.equals(Point3D.ZERO))
            throw new IllegalArgumentException("the vector can't be Zero");
        this.head = head;
    }

    public Vector add(Vector vec){
        return new Vector(vec.head.x.coord+head.x.coord,vec.head.y.coord+head.y.coord,vec.head.z.coord+head.z.coord);
    }
    public Vector subtract(Vector vec){
        return new Vector(vec.head.x.coord-head.x.coord,vec.head.y.coord-head.y.coord,vec.head.z.coord-head.z.coord);
    }

    public Vector scale(double scl){
        return new Vector(head.x.coord*scl,head.y.coord*scl,head.z.coord*scl);
    }

    public double dotProduct(Vector vec){
        return (vec.head.x.coord*head.x.coord+vec.head.y.coord*head.y.coord+vec.head.z.coord*head.z.coord);
    }

    public Vector crossProduct(Vector vec){
        double x = vec.head.y.coord*head.z.coord - head.y.coord*vec.head.z.coord;
        double y = vec.head.z.coord*head.x.coord - head.z.coord*vec.head.x.coord;
        double z = vec.head.x.coord*head.y.coord - head.x.coord*vec.head.y.coord;
        return new Vector(x,y,z);
    }

    public double lengthSquared(){
        return head.x.coord*head.x.coord+head.y.coord*head.y.coord+head.z.coord*head.z.coord;
    }

    public double length(){
        return Math.sqrt(lengthSquared());
    }

    public Vector normalize(){
        double scl = 1/length();
        this.head = scale(scl).head;
        return this;
    }

    public Vector normalized(){
        Vector vec = new Vector(head);
        return vec.normalize();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector vector = (Vector) o;
        return Objects.equals(head, vector.head);
    }
}