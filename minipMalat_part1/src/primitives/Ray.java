package primitives;

import geometries.Intersectable;

import java.util.List;
import java.util.Objects;

import static primitives.Util.*;
import geometries.Intersectable.GeoPoint;

/**
 *A class that represents a ray
 * vector that starts from a particular point
 *
 *@author Achiya Danziger Malachi Mahpod
 * @version 1
 */
public class Ray {
    /**
     * the vector and the start point of this ray
     */
    Point3D p0;
    Vector dir;

    /**
     * A constructor that gets a vector and a point
     * the constructor will normalize the vector
     *
     * @param p0 the tail of the ray
     * @param dir the vector(direction) of the ray
     */
    public Ray(Point3D p0, Vector dir) {
        this.p0 = p0;
        this.dir = dir.normalized();
    }

    //region getters
    public Point3D getP0() {
        return p0;
    }

    public Vector getDir() {
        return dir;
    }
    //endregion
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ray ray = (Ray) o;
        return Objects.equals(p0, ray.p0) && Objects.equals(dir, ray.dir);
    }

    public Point3D getPoint(double t){
        return p0.add(dir.scale(t));
    }


    public  Point3D findClosestPoint(List<Point3D> points){
        if(points == null)
            return null;
        Point3D closest = points.get(0);
        double dis = closest.distance(p0);
        for (Point3D point:points) {
            double dis2 = point.distance(p0);
            if(dis2 < dis) {
                dis = dis2;
                closest = point;
            }
        }
        return closest;
    }
    public GeoPoint findClosestGeoPoint(List<GeoPoint> listPoints){
        if(listPoints == null)
            return null;
        GeoPoint closest = listPoints.get(0);
        double dis = closest.point.distance(p0);
        for (GeoPoint geoPoint:listPoints) {
            double dis2 = geoPoint.point.distance(p0);
            if(dis2 < dis) {
                dis = dis2;
                closest = geoPoint;
            }
        }
        return closest;

    }
}
