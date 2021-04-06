package unittests.primitives;

import org.junit.jupiter.api.Test;
import primitives.Coordinate;
import primitives.Point3D;
import primitives.Vector;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static primitives.Util.isZero;

class VectorTest {

    /**
     * Test method for {@link primitives.Vector# Vector()}.
     */
    @Test
    public void testConstructor() {
        //check 3 constructors throw

        // =============== Boundary Values Tests ==================
        // TC11: test 3 doubles zero
        assertThrows(IllegalArgumentException.class,()->new Vector(0,0,0),"the vector can't be Zero");

        // TC12: test 3 coordinates zero
        assertThrows(IllegalArgumentException.class,()->new Vector(new Coordinate(0),new Coordinate(0),new Coordinate(0)),"the vector can't be Zero");

        // TC13: test point 3d zero
        assertThrows(IllegalArgumentException.class,()->new Vector(new Point3D(0,0,0)),"the vector can't be Zero");

    }

    /**
     * Test method for {@link primitives.Vector#add(Vector)}.
     */
    @Test
    void add() {
        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(0, 3, -2);

        // ============ Equivalence Partitions Tests ==============
        // TC01: Test that value of lengthSquared is proper
        assertEquals(v1.add(v2),new Vector(1,5,1),"ERROR: scale wrong value");
    }
    /**
     * Test method for {@link primitives.Vector#subtract(Vector)}.
     */
    @Test
    void subtract() {
        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(0, 3, -2);

        // ============ Equivalence Partitions Tests ==============
        // TC01: Test that value of lengthSquared is proper
        assertEquals(v1.subtract(v2),new Vector(-1,1,-5),"ERROR: scale wrong value");
    }

    /**
     * Test method for {@link primitives.Vector#scale(double)}.
     */
    @Test
    void scale() {
        Vector v1 = new Vector(1, 2, 3);

        // ============ Equivalence Partitions Tests ==============
        // TC01: Test that value of lengthSquared is proper
        assertEquals(v1.scale(3),new Vector(3,6,9),"ERROR: scale wrong value");
        // =============== Boundary Values Tests ==================
        // TC11: test Zero scale
        assertThrows(IllegalArgumentException.class,()->v1.scale(0),"the vector can't be Zero");
    }


    /**
     * Test method for {@link primitives.Vector#dotProduct(primitives.Vector)}.
     */
    @Test
    void dotProduct() {
        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(-2, -4, -6);
        Vector v3 = new Vector(0, 3, -2);

        // ============ Equivalence Partitions Tests ==============
        // TC01: Test that value of dot-product is proper
        assertEquals(v1.dotProduct(v2),-28,0.0001,"ERROR: dotProduct() wrong value");

        // =============== Boundary Values Tests ==================
        assertTrue(isZero(v1.dotProduct(v3)),"ERROR: dotProduct() for orthogonal vectors is not zero");
    }

    /**
     * Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}.
     */
    @Test
    public void crossProduct() {
        Vector v1 = new Vector(1, 2, 3);

        // ============ Equivalence Partitions Tests ==============
        Vector v2 = new Vector(0, 3, -2);
        Vector vr = v1.crossProduct(v2);

        // TC01: Test that length of cross-product is proper (orthogonal vectors taken
        // for simplicity)
        assertEquals( v1.length() * v2.length(), vr.length(), 0.00001,"crossProduct() wrong result length");

        // TC02: Test cross-product result orthogonality to its operands
        assertTrue( isZero(vr.dotProduct(v1)),"crossProduct() result is not orthogonal to 1st operand\"");
        assertTrue( isZero(vr.dotProduct(v2)),"crossProduct() result is not orthogonal to 2nd operand");

        // =============== Boundary Values Tests ==================
        // TC11: test zero vector from cross-productof co-lined vectors
        Vector v3 = new Vector(-2, -4, -6);
        assertThrows( IllegalArgumentException.class, () -> v1.crossProduct(v3),
                "crossProduct() for parallel vectors does not throw an exception");
        // try {
       //      v1.crossProduct(v2);
      //       fail("crossProduct() for parallel vectors does not throw an exception");
        // } catch (Exception e) {}
    }

    /**
     * Test method for {@link Vector#lengthSquared()}
     */
    @Test
    void lengthSquared() {
        Vector v1 = new Vector(1, 2, 3);

        // ============ Equivalence Partitions Tests ==============
        // TC01: Test that value of lengthSquared is proper
        assertEquals(v1.lengthSquared(),14,0.0001,"ERROR: lengthSquared() wrong value");

    }

    /**
     * Test method for {@link Vector#length()}
     */
    @Test
    void length() {

        // ============ Equivalence Partitions Tests ==============
        // TC01: Test that value of length is proper
        assertEquals(new Vector(0, 3, 4).length() ,5,0.0001,"ERROR: length() wrong value");
    }

    /**
     * Test method for {@link Vector#normalize()}
     */
    @Test
    void normalize() {
        Vector v = new Vector(1, 2, 3);
        Vector vCopy = new Vector(v.getHead());
        Vector vCopyNormalize = vCopy.normalize();

        // ============ Equivalence Partitions Tests ==============
        // TC01: Test that value of lengthSquared is proper
        assertEquals(vCopyNormalize.length(), 1, "ERROR: normalize() result is not a unit vector");

        // TC02: Test that normlaize() not create new vector
        assertEquals(vCopy, vCopyNormalize, "ERROR: normalize() function creates a new vector");


    }

    /**
     * Test method for {@link Vector#normalized()}
     */
    @Test
    void normalized() {
        Vector v = new Vector(1, 2, 3);
        // ============ Equivalence Partitions Tests ==============
        //TC01 Test that the creates a new vector
        Vector u = v.normalized();
        assertNotEquals(u,v,"ERROR: normalizated() function does not create a new vector");

    }
}