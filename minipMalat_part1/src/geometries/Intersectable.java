package geometries;
import primitives.Point3D;
import primitives.Ray;

import java.util.List;

/**
 * 
 */
public interface Intersectable {
    /**
     * finds the intersections between a ray and the Geometric body
     * @param ray the ray
     * @return List of the intersection points
     */
    List<Point3D> findIntersections(Ray ray);
}
