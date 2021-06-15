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

    public void add(List<Intersectable> geometriesList) {
        this.geometriesList.addAll(geometriesList);
        if (mainBox == null)
            mainBox = new BoundingBox(geometriesList.get(0).getBoundingBox());
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

    /**
     * builds the hierarchy
     * probably recursive
     */
    public void buildHierarchy() {
        if (this.geometriesList.size() <= 4)
            return;
        else {
            int edge = mainBox.findBiggestEdge();
            geometriesList.sort((Intersectable a, Intersectable b) -> {
                double sizea = a.getBoundingBox().getCenter().getCoord(edge),
                        sizeb = b.getBoundingBox().getCenter().getCoord(edge);
                return sizea < sizeb ? -1 : sizea == sizeb ? 0 : 1;
            });
            Geometries GeoL=new Geometries(),GeoR=new Geometries();

            int half = geometriesList.size()/2;
            GeoL.add(geometriesList.subList(0,half));
            GeoR.add(geometriesList.subList(half, geometriesList.size()));

            GeoL.buildHierarchy();
            GeoR.buildHierarchy();

            this.mainBox=null;
            this.geometriesList = new ArrayList<Intersectable>();
            this.add(GeoL,GeoR);
        }
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
