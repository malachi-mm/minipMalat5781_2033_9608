package geometries;

import primitives.Point3D;
import primitives.Ray;

import java.util.ArrayList;
import java.util.List;

public class Geometries implements Intersectable{
    List<Intersectable> geometriesList;

    public Geometries() {
        geometriesList=new ArrayList<Intersectable>();
    }

    public Geometries(Intersectable... geometriesList) {
        this.geometriesList = List.of(geometriesList);
    }

    public void add(Intersectable... geometriesList) {
        this.geometriesList.addAll(List.of(geometriesList));
    }

    @Override
    public List<Point3D> findIntersections(Ray ray) {
        List<Point3D> listPoints = null;
        for (Intersectable geometry:this.geometriesList) {
            List<Point3D> points=geometry.findIntersections(ray);
            if (points!=null) {
                if (listPoints==null)
                    listPoints=new ArrayList<Point3D>();
                listPoints.addAll(points);
            }

        }
        return listPoints;
    }
}
