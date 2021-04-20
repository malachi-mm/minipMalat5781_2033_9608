package unittests.geometries;

import geometries.*;
import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GeometriesTest {

    /**
     * Test method for {@link geometries.Geometries#findIntersections(Ray)}.
     */
    @Test
    void findIntersections() {
        Sphere sphere1=new Sphere(new Point3D(2,0,0),1d);
        Sphere sphere2=new Sphere(new Point3D(4,4,0),2d);
        Triangle triangle=new Triangle(new Point3D(0,3,0),new Point3D(4,0,0),new Point3D(0,0,6));
        Plane plane1=new Plane(new Point3D(-6,0,0),new Point3D(0,5,0),new Point3D(-5,5,10));
        Geometries geometries=new Geometries(sphere1,sphere2,triangle,plane1);
        // ============ Equivalence Partitions Tests ==============
        //TC01 a couple of geometries and some of them have an intersection
        assertEquals( 3, geometries.findIntersections(new Ray(new Point3D(4,-4,0),new Vector(-4,6,0))).size(),
                "Wrong number of intersection points");


        // =============== Boundary Values Tests ==================
        //TC11 there is no geometries at all
        assertNull(new Geometries().findIntersections(new Ray(new Point3D(3,3,3),new Vector(2,2,2)))
                ,"there is no intersections");

        //TC12 non of the Geometries have an intersection
        assertNull( geometries.findIntersections(new Ray(new Point3D(0,-5,0),new Vector(6,5,5)))
                ,"there is no intersections");

        //TC12 there is only one intersection point
        assertEquals( 1, geometries.findIntersections(new Ray(new Point3D(0,-5,0),new Vector(-6,5,5))).size(),
                "Wrong number of intersection points");
        //TC13 all of the Geometries is being intersected
        assertEquals( 5, geometries.findIntersections(new Ray(new Point3D(0,-5,0),new Vector(2,5.5,0))).size(),
                "Wrong number of intersection points");
    }
}