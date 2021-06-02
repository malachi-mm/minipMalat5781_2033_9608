package renderer;


import primitives.Color;
import primitives.Ray;
import scene.Scene;

/**
 *
 */
public abstract class RayTracerBase {
    protected Scene scene;

    public RayTracerBase(Scene scene) {
        this.scene = scene;
    }
    public abstract Color traceRay(Ray ray);

    protected boolean useGlossySurfaces=false;
        protected int sizeSuperSamplingPart2=0;
    /**
     * @param useGlossySurfaces whether we want to calculate glossy surfaces
     * @return this according to builder pattern
     */
    public void setUseGlossySurfaces(boolean useGlossySurfaces) {
        this.useGlossySurfaces = useGlossySurfaces;
    }

    public void setSizeSuperSamplingPart2(int sizeSuperSamplingPart2) {
        this.sizeSuperSamplingPart2 = sizeSuperSamplingPart2;
    }
}
