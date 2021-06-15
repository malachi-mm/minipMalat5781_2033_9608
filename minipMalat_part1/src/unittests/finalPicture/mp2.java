package unittests.finalPicture;

import elements.*;
import geometries.*;
import org.junit.jupiter.api.Test;
import primitives.*;
import renderer.ImageWriter;
import renderer.RayTracerBasic;
import renderer.Render;
import scene.Scene;

/**
 * the final picture for MP2
 */
public class mp2 {

    Geometries cube(Point3D startPoint, Vector x, Vector y, Vector z, Color color) {
        Geometries polys = new Geometries();

        polys.add(
                new Polygon(startPoint,
                        startPoint.add(x),
                        startPoint.add(x).add(y),
                        startPoint.add(y))
                        .setEmission(color) //
                        .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),


                new Polygon(startPoint,
                        startPoint.add(y),
                        startPoint.add(y).add(z),
                        startPoint.add(z))
                        .setEmission(color) //
                        .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),


                new Polygon(startPoint,
                        startPoint.add(z),
                        startPoint.add(z).add(x),
                        startPoint.add(x))
                        .setEmission(color) //
                        .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),

                new Polygon(startPoint.add(z),
                        startPoint.add(z).add(x),
                        startPoint.add(z).add(x).add(y),
                        startPoint.add(z).add(y))
                        .setEmission(color) //
                        .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),


                new Polygon(startPoint.add(x),
                        startPoint.add(x).add(y),
                        startPoint.add(x).add(y).add(z),
                        startPoint.add(x).add(z))
                        .setEmission(color) //
                        .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),


                new Polygon(startPoint.add(y),
                        startPoint.add(y).add(z),
                        startPoint.add(y).add(z).add(x),
                        startPoint.add(y).add(x))
                        .setEmission(color) //
                        .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20))
        );

        return polys;
    }

    @Test
    public void modelTest() {
        Camera camera = new Camera(new Point3D(0, 100, -100), new Vector(0, 0, 1), new Vector(0, 1, 0)) //
                .setViewPlaneSize(200, 200).setDistance(100); //
        //Camera camera = new Camera(new Point3D(377, 90, 0), new Vector(0, -0.5, -1), new Vector(0, 1, -0.5)) //
        //        .setViewPlaneSize(200, 200).setDistance(100); //
        Scene scene = new Scene("scene");
        scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.1));
        double d = 8;

        scene.geometries.add(
                new Polygon(new Point3D(100, 200, 100),
                        new Point3D(100, 0, 100),
                        new Point3D(100, 0, 0),
                        new Point3D(100, 200, 0))
                        .setEmission(new Color(150, 120, 90)) //
                        .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),
                new Polygon(new Point3D(-100, 200, 100),
                        new Point3D(-100, 0, 100),
                        new Point3D(100, 0, 100),
                        new Point3D(100, 200, 100))
                        .setEmission(new Color(150, 120, 90)) //
                        .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),
                new Polygon(new Point3D(-100, 0, 100),
                        new Point3D(-100, 0, 0),
                        new Point3D(100, 0, 0),
                        new Point3D(100, 0, 100))
                        .setEmission(new Color(150, 120, 90)) //
                        .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20))
        );


        scene.lights.add(new SpotLight(new Color(800, 400, 400),
                new Point3D(10, 60, -30), new Vector(0, -1, 1)) //
                .setkL(4E-5).setkQ(2E-7));
        //scene.lights.add(new DirectionalLight(new Color(500, 500, 500), new Vector(-1, -1, 1)));
        //scene.lights.add(new PointLight(new Color(255, 255, 255), new Point3D(377, 5, -170)));

        ImageWriter imageWriter = new ImageWriter("mp1", 1000, 1000);
        Render render = new Render() //
                .setWriter(imageWriter) //
                .setCamera(camera) //
                .setRayTracer(new RayTracerBasic(scene)).setUseDOF(false).setDebugPrint()
                .setMultithreading(3)
                .setUseBounding(true)
                .buildHierarchy();

        render.renderImage();
        render.writeToImage();
    }


}
