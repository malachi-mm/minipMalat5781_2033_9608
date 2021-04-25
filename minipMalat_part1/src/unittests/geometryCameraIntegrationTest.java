package unittests;

import elements.Camera;
import geometries.Intersectable;
import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class geometryCameraIntegrationTest {
    int nX=3;
    int nY=3;


    /**
     * sums the number of intersection of a specific body
     * @param body the geometry
     * @param camera the camera
     * @return the number of intersections
     */
    public int getIntersectionNumbers(Intersectable body,Camera camera){
        int sum=0;
        camera.setViewPlaneSize(3,3);
        for (int i = 0; i < nX; i++) {
            for (int j = 0; j < nY; j++) {
                List<Point3D> points=body.findIntersections(camera.constructRayThroughPixel(nX, nY,i,j ));
                if (points!=null)
                    sum+=points.size();
            }
        }
        return sum;
    }

    /**
     * test for intersection of sphere in viewPlane
     */
    @Test
    public void testIntersectionSphere() {
        Camera camera = new Camera(Point3D.ZERO, new Vector(0, 0, -1), new Vector(0, 1, 0)).setDistance(1);
        Camera camera2 = new Camera(new Point3D(0,0,0.5d), new Vector(0, 0, -1), new Vector(0, 1, 0)).setDistance(1);
        //TC01 sphere is in front of the camera but only the main pixel have intersection(or eny other one) (2 intersection points)
       Sphere sphere=new Sphere(new Point3D(0,0,-3),1d);
        assertEquals(getIntersectionNumbers(sphere,camera),2
                ,"the number of intersection isn't right");

        //TC02 sphere is in front of the camera and every pixel intersects it twice (18 intersection points)
         sphere=new Sphere(new Point3D(0,0,-2.5),2.5d);
        assertEquals(getIntersectionNumbers(sphere,camera2),18
                ,"the number of intersection isn't right");

        //TC03 sphere is in front of the camera but only tsome of the rays intersects the sphere(10 intersection points)
         sphere=new Sphere(new Point3D(0,0,-2),2d);
        assertEquals(getIntersectionNumbers(sphere,camera2),10
                ,"the number of intersection isn't right");

        //TC04 sphere encapsulates the camera and vewPlane (9 intersection points)
         sphere=new Sphere(new Point3D(0,0,-3),7d);
        assertEquals(getIntersectionNumbers(sphere,camera),9
                ,"the number of intersection isn't right");

        //TC05 sphere is after the camera  (0 intersection points)
         sphere=new Sphere(new Point3D(0,0,10),1d);
        assertEquals(getIntersectionNumbers(sphere,camera),0
                ,"the number of intersection isn't right");


    }

    /**
     * test for intersection of triangle in viewPlane
     */
    @Test
    public void testIntersectionTriangle() {
        Camera camera = new Camera(Point3D.ZERO, new Vector(0, 0, 1), new Vector(0, 1, 0)).setDistance(1);
         Camera camera2 = new Camera(new Point3D(0,0,0.5d), new Vector(0, 0, -1), new Vector(0, 1, 0)).setDistance(1);

        //TC01 the triangle  is in front of the camera and is small so only one pixel hase intersection with it (1 intersection points)
        Triangle triangle =new Triangle(new Point3D(0,1,-2),new Point3D(1,-1,-2),new Point3D(-1,-1,-2));
        assertEquals(getIntersectionNumbers(triangle,camera2),1
                ,"the number of intersection isn't right");

        //TC02 the triangle  is in front of the camera and is small and high  so only two pixel hase intersection with it (1 intersection points)
         triangle =new Triangle(new Point3D(0,20,-2),new Point3D(1,-1,-2),new Point3D(-1,-1,-2));
        assertEquals(getIntersectionNumbers(triangle,camera2),2
                ,"the number of intersection isn't right");

    }

    /**
     * test for intersection of plane in viewPlane
     */
    @Test
    public void testIntersectionPlane() {

        Camera camera = new Camera(Point3D.ZERO, new Vector(0, 0, 1), new Vector(0, 1, 0)).setDistance(1);
       // Camera camera2 = new Camera(new Point3D(0,0,0.5d), new Vector(0, 0, -1), new Vector(0, 1, 0)).setDistance(1);

        //TC01 the plane  is in front of the camera and is parallel to the view plane-every pixel intersects it once(9 intersection points)
        Plane plane =new Plane(new Point3D(0,1,2),new Point3D(1,1,2),new Point3D(0,2,2));
        assertEquals(getIntersectionNumbers(plane,camera),9
                ,"the number of intersection isn't right");


        //TC02 the plane  is in front of the camera every pixel intersects it once(9 intersection points)
        plane =new Plane(new Point3D(0,1,7),new Point3D(1,1,7),new Point3D(0,3,8));
        assertEquals(getIntersectionNumbers(plane,camera),9
                ,"the number of intersection isn't right");

        //TC03 the plane  cuts the view plane so only some of the pixels intersect him(5 intersection points)
        plane =new Plane(new Point3D(0,-1,7),new Point3D(1,2,1),new Point3D(1,3,8));
        assertEquals(getIntersectionNumbers(plane,camera),5
                ,"the number of intersection isn't right");

        //TC03 the plane is perpendicular to the center of the view point(0 intersection points)
        plane =new Plane(new Point3D(0,-1,7),new Point3D(0,2,1),new Point3D(0,3,8));
        assertEquals(getIntersectionNumbers(plane,camera),0
                ,"the number of intersection isn't right");

    }
}
