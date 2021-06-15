package geometries;

import primitives.*;

public abstract class Geometry implements Intersectable {

    protected Color emission = Color.BLACK;
    private Material material = new Material();
    protected BoundingBox boundingBox;

    /**
     * setter for the emission light
     *
     * @param emission the new emission color
     * @return the geometry
     */
    public Geometry setEmission(Color emission) {
        this.emission = emission;
        return this;
    }

    /**
     * @return the emission light
     */
    public Color getEmission() {
        return emission;
    }

    public abstract Vector getNormal(Point3D point);


    /**
     * return the material of the geometry
     *
     * @return the material
     */
    public Material getMaterial() {
        return material;
    }

    /**
     * sets the material of the geometry
     *
     * @param material the new material
     * @return the geometry after changing the material
     */
    public Geometry setMaterial(Material material) {
        this.material = material;
        return this;
    }

    /**
     * @return the bounding box of the geometry
     */
    public BoundingBox getBoundingBox() {
        return boundingBox;
    }


    /**
     * find weather the bounding box has intesdection with the ray
     * @param ray the ray we wand to check intersection with
     * @return
     */
    public boolean BBGetIntersection(Ray ray) {
        return boundingBox.hasIntersection(ray);
    }

    protected BoundingBox findBoundingBox() {
        return null;
    }
}
