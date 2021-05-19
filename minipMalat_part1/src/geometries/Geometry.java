package geometries;

import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;

public abstract class Geometry implements Intersectable{

    protected Color emission=Color.BLACK;
    private Material material=new Material();
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


    /**
     * return the material of the geometry
     * @return the material
     */
    public Material getMaterial() {
        return material;
    }

    /**
     * sets the material of the geometry
     * @param material the new material
     * @return the geometry after changing the material
     */
    public Geometry setMaterial(Material material) {
        this.material = material;
        return this;
    }
}
