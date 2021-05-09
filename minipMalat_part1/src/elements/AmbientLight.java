package elements;

import primitives.Color;

/**
 * A class for Ambient light
 */
public class AmbientLight {

    Color intensity;


    public AmbientLight(Color iA, double kA) {
        this.intensity = iA.scale(kA);
    }

    public AmbientLight() {
        this.intensity = Color.BLACK;
    }
    //********Getters*******************************
    public Color getIntensity() {
        return intensity;
    }
}