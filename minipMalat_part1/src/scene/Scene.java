package scene;


import elements.AmbientLight;
import elements.LightSource;
import geometries.Geometries;
import primitives.Color;

import java.util.LinkedList;
import java.util.List;

public class Scene {
    public String name;
    public Color background=Color.BLACK;
    public AmbientLight ambientLight;//should we delete it
    public Geometries geometries;
    public List<LightSource> lights=new LinkedList<LightSource>();

    public Scene(String name) {
        this.name = name;
        geometries=new Geometries();
    }

    public Scene setBackground(Color background) {
        this.background = background;
        return this;
    }

    public Scene setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight = ambientLight;
        return this;
    }

    /**
     * sets the geometries in the scene
     * @param geometries the light to add to the scene
     * @return the scene according to builder pattern
     */
    public Scene setGeometries(Geometries geometries) {
        this.geometries = geometries;
        return this;
    }

    /**
     * sets the lights in the scene
     * @param lights the light to add to the scene
     * @return the scene according to builder pattern
     */
    public Scene setLights(List<LightSource> lights) {
        this.lights = lights;
        return this;
    }
}
