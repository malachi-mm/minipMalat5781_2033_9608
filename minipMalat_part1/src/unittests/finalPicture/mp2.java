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

    //   Camera camera = new Camera(new Point3D(10, 120, -50), new Vector(0, -0.5, 1), new Vector(0, 1, 0.5)) //
    //          .setViewPlaneSize(384, 216).setDistance(153); //

private Scene calcScene(){
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
            //*********************************************************************************
                //windows 11
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
            new Polygon(new Point3D(-200, 200, 200),
                        new Point3D(-200, 200, -100),
                        new Point3D(200, 200, -100),
                        new Point3D(200, 200, 200))
            .setEmission(new Color(150, 120, 90)) //
            .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),
            new Polygon(new Point3D(-200, 0, 200),
                        new Point3D(-200, 0, -100),
                        new Point3D(200, 0, -100),
                        new Point3D(200, 0, 200))
            .setEmission(new Color(150, 120, 90)) //
            .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),

            new Polygon(new Point3D(-200, 1, 200),
                        new Point3D(-200, 1, 150),
                        new Point3D(-160, 1, 150),
                        new Point3D(-160, 1, 200))
            .setEmission(new Color(110, 65, 30)) //
            .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(50)),
            new Polygon(new Point3D(-120, 1, 200),
                        new Point3D(-120, 1, 175),
                        new Point3D(-159, 1, 175),
                        new Point3D(-159, 1, 200))
            .setEmission(new Color(115, 63, 35)) //
            .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(50)),
            new Polygon(new Point3D(-80, 1, 200),
                        new Point3D(-80, 1, 150),
                        new Point3D(-119, 1, 150),
                        new Point3D(-119, 1, 200))
            .setEmission(new Color(120, 60, 30)) //
            .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(50)),
            new Polygon(new Point3D(-40, 1, 200),
                        new Point3D(-40, 1, 175),
                        new Point3D(-79, 1, 175),
                        new Point3D(-79, 1, 200))
            .setEmission(new Color(130, 60, 30)) //
            .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(50)),
            new Polygon(new Point3D(0, 1, 200),
                        new Point3D(0, 1, 150),
                        new Point3D(-39, 1, 150),
                        new Point3D(-39, 1, 200))
            .setEmission(new Color(125, 75, 30)) //
            .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(50)),
            new Polygon(new Point3D(40, 1, 200),
                        new Point3D(40, 1, 175),
                        new Point3D(1, 1, 175),
                        new Point3D(1, 1, 200))
            .setEmission(new Color(125, 70, 30)) //
            .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(50)),
            new Polygon(new Point3D(80, 1, 200),
                        new Point3D(80, 1, 150),
                        new Point3D(41, 1, 150),
                        new Point3D(41, 1, 200))
            .setEmission(new Color(140, 60, 40)) //
            .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),
            new Polygon(new Point3D(120, 1, 200),
                        new Point3D(120, 1, 175),
                        new Point3D(81, 1, 175),
                        new Point3D(81, 1, 200))
            .setEmission(new Color(135, 60, 35)) //
            .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),
            new Polygon(new Point3D(160, 1, 200),
                        new Point3D(160, 1, 150),
                        new Point3D(121, 1, 150),
                        new Point3D(121, 1, 200))
            .setEmission(new Color(140, 60, 40)) //
            .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),
            new Polygon(new Point3D(200, 1, 200),
                        new Point3D(200, 1, 175),
                        new Point3D(161, 1, 175),
                        new Point3D(161, 1, 200))
            .setEmission(new Color(120, 60, 50)) //
            .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),


//row 2
            new Polygon(new Point3D(-200, 1, 149),
                        new Point3D(-200, 1, 100),
                        new Point3D(-160, 1, 100),
                        new Point3D(-160, 1, 149))
            .setEmission(new Color(120, 65, 30)) //
            .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),

            new Polygon(new Point3D(-80, 1, 149),
                        new Point3D(-80, 1, 100),
                        new Point3D(-119, 1, 100),
                        new Point3D(-119, 1, 149))
            .setEmission(new Color(120, 65, 25)) //
            .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),

            new Polygon(new Point3D(0, 1, 149),
                        new Point3D(0, 1, 100),
                        new Point3D(-39, 1, 100),
                        new Point3D(-39, 1, 149))
            .setEmission(new Color(125, 70, 30)) //
            .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),

            new Polygon(new Point3D(80, 1, 149),
                        new Point3D(80, 1, 100),
                        new Point3D(41, 1, 100),
                        new Point3D(41, 1, 149))
            .setEmission(new Color(135, 60, 40)) //
            .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),

            new Polygon(new Point3D(160, 1, 149),
                        new Point3D(160, 1, 100),
                        new Point3D(121, 1, 100),
                        new Point3D(121, 1, 149))
            .setEmission(new Color(140, 60, 35)) //
            .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),


            new Polygon(new Point3D(40, 1, 174),
                        new Point3D(40, 1, 125),
                        new Point3D(1, 1, 125),
                        new Point3D(1, 1, 174))
            .setEmission(new Color(130, 70, 30)) //
            .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),
            new Polygon(new Point3D(-120, 1, 174),
                        new Point3D(-120, 1, 125),
                        new Point3D(-159, 1, 125),
                        new Point3D(-159, 1, 174))
            .setEmission(new Color(115, 55, 35)) //
            .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),
            new Polygon(new Point3D(-40, 1, 174),
                        new Point3D(-40, 1, 125),
                        new Point3D(-79, 1, 125),
                        new Point3D(-79, 1, 174))
            .setEmission(new Color(120, 60, 30)) //
            .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),
            new Polygon(new Point3D(120, 1, 174),
                        new Point3D(120, 1, 125),
                        new Point3D(81, 1, 125),
                        new Point3D(81, 1, 174))
            .setEmission(new Color(130, 60, 35)) //
            .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),
            new Polygon(new Point3D(200, 1, 174),
                        new Point3D(200, 1, 125),
                        new Point3D(161, 1, 125),
                        new Point3D(161, 1, 174))
            .setEmission(new Color(125, 60, 45)) //
            .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),


            new Polygon(new Point3D(40, 1, 124),
                        new Point3D(40, 1, 75),
                        new Point3D(1, 1, 75),
                        new Point3D(1, 1, 124))
            .setEmission(new Color(125, 50, 20)) //
            .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),
            new Polygon(new Point3D(-120, 1, 124),
                        new Point3D(-120, 1, 75),
                        new Point3D(-159, 1, 75),
                        new Point3D(-159, 1, 124))
            .setEmission(new Color(125, 63, 25)) //
            .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),
            new Polygon(new Point3D(-40, 1, 124),
                        new Point3D(-40, 1, 75),
                        new Point3D(-79, 1, 75),
                        new Point3D(-79, 1, 124))
            .setEmission(new Color(115, 55, 25)) //
            .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),
            new Polygon(new Point3D(120, 1, 124),
                        new Point3D(120, 1, 75),
                        new Point3D(81, 1, 75),
                        new Point3D(81, 1, 124))
            .setEmission(new Color(140, 65, 35)) //
            .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),
            new Polygon(new Point3D(200, 1, 124),
                        new Point3D(200, 1, 75),
                        new Point3D(161, 1, 75),
                        new Point3D(161, 1, 124))
            .setEmission(new Color(130, 60, 45)) //
            .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),


            new Polygon(new Point3D(40, 1, 74),
                        new Point3D(40, 1, 25),
                        new Point3D(1, 1, 25),
                        new Point3D(1, 1, 74))
            .setEmission(new Color(150, 70, 30)) //
            .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),
            new Polygon(new Point3D(-120, 1, 74),
                        new Point3D(-120, 1, 25),
                        new Point3D(-159, 1, 25),
                        new Point3D(-159, 1, 74))
            .setEmission(new Color(115, 63, 35)) //
            .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),
            new Polygon(new Point3D(-40, 1, 74),
                        new Point3D(-40, 1, 25),
                        new Point3D(-79, 1, 25),
                        new Point3D(-79, 1, 74))
            .setEmission(new Color(110, 55, 25)) //
            .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),
            new Polygon(new Point3D(120, 1, 74),
                        new Point3D(120, 1, 25),
                        new Point3D(81, 1, 25),
                        new Point3D(81, 1, 74))
            .setEmission(new Color(135, 60, 35)) //
            .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),
            new Polygon(new Point3D(200, 1, 74),
                        new Point3D(200, 1, 25),
                        new Point3D(161, 1, 25),
                        new Point3D(161, 1, 74))
            .setEmission(new Color(120, 60, 50)) //
            .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),


            new Polygon(new Point3D(120, 1, 24),
                        new Point3D(120, 1, -25),
                        new Point3D(81, 1, -25),
                        new Point3D(81, 1, 24))
            .setEmission(new Color(140, 70, 35)) //
            .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),

            new Polygon(new Point3D(40, 1, 24),
                        new Point3D(40, 1, -25),
                        new Point3D(1, 1, -25),
                        new Point3D(1, 1, 24))
            .setEmission(new Color(115, 70, 30)) //
            .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),
            new Polygon(new Point3D(-40, 1, 24),
                        new Point3D(-40, 1, -25),
                        new Point3D(-79, 1, -25),
                        new Point3D(-79, 1, 24))
            .setEmission(new Color(115, 40, 25)) //
            .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),


            new Polygon(new Point3D(-200, 1, 99),
                        new Point3D(-200, 1, 50),
                        new Point3D(-160, 1, 50),
                        new Point3D(-160, 1, 99))
            .setEmission(new Color(110, 60, 30)) //
            .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),

            new Polygon(new Point3D(-80, 1, 99),
                        new Point3D(-80, 1, 50),
                        new Point3D(-119, 1, 50),
                        new Point3D(-119, 1, 99))
            .setEmission(new Color(130, 60, 25)) //
            .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),

            new Polygon(new Point3D(0, 1, 99),
                        new Point3D(0, 1, 50),
                        new Point3D(-39, 1, 50),
                        new Point3D(-39, 1, 99))
            .setEmission(new Color(125, 70, 45)) //
            .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),

            new Polygon(new Point3D(80, 1, 99),
                        new Point3D(80, 1, 50),
                        new Point3D(41, 1, 50),
                        new Point3D(41, 1, 99))
            .setEmission(new Color(140, 60, 35)) //
            .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),

            new Polygon(new Point3D(160, 1, 99),
                        new Point3D(160, 1, 50),
                        new Point3D(121, 1, 50),
                        new Point3D(121, 1, 99))
            .setEmission(new Color(135, 70, 25)) //
            .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),


            new Polygon(new Point3D(-80, 1, 49),
                        new Point3D(-80, 1, 0),
                        new Point3D(-119, 1, 0),
                        new Point3D(-119, 1, 49))
            .setEmission(new Color(120, 60, 25)) //
            .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),

            new Polygon(new Point3D(0, 1, 49),
                        new Point3D(0, 1, 0),
                        new Point3D(-39, 1, 0),
                        new Point3D(-39, 1, 49))
            .setEmission(new Color(125, 60, 35)) //
            .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),

            new Polygon(new Point3D(80, 1, 49),
                        new Point3D(80, 1, 0),
                        new Point3D(41, 1, 0),
                        new Point3D(41, 1, 49))
            .setEmission(new Color(130, 65, 40)) //
            .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),

            new Polygon(new Point3D(160, 1, 49),
                        new Point3D(160, 1, 0),
                        new Point3D(121, 1, 0),
                        new Point3D(121, 1, 49))
            .setEmission(new Color(140, 60, 35)) //
            .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),


            new Polygon(new Point3D(-80, 1, -1),
                        new Point3D(-80, 1, -50),
                        new Point3D(-119, 1, -50),
                        new Point3D(-119, 1, -1))
            .setEmission(new Color(120, 55, 25)) //
            .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),

            new Polygon(new Point3D(0, 1, -1),
                        new Point3D(0, 1, -50),
                        new Point3D(-39, 1, -50),
                        new Point3D(-39, 1, -1))
            .setEmission(new Color(130, 60, 30)) //
            .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),

            new Polygon(new Point3D(80, 1, -1),
                        new Point3D(80, 1, -50),
                        new Point3D(41, 1, -50),
                        new Point3D(41, 1, -1))
            .setEmission(new Color(130, 65, 40)) //
            .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),

            new Polygon(new Point3D(-200, 1,49 ),
                        new Point3D(-200, 1, 0),
                        new Point3D(-160, 1,0),
                        new Point3D(-160, 1, 49))
            .setEmission(new Color(140, 60, 35)) //
            .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),
            new Polygon(new Point3D(-159, 1,24 ),
                        new Point3D(-159, 1, -25),
                        new Point3D(-120, 1,-25),
                        new Point3D(-120, 1, 24))
            .setEmission(new Color(120, 60, 30)) //
            .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),

//Table
            new Polygon(new Point3D(75 + t * 1, 50, 25 + t * 0),
                        new Point3D(75 + t * 0.5, 50, 25 + t * 0.866),
                        new Point3D(75 + t * -0.5, 50, 25 + t * 0.866),
                        new Point3D(75 + t * -1, 50, 25 + t * 0),
                        new Point3D(75 + t * -0.5, 50, 25 + t * -0.866),
                        new Point3D(75 + t * 0.5, 50, 25 + t * -0.866))
            .setEmission(Color.BLACK) //
                        .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20).setkT(0.8).setkR(0.1).setkB(0.3)),
            new Polygon(new Point3D(75 + t * 1, 55, 25 + t * 0),
                        new Point3D(75 + t * 0.5, 55, 25 + t * 0.866),
                        new Point3D(75 + t * -0.5, 55, 25 + t * 0.866),
                        new Point3D(75 + t * -1, 55, 25 + t * 0),
                        new Point3D(75 + t * -0.5, 55, 25 + t * -0.866),
                        new Point3D(75 + t * 0.5, 55, 25 + t * -0.866))
            .setEmission(Color.BLACK) //
                        .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20).setkT(0.9).setkB(0)),

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
            //Books on the table

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


    //****************************************************************************
    // the ball under the table
                new Sphere(new Point3D(75, 10, 50), 10)
                        .setEmission(new Color(150, 120, 200)) //
                        .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),
    //****************************************************************************************************8
    //Chair

    cube(new Point3D(0, 1, 30),
                        new Vector(2, 0, 3), new Vector(-3, 0, 2), new Vector(0, 29, 0),
                        new Color(10, 10, 150)),
    cube(new Point3D(-30, 1, 50),
                        new Vector(2, 0, 3), new Vector(-3, 0, 2), new Vector(0, 79, 0),
                        new Color(10, 10, 150)),
    cube(new Point3D(-10, 1, 80),
                        new Vector(2, 0, 3), new Vector(-3, 0, 2), new Vector(0, 79, 0),
                        new Color(10, 10, 150)),
    cube(new Point3D(20, 1, 60),
                        new Vector(2, 0, 3), new Vector(-3, 0, 2), new Vector(0, 29, 0),
                        new Color(10, 10, 150)),
    cube(new Point3D(0, 30, 30),
                        new Vector(22, 0, 33), new Vector(-33, 0, 22), new Vector(0, 5, 0),
                        new Color(10, 10, 150)),

            new Cylinder(new Ray(new Point3D(-26, 50, 56), new Vector(2, 0, 3)), 4, 22)
            .setEmission(new Color(200, 150, 150)) //
            .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),
            new Sphere(new Point3D(-26, 50, 56), 4)
            .setEmission(new Color(200, 150, 150)) //
            .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),
            new Sphere(new Point3D(-13.797, 50, 74.305), 4)
            .setEmission(new Color(200, 150, 150)) //
            .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),

            new Cylinder(new Ray(new Point3D(-26, 56, 56), new Vector(2, 0, 3)), 4, 22)
            .setEmission(new Color(200, 150, 150)) //
            .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),
            new Sphere(new Point3D(-26, 56, 56), 4)
            .setEmission(new Color(200, 150, 150)) //
            .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),
            new Sphere(new Point3D(-13.797, 56, 74.305), 4)
            .setEmission(new Color(200, 150, 150)) //
            .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),

            new Cylinder(new Ray(new Point3D(-26, 62, 56), new Vector(2, 0, 3)), 4, 22)
            .setEmission(new Color(200, 150, 150)) //
            .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),
            new Sphere(new Point3D(-26, 62, 56), 4)
            .setEmission(new Color(200, 150, 150)) //
            .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),
            new Sphere(new Point3D(-13.797, 62, 74.305), 4)
            .setEmission(new Color(200, 150, 150)) //
            .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),

            new Cylinder(new Ray(new Point3D(-26, 68, 56), new Vector(2, 0, 3)), 4, 22)
            .setEmission(new Color(200, 150, 150)) //
            .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),
            new Sphere(new Point3D(-26, 68, 56), 4)
            .setEmission(new Color(200, 150, 150)) //
            .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),
            new Sphere(new Point3D(-13.797, 68, 74.305), 4)
            .setEmission(new Color(200, 150, 150)) //
            .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),

            new Cylinder(new Ray(new Point3D(-26, 74, 56), new Vector(2, 0, 3)), 4, 22)
            .setEmission(new Color(200, 150, 150)) //
            .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),
            new Sphere(new Point3D(-26, 74, 56), 4)
            .setEmission(new Color(200, 150, 150)) //
            .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),
            new Sphere(new Point3D(-13.797, 74, 74.305), 4)
            .setEmission(new Color(200, 150, 150)) //
            .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),

            new Cylinder(new Ray(new Point3D(-26, 80, 56), new Vector(2, 0, 3)), 4, 22)
            .setEmission(new Color(200, 150, 150)) //
            .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),
            new Sphere(new Point3D(-26, 80, 56), 4)
            .setEmission(new Color(200, 150, 150)) //
            .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),
            new Sphere(new Point3D(-13.797, 80, 74.305), 4)
            .setEmission(new Color(200, 150, 150)) //
            .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),


            //**********************************************************************************************************
            //the four lights
                new Cylinder(new Ray(new Point3D(92, 110, 62), new Vector(0, 1, 0)), 0.2, 100)
                        .setEmission(Color.BLACK) //
                        .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),
                new Cylinder(new Ray(new Point3D(66, 110, 62), new Vector(0, 1, 0)), 0.2, 100)
                        .setEmission(Color.BLACK) //
                        .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),

                new Sphere(new Point3D(92, 100, 62), 10)
                        .setEmission(new Color(100, 100, 100)) //
                        .setMaterial(new Material().setkD(0.25).setkS(0.25).setkT(0.4)),

                new Sphere(new Point3D(66, 100, 62), 10)
                        .setEmission(new Color(200, 200, 200)) //
                        .setMaterial(new Material().setkD(0.25).setkS(0.25).setkT(0.4)),

                new Cylinder(new Ray(new Point3D(92, 110, 38), new Vector(0, 1, 0)), 0.2, 100)
                        .setEmission(Color.BLACK) //
                        .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),
                new Cylinder(new Ray(new Point3D(66, 110, 38), new Vector(0, 1, 0)), 0.2, 100)
                        .setEmission(Color.BLACK) //
                        .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),

                new Sphere(new Point3D(92, 100, 38), 10)
                        .setEmission(new Color(200, 200, 200)) //
                        .setMaterial(new Material().setkD(0.25).setkS(0.25).setkT(0.4)),

                new Sphere(new Point3D(66, 100, 38), 10)
                        .setEmission(new Color(100, 100, 100)) //
                        .setMaterial(new Material().setkD(0.25).setkS(0.25).setkT(0.4)),


    //*****************************************************************************************************************************************88
                //shelf

    cube(new Point3D(-200, 80, 200),
                        new Vector(20, 0, 0), new Vector(0, 10, 0), new Vector(0, 0, -80),
                        new Color(86, 47, 14)),

            new Polygon(new Point3D(-200, 60, 190),
                        new Point3D(-200, 60, 180),
                        new Point3D(-180, 80, 180),
                        new Point3D(-180, 80, 190))
            .setEmission(new Color(86, 47, 14))
            .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),

            new Polygon(new Point3D(-200, 60, 180),
                        new Point3D(-200, 80, 180),
                        new Point3D(-180, 80, 180))
            .setEmission(new Color(86, 47, 14))
            .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),
            new Polygon(new Point3D(-200, 60, 190),
                        new Point3D(-200, 80, 190),
                        new Point3D(-180, 80, 190))
            .setEmission(new Color(86, 47, 14))
            .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),

            new Polygon(new Point3D(-200, 60, 140),
                        new Point3D(-200, 60, 130),
                        new Point3D(-180, 80, 130),
                        new Point3D(-180, 80, 140))
            .setEmission(new Color(86, 47, 14))
            .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),

            new Polygon(new Point3D(-200, 60, 130),
                        new Point3D(-200, 80, 130),
                        new Point3D(-180, 80, 130))
            .setEmission(new Color(86, 47, 14))
            .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),

            new Polygon(new Point3D(-200, 60, 140),
                        new Point3D(-200, 80, 140),
                        new Point3D(-180, 80, 140))
            .setEmission(new Color(86, 47, 14))
            .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),

//***************************************************************************************************88
                //books on the shelf
    cube(new Point3D(-182, 90, 200),
                        new Vector(-15, 0, 0), new Vector(0, 25, 0), new Vector(0, 0, -10),
                        new Color(0, 0, 150)),
            new Cylinder(new Ray(new Point3D(-182, 90, 195), new Vector(0, 1, 0)), 5, 25)
            .setEmission(new Color(0, 0, 150))
            .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),

    cube(new Point3D(-182, 90, 189),
                        new Vector(-15, 0, 0), new Vector(0, 25, 0), new Vector(0, 0, -10),
                        new Color(15, 15, 150)),
            new Cylinder(new Ray(new Point3D(-182, 90, 184), new Vector(0, 1, 0)), 5, 25)
            .setEmission(new Color(15, 15, 150))
            .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),

    cube(new Point3D(-182, 90, 178),
                        new Vector(-15, 0, 0), new Vector(0, 25, 0), new Vector(0, 0, -10),
                        new Color(30, 30, 150)),
            new Cylinder(new Ray(new Point3D(-182, 90, 173), new Vector(0, 1, 0)), 5, 25)
            .setEmission(new Color(30, 30, 150))
            .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),

    cube(new Point3D(-182, 90, 167),
                        new Vector(-15, 0, 0), new Vector(0, 25, 0), new Vector(0, 0, -10),
                        new Color(45, 45, 150)),
            new Cylinder(new Ray(new Point3D(-182, 90, 162), new Vector(0, 1, 0)), 5, 25)
            .setEmission(new Color(45, 45, 150))
            .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),

    cube(new Point3D(-182, 90, 156),
                        new Vector(-15, 0, 0), new Vector(0, 25, 0), new Vector(0, 0, -10),
                        new Color(60, 60, 150)),
            new Cylinder(new Ray(new Point3D(-182, 90, 151), new Vector(0, 1, 0)), 5, 25)
            .setEmission(new Color(60, 60, 150))
            .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),

    cube(new Point3D(-182, 90, 141),
                        new Vector(-15, 0, 0), new Vector(0, 10, 0), new Vector(0, 0, -25),
                        new Color(120, 60, 60)),

            new Cylinder(new Ray(new Point3D(-182, 95, 141), new Vector(0, 0, -1)), 5, 25)
            .setEmission(new Color(120, 60, 60))
            .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),

                new Polygon(new Point3D(-182, 91.5, 115.9),
                        new Point3D(-182, 98.5, 115.9),
                        new Point3D(-196.9, 98.5, 115.9),
                        new Point3D(-196.9, 91.5, 115.9))
                        .setEmission(new Color(255, 255, 255)),

            new Cylinder(new Ray(new Point3D(-182.2, 95, 115.9), new Vector(0, 0, -1)), 3.5, 0.1)
            .setEmission(new Color(255, 255, 255))
            .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),

            new Cylinder(new Ray(new Point3D(-182.2, 95, 141.1), new Vector(0, 0, -1)), 3.5, 0.1)
                .setEmission(new Color(255, 255, 255))
                .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20))


            );


//########################################################################################################################
        //lights
    scene.lights.add(new DirectionalLight(new Color(300, 300, 300), new Vector(1, -1.3, -1)));
    scene.lights.add(new PointLight(new Color(50, 50, 50), new Point3D(66, 100, 62)));
    scene.lights.add(new PointLight(new Color(50, 50, 50), new Point3D(66, 100, 38)));
    scene.lights.add(new PointLight(new Color(50, 50, 50), new Point3D(92, 100, 62)));
    scene.lights.add(new PointLight(new Color(50, 50, 50), new Point3D(92, 100, 38)));
     return scene;
    }

    /**
     * test with BVH and threads
     */
    @Test
    public void modelTest() {

        Camera camera = new Camera(new Point3D(10, 120, -45), new Vector(0, -0.5, 1), new Vector(0, 1, 0.5)) //
                .setViewPlaneSize(384, 216).setDistance(152); //
        Scene scene=calcScene();
        ImageWriter imageWriter = new ImageWriter("mp1", 1920, 1080);
        Render render = new Render() //
                .setWriter(imageWriter) //
                .setCamera(camera) //
                .setRayTracer(new RayTracerBasic(scene)).setUseDOF(false).setDebugPrint()
                .setMultithreading(3)
                .setUseBounding(true)
                .buildHierarchy()
                .setUseGlossySurfaces(true)
                .setSizeSuperSamplingGlossySurfaces(49)
                .setUseAntiAliasing(true)
                .setSizeSuperSamplingAntiAliasing(121)
                .setUseAdaptiveSuperSampling(true);

        render.renderImage();
        render.writeToImage();
    }

    @Test
    public void modelTestWithoutMP2() {

        Camera camera = new Camera(new Point3D(10, 120, -45), new Vector(0, -0.5, 1), new Vector(0, 1, 0.5)) //
                .setViewPlaneSize(384, 216).setDistance(152); //
        Scene scene=calcScene();
        ImageWriter imageWriter = new ImageWriter("mp2Without", 1920, 1080);
        Render render = new Render() //
                .setWriter(imageWriter) //
                .setCamera(camera) //
                .setRayTracer(new RayTracerBasic(scene)).setUseDOF(false).setDebugPrint()
                .setUseGlossySurfaces(true)
                .setSizeSuperSamplingGlossySurfaces(16)
                .setUseAntiAliasing(false)
                .setSizeSuperSamplingAntiAliasing(16)
                .setUseAdaptiveSuperSampling(false);

        render.renderImage();
        render.writeToImage();
    }


}
