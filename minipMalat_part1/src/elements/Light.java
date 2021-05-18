package elements;

import primitives.Color;

/**
 * an abstract class that represents the different kinds of light
 */
 abstract class Light {
     private Color intensity;

    /**
     * a constructor that gets the intensity of the light
     * @param intensity the intensity of the light
     */
    protected Light(Color intensity) {
        this.intensity = intensity;
    }

    /**
     * getter for the intensity
     * @return the intensity of the light
     */
    public Color getIntensity() {
        return intensity;
    }
}
