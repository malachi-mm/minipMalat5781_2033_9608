package unittests.primitives;

import org.junit.jupiter.api.Test;
import primitives.Coordinate;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RayTest {
    /**
     * Test method for {@link primitives.Ray#findClosestPoint Vector()}.
     */
    @Test
    public void testConstructor() {
        Ray ray =new Ray(Point3D.ZERO,new Vector(1,2.5d,0));
        List<Point3D> points=new ArrayList<Point3D>();
        Point3D p1=new Point3D(1,2.5,1);
        Point3D p2=new Point3D(2,5,0);
        Point3D p3=new Point3D(4,10,0);

        // ============ Equivalence Partitions Tests ==============
        //TC01 the closest point is in the middle of the list
        points.add(p2);points.add(p1);points.add(p3);
        assertEquals(ray.findClosestPoint(points),p1,"incorrect point");

        // =============== Boundary Values Tests ==================
        // TC11: there is no points given
        assertNull(ray.findClosestPoint(null),
                "there is no points  the function should return null");

        // TC12: the closest point is in the beginning of the list
        points.clear();
        points.add(p1);points.add(p2);points.add(p3);
        assertEquals(ray.findClosestPoint(points),p1,"incorrect point");

        // TC13: the closest point is in the end of the list
        points.clear();
        points.add(p3);points.add(p2);points.add(p1);
        assertEquals(ray.findClosestPoint(points),p1,"incorrect point");
    }

}