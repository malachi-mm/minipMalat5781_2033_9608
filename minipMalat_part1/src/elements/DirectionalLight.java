package elements;

import elements.LightSource;
import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * A class that represents Directional light-
 * a light that comes from one direction and not one particular light source
 */
public class DirectionalLight extends Light implements LightSource  {

    private Vector direction;


    /**
     * A constructor that gets the intensity and direction of the light
     * @param intensity the intensity of the light
     * @param direction the direction of the light
     */
    public DirectionalLight(Color intensity, Vector direction) {
        super(intensity);
        this.direction = direction.normalized();
    }

    @Override
    public Color getIntensity(Point3D p) {
    return getIntensity();
    }

    @Override
    public Vector getL(Point3D p) {
        return direction;
    }

    @Override
    public double getDistance(Point3D point) {
        return Double.POSITIVE_INFINITY;
    }
}
