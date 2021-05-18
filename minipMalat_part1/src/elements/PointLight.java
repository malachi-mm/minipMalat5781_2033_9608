package elements;


import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * A class that represents alight that comes from one particular point
 */
public class PointLight extends Light implements LightSource  {

    protected Point3D position;
    protected double kC,kL,kQ;

    /**
     * A constructor that gets the intensity of the light and the position of the light
     * @param intensity the intensity of the light
     * @param position the position of the light
     */
    public PointLight(Color intensity, Point3D position) {
        super(intensity);
        this.position = position;
        kC=1;kL=0;kQ=0;
    }

    public PointLight setkC(double kC) {
        this.kC = kC;
        return this;
    }

    public PointLight setkL(double kL) {
        this.kL = kL;
        return this;
    }

    public PointLight setkQ(double kQ) {
        this.kQ = kQ;
        return this;
    }

    protected PointLight(Color intensity) {
        super(intensity);
    }

    @Override
    public Color getIntensity(Point3D p) {
        double d=p.distance(this.position);
        double denominator=kC+kL*d+kQ*d*d;
        return getIntensity().scale(1/denominator);
    }

    @Override
    public Vector getL(Point3D p) {
        return p.subtract(this.position).normalized();
    }
}
