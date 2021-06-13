package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

/**
 * a class that represents a triangle
 */
public class Triangle extends Polygon{

    /**
     * A constructor that gets three points for the Vertices
     * @param p1 a vertice
     * @param p2 a vertice
     * @param p3 a vertice
     */
    public Triangle(Point3D p1,Point3D p2,Point3D p3) {
        super(p1,p2,p3);
        this.boundingBox = super.findBoundingBox();
    }
    //we use the getNormal and  of the polygon

    @Override
    public String toString() {
        return "Triangle{" +
                "vertices=" + vertices +
                ", plane=" + plane +
                '}';
    }

    //@Override
    //public List<Point3D> findIntersections(Ray ray) {
    //    return null;
    //}
}
