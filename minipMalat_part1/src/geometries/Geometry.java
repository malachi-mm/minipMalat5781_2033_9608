package geometries;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

public abstract class Geometry implements Intersectable{

    protected Color emission=Color.BLACK;

    /**
     * setter for the emission light
     * @param emission the new emission color
     * @return the geometry
     */
    public Geometry setEmission(Color emission) {
        this.emission = emission;
        return this;
    }

    /**
     * @return the emission lihgt
     */
    public Color getEmission() {
        return emission;
    }

    public abstract Vector getNormal(Point3D point);
}
