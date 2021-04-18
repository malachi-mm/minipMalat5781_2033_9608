package unittests.geometries;

import geometries.Plane;
import geometries.Sphere;
import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlaneTest {

    /**
     * Test method for {@link geometries.Plane#Plane(Point3D, Point3D, Point3D)}.
     */
    @Test
    void constructor(){
        Point3D p1 = new Point3D(0,0,0);
        Point3D p2= new Point3D(0,1,0);
        Point3D p3 = new Point3D(0,2,0);
        // =============== Boundary Values Tests ==================
        //TC 11 checks that the points are all different
        assertThrows( IllegalArgumentException.class, () -> new Plane(p2,p2,p3),
                "the points should all be different");
        //TC11 checks that the point aren't on the same vector
        assertThrows( IllegalArgumentException.class, () -> new Plane(p1,p2,p3),
                "the points shouldn't be on the same vector");
    }

    /**
     * Test method for {@link geometries.Plane#getNormal(Point3D)}.
     */
    @Test
    void getNormal() {
        Point3D p1 = new Point3D(1,1,0);
        Point3D p2 = new Point3D(1,0,0);
        Point3D p3 = new Point3D(0,1,0);
        Plane pl = new Plane(p1,p2,p3);
        Vector norm=pl.getNormal(Point3D.ZERO);
        // ============ Equivalence Partitions Tests ==============
        //TC01 check normal
        assertTrue(norm.equals(new Vector(0,0,1))||norm.equals(new Vector(0,0,-1))
                ,"the normal isn't right");

    }

    /**
     * Test method for {@link geometries.Plane#findIntersections(Ray)}.
     */
    @Test
    void findIntersections(){
        Plane plane =new Plane(new Point3D(2,2,2),new Point3D(2,5,5),new Point3D(-1,1,0));
        // ============ Equivalence Partitions Tests ==============

        //***********The Ray is neither orthogonal nor parallel to the plane*************
        //TC01 Ray intersectsthe plane
        Point3D point=new Point3D(2,0,0);
        List<Point3D> result = plane.findIntersections(new Ray(new Point3D(1, 0, 0),
                new Vector(1, 0, 0)));
        assertEquals( 1, result.size(),"Wrong number of points");
        assertEquals(point, result.get(0),"Ray crosses plane once");

        //TC02 Ray does not intersect the plane
        assertNull(plane.findIntersections(new Ray(new Point3D(3, 0, 0),
                new Vector(1, 0, 0))),"Ray's line out of sphere ");

        // =============== Boundary Values Tests ==================

        //***********Ray is parallel to the plane*********************
        //TC11 The Ray is  included  in the plain
        assertNull(plane.findIntersections(new Ray(new Point3D(2, 0, 0),
                new Vector(-3, 1, 0))),"Ray's line out of plane ");

        //TC12 the Ray is not included  in the plain
        assertNull(plane.findIntersections(new Ray(new Point3D(1, 0, 0),
                new Vector(-3, 1, 0))),"Ray's line is parallel to point ");

        //************************ Ray is orthogonal to the plane*******************
        //TC13 the Ray starts before the plain
        point=new Point3D(2, 0, 0);
        result = plane.findIntersections(new Ray(new Point3D(1, -3, 3),
                new Vector(1, 3, -3)));
        assertEquals( 1, result.size(),"Wrong number of points");
        assertEquals(point, result.get(0),"Ray crosses plane once");

        //TC14 the Ray plane starts at the plain
        assertNull(plane.findIntersections(new Ray(new Point3D(2, 0, 0),
                new Vector(1, 3, -3))),"Ray's line out of plane ");

        //TC15 the Ray starts after the plain
        assertNull(plane.findIntersections(new Ray(new Point3D(3,0,0),
                new Vector(1, 3, -3))),"Ray's line out of plane ");

        //***********************Ray is neither orthogonal nor parallel to the plane and***********************8888
        //TC16 The Ray  begins at the plane
        assertNull(plane.findIntersections(new Ray(new Point3D(2,0, 0),
                new Vector(2, 3, -3))),"Ray's line out of plane ");
        //TC17 The
        assertNull(plane.findIntersections(new Ray(new Point3D(2,2, 2),
                new Vector(2, 3, -3))),"Ray's line out of plane ");
    }
}
