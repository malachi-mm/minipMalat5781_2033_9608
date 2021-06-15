package elements;

import primitives.Point3D;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;

public class  SuperSampling {

    /**
     *calculates the points we need for supersampeling
     * @param point the azis point of the beam
     * @param l the vector perpendicular to the plane of the point
     * @param SIZE the number of point we want
     * @param radius the size of area we want the points in
     * @return the list of the points
     */
    public static List<Point3D> superSampling(Point3D point, Vector l, int SIZE, double radius) {
        Vector l1;
        try {
             l1 = l.crossProduct(l.add(new Vector(0, 0, 1)));
        }catch(Exception ex){ l1 = l.crossProduct(l.add(new Vector(0, 1, 0)));}
        Vector l2=l.crossProduct(l1);

        return superSampling(point,l1,l2,SIZE,radius,radius);
    }

    /**
     *calculates the points we need for supersampeling
     * @param point the axis point of the beam
     * @param l1 a vector that is in the plane of the point
     * @param l2 a vector that is in the plane of the point
     * @param SIZE the number of point we want
     * @param radius1 the size of area we want the points in the x axis
     * @param radius2 the size of area we want the points in the y axis
     * @return the list of the points
     */
    public static List<Point3D> superSampling(Point3D point, Vector l1,Vector l2, int SIZE, double radius1,double radius2) {

        l1.normalize();
        l2.normalize();

        List<Point3D> points = new ArrayList<Point3D>();
        double newSize = Math.sqrt(SIZE - 1);
        for (int k = 1; k < newSize + 1; k++) {
            for (int t = 1; t < newSize + 1; t++) {
                //might be change because now it is a rectangular but not a circle
                Point3D newPoint=point.add(l1.scale(radius1*(1-((k*2)/newSize))));
                newPoint=newPoint.add(l2.scale(radius2*(1-((t*2)/newSize))));
                points.add(newPoint);
            }
        }
        if(!points.contains(point)){
            points.add(point);
        }
        return points;
    }
}
