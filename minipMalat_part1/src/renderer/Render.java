package renderer;

import elements.Camera;
import primitives.Color;
import scene.Scene;

import java.util.MissingResourceException;

public class Render {

    RayTracerBase rayTracer;
    ImageWriter writer;
    Scene scene;
    Camera camera;
    //***************setters***************************

    public Render setRayTracer(RayTracerBase rayTracer) {
        this.rayTracer = rayTracer;
        return this;
    }

    public Render setWriter(ImageWriter writer) {
        this.writer = writer;
        return this;
    }

    public Render setScene(Scene scene) {
        this.scene = scene;
        return this;
    }

    public Render setCamera(Camera camera) {
        this.camera = camera;
        return this;
    }

    //*********************************88
    public void renderImage() {
        if (camera == null)
            throw new MissingResourceException(camera.getClass().getName(), "missing resource", "1");
        if (scene == null)
            throw new MissingResourceException(scene.getClass().getName(), "missing resource", "2");
        if (writer == null)
            throw new MissingResourceException(writer.getClass().getName(), "missing resource", "3");
        if (rayTracer == null)
            throw new MissingResourceException(rayTracer.getClass().getName(), "missing resource", "4");

        camera.setViewPlaneSize(writer.getNx(),writer.getNy());
        for (int i = 0; i < writer.getNx(); i++) {
            for (int j = 0; j < writer.getNy(); j++) {
                Color color = rayTracer.traceRay(camera.constructRayThroughPixel(writer.getNx(), writer.getNy(), i, j));
                writer.writePixel(i,j,color);
            }
        }
    }


    public void printGrid(int interval , Color color){
        if(writer==null)
            throw new MissingResourceException(writer.getClass().getName(),"missing resource","1");

        for (int i = 0; i <writer.getNx()/50; i++) {
            for (int j = 0; j < writer.getNy(); j++) {
                writer.writePixel(i*50,j,new Color(100,100,20));
            }
        }
        for (int i = 0; i < writer.getNy()/50; i++) {
            for (int j = 0; j < writer.getNx(); j++) {
                writer.writePixel(j,i*50,new Color(100,100,20));
            }
        }
    }

    public void writeToImage(){
        if(writer==null)
            throw new MissingResourceException(writer.getClass().getName(),"missing resource","1");
        writer.writeToImage();
    }
}
