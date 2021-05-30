package renderer;

import elements.Camera;
import primitives.Color;
import primitives.Ray;
import scene.Scene;

import java.util.List;
import java.util.MissingResourceException;

public class Render {

    RayTracerBase rayTracer;
    ImageWriter writer;
    Camera camera;

    /**
     * what features  we want to use in the picture
     */
    boolean useDOF = false;
    boolean useGlossySurfaces = false;


    //***************setters***************************

    public Render setRayTracer(RayTracerBase rayTracer) {
        this.rayTracer = rayTracer;
        return this;
    }

    public Render setWriter(ImageWriter writer) {
        this.writer = writer;
        return this;
    }


    public Render setCamera(Camera camera) {
        this.camera = camera;
        return this;
    }

    /**
     * @param useDOF whether we want to calculate focus and depth of field
     * @return this according to builder pattern
     */
    public Render setUseDOF(boolean useDOF) {
        this.useDOF = useDOF;
        return this;
    }

    /**
     * @param useGlossySurfaces whether we want to calculate glossy surfaces
     * @return this according to builder pattern
     */
    public Render setUseGlossySurfaces(boolean useGlossySurfaces) {
        this.useGlossySurfaces = useGlossySurfaces;
        return this;
    }

    //*********************************88
    public void renderImage() {
        if (camera == null)
            throw new MissingResourceException(camera.getClass().getName(), "missing resource", "1");

        if (writer == null)
            throw new MissingResourceException(writer.getClass().getName(), "missing resource", "3");
        if (rayTracer == null)
            throw new MissingResourceException(rayTracer.getClass().getName(), "missing resource", "4");

        //camera.setViewPlaneSize(writer.getNx(),writer.getNy());
        int Ny = writer.getNy();
        int Nx = writer.getNx();
        for (int i = 0; i < Nx; i++) {
            for (int j = 0; j < Ny; j++) {
                Color color = Color.BLACK;
                if (useDOF) {
                    List<Ray> rays = camera.calcApertureRays(Nx, Ny, i, j);
                    for (Ray ray : rays) {
                        color.add(rayTracer.traceRay(ray));
                    }
                    color.reduce(rays.size());
                } else
                    color = rayTracer.traceRay(camera.constructRayThroughPixel(Nx, Ny, i, j));
                writer.writePixel(i, j, color);
            }
        }
    }


    public void printGrid(int interval, Color color) {
        if (writer == null)
            throw new MissingResourceException(writer.getClass().getName(), "missing resource", "1");

        for (int i = 0; i < writer.getNx() / 50; i++) {
            for (int j = 0; j < writer.getNy(); j++) {
                writer.writePixel(i * 50, j, new Color(100, 100, 20));
            }
        }
        for (int i = 0; i < writer.getNy() / 50; i++) {
            for (int j = 0; j < writer.getNx(); j++) {
                writer.writePixel(j, i * 50, new Color(100, 100, 20));
            }
        }
    }

    public void writeToImage() {
        if (writer == null)
            throw new MissingResourceException(writer.getClass().getName(), "missing resource", "1");
        writer.writeToImage();
    }
}
