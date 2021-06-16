package geometries;

import primitives.BoundingBox;
import primitives.Point3D;
import primitives.Ray;

import java.util.ArrayList;
import java.util.List;

/**
 * a class that represents
 */
public class Geometries implements Intersectable {
    //the geometries
    List<Intersectable> geometriesList;
    //the bounding box of this object
    BoundingBox mainBox;

    static Boolean useBounding = false;

    /**
     * a constructor that gets no geometries
     */
    public Geometries() {
        geometriesList = new ArrayList<Intersectable>();
    }

    /**
     * a constructor that gets geometries and adds them to the list of geometries
     * and also updates the bounding box of the object
     *
     * @param geometriesList the geometries we want the list to have
     */
    public Geometries(Intersectable... geometriesList) {
        this.geometriesList = List.of(geometriesList);
        mainBox = new BoundingBox(geometriesList[0].getBoundingBox());
        for (Intersectable geo : geometriesList) {
            mainBox.addBoundingBox(geo.getBoundingBox());
        }
    }

    /**
     * a function that gets geometries and adds them to the list of geometries
     * and also updates the bounding box of the object
     *
     * @param geometriesList the geometries we want the list to have
     */
    public void add(Intersectable... geometriesList) {
        if (geometriesList != null) {
            this.geometriesList.addAll(List.of(geometriesList));
            if (mainBox == null)
                mainBox = new BoundingBox(geometriesList[0].getBoundingBox());
            for (Intersectable geo : geometriesList) {
                mainBox.addBoundingBox(geo.getBoundingBox());
            }
        }
    }

    /**
     * a function that gets geometries and adds them to the list of geometries
     * and also updates the bounding box of the object
     *
     * @param geometriesList the geometries we want the list to have
     */
    public void add(List<Intersectable> geometriesList) {
        this.geometriesList.addAll(geometriesList);
        if (mainBox == null && geometriesList.size() > 0)
            mainBox = new BoundingBox(geometriesList.get(0).getBoundingBox());
        for (Intersectable geo : geometriesList) {
            mainBox.addBoundingBox(geo.getBoundingBox());
        }
    }

    /**
     * finds the intersections between the ray and the geometries in the list
     *
     * @param ray the ray
     * @return list of the intersection points
     */
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

    /**
     * finds the intersections between the ray and the geometries in the list
     *
     * @param ray the ray
     * @return list which has a geoPoint for each  intersection
     */
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
     * recursive
     */
    public void buildHierarchy() {
        if (this.geometriesList.size() <= 4)//the end building more won't help
            return;
        else {
            int edge = mainBox.findBiggestEdge();//what edge is the biggest in the Bounding box
            geometriesList.sort((Intersectable a, Intersectable b) -> {//sorts the points according to the biggest axis
                double sizea = a.getBoundingBox().getCenter().getCoord(edge),
                        sizeb = b.getBoundingBox().getCenter().getCoord(edge);
                return sizea < sizeb ? -1 : sizea == sizeb ? 0 : 1;
            });
            Geometries GeoL = new Geometries(), GeoR = new Geometries();//the two new geometries
            int half = 0;
            /*for (; half < geometriesList.size(); ++half) {
                if (geometriesList.get(half).getBoundingBox().getCenter().getCoord(edge) < mainBox.getPointMax().getCoord(edge) / 2) {
                    GeoL.add(geometriesList.get(half));
                } else {
                    GeoR.add(geometriesList.get(half));
                }
            }*/
            half = geometriesList.size()/2;
            GeoL.add(geometriesList.subList(0,half));
            GeoR.add(geometriesList.subList(half, geometriesList.size()));

            GeoL.buildHierarchy();//the recursion
            GeoR.buildHierarchy();

            this.mainBox = null;
            this.geometriesList = new ArrayList<Intersectable>();
            this.add(GeoL, GeoR);
        }
    }

    /**
     * @return the bounding box of this object
     */
    public BoundingBox getBoundingBox() {
        return mainBox;
    }

    public boolean BBGetIntersection(Ray ray) {
        return mainBox.hasIntersection(ray);
    }

    /**
     * @param useBounding weather we are using BVH or not
     */
    public static void setUseBounding(Boolean useBounding) {
        Geometries.useBounding = useBounding;
    }
}
