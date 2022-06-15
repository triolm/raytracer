import cameras.*;
import geometry.*;
import images.*;
import lights.*;
import mesh.*;
import shapes.*;
import shapes.complex.*;

public class SceneCreator {

        public static Scene scene1(double xResolution, double yResolution) {
                PerspectiveCamera cam = new PerspectiveCamera(new Point(0, 0, 0), // camera location
                                new Vector(0, .01, -1), // forward vector/view direction
                                new Vector(0, 1, 0), // up vector
                                19, // field of view
                                xResolution / yResolution); // aspect ratio
                Scene s = new Scene(cam);

                // Each sphere takes a Point (its center), the radius, and a material.
                // For now, since we have not implemented the Material classes, we simply say
                // they are null.
                Surface s1 = new Sphere(new Point(4, 4, -30), 2, new Lambert(new Color(.4, .6, .8)));
                s.addSurface(s1);

                Surface s2 = new Sphere(new Point(-4, -4, -25), 2, new Phong(new Color(.6,
                                .6, .8), Colors.LTGREY, 5));
                s.addSurface(s2);

                Surface s3 = new Tube(new Point(-6, 10, -40), new Point(0, -4, -50), 4,
                                new MirrorPhong(new Color(.4, .6, .8), Colors.LTGREY, 5, .5, 0));
                s.addSurface(s3);
                Surface s4 = new Cone(new Point(6, -10, -45), new Point(10, -4, -45), 4,
                                new Phong(new Color(.8, .6, .4), Colors.LTGREY, 5));
                s.addSurface(s4);

                Light lt = new LightBulb(new Color(.75, .75, .75), new Point(10, 10,
                                0), .1);
                s.addLight(lt);
                Light lt2 = new PointLight(new Color(1, .5, 1), new Point(-20, 20,
                                0));
                s.addLight(lt2);
                return s;
        }

        public static Scene scene2(double xResolution, double yResolution) {
                Camera cam = new DOFCamera(new Point(0, 0, 0), // camera location
                                new Vector(0, 0, -1), // forward vector/view direction
                                new Vector(0, 1, 0), // up vector
                                20, // field of view
                                xResolution / yResolution, 0, 19); // aspect ratio
                Scene s = new Scene(cam);

                s.addSurface(new Cylinder(new Point(-5, -1, -20), new Point(-3, -1, -27), 1,
                                new Lambert(Colors.LTSKYBLUE)));

                s.addSurface(new Quadrilateral(new Point(0, -2, -31),
                                new Point(0, 1, -31), new Point(4, 1, -29),
                                new Point(4, -2, -29), new MirrorPhong(Colors.DKGREY, Colors.WHITE, 5, .8, .001)));

                s.addSurface(new Sphere(new Point(-0, 2, -19), 1, new Lambert(Colors.LTYELLOW)));
                s.addSurface(new Sphere(new Point(5, -1, -14), 1, new Lambert(Colors.LTPURPLE)));
                s.addSurface(new Cone(new Point(4, -2, -25), new Point(4, 0, -25), 1, new Lambert(Colors.LTGREEN)));
                s.addSurface(new Cone(new Point(-4.5, -2, -14), new Point(-4.5, 0, -14), 1, new Lambert(Colors.LTRED)));

                s.addSurface(new Triangle(new Point(0, -2, 0), new Point(-200, -2, -200), new Point(200, -2, -200),
                                new MirrorPhong(Colors.WHITE, Colors.GREY, 5, .5, .01)));
                s.addSurface(new Sphere(new Point(0, 0, 0), 50, new Lambert(Colors.LTBLUE)));

                Light lt = new DirectionalLight(Colors.WHITE, new Vector(0, 1,
                                -.3), 10);
                s.addLight(lt);

                s.addLight(new AmbientLight(new Color(.01, .01, .01)));
                return s;
        }

        public static Scene colorTest(double xResolution, double yResolution) {
                PerspectiveCamera cam = new PerspectiveCamera(new Point(0, 0, 0), // camera location
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

        public static Scene scene3(double xResolution, double yResolution) {
                Camera cam = new DOFCamera(new Point(0, 0, 0), // camera location
                                new Vector(0, -.02, -1), // forward vector/view direction
                                new Vector(0, 1, 0), // up vector
                                20, // field of view
                                xResolution / yResolution, 0, 19); // aspect ratio
                Scene s = new Scene(cam);

                s.addSurface(new Cone(new Point(-4.5, -2, -14), new Point(-4.5, 0.5, -14), 1,
                                new BlinnPhong(Colors.LTRED, Colors.LTGREY, 5)));

                s.addSurface(new Cylinder(new Point(-5, -1, -20), new Point(-3, -1, -27), 1,
                                new BlinnPhong(new Color(1, .75, .5), Colors.LTGREY, 5)));

                s.addSurface(new Sphere(new Point(-0, -1, -19), 1,
                                new BlinnPhong(Colors.LTYELLOW, Colors.LTGREY, 10)));

                s.addSurface(new Cylinder(new Point(3, -2, -33), new Point(3, 0, -33), 1,
                                new BlinnPhong(Colors.LTGREEN, Colors.LTGREY, 5)));

                s.addSurface(new Sphere(new Point(3, -1.25, -20), .75, new Vector(0, 0, 1), new Vector(0, -1, 0),
                                new ImageBlinn("./textures/soccer.png", Colors.LTGREY, 5)));

                s.addSurface(new Cone(new Point(6, -2, -26), new Point(6, 0, -26), 1,
                                new BlinnPhong(Colors.LTBLUE, Colors.LTGREY, 5)));

                s.addSurface(new Cone(new Point(4, -2, -13), new Point(4, -.5, -13), .75,
                                new BlinnPhong(Colors.LTPURPLE, Colors.LTGREY, 5)));

                s.addSurface(new Triangle(new Point(0, -2, 0), new Point(-200, -2, -200), new Point(200, -2, -200),
                                new MirrorPhong(Colors.WHITE, Colors.GREY, 5, .5, .01)));
                s.addSurface(new Sphere(new Point(0, 0, 0), 50, new Lambert(new Color(.8, .8, 1))));

                s.addLight(new LightBulb(new Color(.88, .8, .88), new Point(10, 10, 0), 2));
                s.addLight(new AmbientLight(new Color(.15, .15, .15)));
                return s;

        }

        public static Scene UIScene(double xResolution, double yResolution, PerspectiveCamera cam) {
                Scene s = new Scene(cam);

                s.addSurface(new Sphere(new Point(0, 0, 0), 100, new Lambert(Colors.LTGREY)));

                s.addSurface(new Sphere(new Point(4, 4, -30), 2, new Lambert(new Color(.4,
                                .6, .8))));
                s.addSurface(new Sphere(new Point(-4, -4, -25), 2, new Phong(new Color(.6,
                                .6, .8), Colors.LTGREY, 5)));
                s.addSurface(new Tube(new Point(-6, 10, -40), new Point(0, -4, -50), 4,
                                new Phong(new Color(.4, .6, .8), Colors.LTGREY, 5)));
                s.addSurface(new Cone(new Point(6, -10, -45), new Point(10, -4, -45), 4,
                                new Phong(new Color(.8, .6, .4), Colors.LTGREY, 5)));

                PointLight lt = new PointLight(new Color(.85, .75, .85), new Point(10, 10,
                                0));
                s.addLight(lt);
                return s;
        }

        public static Scene UIScene2(double xResolution, double yResolution, PerspectiveCamera cam) {
                Scene s = new Scene(cam);

                s.addSurface(new Sphere(new Point(0, 0, 0), 100,
                                new BlinnPhong(Colors.LTGREY, Colors.WHITE, .5)));

                s.addSurface(new Sphere(new Point(0, 0, 20), 2, new Lambert(Colors.LTRED)));
                s.addSurface(new Sphere(new Point(14.14, 0, 14.14), 2, new Lambert(Colors.LTORANGE)));

                s.addSurface(new Sphere(new Point(20, 0, 0), 2, new Lambert(Colors.LTYELLOW)));
                s.addSurface(new Sphere(new Point(14.14, 0, -14.14), 2, new Lambert(Colors.LTGREEN)));

                s.addSurface(new Sphere(new Point(0, 0, -20), 2, new Lambert(Colors.LTSKYBLUE)));
                s.addSurface(new Sphere(new Point(-14.14, 0, -14.14), 2, new Lambert(Colors.BLUE)));

                s.addSurface(new Sphere(new Point(-20, 0, 0), 2, new Lambert(Colors.LTBLURPLE)));
                s.addSurface(new Sphere(new Point(-14.14, 0, 14.14), 2, new Lambert(Colors.LTPURPLE)));

                PointLight lt = new PointLight(new Color(.8, .8, .8), new Point(10, 10,
                                0));
                s.addLight(lt);
                return s;
        }

        public static Scene Movie(double xResolution, double yResolution, int frame) {
                Camera cam = new DOFCamera(new Point(0, 0, 0), // camera location
                                new Vector(0, -.05, -1), // forward vector/view direction
                                new Vector(0, 1, 0), // up vector
                                20, // field of view
                                xResolution / yResolution, 0, 21); // aspect ratio
                Scene s = new Scene(cam);

                s.addSurface(new Sphere(new Point(-0, -1, -19), 1,
                                new BlinnPhong(Colors.LTYELLOW, Colors.LTGREY, 10)));

                double dx = 0;
                double dz = 0;
                frame = frame % 20;
                if (frame < 5) {
                        dx = 0.2 * frame;
                        dz = 1 - 0.2 * frame;
                } else if (frame < 10) {
                        frame = frame - 5;
                        dx = 1 - 0.2 * frame;
                        dz = -0.2 * frame;
                } else if (frame < 15) {
                        frame = frame - 10;
                        dx = -0.2 * frame;
                        dz = -1 + 0.2 * frame;
                } else {
                        frame = frame - 15;
                        dx = -1 + 0.2 * frame;
                        dz = 0.2 * frame;
                }

                s.addSurface(new Sphere(new Point(3, -1, -20), 1, new Vector(dx, 0, dz),
                                new Vector(0, -1, 0),
                                new ImageLambert("./textures/earth.jpg")));

                s.addSurface(new Triangle(new Point(0, -2, 0), new Point(-200, -2, -200), new Point(200, -2, -200),
                                new MirrorPhong(Colors.WHITE, Colors.GREY, 5, .5, .01)));

                s.addLight(new LightBulb(new Color(.88, .8, .88), new Point(10, 10, 0), 2));
                s.addLight(new AmbientLight(new Color(.15, .15, .15)));
                return s;

        }

        public static Scene minecraft(double xResolution, double yResolution, PerspectiveCamera cam) {

                // Camera cam = new DOFCamera(new Point(0, 0, 0), // camera location
                // new Vector(0, .1, -1), // forward vector/view direction
                // new Vector(0, 1, 0), // up vector
                // 20, // field of view
                // xResolution / yResolution, 0, 19); // aspect ratio
                Scene s = new Scene(cam);

                s.addSurface(new MinecraftTree(new Point(2, -1, -7), 1));
                // s.addSurface(new MinecraftTree(new Point(-2, -1, -9), 1.5));
                s.addSurface(new Triangle(new Point(-40, -1, 40),
                                new Point(40, -1, 40), new Point(0, -1, -40),
                                new Lambert(new Color(130 / 255.0, 300 / 255.0, 90 / 255.0))));

                s.addSurface(new Sphere(new Point(0, 0, 0), 20, new Lambert(Colors.LTSKYBLUE)));

                s.addLight(new PointLight(Colors.WHITE, new Point(-10, 5, 0)));
                // s.addLight(new AmbientLight(Colors.DKGREY));

                return s;
        }

        public static Scene scene4(double xResolution, double yResolution) {
                Camera cam = new DOFCamera(new Point(0, 0, 0), // camera location
                                new Vector(0, -.04, -1), // forward vector/view direction
                                new Vector(0, 1, 0), // up vector
                                20, // field of view
                                xResolution / yResolution, 0, 19); // aspect ratio
                Scene s = new Scene(cam);

                s.addSurface(new Sphere(new Point(0, -.5, -19), 1.5, new Vector(0, 0, 1), new Vector(0, -1, 0),
                                new MirrorPhong(new Color(0, 0, 0), Colors.LTGREY, 5, .75, .00)));

                s.addSurface(new Sphere(new Point(3, -1.25, -26), .75, new Vector(0, 0, 1), new Vector(0, -1, 0),
                                new BlinnPhong(Colors.LTYELLOW, Colors.LTGREY, 5)));

                s.addSurface(new Sphere(new Point(-3, -1.5, -18), .5, new Vector(0, 0, 1), new Vector(0, -1, 0),
                                new BlinnPhong(Colors.LTRED, Colors.LTGREY, 5)));

                s.addSurface(new Sphere(new Point(-3, -1.75, -13), .25, new Vector(0, 0, 1), new Vector(0, -1, 0),
                                new BlinnPhong(Colors.LTYELLOW, Colors.LTGREY, 5)));

                s.addSurface(new Sphere(new Point(3.5, -1.75, -11), .25, new Vector(0, 0, 1), new Vector(0, -1, 0),
                                new BlinnPhong(Colors.LTRED, Colors.LTGREY, 5)));

                s.addSurface(new Sphere(new Point(-1, -1.5, -12), .5, new Vector(0, 0, 1), new Vector(0, -1, 0),
                                new BlinnPhong(Colors.LTPURPLE, Colors.LTGREY, 5)));

                s.addSurface(new Sphere(new Point(-3, -1.75, -28), .25, new Vector(0, 0, 1), new Vector(0, -1, 0),
                                new BlinnPhong(Colors.LTGREEN, Colors.LTGREY, 5)));

                s.addSurface(new Sphere(new Point(5, -1.25, -16), .75, new Vector(0, 0, 1), new Vector(0, -1, 0),
                                new BlinnPhong(Colors.LTYELLOW, Colors.LTGREY, 5)));

                s.addSurface(new Sphere(new Point(-6, -1.25, -22), .75, new Vector(0, 0, 1), new Vector(0, -1, 0),
                                new BlinnPhong(Colors.LTGREEN, Colors.LTGREY, 5)));

                s.addSurface(new Sphere(new Point(7, -1.5, -32), .5, new Vector(0, 0, 1), new Vector(0, -1, 0),
                                new BlinnPhong(Colors.LTBLUE, Colors.LTGREY, 5)));

                s.addSurface(new Sphere(new Point(-3.75, -1.75, -11), .25, new Vector(0, 0, 1), new Vector(0, -1, 0),
                                new BlinnPhong(Colors.LTBLUE, Colors.LTGREY, 5)));

                s.addSurface(new Sphere(new Point(1, -1.75, -13), .25, new Vector(0, 0, 1), new Vector(0, -1, 0),
                                new BlinnPhong(Colors.LTGREEN, Colors.LTGREY, 5)));

                s.addSurface(new Sphere(new Point(3, -1.5, -15), .5, new Vector(0, 0, 1), new Vector(0, -1, 0),
                                new BlinnPhong(Colors.LTPURPLE, Colors.LTGREY, 5)));

                s.addSurface(new Triangle(new Point(0, -2, 0), new Point(-200, -2, -200), new Point(200, -2, -200),
                                new MirrorPhong(Colors.WHITE, Colors.GREY, 5, .5, .01)));

                s.addSurface(new Sphere(new Point(0, 0, 0), 50, new Lambert(new Color(.5, .8, 1))));

                s.addLight(new LightBulb(new Color(.33, .1, .33), new Point(3, 10, 0), 2));
                s.addLight(new LightBulb(new Color(.5, .5, .5), new Point(-10, 10, 0), 2));
                s.addLight(new AmbientLight(new Color(.15, .15, .15)));
                return s;

        }

        public static Scene pokemon(double xResolution, double yResolution) {
                Camera cam = new PerspectiveCamera(new Point(0, 0, -13), // camera location
                                new Vector(-.03, 0, 1), // forward vector/view direction
                                new Vector(0, 1, 0), // up vector
                                30, // field of view
                                xResolution / yResolution); // aspect ratio
                Scene s = new Scene(cam);
                s.addLight(new PointLight(Colors.WHITE, new Point(10, 10, -15)));
                s.addLight(new AmbientLight(Colors.DKGREY));

                s.addSurface(new Triangle(new Point(-1000, -2, -1000), new Point(1000, -2, -1000),
                                new Point(0, -2, 1000), new MirrorPhong(Colors.LTGREY, Colors.WHITE, 5,.5,.01)));
                s.addSurface(new Sphere(new Point(0, 0, 0), 40, new Lambert(new Color(.75, .75, 1))));

                OBJParser.parse("./assets/bulbasaur.obj", s, .1, new Vector(-5, -2, 0),
                                new Lambert(new Color(.5, 1, .8)));
                OBJParser.parse("./assets/charmander.obj", s, .1, new Vector(-1, -2, 0),
                                new Lambert(new Color(1, .6, .5)));
                OBJParser.parse("./assets/squirtle.obj", s, .1, new Vector(3, -2, 0),
                                new Lambert(new Color(.4, .7, 1)));

                return s;
        }
}
