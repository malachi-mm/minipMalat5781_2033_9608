/**
 *
 */
package unittests.geometries;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import geometries.*;
import primitives.*;

import java.util.List;


/**
 * Testing Polygons
 *
 * @author Dan
 *
 */
public class PolygonTest {

    /**
     * Test method for
     * {@link geometries.Polygon# Polygon(primitives.Point3D, primitives.Point3D, primitives.Point3D, primitives.Point3D)}.
     */
    @Test
    public void testConstructor() {
        // ============ Equivalence Partitions Tests ==============

        // TC01: Correct concave quadrangular with vertices in correct order
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0), new Point3D(-1, 1, 1));
        } catch (IllegalArgumentException e) {
            fail("Failed constructing a correct polygon");
        }

        // TC02: Wrong vertices order
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(0, 1, 0),
                    new Point3D(1, 0, 0), new Point3D(-1, 1, 1));
            fail("Constructed a polygon with wrong order of vertices");
        } catch (IllegalArgumentException e) {}

        // TC03: Not in the same plane
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0), new Point3D(0, 2, 2));
            fail("Constructed a polygon with vertices that are not in the same plane");
        } catch (IllegalArgumentException e) {}

        // TC04: Concave quadrangular
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0), new Point3D(0.5, 0.25, 0.5));
            fail("Constructed a concave polygon");
        } catch (IllegalArgumentException e) {}

        // =============== Boundary Values Tests ==================

        // TC10: Vertex on a side of a quadrangular
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0), new Point3D(0, 0.5, 0.5));
            fail("Constructed a polygon with vertix on a side");
        } catch (IllegalArgumentException e) {}

        // TC11: Last point = first point
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0), new Point3D(0, 0, 1));
            fail("Constructed a polygon with vertice on a side");
        } catch (IllegalArgumentException e) {}

        // TC12: Colocated points
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0), new Point3D(0, 1, 0));
            fail("Constructed a polygon with vertice on a side");
        } catch (IllegalArgumentException e) {}

    }

    /**
     * Test method for {@link geometries.Polygon#getNormal(primitives.Point3D)}.
     */
    @Test
    public void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        Polygon pl = new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0), new Point3D(0, 1, 0),
                new Point3D(-1, 1, 1));
        double sqrt3 = Math.sqrt(1d / 3);
        assertEquals(new Vector(sqrt3, sqrt3, sqrt3), pl.getNormal(new Point3D(0, 0, 1)),"Bad normal to trinagle");
    }
    /**
     * Test method for {@link geometries.Polygon#findIntersections(Ray)}.
     */
    @Test
    void findIntersections(){

        Polygon polygon= new Polygon(new Point3D(3,0,0),new Point3D(0,0,1),new Point3D(0,0,4), new Point3D(5,0,2));

        // ============ Equivalence Partitions Tests ==============
        //TC01 The Intersection with the Plane is Inside polygon
        Point3D point=new Point3D(1,0,1);
        List<Point3D> result = polygon.findIntersections(new Ray(new Point3D(1, -1, 1),
                new Vector(0,1,0)));
        assertEquals( 1, result.size(),"Wrong number of points");
        assertEquals(point, result.get(0),"Ray crosses Polygon once");

        //TC02 The Intersection with the Plane is Outside against edge
        assertNull(polygon.findIntersections(new Ray(new Point3D(0,-1, 2),
                new Vector(0, 1, 0))),"Ray's line out of Polygon ");
        //TC03 The Intersection with the Plane is Outside against vertex
        assertNull(polygon.findIntersections(new Ray(new Point3D(0,-1, 1),
                new Vector(0, 1 ,0))),"Ray's line out of the Polygon  ");

        // =============== Boundary Values Tests ==================

        //TC11 the Intersection with the Plane is Outside on the edge
        assertNull(polygon.findIntersections(new Ray(new Point3D(0,-1, 1),
                new Vector(-1, 3,3))),"Ray's line out of the Polygon  ");

        //TC12 the Intersection with the Plane is Outside In vertex
        assertNull(polygon.findIntersections(new Ray(new Point3D(-1,-1,0),
                new Vector(0, 1,0))),"Ray's line out of the Polygon  ");

        //TC13 the Intersection with the Plane is Outside between 2 edges
        assertNull(polygon.findIntersections(new Ray(new Point3D(5.1,-3,2),
                new Vector(0, 1,0))),"Ray's line out of the Polygon  ");

    }

}
