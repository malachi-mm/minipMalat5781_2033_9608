package geometries;

import primitives.BoundingBox;
import primitives.Point3D;
import primitives.Ray;
import renderer.Render;

import java.util.List;
import java.util.Objects;

/**
 *
 */
public interface Intersectable {
    /**
     * finds the intersections between a ray and the Geometric body
     *
     * @param ray the ray
     * @return List of the intersection points
     */
    List<Point3D> findIntersections(Ray ray);

    /*   default List<Point3D> findIntersections(Ray ray) {
        var geoList = findGeoIntersections(ray);
        return geoList == null ? null
                : geoList.stream().map(gp -> gp.point).collect(Collectors.toList());
    }*///we stuck to the first implementation because we thought it was better

    /**
     * saves the intersection point
     * and the geometry that had the intersection
     */
    public static class GeoPoint {
        public Geometry geometry;
        public Point3D point;

        /**
         * A constructor that gets the geometry ind the point
         *
         * @param geometry the geometry
         * @param point    the point
         */
        public GeoPoint(Geometry geometry, Point3D point) {
            this.geometry = geometry;
            this.point = point;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            GeoPoint geoPoint = (GeoPoint) o;
            return Objects.equals(geometry, geoPoint.geometry) && Objects.equals(point, geoPoint.point);
        }

    }

    default List<GeoPoint> findGeoIntersections(Ray ray) {
        if (this.BBGetIntersection(ray)||!Geometries.useBounding)
            return findGeoIntersections(ray, Double.POSITIVE_INFINITY);
        return null;
    }

    List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance);

    public BoundingBox getBoundingBox();

    public boolean BBGetIntersection(Ray ray);

}
