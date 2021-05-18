package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * an interface that represents light sources
 */
public interface LightSource {
    /**
     *returns the intensity the source has in a point
     * @param p the point
     * @return the intensity
     */
    public Color getIntensity(Point3D p);

    /**
     * calculates the  direction from the light source to the point
     * @param p the point
     * @return the normalized vector from the light source to the point
     */
    public Vector getL(Point3D p);

}
