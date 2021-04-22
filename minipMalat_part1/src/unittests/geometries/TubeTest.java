package unittests.geometries;

import geometries.Tube;
import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TubeTest {

    /**
     * Test method for {@link geometries.Tube#getNormal(Point3D)}.
     */
    @Test
    void getNormal() {
        Tube tb=new Tube(new Ray(Point3D.ZERO,new Vector(0,0,1)),1);
        // ============ Equivalence Partitions Tests ==============
        //TC01 check ordinary normal
        assertEquals(tb.getNormal(new Point3D(1,0,1)),new Vector(1,0,0)
        ,"The Tube normal doesn't work");
        assertEquals(tb.getNormal(new Point3D(1,0,-1)),new Vector(1,0,0)
                ,"The Tube normal doesn't work");

        // =============== Boundary Values Tests ==================
        //TC11 checks normal of point while the point is perpendicular to the beginning of the Ray");
        assertEquals(tb.getNormal(new Point3D(1,0,0)),new Vector(1,0,0)
                ,"The Tube normal doesn't work  if the point is perpendicular to the beginning of the Ray");
    }
    /**
     * Test method for {@link geometries.Tube#findIntersections(Ray)}.
     */
    @Test
    void findIntersections(){
        Tube tb=new Tube(new Ray(new Point3D(1,0,1),new Vector(0,0,1)),1);
        // ============ Equivalence Partitions Tests ==============
        //TC01 the ray has two intersection points with the tube(2 points)
        Point3D p1 = new Point3D(1, 1, 3);
        Point3D p2 = new Point3D(0, 0, 4);
        List<Point3D> result = tb.findIntersections(new Ray(new Point3D(2, 2, 2),
                new Vector(-2, -2, 2)));
        assertEquals( 2, result.size(),"Wrong number of points");
        if (result.get(0).getX() > result.get(1).getX())
            result = List.of(result.get(1), result.get(0));
        assertEquals(List.of(p1, p2), result,"Ray crosses sphere");

        // TC02: Ray's line is outside the tube (0 points)
        assertNull(tb.findIntersections(new Ray(new Point3D(3, 1, 1),
                new Vector(-2, 1, 1))),"Ray's line out of sphere");

        // TC03: Ray starts inside the tube (1 point)
        p1 = new Point3D(1, 1, 2);
        result = tb.findIntersections(new Ray(new Point3D(1, 0.5d, 1),
                new Vector(0, 1.5, 3)));
        assertEquals( 1, result.size(),"Wrong number of points");
        assertEquals(p1, result.get(0),"Ray crosses tube");

        // TC04: Ray starts after the tube (0 points)

        assertNull(tb.findIntersections(new Ray(new Point3D(1, 2, 4),
                new Vector(0, 1, 1))),"Ray's line out of sphere");

        // =============== Boundary Values Tests ==================



    }
}