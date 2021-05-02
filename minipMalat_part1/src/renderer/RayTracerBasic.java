package renderer;

import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import scene.Scene;

import java.util.List;

/**
 * the basic  ray tracer
 *
 * @author Achiya Danziger
 * @author Malachi Mahpod
 */
public class  RayTracerBasic extends RayTracerBase {
    /**
     * the constructor for our ray tracer gets the scene
     * @param scene our scene
     */
    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    /**
     * calculates the color of the point
     * @param point the point we want to get it's color
     * @return the color of the point
     */
    private Color calcColor(Point3D point){
        return scene.ambientLight.getIntensity();
    }

    @Override
    public Color traceRay(Ray ray) {
        Point3D point = ray.findClosestPoint(scene.geometries.findIntersections(ray));

        if(point == null)
            return scene.background;

        return calcColor(point);
    }


}
