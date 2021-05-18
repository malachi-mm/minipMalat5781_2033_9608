package elements;

import primitives.Color;

/**
 * A class for Ambient light
 */
public class AmbientLight extends Light{


    /**
     * A constructor that gets every variable
     * @param iA
     * @param kA
     */
    public AmbientLight(Color iA, double kA) {
        super(iA.scale(kA));
    }

    public AmbientLight() {
        super(Color.BLACK);
    }

}
