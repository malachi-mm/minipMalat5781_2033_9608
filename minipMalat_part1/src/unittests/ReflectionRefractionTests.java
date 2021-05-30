/**
 * 
 */
package unittests;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import geometries.Plane;
import geometries.Tube;
import org.junit.jupiter.api.Test;

import elements.*;
import geometries.Sphere;
import geometries.Triangle;
import primitives.*;
import renderer.*;
import scene.Scene;

/**
 * Tests for reflection and transparency functionality, test for partial shadows
 * (with transparency)
 * 
 * @author dzilb
 */
public class ReflectionRefractionTests {
	private Scene scene = new Scene("Test scene");

	/**
	 * Produce a picture of a sphere lighted by a spot light
	 */
	@Test
	public void twoSpheres() {
		Camera camera = new Camera(new Point3D(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
				.setViewPlaneSize(150, 150).setDistance(1000);

		scene.geometries.add( //
				new Sphere( new Point3D(0, 0, -50),50) //
						.setEmission(new Color(java.awt.Color.BLUE)) //
						.setMaterial(new Material().setkD(0.4).setkS(0.3).setnShininess(100).setkT(0.3)),
				new Sphere(new Point3D(0, 0, -50),25) //
						.setEmission(new Color(java.awt.Color.RED)) //
						.setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)));
		scene.lights.add( //
				new SpotLight(new Color(1000, 600, 0), new Point3D(-100, -100, 500), new Vector(-1, -1, -2)) //
						.setkL(0.0004).setkQ(0.0000006));

		Render render = new Render() //
				.setWriter(new ImageWriter("refractionTwoSpheres", 500, 500)) //
				.setCamera(camera) //
				.setRayTracer(new RayTracerBasic(scene));
		render.renderImage();
		render.writeToImage();
	}

	/**
	 * Produce a picture of a sphere lighted by a spot light
	 */
	@Test
	public void twoSpheresOnMirrors() {
		Camera camera = new Camera(new Point3D(0, 0, 10000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
				.setViewPlaneSize(2500, 2500).setDistance(10000); //

		scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.1));

		scene.geometries.add( //
				new Sphere( new Point3D(-950, -900, -1000),400) //
						.setEmission(new Color(0, 0, 100)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20).setkT(0.5)),
				new Sphere( new Point3D(-950, -900, -1000),200) //
						.setEmission(new Color(100, 20, 20)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),
				new Triangle(new Point3D(1500, -1500, -1500), new Point3D(-1500, 1500, -1500),
						new Point3D(670, 670, 3000)) //
								.setEmission(new Color(20, 20, 20)) //
								.setMaterial(new Material().setkR(1)),
				new Triangle(new Point3D(1500, -1500, -1500), new Point3D(-1500, 1500, -1500),
						new Point3D(-1500, -1500, -2000)) //
								.setEmission(new Color(20, 20, 20)) //
								.setMaterial(new Material().setkR(0.5)));

		scene.lights.add(new SpotLight(new Color(1020, 400, 400), new Point3D(-750, -750, -150), new Vector(-1, -1, -4)) //
				.setkL(0.00001).setkQ(0.000005));

		ImageWriter imageWriter = new ImageWriter("reflectionTwoSpheresMirrored", 500, 500);
		Render render = new Render() //
				.setWriter(imageWriter) //
				.setCamera(camera) //
				.setRayTracer(new RayTracerBasic(scene));

		render.renderImage();
		render.writeToImage();
	}

	/**
	 * Produce a picture of a two triangles lighted by a spot light with a partially
	 * transparent Sphere producing partial shadow
	 */
	@Test
	public void trianglesTransparentSphere() {
		Camera camera = new Camera(new Point3D(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
				.setViewPlaneSize(200, 200).setDistance(1000);

		scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));

		scene.geometries.add( //
				new Triangle(new Point3D(-150, -150, -115), new Point3D(150, -150, -135), new Point3D(75, 75, -150)) //
						.setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(60)), //
				new Triangle(new Point3D(-150, -150, -115), new Point3D(-70, 70, -140), new Point3D(75, 75, -150)) //
						.setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(60)), //
				new Sphere(new Point3D(60, 50, -50),30) //
						.setEmission(new Color(java.awt.Color.BLUE)) //
						.setMaterial(new Material().setkD(0.2).setkS(0.2).setnShininess(30).setkT(0.6)));

		scene.lights.add(new SpotLight(new Color(700, 400, 400), new Point3D(60, 50, 0), new Vector(0, 0, -1)) //
				.setkL(4E-5).setkQ(2E-7));

		ImageWriter imageWriter = new ImageWriter("refractionShadow", 600, 600);
		Render render = new Render() //
				.setWriter(imageWriter) //
				.setCamera(camera) //
				.setRayTracer(new RayTracerBasic(scene));

		render.renderImage();
		render.writeToImage();
	}

	/**
	 * our test with 4 geometries
	 */
	@Test
	public void addedTest() {
		Camera camera = new Camera(new Point3D(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
				.setViewPlaneSize(200, 200).setDistance(1000); //

		scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.1));

		scene.geometries.add( //
				new Sphere( new Point3D(0, 0, -100),40) //
						.setEmission(new Color(0, 0, 100)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20).setkT(0.7)),
				new Sphere( new Point3D(0, 0, -100),20) //
						.setEmission(new Color(100, 20, 20)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),
				new Triangle(new Point3D(0, 0, -200), new Point3D(-100, -100, -100),
						new Point3D(100, -100, -100)) //
						.setEmission(new Color(20, 20, 20)) //
						.setMaterial(new Material().setkR(0.9)),
				new Triangle(new Point3D(0, 0, -200), new Point3D(-100, -100, -100),
						new Point3D(-100, 100, -100)) //
						.setEmission(new Color(20, 20, 20)) //
						.setMaterial(new Material().setkR(0.9)),
				new Triangle(new Point3D(0, 0, -200), new Point3D(100, 100, -100),
						new Point3D(-100, 100, -100)) //
						.setEmission(new Color(20, 20, 20)) //
						.setMaterial(new Material().setkR(0.9)),
				new Triangle(new Point3D(0, 0, -200), new Point3D(100, 100, -100),
						new Point3D(100, -100, -100)) //
						.setEmission(new Color(20, 20, 20)) //
						.setMaterial(new Material().setkR(0.9)));

		scene.lights.add(new SpotLight(new Color(700, 400, 400), new Point3D(60, 50, 0), new Vector(0, 0, -1)) //
				.setkL(4E-5).setkQ(2E-7));

		ImageWriter imageWriter = new ImageWriter("refractionRefractionFourGeometries", 600, 600);
		Render render = new Render() //
				.setWriter(imageWriter) //
				.setCamera(camera) //
				.setRayTracer(new RayTracerBasic(scene));

		render.renderImage();
		render.writeToImage();
	}


	/**
	 * Bonus 1
	 */
	@Test
	public void Bonus1Test() {
		Camera camera = new Camera(new Point3D(370, -370, 1000), new Vector(-1, 1, -3), new Vector(0, 3, 1)) //
				.setViewPlaneSize(200, 200).setDistance(1000); //

		scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.1));

		scene.geometries.add( //
				new Sphere(new Point3D(-40,40,-40),20)
						.setEmission(new Color(200, 100, 50)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20).setkR(0.5)),
				new Tube(new Ray(new Point3D(-80,-80,-100),new Vector(0,1,0)),5 )
						.setEmission(new Color(0, 0, 100)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20).setkT(0.7)),
				new Tube(new Ray(new Point3D(-40,-40,-80),new Vector(0,1,0)),5 )
						.setEmission(new Color(0, 0, 150)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20).setkT(0.7)),
				new Tube(new Ray(new Point3D(0,0,-60),new Vector(0,1,0)),5 )
						.setEmission(new Color(0, 0, 200)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20).setkT(0.7)),
				new Tube(new Ray(new Point3D(40,40,-40),new Vector(0,1,0)),5 )
						.setEmission(new Color(0, 0, 250)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20).setkT(0.7)),
				new Tube(new Ray(new Point3D(80,80,-20),new Vector(0,1,0)),5 )
						.setEmission(new Color(0, 0, 300)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20).setkT(0.7)),

				new Tube(new Ray(new Point3D(-80,-80,-100),new Vector(1,0,0)),5 )
						.setEmission(new Color(0, 0, 100)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20).setkT(0.7)),
				new Tube(new Ray(new Point3D(-40,-40,-80),new Vector(1,0,0)),5 )
						.setEmission(new Color(0, 0, 150)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20).setkT(0.7)),
				new Tube(new Ray(new Point3D(0,0,-60),new Vector(1,0,0)),5 )
						.setEmission(new Color(0, 0, 200)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20).setkT(0.7)),
				new Tube(new Ray(new Point3D(40,40,-40),new Vector(1,0,0)),5 )
						.setEmission(new Color(0, 0, 250)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20).setkT(0.7)),
				new Tube(new Ray(new Point3D(80,80,-20),new Vector(1,0,0)),5 )
						.setEmission(new Color(0, 0, 300)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20).setkT(0.7)),

				new Plane(new Point3D(0,100,-100),new Point3D(100,0,-100),new Point3D(0,0,-100))
						.setEmission(new Color(10, 10, 10)) //
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
		//scene.lights.add(new DirectionalLight(new Color(400, 40, 0), new Vector(0, 0, 1)));

		ImageWriter imageWriter = new ImageWriter("refractionRefractionBonus", 600, 600);
		Render render = new Render() //
				.setWriter(imageWriter) //
				.setCamera(camera) //
				.setRayTracer(new RayTracerBasic(scene));

		render.renderImage();
		render.writeToImage();
	}


	@Test
	public void DepthOfField() {
		Camera camera = new Camera(new Point3D(0, 0, 100), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
				.setViewPlaneSize(200, 200).setDistance(100).setApertureDistance(20).setApertureRadius(0.1); //

		scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.1));

		scene.geometries.add( //
				new Sphere(new Point3D(-40,40,-40),40)
						.setEmission(new Color(200, 100, 50)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),

				new Sphere(new Point3D(-80,-40,-80),40)
						.setEmission(new Color(0, 100, 50)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),

				new Plane(new Point3D(0,100,-100),new Point3D(100,0,-100),new Point3D(0,0,-100))
						.setEmission(new Color(10, 10, 10)) //
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
				.setRayTracer(new RayTracerBasic(scene)).setUseDOF(true);

		render.renderImage();
		render.writeToImage();
	}
}
