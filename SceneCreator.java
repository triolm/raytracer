import geometry.Point;
import geometry.Vector;
import images.Color;
import lights.PointLight;
import mesh.Lambert;
import mesh.Surface;
import shapes.*;

/**
 * Details the static methods to creating various scenes for use in the
 * raytracer.
 * scene1() is included as an example. You can add more static methods (for
 * example scene2(),
 * scene3(), etc.) to create different scenes without affecting scene1.
 *
 * @author Ben Farrar
 * @version 2019.05.22
 */
public class SceneCreator {
        public static Scene scene1(double xResolution, double yResolution) {
                Camera cam = new Camera(new Point(0, 0, 0), // camera location
                                new Vector(0, 0, -1), // forward vector/view direction
                                new Vector(0, 1, 0), // up vector
                                20, // field of view
                                xResolution / yResolution); // aspect ratio
                Scene s = new Scene(cam);

                // Each sphere takes a Point (its center), the radius, and a material.
                // For now, since we have not implemented the Material classes, we simply say
                // they are null.
                Surface s1 = new Sphere(new Point(0, 0, -20), 3, null);
                s.addSurface(s1);
                Surface s2 = new Sphere(new Point(0, 4, -15), 1, null);
                s.addSurface(s2);
                Surface s3 = new Sphere(new Point(5, 0, -20), 1.5, null);
                s.addSurface(s3);
                // Each triangle takes 3 Points (its vertexes), and a material.
                Surface t1 = new Triangle(new Point(-3.5, -1, -15), new Point(-3.5, 1, -15), new Point(-5, 0, -16),
                                null);
                s.addSurface(t1);
                Surface floor = new Triangle(new Point(0, -5, 0), new Point(3000, -5, -1000),
                                new Point(-3000, -5, -1000),
                                null);
                s.addSurface(floor);

                return s;
        }

        public static Scene scene2(double xResolution, double yResolution) {
                Camera cam = new Camera(new Point(0, 0, 0), // camera location
                                new Vector(0, 0, -1), // forward vector/view direction
                                new Vector(0, 1, 0), // up vector
                                20, // field of view
                                xResolution / yResolution); // aspect ratio
                Scene s = new Scene(cam);

                // Each sphere takes a Point (its center), the radius, and a material.
                // For now, since we have not implemented the Material classes, we simply say
                // they are null.
                Surface s1 = new Sphere(new Point(4, 4, -30), 2, new Lambert(new Color(.4, .6, .8)));
                s.addSurface(s1);

                Surface s2 = new Sphere(new Point(-4, -4, -25), 2, new Lambert(new Color(.6, .4, 1)));
                s.addSurface(s2);
                Surface s3 = new Tube(new Point(-6, 10, -40), new Point(0, -4, -50), 4,
                                new Lambert(new Color(.4, .6, .8)));
                s.addSurface(s3);
                Surface s4 = new Cone(new Point(6, -10, -45), new Point(10, -4, -45), 4,
                                new Lambert(new Color(.8, .6, .4)));
                s.addSurface(s4);

                PointLight lt = new PointLight(new Color(.025, .025, .025), new Point(10, 10, 0));
                s.addLight(lt);
                PointLight lt2 = new PointLight(new Color(.03, .01, .02), new Point(-20, 20,
                                0));
                s.addLight(lt2);
                return s;
        }

}
