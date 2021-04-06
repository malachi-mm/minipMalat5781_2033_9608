package unittests.primitives;

import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Vector;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;

class Point3DTest {
    /**
     * Test method for {@link primitives.Point3D#subtract(Point3D)}.
     */
    @Test
    void subtract() {

        Point3D p1 = new Point3D(1, 2, 3);
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test that value of subtract is proper
        assertEquals(new Point3D(2, 3, 4).subtract(p1), new Vector(1,1,1),"ERROR: Point - Point does not work correctly");

    }
    /**
     * Test method for {@link primitives.Point3D#add(Vector)}.
     */
    @Test
    void add() {
        Point3D p1 = new Point3D(1, 2, 3);
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test that value of add is proper
        assertEquals(p1.add(new Vector(-1, -2, -3)),Point3D.ZERO,"ERROR: Point + Vector does not work correctly");

    }

    /**
     * Test method for {@link primitives.Point3D#distanceSquared(Point3D)}
     */
    @Test
    void distanceSquared() {
        Point3D p1 = new Point3D(1, 4, 3);
        Point3D p2 = new Point3D(1, 0, 6);

        // ============ Equivalence Partitions Tests ==============
        // TC01: Test that value of distanceSquared is proper
        assertEquals(p1.distanceSquared(p2), 25,0.0001, "ERROR: the distance squared don't work");

        // =============== Boundary Values Tests ==================
        // TC11: test Zero
        assertEquals(p1.distanceSquared(p1), 0,0.0001, "ERROR: the distanceSquared don't work");

    }

    /**
     * Test method for {@link primitives.Point3D#distance(Point3D)}
     */
    @Test
    void distance() {
        Point3D p1 = new Point3D(1, 4, 3);
        Point3D p2 = new Point3D(1, 0, 6);

        // ============ Equivalence Partitions Tests ==============
        // TC01: Test that value of distance is proper
        assertEquals(p1.distance(p2), 5,0.0001, "ERROR: the distance don't work");

        // =============== Boundary Values Tests ==================
        // TC11: test Zero
        assertEquals(p1.distance(p1), 0,0.0001, "ERROR: the distance don't work");

    }
}