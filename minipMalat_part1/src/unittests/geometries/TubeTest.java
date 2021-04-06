package unittests.geometries;

import geometries.Tube;
import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TubeTest {

    /**
     * Test method for {@link geometries.Sphere#getNormal(Point3D)}.
     */
    @Test
    void getNormal() {
        Tube tb=new Tube(new Ray(Point3D.ZERO,new Vector(0,0,1)),1);
        // ============ Equivalence Partitions Tests ==============
        //TC01 check ordinary normal
        assertEquals(tb.getNormal(new Point3D(1,0,1)),new Vector(1,0,0)
        ,"The Tube normal doesn't work");

        // =============== Boundary Values Tests ==================
        //TC11 checks normal of point while the point is perpendicular to the beginning of the Ray");
        assertEquals(tb.getNormal(new Point3D(1,0,0)),new Vector(1,0,0)
                ,"The Tube normal doesn't work  if the point is perpendicular to the beginning of the Ray");
    }
}