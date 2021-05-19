package elements;


import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * A light source that starts from one point and light to only one direction
 */
public class SpotLight extends PointLight {

    private Vector direction;
    private double angle=0.5;
    /**
     * A constructor that gets the intensity,position and the direction of the light
     * @param intensity the intensity of the light
     * @param position the position of the light inherited from PointLight
     * @param direction the direction of the light
     */
    public SpotLight(Color intensity, Point3D position, Vector direction) {
        super(intensity, position);
        this.direction = direction.normalized();
    }

    public SpotLight setAngle(double angle) {
        this.angle = angle;
        return this;
    }

    @Override
    public Color getIntensity(Point3D p) {
        double l;
        double m = direction.dotProduct(getL(p));

        if(angle==0.5)
        {l=m;}
        else {
             l = Math.cos(Math.acos(m) * (0.5 / angle));

            if (Math.cos(angle * Math.PI) > m)
                l = 0;
        }
        return super.getIntensity(p).scale(Math.max(0,l));
    }

}
