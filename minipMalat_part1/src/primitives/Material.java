package primitives;

/**
 *
 */
public class Material {

    public double kD=0,kS=0;
    /**
     * kT- determines the refraction(Transparency) of the Material
     * kR determines how much the Material is  reflecting
     */
    public double kT=0.0,kR=0.0;
    /**
     *kB the blurriness of the material(in transparency)
     * kG the glossiness of the material(in reflection)
     */

    public double kB=0.0,kG=0.0;


/**
the shininess of the material
 */
    public int nShininess=0;

    /**
     * sets the diffuse blurry glass
     * @param kB the size of the super sampling area for blurriness
     * @return this object after replacing kB
     */
    public Material setkB(double kB) {
        this.kB = kB;
        return this;
    }

    /**
     * the Glossiness of the matter
     * @param kG the size of the super sampling area for glossiness
     * @return this object after replacing kG
     */
    public Material setkG(double kG) {
        this.kG = kG;
        return this;
    }

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

    public double getkT() {
        return kT;
    }

    public double getkR() {
        return kR;
    }

    public int getnShininess() {
        return nShininess;
    }

    /**
     * sets the kT
     * @param kT the new kT
     * @return the material
     */
    public Material setkT(double kT) {
        this.kT = kT;
        return this;
    }

    /**
     * sets the kR
     * @param kR the new kR
     * @return the material
     */
    public Material setkR(double kR) {
        this.kR = kR;
        return this;
    }
}
