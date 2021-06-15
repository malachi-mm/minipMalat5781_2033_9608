package geometries;

import primitives.BoundingBox;
import primitives.Point3D;
import primitives.Ray;

import java.util.ArrayList;
import java.util.List;

public class Geometries implements Intersectable {
    List<Intersectable> geometriesList;
    BoundingBox mainBox;

    static Boolean useBounding = false;


    public Geometries() {
        geometriesList = new ArrayList<Intersectable>();
    }

    public Geometries(Intersectable... geometriesList) {
        this.geometriesList = List.of(geometriesList);
        mainBox = new BoundingBox(geometriesList[0].getBoundingBox());
        for (Intersectable geo : geometriesList) {
            mainBox.addBoundingBox(geo.getBoundingBox());
        }
    }

    public void add(Intersectable... geometriesList) {
        this.geometriesList.addAll(List.of(geometriesList));
        if (mainBox == null)
            mainBox = new BoundingBox(geometriesList[0].getBoundingBox());
        for (Intersectable geo : geometriesList) {
            mainBox.addBoundingBox(geo.getBoundingBox());
        }
    }

    @Override
    public List<Point3D> findIntersections(Ray ray) {
        List<Point3D> listPoints = null;
        for (Intersectable geometry : this.geometriesList) {
            if (geometry.BBGetIntersection(ray) || !useBounding) {
                List<Point3D> points = geometry.findIntersections(ray);
                if (points != null) {
                    if (listPoints == null)
                        listPoints = new ArrayList<Point3D>();
                    listPoints.addAll(points);
                }
            }
        }
        return listPoints;
    }

    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance) {
        List<GeoPoint> listPoints = null;
        for (Intersectable geometry : this.geometriesList) {
            if (geometry.BBGetIntersection(ray) || !useBounding) {
                List<GeoPoint> points = geometry.findGeoIntersections(ray, maxDistance);
                if (points != null) {
                    if (listPoints == null)
                        listPoints = new ArrayList<GeoPoint>();
                    listPoints.addAll(points);
                }
            }
        }
        return listPoints;
    }

    public BoundingBox getBoundingBox() {
        return mainBox;
    }

    public boolean BBGetIntersection(Ray ray) {
        return mainBox.hasIntersection(ray);
    }

    public static void setUseBounding(Boolean useBounding) {
        Geometries.useBounding = useBounding;
    }
}
