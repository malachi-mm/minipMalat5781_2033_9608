package unittests;

import elements.AmbientLight;
import elements.Camera;
import elements.DirectionalLight;
import elements.SpotLight;
import geometries.Plane;
import geometries.Sphere;
import org.junit.jupiter.api.Test;
import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;
import renderer.ImageWriter;
import renderer.RayTracerBasic;
import renderer.Render;
import scene.Scene;


public class Project1Tests {




    @Test
    public void DepthOfFieldTest() {
        Camera camera = new Camera(new Point3D(0, 0, 100), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setViewPlaneSize(200, 200).setDistance(100); //
        Scene scene=new Scene("DOF scene");
        scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.1));

        scene.geometries.add( //
                new Sphere(new Point3D(-60,-70,-40),40)
                        .setEmission(new Color(200, 100, 50)) //
                        .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20).setkT(0.1)),
                new Sphere(new Point3D(-30,-55,-80),40)
                        .setEmission(new Color(200, 100, 50)) //
                        .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20).setkT(0.1)),
                new Sphere(new Point3D(0,-40,-120),40)
                        .setEmission(new Color(200, 100, 50)) //
                        .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20).setkT(0.1)),
                new Sphere(new Point3D(30,-25,-160),40)
                        .setEmission(new Color(200, 100, 50)) //
                        .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20).setkT(0.1)),
                new Sphere(new Point3D(60,-10,-200),40)
                        .setEmission(new Color(200, 100, 50)) //
                        .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20).setkT(0.1)),

                new Sphere(new Point3D(-60,-10,-40),20)
                        .setEmission(new Color(0, 100, 50)) //
                        .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20).setkT(0.1)),
                new Sphere(new Point3D(-30,15,-80),20)
                        .setEmission(new Color(0, 100, 50)) //
                        .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20).setkT(0.1)),
                new Sphere(new Point3D(0,40,-120),20)
                        .setEmission(new Color(0, 100, 50)) //
                        .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20).setkT(0.1)),
                new Sphere(new Point3D(30,65,-160),20)
                        .setEmission(new Color(0, 100, 50)) //
                        .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20).setkT(0.1)),
                new Sphere(new Point3D(60,90,-200),20)
                        .setEmission(new Color(0, 100, 50)) //
                        .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20).setkT(0.1)),

                new Plane(new Point3D(0,-10,-500),new Point3D(500,-10,-500),new Point3D(200,-110,-50))
                        .setEmission(new Color(10, 10, 80)) //
                        .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20).setkR(0.7))
        );

        scene.lights.add(new SpotLight(new Color(400, 0, 0), new Point3D(80, 0, 0), new Vector(0, 0, -1)) //
                .setkL(4E-5).setkQ(2E-7));
        scene.lights.add(new SpotLight(new Color(0, 400, 0), new Point3D(-80, 50, 0), new Vector(0, 0, -1)) //
                .setkL(4E-5).setkQ(2E-7));
        scene.lights.add(new SpotLight(new Color(400, 400, 0), new Point3D(60, 80, 0), new Vector(0, 0, -1)) //
                .setkL(4E-5).setkQ(2E-7));
        scene.lights.add(new SpotLight(new Color(400, 200, 200), new Point3D(30, -120, 0), new Vector(0, 1, -1)) //
                .setkL(4E-5).setkQ(2E-7));
        scene.lights.add(new DirectionalLight(new Color(0, 40, 400), new Vector(0, 0, 1)));

        ImageWriter imageWriter = new ImageWriter("DepthOfField", 600, 600);
        Render render = new Render() //
                .setWriter(imageWriter) //
                .setCamera(camera) //
                .setRayTracer(new RayTracerBasic(scene)).setUseDOF(true)
                .setApertureDistance(100).setApertureSize(10).setSizeSuperSamplingDOF(81);

        render.renderImage();
        render.writeToImage();

        imageWriter = new ImageWriter("DepthOfField2", 600, 600);
        render.setWriter(imageWriter).setUseDOF(false);

        render.renderImage();
        render.writeToImage();
    }


    @Test
    public void GlossyDiffusiveTest() {
        Camera camera = new Camera(new Point3D(0, 0, 100), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setViewPlaneSize(200, 200).setDistance(100); //
        Scene scene=new Scene("Glossy surfaces/ Diffuse Glass scene");
        scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.1));

        scene.geometries.add( //
               // new Sphere(new Point3D(-60,-70,-40),40)
               //         .setEmission(new Color(200, 100, 50)) //
               //         .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20).setkT(1)),

                new Plane(new Point3D(0,-10,-500),new Point3D(500,-10,-500),new Point3D(200,-110,-50))
                        .setEmission(new Color(10, 10, 80)) //
                        .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20).setkT(0.7))
        );

        scene.lights.add(new SpotLight(new Color(400, 0, 0), new Point3D(80, 0, 0), new Vector(0, 0, -1)) //
                .setkL(4E-5).setkQ(2E-7));
        scene.lights.add(new SpotLight(new Color(0, 400, 0), new Point3D(-80, 50, 0), new Vector(0, 0, -1)) //
                .setkL(4E-5).setkQ(2E-7));
        scene.lights.add(new SpotLight(new Color(400, 400, 0), new Point3D(60, 80, 0), new Vector(0, 0, -1)) //
                .setkL(4E-5).setkQ(2E-7));
        scene.lights.add(new SpotLight(new Color(400, 200, 200), new Point3D(30, -120, 0), new Vector(0, 1, -1)) //
                .setkL(4E-5).setkQ(2E-7));
        scene.lights.add(new DirectionalLight(new Color(0, 40, 400), new Vector(0, 0, 1)));

        ImageWriter imageWriter = new ImageWriter("GlossySurfacesDiffuseGlass", 600, 600);
        Render render = new Render() //
                .setWriter(imageWriter) //
                .setCamera(camera) //
                .setRayTracer(new RayTracerBasic(scene)).setUseGlossySurfaces(true);

        render.renderImage();
        render.writeToImage();

        imageWriter = new ImageWriter("GlossySurfacesDiffuseGlass2", 600, 600);
        render.setWriter(imageWriter).setUseGlossySurfaces(false);

        render.renderImage();
        render.writeToImage();
    }
}
