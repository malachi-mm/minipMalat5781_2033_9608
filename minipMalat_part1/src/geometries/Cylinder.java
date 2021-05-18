package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * A class to represent a cylinder with Tube and the height of the cylinder
 */
public class Cylinder extends Tube{

    double height;

    /**
     * the main constructor of the cylinder
     * that gets everything necessary for a tube-axis ray and radius
     * with additional double for height
     * @param axisRay the axis ray
     * @param radius the radius of the cylinder
     * @param height the height of the cylinder
     * @exception  IllegalArgumentException if the  radius or height are not positive
     */
    public Cylinder(Ray axisRay, double radius, double height) {
        super(axisRay, radius);
        if(alignZero(height) <= 0)
            throw new IllegalArgumentException("radius must be positive");
        this.height = height;
    }

    /**
     * @return the height of the cylinder
     */
    public double getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return "Cylinder{" +
                ", axisRay=" + axisRay +
                ", radius=" + radius +
                "height=" + height +
                '}';
    }

    @Override
    public Vector getNormal(Point3D point) {
        Vector norm=axisRay.getDir();//the normal
        Point3D upPoint=axisRay.getP0().add(axisRay.getDir().scale(height));//the center of the upper base

        if (point.equals(axisRay.getP0())) {//if its the center of the lower base
            norm=norm.scale(-1);
        }
        else if(!point.equals(upPoint)){//if it is not the center of either bases

            Vector upvec = upPoint.subtract(point);//the vector between the point and the center of the upper base
            Vector lowVec = axisRay.getP0().subtract(point);//the Vector between the point and the center of the lower base

             if(isZero(lowVec.dotProduct(axisRay.getDir()))) {//if the axis ray and the lowvec are perpendicular(the point is in the lower bse)
                norm=norm.scale(-1);//returns Vector of the axis ray scaled by -1
            }
            else if(!isZero(upvec.dotProduct(axisRay.getDir()))) {//if the point is not on the upper(and lower bases)
                norm = super.getNormal(point);//it is the same as Tube
            }
        }

        return norm;
    }

    @Override
    public List<Point3D> findIntersections(Ray ray) {
        return null;
    }

    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray) {
        List<Point3D> listPoints= findIntersections(ray);
        if(listPoints==null)
            return null;
        List<GeoPoint> listGeoPoints= new ArrayList<GeoPoint>();

        for (Point3D inter:listPoints)
            listGeoPoints.add(new GeoPoint(this,inter));
        return listGeoPoints;
    }

}
