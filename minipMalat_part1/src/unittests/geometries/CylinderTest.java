package unittests.geometries;

import geometries.Cylinder;
import geometries.Tube;
import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class CylinderTest {


    /**
     * Test method for {@link geometries.Cylinder#getNormal(Point3D)}.
     */
    @Test
    void getNormal() {
        Cylinder cy = new Cylinder(new Ray(Point3D.ZERO,new Vector(0,0,1)),2, 4);
        // ============ Equivalence Partitions Tests ==============
        //TC01 check ordinary normal
        assertEquals(cy.getNormal(new Point3D(2,0,1)),new Vector(1,0,0)
                ,"The Cylinder normal doesn't work");

        //TC02 checks normal of point in the upper base
        assertEquals(cy.getNormal(new Point3D(1,1,4)),new Vector(0,0,1)
                ,"The Cylinder normal doesn't work  if the point is on the upper base");

        //TC03 checks normal of point in the lower base
        assertEquals(cy.getNormal(new Point3D(-1,1,0)),new Vector(0,0,-1)
                ,"The Cylinder normal doesn't work  if the point is on the lower base");


        // =============== Boundary Values Tests ==================
        //TC11 checks normal of point in center of the upper base
        assertEquals(cy.getNormal(new Point3D(0,0,4)),new Vector(0,0,1)
                ,"The Cylinder normal doesn't work  if the point is center of the upper base");

        //TC12 checks normal of point in center of the lower base
        assertEquals(cy.getNormal(new Point3D(0,0,0)),new Vector(0,0,-1)
                ,"The Cylinder normal doesn't work  if the point is center of the lower base");

    }
}