package renderer;

import elements.LightSource;
import geometries.Intersectable;
import geometries.Intersectable.GeoPoint;
import primitives.*;
import scene.Scene;

import java.util.List;

import static primitives.Util.alignZero;

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
     * @param intersection the GeoPoint we want to get it's color
     * @return the color of the point
     */
    private Color calcColor(GeoPoint intersection,Ray ray){

            return scene.ambientLight.getIntensity()
                    .add(intersection.geometry.getEmission())
                    // add calculated light contribution from all light sources)
                    .add(calcLocalEffects(intersection, ray));
       /* Color iP=Color.BLACK;
        if (scene.ambientLight!=null)
            iP=scene.ambientLight.getIntensity().add(point.geometry.getEmission());
        Material material=point.geometry.getMaterial();
        Vector n = point.geometry.getNormal(point.point);

        for (LightSource light : scene.lights) {
            Vector l = light.getL(point.point);

            double iD=material.kD*Math.abs(n.dotProduct(l));
             Vector r=l.subtract(n.scale(2*l.dotProduct(n))).normalize();
            double iS=material.kS*Math.pow(Math.max(0,r.dotProduct(ray.getDir().scale(-1))),material.nShininess);

            if(l.dotProduct(n) * n.dotProduct(ray.getDir()) > 0)
                iP = iP.add(light.getIntensity(point.point).scale(iD+iS));
        }
        return  iP;*/
    }



    private Color calcLocalEffects(GeoPoint intersection, Ray ray) {
        Vector v = ray.getDir (); Vector n = intersection.geometry.getNormal(intersection.point);
        double nv = alignZero(n.dotProduct(v)); if (nv == 0) return Color.BLACK;
        Material material = intersection.geometry.getMaterial();
        int nShininess = material.getnShininess();
        double kd = material.getkD(), ks = material.getkS();
        Color color = Color.BLACK;
        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(intersection.point);
            double nl = alignZero(n.dotProduct(l));
            if (nl * nv > 0) { // sign(nl) == sing(nv)
                Color lightIntensity = lightSource.getIntensity(intersection.point);
                color = color.add(calcDiffusive(kd, l, n, lightIntensity),
                        calcSpecular(ks, l, n, v, nShininess, lightIntensity));
            }
        }
        return color;
    }

    private Color calcSpecular(double ks, Vector l, Vector n, Vector v, int nShininess, Color lightIntensity) {
        Vector r=l.add(n.scale(-2*l.dotProduct(n))).normalize();
        double iS=ks*Math.pow(Math.max(0,r.dotProduct(v.scale(-1))),nShininess);
        return lightIntensity.scale(iS);
    }

    private Color calcDiffusive(double kd, Vector l, Vector n, Color lightIntensity) {
        return lightIntensity.scale(kd*Math.abs(n.dotProduct(l)));
    }

    @Override
    public Color traceRay(Ray ray) {
        GeoPoint point = ray.findClosestGeoPoint(scene.geometries.findGeoIntersections(ray));

        if(point == null)
            return scene.background;

        return calcColor(point,ray);
    }


}
