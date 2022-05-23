import geometry.*;
import images.*;
import lights.*;
import mesh.*;
import shapes.*;

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
                Surface s1 = new Sphere(new Point(4, 4, -30), 2, new Lambert(new Color(.4, .6, .8)));
                s.addSurface(s1);

                Surface s2 = new Sphere(new Point(-4, -4, -25), 2, new Lambert(new Color(.6,
                                .6, .8)));
                s.addSurface(s2);

                Surface s3 = new Tube(new Point(-6, 10, -40), new Point(0, -4, -50), 4,
                                new Lambert(new Color(.4, .6, .8)));
                s.addSurface(s3);
                Surface s4 = new Cone(new Point(6, -10, -45), new Point(10, -4, -45), 4,
                                new Lambert(new Color(.8, .6, .4)));
                s.addSurface(s4);

                PointLight lt = new PointLight(new Color(.75, .75, .75), new Point(10, 10,
                                0));
                s.addLight(lt);
                PointLight lt2 = new PointLight(new Color(1, .5, 1), new Point(-20, 20,
                                0));
                s.addLight(lt2);
                return s;
        }

        public static Scene scene2(double xResolution, double yResolution) {
                Camera cam = new Camera(new Point(0, 0, 0), // camera location
                                new Vector(0, 0, -1), // forward vector/view direction
                                new Vector(0, 1, 0), // up vector
                                18, // field of view
                                xResolution / yResolution); // aspect ratio
                Scene s = new Scene(cam);

                s.addSurface(new Tube(new Point(-5, -1, -20), new Point(-3, -1, -25), 1,
                                new Lambert(Colors.LTSKYBLUE)));

                s.addSurface(new Sphere(new Point(-0, -1, -19), 1, new Lambert(Colors.LTYELLOW)));
                s.addSurface(new Cone(new Point(4, -2, -25), new Point(4, 0, -25), 1, new Lambert(Colors.LTGREEN)));
                s.addSurface(new Triangle(new Point(0, -2, 0), new Point(-200, -2, -200), new Point(200, -2, -200),
                                new MirrorPhong(Colors.WHITE, Colors.GREY, 5, .5)));

                PointLight lt = new PointLight(Colors.WHITE, new Point(10, 10,
                                0));
                s.addLight(lt);
                PointLight lt2 = new PointLight(Colors.BLUE, new Point(-10, 10,
                                -0));
                s.addLight(lt2);
                return s;
        }

        public static Scene colorTest(double xResolution, double yResolution) {
                Camera cam = new Camera(new Point(0, 0, 0), // camera location
                                new Vector(0, 0, -1), // forward vector/view direction
                                new Vector(0, 1, 0), // up vector
                                20, // field of view
                                xResolution / yResolution); // aspect ratio
                Scene s = new Scene(cam);

                s.addSurface(new Sphere(new Point(-6, 0, -20), .8, new Phong(Colors.RED, Colors.GREY, 5)));
                s.addSurface(new Sphere(new Point(-4, 0, -20), .8, new Lambert(Colors.ORANGE)));
                s.addSurface(new Sphere(new Point(-2, 0, -20), .8, new Lambert(Colors.YELLOW)));
                s.addSurface(new Sphere(new Point(0, 0, -20), .8, new Phong(Colors.GREEN, Colors.GREY, 5)));
                s.addSurface(new Sphere(new Point(2, 0, -20), .8, new Lambert(Colors.BLUE)));
                s.addSurface(new Sphere(new Point(4, 0, -20), .8, new Lambert(Colors.PURPLE)));
                s.addSurface(new Sphere(new Point(6, 0, -20), .8, new Phong(Colors.GREY, Colors.GREY, 5)));

                s.addSurface(new Sphere(new Point(-6, 2, -20), .8, new Lambert(Colors.LTRED)));
                s.addSurface(new Sphere(new Point(-4, 2, -20), .8, new Phong(Colors.LTORANGE, Colors.GREY, 5)));
                s.addSurface(new Sphere(new Point(-2, 2, -20), .8, new Lambert(Colors.LTYELLOW)));
                s.addSurface(new Sphere(new Point(0, 2, -20), .8, new Lambert(Colors.LTGREEN)));
                s.addSurface(new Sphere(new Point(2, 2, -20), .8, new Phong(Colors.LTBLUE, Colors.GREY, 5)));
                s.addSurface(new Sphere(new Point(4, 2, -20), .8, new Lambert(Colors.LTPURPLE)));
                s.addSurface(new Sphere(new Point(6, 2, -20), .8, new Lambert(Colors.LTGREY)));

                s.addSurface(new Sphere(new Point(-6, -2, -20), .8, new Lambert(Colors.DKRED)));
                s.addSurface(new Sphere(new Point(-4, -2, -20), .8, new Lambert(Colors.DKORANGE)));
                s.addSurface(new Sphere(new Point(-2, -2, -20), .8, new Phong(Colors.DKYELLOW, Colors.DKGREY, 5)));
                s.addSurface(new Sphere(new Point(0, -2, -20), .8, new Lambert(Colors.DKGREEN)));
                s.addSurface(new Sphere(new Point(2, -2, -20), .8, new Lambert(Colors.DKBLUE)));
                s.addSurface(new Sphere(new Point(4, -2, -20), .8, new Phong(Colors.DKPURPLE, Colors.DKGREY, 5)));
                s.addSurface(new Sphere(new Point(6, -2, -20), .8, new Lambert(Colors.DKGREY)));

                PointLight lt = new PointLight(Colors.LTGREY, new Point(10, 16,
                                0));
                s.addLight(lt);
                PointLight lt2 = new PointLight(Colors.DKGREY, new Point(-10, 16, -1));
                s.addLight(lt2);
                return s;
        }

}
