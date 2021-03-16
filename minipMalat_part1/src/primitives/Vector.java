package primitives;

import java.util.Objects;

import static primitives.Util.*;
/**
 * this class represents a Vector in 3D
 *using the head of the vector
 * the tail of the vector is the origin
 *
 *@author Achiya Danziger Malachi Mahpod
 * @version 1
 */
public class Vector {


    /**
     * the head vector
     */
    Point3D head;

    /**
     * @return the head of the vector
     */
    public Point3D getHead() {
        return head;
    }

    /**
     * a constructor that gets Point 3D
     * @param head the head of the vector
     */
    public Vector(Point3D head) {
        if (head.equals(Point3D.ZERO))//checks that the vector isn't the zero vector
                throw new IllegalArgumentException("the vector can't be Zero");
        this.head = head;
    }

    /**
     * a constructor that gets three double variable
     * for constructing the head of the vector
     * @param x the value of the x coordinate
     * @param y the value of the y coordinate
     * @param z the value of the z coordinate
     */
    public Vector(double x, double y, double z) {
        Point3D point = new Point3D(x, y, z);
        if (point.equals(Point3D.ZERO))
            throw new IllegalArgumentException("the vector can't be Zero");
        this.head = point;
    }

    /**
     *a constructor that gets three coordination's
     *
     * @param x the x coordinate
     * @param y the y coordinate
     * @param z the z coordinate
     */
    public Vector(Coordinate x, Coordinate y, Coordinate z) {
        Point3D point = new Point3D(x, y, z);
        if (head.equals(Point3D.ZERO))
            throw new IllegalArgumentException("the vector can't be Zero");
        this.head = head;
    }

    /**
     *adds another vector to this vector
     *
     * @param vec the vector we add to this vector
     * @return the result of the addition of the two vectors together
     */
    public Vector add(Vector vec){
        return new Vector(vec.head.x.coord+head.x.coord,vec.head.y.coord+head.y.coord,vec.head.z.coord+head.z.coord);
    }

    /**
     * subtracts a vector from this vector
     *
     * @param vec the vector we subtract from this vector
     * @return the result of the subtraction of the new vector from this vector
     */
    public Vector subtract(Vector vec){
        return new Vector(vec.head.x.coord-head.x.coord,vec.head.y.coord-head.y.coord,vec.head.z.coord-head.z.coord);
    }

    /**
     * scales the vector with a scalar
     *
     * @param scl the number we want to scale this vector with
     * @return the scaled vector
     */
    public Vector scale(double scl){
        return new Vector(head.x.coord*scl,head.y.coord*scl,head.z.coord*scl);
    }

    /**
     *calculates the dot product of two vectors
     *
     * @param vec the second vector
     * @return double- the result of the dot-product between the two vectors
     */
    public double dotProduct(Vector vec){
        return (vec.head.x.coord*head.x.coord+vec.head.y.coord*head.y.coord+vec.head.z.coord*head.z.coord);
    }

    /**
     *calculates the cross product of two vector
     *
     * @param vec the second vector
     * @return Vector- the result of the cross-product between the two vectors
     */
    public Vector crossProduct(Vector vec){
        double x = vec.head.y.coord*head.z.coord - head.y.coord*vec.head.z.coord;
        double y = vec.head.z.coord*head.x.coord - head.z.coord*vec.head.x.coord;
        double z = vec.head.x.coord*head.y.coord - head.x.coord*vec.head.y.coord;
        return new Vector(x,y,z);
    }

    /**
     * the length squared  of this vector
     *
     * @return the square of the length of this vector
     */
    public double lengthSquared(){
        return head.x.coord*head.x.coord+head.y.coord*head.y.coord+head.z.coord*head.z.coord;
    }

    /**
     * the length of this vector
     * @return the length of this vector
     */
    public double length(){
        return Math.sqrt(lengthSquared());
    }

    /**
     * normalizes the vector (dividing the vector by its length)
     *
     * @return this vector after normalization
     */
    public Vector normalize(){
        double scl = 1/length();//getting the length of this vector
        this.head = scale(scl).head;//dividing the vector by it's length
        return this;
    }

    /**
     * return this vector after normalization
     * note:this doesn't change this vector
     *
     * @return the normalized vector
     */
    public Vector normalized(){
        Vector vec = new Vector(head);
        return vec.normalize();
    }

    @Override
    public String toString() {
        return "Vector{" +
                "head=" + head +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector vector = (Vector) o;
        return Objects.equals(head, vector.head);

    }
}