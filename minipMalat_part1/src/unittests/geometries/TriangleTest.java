package unittests.geometries;

import geometries.Plane;
import geometries.Triangle;
import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class TriangleTest {

    /**
     * Test method for {@link geometries.Triangle#getNormal(Point3D)}.
     */
    @Test
    void getNormal() {
        Point3D p1 = new Point3D(0,2,0);
        Point3D p2 = new Point3D(2,0,0);
        Point3D p3 = new Point3D(0,0,0);
        Triangle pl = new Triangle(p1,p2,p3);

        // ============ Equivalence Partitions Tests ==============
        //TC01 check normal
        assertEquals(pl.getNormal(new Point3D(1,1,0))
                ,new Vector(0,0,1),"the normal isn't right");
    }
}