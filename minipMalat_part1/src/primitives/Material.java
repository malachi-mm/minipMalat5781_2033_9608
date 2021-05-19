package primitives;

/**
 *
 */
public class Material {

    public double kD=0,kS=0;
    public int nShininess=0;

    /**
     * @param kD the of the material
     * @return the object after replacing kD
     */
    public Material setkD(double kD) {
        this.kD = kD;
        return this;
    }

    /**
     * @param kS the of the material
     * @return the object after replacing ks
     */
    public Material setkS(double kS) {
        this.kS = kS;
        return this;
    }

    /**
     * @param nShininess the of the material
     * @return the object after replacing nShininess
     */
    public Material setnShininess(int nShininess) {
        this.nShininess = nShininess;
        return this;
    }

    public double getkD() {
        return kD;
    }

    public double getkS() {
        return kS;
    }

    public int getnShininess() {
        return nShininess;
    }
}
