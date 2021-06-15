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
        Camera camera = new Camera(new Point3D(10, 120, -60), new Vector(0, -0.5, 1), new Vector(0, 1, 0.5)) //
                .setViewPlaneSize(200, 200).setDistance(100); //
        //camera.lookAt(new Point3D(75,0,25));
        //Camera camera = new Camera(new Point3D(377, 90, 0), new Vector(0, -0.5, -1), new Vector(0, 1, -0.5)) //
        //        .setViewPlaneSize(200, 200).setDistance(100); //
        Scene scene = new Scene("scene");
        scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.1));
        double t = 50;

        scene.geometries.add(
                new Polygon(new Point3D(-500, -10, 500),
                        new Point3D(-500, -10, -500),
                        new Point3D(500, -10, -500),
                        new Point3D(500, -10, 500))
                        .setEmission(new Color(0, 127, 255)) //
                        .setMaterial(new Material().setkD(0.25).setkS(0.25)),

                new Polygon(new Point3D(200, 200, 200),
                        new Point3D(200, 0, 200),
                        new Point3D(200, 0, -100),
                        new Point3D(200, 200, -100))
                        .setEmission(new Color(150, 120, 90)) //
                        .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),
                new Polygon(new Point3D(-200, 200, -100),
                        new Point3D(-200, 0, -100),
                        new Point3D(200, 0, -100),
                        new Point3D(200, 200, -100))
                        .setEmission(new Color(150, 120, 90)) //
                        .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),
                new Polygon(new Point3D(-200, 200, 200),
                        new Point3D(-200, 0, 200),
                        new Point3D(-200, 0, -100),
                        new Point3D(-200, 200, -100))
                        .setEmission(new Color(150, 120, 90)) //
                        .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),
                new Polygon(new Point3D(-200, 40, 200),
                        new Point3D(-200, 0, 200),
                        new Point3D(200, 0, 200),
                        new Point3D(200, 40, 200))
                        .setEmission(new Color(150, 120, 90)) //
                        .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),
                new Polygon(new Point3D(-200, 200, 200),
                        new Point3D(-200, 160, 200),
                        new Point3D(200, 160, 200),
                        new Point3D(200, 200, 200))
                        .setEmission(new Color(150, 120, 90)) //
                        .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),
                new Polygon(new Point3D(-200, 160, 200),
                        new Point3D(-200, 40, 200),
                        new Point3D(-160, 40, 200),
                        new Point3D(-160, 160, 200))
                        .setEmission(new Color(150, 120, 90)) //
                        .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),
                new Polygon(new Point3D(200, 160, 200),
                        new Point3D(200, 40, 200),
                        new Point3D(160, 40, 200),
                        new Point3D(160, 160, 200))
                        .setEmission(new Color(150, 120, 90)) //
                        .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),
                new Polygon(new Point3D(-20, 160, 200),
                        new Point3D(-20, 40, 200),
                        new Point3D(-160, 40, 200),
                        new Point3D(-160, 160, 200))
                        .setEmission(Color.BLACK) //
                        .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20).setkT(1).setkR(0.25)),
                cube(new Point3D(-160, 40, 200),
                        new Vector(5, 0, 0),
                        new Vector(0, 120, 0),
                        new Vector(0, 0, -5),
                        new Color(139, 69, 19)),
                cube(new Point3D(-160, 40, 200),
                        new Vector(140, 0, 0),
                        new Vector(0, 5, 0),
                        new Vector(0, 0, -10),
                        new Color(139, 69, 19)),
                cube(new Point3D(-25, 40, 200),
                        new Vector(5, 0, 0),
                        new Vector(0, 120, 0),
                        new Vector(0, 0, -5),
                        new Color(139, 69, 19)),
                cube(new Point3D(-97.5, 40, 200),
                        new Vector(5, 0, 0),
                        new Vector(0, 120, 0),
                        new Vector(0, 0, -5),
                        new Color(139, 69, 19)),
                cube(new Point3D(-160, 160, 200),
                        new Vector(140, 0, 0),
                        new Vector(0, 5, 0),
                        new Vector(0, 0, -5),
                        new Color(139, 69, 19)),
                cube(new Point3D(-160, 80, 200),
                        new Vector(140, 0, 0),
                        new Vector(0, 5, 0),
                        new Vector(0, 0, -5),
                        new Color(139, 69, 19)),
                cube(new Point3D(-160, 120, 200),
                        new Vector(140, 0, 0),
                        new Vector(0, 5, 0),
                        new Vector(0, 0, -5),
                        new Color(139, 69, 19)),

                new Polygon(new Point3D(20, 160, 200),
                        new Point3D(20, 40, 200),
                        new Point3D(160, 40, 200),
                        new Point3D(160, 160, 200))
                        .setEmission(Color.BLACK) //
                        .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20).setkT(1).setkR(0.25)),
                cube(new Point3D(160, 40, 200),
                        new Vector(-5, 0, 0),
                        new Vector(0, 120, 0),
                        new Vector(0, 0, -5),
                        new Color(139, 69, 19)),
                cube(new Point3D(160, 40, 200),
                        new Vector(-140, 0, 0),
                        new Vector(0, 5, 0),
                        new Vector(0, 0, -10),
                        new Color(139, 69, 19)),
                cube(new Point3D(25, 40, 200),
                        new Vector(-5, 0, 0),
                        new Vector(0, 120, 0),
                        new Vector(0, 0, -5),
                        new Color(139, 69, 19)),
                cube(new Point3D(97.5, 40, 200),
                        new Vector(-5, 0, 0),
                        new Vector(0, 120, 0),
                        new Vector(0, 0, -5),
                        new Color(139, 69, 19)),
                cube(new Point3D(160, 160, 200),
                        new Vector(-140, 0, 0),
                        new Vector(0, 5, 0),
                        new Vector(0, 0, -5),
                        new Color(139, 69, 19)),
                cube(new Point3D(160, 80, 200),
                        new Vector(-140, 0, 0),
                        new Vector(0, 5, 0),
                        new Vector(0, 0, -5),
                        new Color(139, 69, 19)),
                cube(new Point3D(160, 120, 200),
                        new Vector(-140, 0, 0),
                        new Vector(0, 5, 0),
                        new Vector(0, 0, -5),
                        new Color(139, 69, 19)),

                new Polygon(new Point3D(-20, 160, 200),
                        new Point3D(-20, 40, 200),
                        new Point3D(20, 40, 200),
                        new Point3D(20, 160, 200))
                        .setEmission(new Color(150, 120, 90)) //
                        .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),
                new Polygon(new Point3D(-200, 0, 200),
                        new Point3D(-200, 0, -100),
                        new Point3D(200, 0, -100),
                        new Point3D(200, 0, 200))
                        .setEmission(new Color(150, 120, 90)) //
                        .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),
                new Polygon(new Point3D(-200, 200, 200),
                        new Point3D(-200, 200, -100),
                        new Point3D(200, 200, -100),
                        new Point3D(200, 200, 200))
                        .setEmission(new Color(150, 120, 90)) //
                        .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),

//Table
                new Polygon(new Point3D(75 + t * 1, 50, 25 + t * 0),
                        new Point3D(75 + t * 0.5, 50, 25 + t * 0.866),
                        new Point3D(75 + t * -0.5, 50, 25 + t * 0.866),
                        new Point3D(75 + t * -1, 50, 25 + t * 0),
                        new Point3D(75 + t * -0.5, 50, 25 + t * -0.866),
                        new Point3D(75 + t * 0.5, 50, 25 + t * -0.866))
                        .setEmission(Color.BLACK) //
                        .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20).setkT(0.9).setkB(0.5)),
                new Polygon(new Point3D(75 + t * 1, 55, 25 + t * 0),
                        new Point3D(75 + t * 0.5, 55, 25 + t * 0.866),
                        new Point3D(75 + t * -0.5, 55, 25 + t * 0.866),
                        new Point3D(75 + t * -1, 55, 25 + t * 0),
                        new Point3D(75 + t * -0.5, 55, 25 + t * -0.866),
                        new Point3D(75 + t * 0.5, 55, 25 + t * -0.866))
                        .setEmission(Color.BLACK) //
                        .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20).setkT(0.9).setkB(0.5)),

                new Polygon(new Point3D(75 + t * 1, 50, 25 + t * 0),
                        new Point3D(75 + t * 0.5, 50, 25 + t * 0.866),
                        new Point3D(75 + t * 0.5, 55, 25 + t * 0.866),
                        new Point3D(75 + t * 1, 55, 25 + t * 0))
                        .setEmission(new Color(164, 116, 73))
                        .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),
                new Polygon(new Point3D(75 + t * 1, 50, 25 + t * 0),
                        new Point3D(75 + t * 0.5, 50, 25 + t * -0.866),
                        new Point3D(75 + t * 0.5, 55, 25 + t * -0.866),
                        new Point3D(75 + t * 1, 55, 25 + t * 0))
                        .setEmission(new Color(164, 116, 73))
                        .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),
                new Polygon(new Point3D(75 + t * 0.5, 50, 25 + t * -0.866),
                        new Point3D(75 + t * -0.5, 50, 25 + t * -0.866),
                        new Point3D(75 + t * -0.5, 55, 25 + t * -0.866),
                        new Point3D(75 + t * 0.5, 55, 25 + t * -0.866))
                        .setEmission(new Color(164, 116, 73))
                        .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),
                new Polygon(new Point3D(75 + t * -0.5, 50, 25 + t * 0.866),
                        new Point3D(75 + t * 0.5, 50, 25 + t * 0.866),
                        new Point3D(75 + t * 0.5, 55, 25 + t * 0.866),
                        new Point3D(75 + t * -0.5, 55, 25 + t * 0.866))
                        .setEmission(new Color(164, 116, 73))
                        .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),
                new Polygon(new Point3D(75 + t * -1, 55, 25 + t * 0),
                        new Point3D(75 + t * -0.5, 55, 25 + t * 0.866),
                        new Point3D(75 + t * -0.5, 50, 25 + t * 0.866),
                        new Point3D(75 + t * -1, 50, 25 + t * 0))
                        .setEmission(new Color(164, 116, 73))
                        .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),
                new Polygon(new Point3D(75 + t * -1, 55, 25 + t * 0),
                        new Point3D(75 + t * -0.5, 55, 25 + t * -0.866),
                        new Point3D(75 + t * -0.5, 50, 25 + t * -0.866),
                        new Point3D(75 + t * -1, 50, 25 + t * 0))
                        .setEmission(new Color(164, 116, 73))
                        .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),
                //Book

                new Polygon(new Point3D(75.1, 55.2, 34.9),
                        new Point3D(85.1, 55.2, 44.9),
                        new Point3D(85.1, 59.8, 44.9),
                        new Point3D(75.1, 59.8, 34.9))
                        .setEmission(new Color(255, 255, 255)),

                cube(new Point3D(75, 55, 35),
                        new Vector(-15, 0, 15), new Vector(10, 0, 10), new Vector(0, 5, 0),
                        new Color(150, 10, 10)),
                new Cylinder(new Ray(new Point3D(75, 57.5, 35), new Vector(-1, 0, 1)), 2.5, 21)
                        .setEmission(new Color(150, 10, 10))
                        .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),

                cube(new Point3D(82.3, 55, 27.8),
                        new Vector(-15, 10, 15), new Vector(10, 0, 10), new Vector(1.5, 4.5, -1.5),
                        new Color(10, 150, 10)),
                new Cylinder(new Ray(new Point3D(83.05, 57.25, 27.05), new Vector(-15, 10, 15)), 2.5, 23.5)
                        .setEmission(new Color(10, 150, 10))
                        .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),


                new Cylinder(new Ray(new Point3D(75, 20, 25), new Vector(0, 1, 0)), 2, 30)
                        .setEmission(new Color(100, 100, 100)) //
                        .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20).setkR(0)),

                new Cylinder(new Ray(new Point3D(75, 20, 25), new Vector(1, -1, 0)), 2, 30)
                        .setEmission(new Color(100, 100, 100)) //
                        .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20).setkR(0)),
                new Cylinder(new Ray(new Point3D(75, 20, 25), new Vector(-1, -1, 0)), 2, 30)
                        .setEmission(new Color(100, 100, 100)) //
                        .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20).setkR(0)),
                new Cylinder(new Ray(new Point3D(75, 20, 25), new Vector(0, -1, 1)), 2, 30)
                        .setEmission(new Color(100, 100, 100)) //
                        .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20).setkR(0)),
                new Cylinder(new Ray(new Point3D(75, 20, 25), new Vector(0, -1, -1)), 2, 30)
                        .setEmission(new Color(100, 100, 100)) //
                        .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20).setkR(0)),


                //Chair
                cube(new Point3D(0, 0, 30),
                        new Vector(2, 0, 3), new Vector(-3, 0, 2), new Vector(0, 30, 0),
                        new Color(10, 10, 150)),
                cube(new Point3D(-30, 0, 50),
                        new Vector(2, 0, 3), new Vector(-3, 0, 2), new Vector(0, 80, 0),
                        new Color(10, 10, 150)),
                cube(new Point3D(-10, 0, 80),
                        new Vector(2, 0, 3), new Vector(-3, 0, 2), new Vector(0, 80, 0),
                        new Color(10, 10, 150)),
                cube(new Point3D(20, 0, 60),
                        new Vector(2, 0, 3), new Vector(-3, 0, 2), new Vector(0, 30, 0),
                        new Color(10, 10, 150)),
                cube(new Point3D(0, 30, 30),
                        new Vector(22, 0, 33), new Vector(-33, 0, 22), new Vector(0, 5, 0),
                        new Color(10, 10, 150)),

                new Cylinder(new Ray(new Point3D(-26, 50, 56), new Vector(2, 0, 3)), 4, 22)
                        .setEmission(new Color(150, 120, 90)) //
                        .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),
                new Sphere(new Point3D(-26, 50, 56), 4)
                        .setEmission(new Color(150, 120, 90)) //
                        .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),
                new Sphere(new Point3D(-13.797, 50, 74.305), 4)
                        .setEmission(new Color(150, 120, 90)) //
                        .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),

                new Cylinder(new Ray(new Point3D(-26, 56, 56), new Vector(2, 0, 3)), 4, 22)
                        .setEmission(new Color(150, 120, 90)) //
                        .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),
                new Sphere(new Point3D(-26, 56, 56), 4)
                        .setEmission(new Color(150, 120, 90)) //
                        .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),
                new Sphere(new Point3D(-13.797, 56, 74.305), 4)
                        .setEmission(new Color(150, 120, 90)) //
                        .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),

                new Cylinder(new Ray(new Point3D(-26, 62, 56), new Vector(2, 0, 3)), 4, 22)
                        .setEmission(new Color(150, 120, 90)) //
                        .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),
                new Sphere(new Point3D(-26, 62, 56), 4)
                        .setEmission(new Color(150, 120, 90)) //
                        .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),
                new Sphere(new Point3D(-13.797, 62, 74.305), 4)
                        .setEmission(new Color(150, 120, 90)) //
                        .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),

                new Cylinder(new Ray(new Point3D(-26, 68, 56), new Vector(2, 0, 3)), 4, 22)
                        .setEmission(new Color(150, 120, 90)) //
                        .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),
                new Sphere(new Point3D(-26, 68, 56), 4)
                        .setEmission(new Color(150, 120, 90)) //
                        .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),
                new Sphere(new Point3D(-13.797, 68, 74.305), 4)
                        .setEmission(new Color(150, 120, 90)) //
                        .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),

                new Cylinder(new Ray(new Point3D(-26, 74, 56), new Vector(2, 0, 3)), 4, 22)
                        .setEmission(new Color(150, 120, 90)) //
                        .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),
                new Sphere(new Point3D(-26, 74, 56), 4)
                        .setEmission(new Color(150, 120, 90)) //
                        .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),
                new Sphere(new Point3D(-13.797, 74, 74.305), 4)
                        .setEmission(new Color(150, 120, 90)) //
                        .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),

                new Cylinder(new Ray(new Point3D(-26, 80, 56), new Vector(2, 0, 3)), 4, 22)
                        .setEmission(new Color(150, 120, 90)) //
                        .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),
                new Sphere(new Point3D(-26, 80, 56), 4)
                        .setEmission(new Color(150, 120, 90)) //
                        .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),
                new Sphere(new Point3D(-13.797, 80, 74.305), 4)
                        .setEmission(new Color(150, 120, 90)) //
                        .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),

                new Cylinder(new Ray(new Point3D(92, 110, 50), new Vector(0, 1, 0)), 0.2, 100)
                        .setEmission(Color.BLACK) //
                        .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),
                new Cylinder(new Ray(new Point3D(66, 110, 50), new Vector(0, 1, 0)), 0.2, 100)
                        .setEmission(Color.BLACK) //
                        .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),

                new Sphere(new Point3D(92, 100, 50), 10)
                        .setEmission(new Color(50, 50, 50)) //
                        .setMaterial(new Material().setkD(0.25).setkS(0.25).setkT(0.8)),
                new Sphere(new Point3D(66, 100, 50), 10)
                        .setEmission(new Color(50, 50, 50)) //
                        .setMaterial(new Material().setkD(0.25).setkS(0.25).setkT(0.8)),


                new Sphere(new Point3D(75, 10, 50), 10)
                        .setEmission(new Color(150, 120, 200)) //
                        .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20))

        );


        //scene.lights.add(new SpotLight(new Color(400, 400, 400),
        //        new Point3D(10, 60, -30), new Vector(0, -1, 1)) //
        //        .setkL(4E-5).setkQ(2E-7));
        scene.lights.add(new DirectionalLight(new Color(300, 300, 300), new Vector(1, -1.3, -1)));
        scene.lights.add(new PointLight(new Color(50, 50, 50), new Point3D(66, 100, 25)));
        scene.lights.add(new PointLight(new Color(50, 50, 50), new Point3D(92, 100, 25)));

        ImageWriter imageWriter = new ImageWriter("mp1", 1000, 1000);
        Render render = new Render() //
                .setWriter(imageWriter) //
                .setCamera(camera) //
                .setRayTracer(new RayTracerBasic(scene)).setUseDOF(false).setDebugPrint()
                .setMultithreading(3)
                .setUseBounding(true)
                .buildHierarchy()
                .setUseGlossySurfaces(true)
                .setSizeSuperSamplingGlossySurfaces(16);

        render.renderImage();
        render.writeToImage();
    }


}
