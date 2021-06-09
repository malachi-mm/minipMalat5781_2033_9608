package renderer;

import elements.LightSource;
import elements.SuperSampling;
import geometries.Intersectable;
import geometries.Intersectable.GeoPoint;
import primitives.*;
import scene.Scene;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static primitives.Util.alignZero;

/**
 * the basic  ray tracer
 *
 * @author Achiya Danziger
 * @author Malachi Mahpod
 */
public class RayTracerBasic extends RayTracerBase {
    /**
     * helps us calculate shading and prevents the slight miscalculation to affect the function
     */
    private static final double DELTA = 0.1;


    private static final int MAX_CALC_COLOR_LEVEL = 10;
    private static final double MIN_CALC_COLOR_K = 0.001;
    private static final double INITIAL_K = 1.0;



    /**
     * the constructor for our ray tracer gets the scene
     *
     * @param scene our scene
     */

    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    /**
     * here we decide the color of a certain point
     *
     * @param ray the central ray that passes through the pixel
     * @return the color of the point
     */
    @Override
    public Color traceRay(Ray ray) {
        GeoPoint point = findClosestIntersection(ray);

        if (point == null)
            return scene.background;

        return calcColor(point, ray);
    }

    /**
     * calculates the color of the point
     *
     * @param geopoint the GeoPoint we want to get the  color of
     * @param ray      the ray from the camera to the point
     * @return the color of the point
     */
    private Color calcColor(GeoPoint geopoint, Ray ray) {
        return calcColor(geopoint, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K).
                add(scene.ambientLight.getIntensity());
    }


    /**
     * a recursive function that calculates color of the point
     * not including ambientLight
     *
     * @param intersection
     * @param ray
     * @param level
     * @param k
     * @return
     */
    private Color calcColor(GeoPoint intersection, Ray ray, int level, double k) {
        Color color = intersection.geometry.getEmission();
        color = color.add(calcLocalEffects(intersection, ray, k));
        return 1 == level ? color : color.add(calcGlobalEffects(intersection, ray.getDir(), level, k));

    }


    private Color calcGlobalEffects(GeoPoint gp, Vector l, int level, double k) {
        Color color = Color.BLACK;
        Vector n = gp.geometry.getNormal(gp.point);
        Material material = gp.geometry.getMaterial();
        double kkr = k * material.kR;
        if (kkr > MIN_CALC_COLOR_K)
            color = calcReflectedColor(l, n, gp.point, level, material.kR, material.kG, kkr);
        double kkt = k * material.kT;
        if (kkt > MIN_CALC_COLOR_K)
            color = color.add(calcRefractedColor(l, n, gp.point, level, material.kT, material.kB, kkt));
        return color;
    }

    private Color calcGlobalEffect(Ray ray, int level, double kx, double kkx) {
        GeoPoint gp = findClosestIntersection(ray);
        return (gp == null ? scene.background : calcColor(gp, ray, level - 1, kkx)).scale(kx);
    }

    /**
     * calculates the local effects of the light
     * diffusion and
     *
     * @param intersection the geopoint we want to calculate the color of
     * @param ray          the ray from the camera to the geopoint
     * @return the light that the color effects add according to phong's model
     */
    private Color calcLocalEffects(GeoPoint intersection, Ray ray, double k) {
        Vector v = ray.getDir();
        Vector n = intersection.geometry.getNormal(intersection.point);
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0) return Color.BLACK;
        Material material = intersection.geometry.getMaterial();
        int nShininess = material.getnShininess();
        double kd = material.getkD(), ks = material.getkS();
        Color color = Color.BLACK;

        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(intersection.point);
            double nl = alignZero(n.dotProduct(l));

            if (nl * nv > 0) { // sign(nl) == sing(nv)
                double ktr = transparency(lightSource, l, n, intersection);
                if (ktr * k > MIN_CALC_COLOR_K) {
                    Color lightIntensity = lightSource.getIntensity(intersection.point).scale(ktr);
                    color = color.add(calcDiffusive(kd, l, n, lightIntensity),
                            calcSpecular(ks, l, n, v, nShininess, lightIntensity));
                }
            }
        }
        return color;
    }


    private Color calcSpecular(double ks, Vector l, Vector n, Vector v, int nShininess, Color lightIntensity) {
        Vector r = l.add(n.scale(-2 * l.dotProduct(n))).normalize();
        double iS = ks * Math.pow(Math.max(0, r.dotProduct(v.scale(-1))), nShininess);
        return lightIntensity.scale(iS);
    }

    private Color calcDiffusive(double kd, Vector l, Vector n, Color lightIntensity) {
        return lightIntensity.scale(kd * Math.abs(n.dotProduct(l)));
    }

    /**
     * calculates the refracted light from the point
     * @param l the direction of the ray
     * @param n the normal from the point
     * @param point the point we want to calculate the color of
     * @param level the level of the recursion
     * @param kT the level of transperency of the matter
     * @param kB  the level of
     * @param kkt another stopping of the recursion
     * @return the color we get from what behind teh color
     */
    private Color calcRefractedColor(Vector l, Vector n, Point3D point,int level, double kT,double kB, double kkt){
        Color color=Color.BLACK;
        List<Ray> rays = constructRefractedRays(l, n, point,kB);
        double dp=alignZero(n.dotProduct(l));
        int i= 0;
        for (Ray ray : rays) {
            if (alignZero(n.dotProduct(ray.getDir()))*dp>0) {//the vector is in the right side if the dot product between him and n is smaller than 0
                color = color.add(calcGlobalEffect(ray, level, kT, kkt));
                i++;
            }

        }
        //if (i!=0)
            color = color.reduce(i);


        return color;

    }
    /**
     * Calculates the beam of  refracted rays from the point
     *
     * @param l     the direction of the light
     * @param n     the normal from the point
     * @param point the geoPoint
     * @param kB
     * @return the refraction ray
     */
    private List<Ray> constructRefractedRays(Vector l, Vector n, Point3D point,double kB) {
        List<Ray> list =new ArrayList<Ray>();
        list.add(new Ray(point, l, n));
        if(useGlossySurfaces&&kB>0){
            List<Point3D> points =SuperSampling.superSampling(point.add(l.scale(10)),l, sizeSuperSamplingPart2, kB);

            List<Ray> rays = new ArrayList<Ray>();
            for (Point3D newPoint : points) {
                list.add(new Ray(point, newPoint.subtract(point)));
            }
        }
        return list;
    }


    /**
     * calculates the refracted light from the point
     * @param l the direction of the ray
     * @param n the normal from the point
     * @param point the point we want to calculate the color of
     * @param level the level of the recursion
     * @param kR the level of reflectivity of the matter
     * @param kkr another stopping of the recursion
     * @return the color we get from what behind teh color
     */
    private Color calcReflectedColor(Vector l, Vector n, Point3D point,int level, double kR,double kG, double kkr){
        Color color=Color.BLACK;
        List<Ray> rays = constructReflectedRays(l, n, point,kG);
        double dp=alignZero(n.dotProduct(constructReflectedRay(l,n,point).getDir()));
        int i= 0;
        for (Ray ray : rays) {
            if (alignZero(n.dotProduct(ray.getDir()))*dp>0) {//the vector is in the right side if the dot product between him and n is smaller than 0
                color = color.add(calcGlobalEffect(ray, level, kR, kkr));
                i++;
            }
        }
        //if (i!=0)
        color = color.reduce(i);
        return color;

    }


    /**
     * Calculates the beam of  reflected rays from the point
     *
     * @param l     the direction of the light
     * @param n     the normal from the point
     * @param point the geoPoint
     * @return the refraction ray
     */
    private List<Ray> constructReflectedRays(Vector l, Vector n, Point3D point,double kG) {
        List<Ray> list =new ArrayList<Ray>() ;
        Ray ray=constructReflectedRay(l,n,point);
        list.add(ray);//adding the new point
        if(useGlossySurfaces&&kG>0){
            List<Point3D> points = SuperSampling.superSampling(ray.getPoint(10), ray.getDir(), sizeSuperSamplingPart2, kG);

            List<Ray> rays = new ArrayList<Ray>();
            for (Point3D newpoint : points) {
                list.add(new Ray(point, newpoint.subtract(point)));
            }
        }
        return list;

    }
    /**
     * Calculates the reflected ray from the point
     *
     * @param l     the direction of the light
     * @param n     the normal from the point
     * @param point the geoPoint
     * @return the reflection ray
     */
    private Ray constructReflectedRay(Vector l, Vector n, Point3D point){
        return new Ray(point, l.add(n.scale(-2 * l.dotProduct(n))).normalize(), n.scale(-1));
    }






    /**
     * checks that the point is unshaded by another body
     *
     * @param l  the ray from the lightSource to the point
     * @param n  the normal from the point
     * @param gp the point
     * @return true if teh GeoPoint is shadowed and false if not
     */
    private boolean unshaded(Vector l, Vector n, GeoPoint gp, LightSource lightSource) {
        Vector lightDirection = l.scale(-1);
        // from point to light source
        /*Vector delta = n.scale(n.dotProduct(lightDirection) > 0 ? DELTA : -DELTA);
        Point3D point = gp.point.add(delta);*/
        Ray lightRay = new Ray(gp.point, lightDirection, n);
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay, lightSource.getDistance(gp.point));
        if (intersections != null)
            for (GeoPoint newGP : intersections)
                if (newGP.geometry.getMaterial().kT == 0)
                    return false;
        return true;
    }

    /**
     * returns how much the body is shaded
     * 1-not shaded at all
     * 0-fully shaded
     *
     * @param ls the light source
     * @param l  the vector from the the geopoint to the light source
     * @param n  the normal of the geometry at this point
     * @param gp the point we are checking for
     * @return see the start
     */
    private double transparency(LightSource ls, Vector l, Vector n, GeoPoint gp) {
        Vector lightDirection = l.scale(-1);
        // from point to light source
        Ray lightRay = new Ray(gp.point, lightDirection, n);
        double lightDistance = ls.getDistance(gp.point);
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay, ls.getDistance(gp.point));
        if (intersections == null)
            return 1.0;

        double ktr = 1.0;
        for (GeoPoint gpFor : intersections) {
            if (alignZero(gpFor.point.distance(gpFor.point) - lightDistance) <= 0) {
                ktr *= gpFor.geometry.getMaterial().getkT();
                if (ktr < MIN_CALC_COLOR_K) return 0.0;
            }
        }
        return ktr;
    }

}
