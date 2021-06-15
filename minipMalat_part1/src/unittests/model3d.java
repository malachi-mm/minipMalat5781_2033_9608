package unittests;

import elements.*;
import geometries.*;
import org.junit.jupiter.api.Test;
import primitives.*;
import renderer.ImageWriter;
import renderer.RayTracerBasic;
import renderer.Render;
import scene.Scene;

import java.util.ArrayList;
import java.util.List;

public class model3d {


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
        Camera camera = new Camera(new Point3D(450, 140, -60), new Vector(-1, -1, -1), new Vector(-0.5, 1, -0.5)) //
                .setViewPlaneSize(200, 200).setDistance(100); //
        //Camera camera = new Camera(new Point3D(377, 90, 0), new Vector(0, -0.5, -1), new Vector(0, 1, -0.5)) //
        //        .setViewPlaneSize(200, 200).setDistance(100); //
        Scene scene = new Scene("scene");
        scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.1));
        double d = 8;

        scene.geometries.add(
                //hechal
               new Polygon(new Point3D(412.5, 6, -376),
                        new Point3D(342.5, 6, -376),
                        new Point3D(342.5, 6, -292),
                        new Point3D(412.5, 6, -292))
                        .setEmission(new Color(20, 20, 150)) //
                        .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),
                cube(new Point3D(412.5, 6, -376), new Vector(-70, 0, 0), new Vector(0, 100, 0), new Vector(0, 0, 84), new Color(150, 120, 90)),


                //haulam
                new Polygon(new Point3D(427.5, 6, -292),
                        new Point3D(327.5, 6, -292),
                        new Point3D(327.5, 6, -276),
                        new Point3D(427.5, 6, -276))
                        .setEmission(new Color(20, 20, 150)) //
                        .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),
                cube(new Point3D(427.5, 6, -292), new Vector(-100, 0, 0), new Vector(0, 100, 0), new Vector(0, 0, 16), new Color(150, 120, 90)),

                //
                cube(new Point3D(377.5, 6, -254), new Vector(-32, 0, 0), new Vector(0, 9, 0), new Vector(0, 0, 32), new Color(150, 120, 90)),
                new Polygon(new Point3D(345.5, 15, -246),
                        new Point3D(345.5, 15, -230),
                        new Point3D(313.5, 6, -230),
                        new Point3D(313.5, 6, -246))
                        .setEmission(new Color(150, 120, 90)) //
                        .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),
                new Polygon(new Point3D(345.5, 15, -230),
                        new Point3D(313.5, 6, -230),
                        new Point3D(345.5, 6, -230))
                        .setEmission(new Color(150, 120, 90)) //
                        .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),
                new Polygon(new Point3D(345.5, 15, -246),
                        new Point3D(313.5, 6, -246),
                        new Point3D(345.5, 6, -246))
                        .setEmission(new Color(150, 120, 90)) //
                        .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),



                //israel
                new Polygon(new Point3D(445, 3.5, -211),
                        new Point3D(310, 3.5, -211),
                        new Point3D(310, 3.5, -200),
                        new Point3D(445, 3.5, -200))
                        .setEmission(new Color(150, 120, 90)) //
                        .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),
                //hazara
                new Polygon(new Point3D(445, 6, -387),
                        new Point3D(310, 6, -387),
                        new Point3D(310, 6, -211),
                        new Point3D(445, 6, -211))
                        .setEmission(new Color(150, 120, 90)) //
                        .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),

                cube(new Point3D(450, -4, -387), new Vector(-145, 0, 0), new Vector(0, 20, 0), new Vector(0, 0, -5), new Color(150, 120, 90)),
                cube(new Point3D(445, -4, -387), new Vector(5, 0, 0), new Vector(0, 20, 0), new Vector(0, 0, 187), new Color(150, 120, 90)),
                cube(new Point3D(305, -4, -387), new Vector(5, 0, 0), new Vector(0, 20, 0), new Vector(0, 0, 187), new Color(150, 120, 90)),


                //ezrat nashim
                new Cylinder(new Ray(new Point3D(377.5,-4,-200),new Vector(0,1,0)),5+d,7.5)
                        .setEmission(new Color(150, 120, 90)) //
                        .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),
                new Cylinder(new Ray(new Point3D(377.5,-4,-200),new Vector(0,1,0)),5.5+d,7)
                        .setEmission(new Color(150, 120, 90)) //
                        .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),
                new Cylinder(new Ray(new Point3D(377.5,-4,-200),new Vector(0,1,0)),6+d,6.5)
                        .setEmission(new Color(150, 120, 90)) //
                        .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),
                new Cylinder(new Ray(new Point3D(377.5,-4,-200),new Vector(0,1,0)),6.5+d,6)
                        .setEmission(new Color(150, 120, 90)) //
                        .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),
                new Cylinder(new Ray(new Point3D(377.5,-4,-200),new Vector(0,1,0)),7+d,5.5)
                        .setEmission(new Color(150, 120, 90)) //
                        .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),
                new Cylinder(new Ray(new Point3D(377.5,-4,-200),new Vector(0,1,0)),7.5+d,5)
                        .setEmission(new Color(150, 120, 90)) //
                        .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),
                new Cylinder(new Ray(new Point3D(377.5,-4,-200),new Vector(0,1,0)),8+d,4.5)
                        .setEmission(new Color(150, 120, 90)) //
                        .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),
                new Cylinder(new Ray(new Point3D(377.5,-4,-200),new Vector(0,1,0)),8.5+d,4)
                        .setEmission(new Color(150, 120, 90)) //
                        .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),
                new Cylinder(new Ray(new Point3D(377.5,-4,-200),new Vector(0,1,0)),9+d,3.5)
                        .setEmission( new Color(150, 120, 90)) //
                        .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),
                new Cylinder(new Ray(new Point3D(377.5,-4,-200),new Vector(0,1,0)),9.5+d,3)
                        .setEmission( new Color(150, 120, 90)) //
                        .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),
                new Cylinder(new Ray(new Point3D(377.5,-4,-200),new Vector(0,1,0)),10+d,2.5)
                        .setEmission(new Color(150, 120, 90)) //
                        .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),
                new Cylinder(new Ray(new Point3D(377.5,-4,-200),new Vector(0,1,0)),10.5+d,2)
                        .setEmission(new Color(150, 120, 90)) //
                        .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),
                new Cylinder(new Ray(new Point3D(377.5,-4,-200),new Vector(0,1,0)),11+d,1.5)
                        .setEmission(new Color(150, 120, 90)) //
                        .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),
                new Cylinder(new Ray(new Point3D(377.5,-4,-200),new Vector(0,1,0)),11.5+d,1)
                        .setEmission(new Color(150, 120, 90)) //
                        .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),
                new Cylinder(new Ray(new Point3D(377.5,-4,-200),new Vector(0,1,0)),12+d,0.5)
                        .setEmission(new Color(150, 120, 90)) //
                        .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),

                /*new Polygon(new Point3D(445, -4, -195),
                        new Point3D(310, -4, -195),
                        new Point3D(310, -4, -60),
                        new Point3D(445, -4, -60))
                        .setEmission(new Color(150, 120, 150)) //
                        .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20).setkT(0.1)),*/

                cube(new Point3D(450, -4, -195), new Vector(-140, 0, 0), new Vector(0, 20, 0), new Vector(0, 0, -5), new Color(150, 120, 90)),
                cube(new Point3D(445, -4, -55), new Vector(-140, 0, 0), new Vector(0, 20, 0), new Vector(0, 0, -5), new Color(150, 120, 90)),
                cube(new Point3D(445, -4, -195), new Vector(5, 0, 0), new Vector(0, 20, 0), new Vector(0, 0, 140), new Color(150, 120, 90)),
                cube(new Point3D(305, -4, -200), new Vector(5, 0, 0), new Vector(0, 20, 0), new Vector(0, 0, 140), new Color(150, 120, 90)),

                cube(new Point3D(355, -4, -195), new Vector(-5, 0, 0), new Vector(0, 20, 0), new Vector(0, 0, 40), new Color(150, 120, 90)),
                cube(new Point3D(445, -4, -105), new Vector(-45, 0, 0), new Vector(0, 20, 0), new Vector(0, 0, 5), new Color(150, 120, 90)),

                cube(new Point3D(355, -4, -105), new Vector(-45, 0, 0), new Vector(0, 20, 0), new Vector(0, 0, 5), new Color(150, 120, 90)),
                cube(new Point3D(405, -4, -100), new Vector(-5, 0, 0), new Vector(0, 20, 0), new Vector(0, 0, 40), new Color(150, 120, 90)),


                cube(new Point3D(355, -4, -100), new Vector(-5, 0, 0), new Vector(0, 20, 0), new Vector(0, 0, 40), new Color(150, 120, 90)),

                cube(new Point3D(355, -4, -155), new Vector(-45, 0, 0), new Vector(0, 20, 0), new Vector(0, 0, 5), new Color(150, 120, 90)),

                cube(new Point3D(405, -4, -195), new Vector(-5, 0, 0), new Vector(0, 20, 0), new Vector(0, 0, 40), new Color(150, 120, 90)),

                cube(new Point3D(445, -4, -155), new Vector(-45, 0, 0), new Vector(0, 20, 0), new Vector(0, 0, 5), new Color(150, 120, 90)),




                //hcahuyl

                cube(new Point3D(465, -10, -402), new Vector(-175, 0, 0), new Vector(0, 0.5, 0), new Vector(0, 0, 362), new Color(150, 120, 90)),
                cube(new Point3D(464.5, -9.5, -401.5), new Vector(-174, 0, 0), new Vector(0, 0.5, 0), new Vector(0, 0, 361), new Color(150, 120, 90)),
                cube(new Point3D(464, -9, -401), new Vector(-173, 0, 0), new Vector(0, 0.5, 0), new Vector(0, 0, 360), new Color(150, 120, 90)),
                cube(new Point3D(463.5, -8.5, -400.5), new Vector(-172, 0, 0), new Vector(0, 0.5, 0), new Vector(0, 0, 359), new Color(150, 120, 90)),
                cube(new Point3D(463, -8, -400), new Vector(-171, 0, 0), new Vector(0, 0.5, 0), new Vector(0, 0, 358), new Color(150, 120, 90)),
                cube(new Point3D(462.5, -7.5, -399.5), new Vector(-170, 0, 0), new Vector(0, 0.5, 0), new Vector(0, 0, 357), new Color(150, 120, 90)),
                cube(new Point3D(462, -7, -399), new Vector(-169, 0, 0), new Vector(0, 0.5, 0), new Vector(0, 0, 356), new Color(150, 120, 90)),
                cube(new Point3D(461.5, -6.5, -398.5), new Vector(-168, 0, 0), new Vector(0, 0.5, 0), new Vector(0, 0, 355), new Color(150, 120, 90)),
                cube(new Point3D(461, -6, -398), new Vector(-167, 0, 0), new Vector(0, 0.5, 0), new Vector(0, 0, 354), new Color(150, 120, 90)),
                cube(new Point3D(460.5, -5.5, -397.5), new Vector(-166, 0, 0), new Vector(0, 0.5, 0), new Vector(0, 0, 353), new Color(150, 120, 90)),
                cube(new Point3D(460, -5, -397), new Vector(-165, 0, 0), new Vector(0, 0.5, 0), new Vector(0, 0, 352), new Color(150, 120, 90)),
                cube(new Point3D(459.5, -4.5, -396.5), new Vector(-164, 0, 0), new Vector(0, 0.5, 0), new Vector(0, 0, 351), new Color(150, 120, 90)),

                //har habayit
                cube(new Point3D(0, -10, 0), new Vector(500, 0, 0), new Vector(0, -1, 0), new Vector(0, 0, -500), new Color(150, 125, 1))

               // new Plane(new Point3D(0, -10, -500), new Point3D(500, -10, -500), new Point3D(200, -10, -50))
                //        .setEmission(new Color(10, 10, 10)) //
               //         .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20))
        );

        scene.lights.add(new SpotLight(new Color(400, 0, 0), new Point3D(500, 50, 100), new Vector(0, 0, -1)) //
                .setkL(4E-5).setkQ(2E-7));
        scene.lights.add(new SpotLight(new Color(0, 400, 0), new Point3D(200, 100, 100), new Vector(0, 0, -1)) //
                .setkL(4E-5).setkQ(2E-7));
        scene.lights.add(new SpotLight(new Color(400, 400, 0), new Point3D(400, 130, 100), new Vector(0, 0, -1)) //
                .setkL(4E-5).setkQ(2E-7));
        scene.lights.add(new SpotLight(new Color(400, 200, 200), new Point3D(30, -60, 100), new Vector(0, 1, -1)) //
                .setkL(4E-5).setkQ(2E-7));
        scene.lights.add(new DirectionalLight(new Color(500, 500, 500), new Vector(-1, -1, 1)));
        scene.lights.add(new PointLight(new Color(255, 255, 255), new Point3D(100, 0, 0)));
        //scene.lights.add(new PointLight(new Color(255, 255, 255), new Point3D(377, 5, -170)));

        ImageWriter imageWriter = new ImageWriter("model3d", 1000, 1000);
        Render render = new Render() //
                .setWriter(imageWriter) //
                .setCamera(camera) //
                .setRayTracer(new RayTracerBasic(scene)).setUseDOF(false).setDebugPrint()
                .setUseBounding(true)
                .buildHierarchy();

        render.renderImage();
        render.writeToImage();
    }


}
