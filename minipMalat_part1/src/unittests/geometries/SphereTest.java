package unittests.geometries;

import geometries.Sphere;
import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SphereTest {
    /**
     * Test method for {@link geometries.Sphere#getNormal(Point3D)}.
     */
    @Test
    void getNormal() {
        // ============ Equivalence Partitions Tests ==============
        //TC01 check normal
        assertEquals(new Sphere(Point3D.ZERO,4).getNormal(new Point3D(0,0,4))
                ,new Vector(0,0,1),"the normal isn't right");

    }
}