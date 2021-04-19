package unittests.geometries;

import geometries.Plane;
import geometries.Triangle;
import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TriangleTest {

    /**
     * Test method for {@link geometries.Triangle#Triangle(Point3D, Point3D, Point3D)}.
     */
    @Test
    void constructor(){
        Point3D p1 = new Point3D(0,0,0);
        Point3D p2= new Point3D(0,1,0);
        Point3D p3 = new Point3D(0,2,0);
        // =============== Boundary Values Tests ==================
        //TC 11 checks that the points are all different
        assertThrows( IllegalArgumentException.class, () -> new Triangle(p2,p2,p3),
                "the points should all be different");
        //TC11 checks that the point aren't on the same vector
        assertThrows( IllegalArgumentException.class, () -> new Triangle(p1,p2,p3),
                "the points shouldn't be on the same vector");
    }

    /**
     * Test method for {@link geometries.Triangle#getNormal(Point3D)}.
     */
    @Test
    void getNormal() {
        Point3D p1 = new Point3D(0,2,0);
        Point3D p2 = new Point3D(2,0,0);
        Point3D p3 = new Point3D(0,0,0);
        Triangle pl = new Triangle(p1,p2,p3);

        Vector norm = pl.getNormal(new Point3D(1,1,0));
        // ============ Equivalence Partitions Tests ==============
        //TC01 check normal
        assertTrue(norm.equals(new Vector(0,0,1))||norm.equals(new Vector(0,0,-1))
                ,"the normal isn't right");
    }
    /**
     * Test method for {@link geometries.Triangle#findIntersections(Ray)}.
     */
    @Test
    void findIntersections(){//only if the ray intersects with the plane

        Triangle triangle= new Triangle(new Point3D(2,2,2),new Point3D(2,-5,-5),new Point3D(0,2,0));
        // ============ Equivalence Partitions Tests ==============
        //TC01 The Intersection with the Plane is Inside polygon/triangle
        Point3D point=new Point3D(1,1,0);
        List<Point3D> result = triangle.findIntersections(new Ray(new Point3D(0, 1, 0),
                new Vector(1, 0, 0)));
        assertEquals( 1, result.size(),"Wrong number of points");
        assertEquals(point, result.get(0),"Ray crosses Triangle once");

        //TC02 The Intersection with the Plane is Outside against edge
        assertNull(triangle.findIntersections(new Ray(new Point3D(0,-1, 0),
                new Vector(1, 0, 0))),"Ray's line out of Triangle ");
        //TC03 The Intersection with the Plane is Outside against vertex
        assertNull(triangle.findIntersections(new Ray(new Point3D(0,6, 0),
                new Vector(1, 0 ,0))),"Ray's line out of the Triangle  ");

        // =============== Boundary Values Tests ==================

        //TC11 the Intersection with the Plane is Outside on the edge
        assertNull(triangle.findIntersections(new Ray(new Point3D(0.5,0, 0),
                new Vector(1, 0,0))),"Ray's line out of the Triangle  ");

        //TC11 the Intersection with the Plane is Outside In vertex
        assertNull(triangle.findIntersections(new Ray(new Point3D(-1,2,0),
                new Vector(1, 0,0))),"Ray's line out of the Triangle  ");

        //TC11 the Intersection with the Plane is Outside On edge's continuation
        assertNull(triangle.findIntersections(new Ray(new Point3D(5,2,6),
                new Vector(1, 0,0))),"Ray's line out of the Triangle  ");

    }

}