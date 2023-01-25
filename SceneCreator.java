import cameras.*;
import geometry.*;
import images.*;
import lights.*;
import mesh.*;
import shapes.*;

public class SceneCreator {

        // shapes floating in space
        public static Scene scene1(double xResolution, double yResolution) {
                PerspectiveCamera cam = new PerspectiveCamera(new Point(0, 0, 0), // camera location
                                new Vector(0, .01, -1), // forward vector/view direction
                                new Vector(0, 1, 0), // up vector
                                30, // field of view
                                    // 18, // field of view
                                xResolution / yResolution); // aspect ratio
                Scene s = new Scene(cam);

                // Surface s1 = new Sphere(Scene.blackHolePosition, Scene.schild, new
                // ImageLambert("./assets/grid.png"));
                // Surface s1 = new Sphere(Scene.blackHolePosition, Scene.schild, new
                // Lambert(new Color(0, 0, 0)));
                // s.addSurface(s1);
                // Surface d1 = new Ring(Scene.blackHolePosition,
                // Scene.schild * 3, Scene.schild, new Vector(0, 1, .1),
                // new Lambert(new Color(1, 1, 1)));
                // Surface d1 = new Ring(Scene.blackHolePosition,
                // Scene.schild * 3, Scene.schild, new Vector(0, 1, .1),
                // new ImageLambert("./assets/image23.png"));
                // s.addSurface(d1);
                Surface d2 = new Ring(new Point(0, 0, Scene.blackHolePosition.getZ() * 4),
                                Scene.schild * 23, 0, new Vector(0, 0, 1),
                                new ImageLambert("./assets/grid3.png"));
                s.addSurface(d2);

                Light lt1 = new PointLight(new Color(1, 1.0, 1.0), new Point(0, 200000,
                                0));
                s.addLight(lt1);
                Light lt2 = new PointLight(new Color(1.0, 1.0, 1), new Point(0, -200000,
                                0));
                s.addLight(lt2);
                // Light lt3 = new PointLight(new Color(1.0, 1, 1.0), new Point(0, 0,
                // Scene.blackHolePosition.getZ() * 2));
                // s.addLight(lt3);
                return s;
        }

        public static Scene rotateBlackHole(double xResolution, double yResolution, double frame) {
                PerspectiveCamera cam = new PerspectiveCamera(new Point(0, 0, 0), // camera location
                                new Vector(0, .01, -1), // forward vector/view direction
                                new Vector(-.5, 1, 0), // up vector
                                20, // field of view
                                xResolution / yResolution); // aspect ratio
                Scene s = new Scene(cam);

                // Surface s1 = new Sphere(Scene.blackHolePosition, Scene.schild,
                // new Vector(0, 0, 1).rotateX(frame * -Math.PI / 15),
                // (new Vector(0, 1, 0)).rotateX(frame * -Math.PI / 15),
                // new ImageLambert("./assets/grid.png"));
                // Surface s1 = new Sphere(Scene.blackHolePosition, Scene.schild, new
                // Lambert(new Color(0, 0, 0)));
                // s.addSurface(s1);
                Surface d1 = new Ring(Scene.blackHolePosition,
                                Scene.schild * 3, Scene.schild,
                                (new Vector(0, 1, .05)).rotateX(frame * -Math.PI / 15),
                                new ImageLambert("./assets/fakedoppler.png"));
                s.addSurface(d1);

                Light lt1 = new PointLight(new Color(1, 1.0, 1.0), new Point(0, 200000,
                                0));
                s.addLight(lt1);
                Light lt2 = new PointLight(new Color(1.0, 1.0, 1), new Point(0, -200000,
                                0));
                s.addLight(lt2);
                // Light lt3 = new PointLight(new Color(1.0, 1, 1.0), new Point(0, 0,
                // Scene.blackHolePosition.getZ() * 2));
                // s.addLight(lt3);
                return s;
        }

        public static Scene alterAccretion(double xResolution, double yResolution, double frame) {
                PerspectiveCamera cam = new PerspectiveCamera(new Point(0, 0, 0), // camera location
                                new Vector(0, .01, -1), // forward vector/view direction
                                new Vector(0, 1, 0), // up vector
                                20, // field of view
                                xResolution / yResolution); // aspect ratio
                Scene s = new Scene(cam);

                Surface s1 = new Sphere(Scene.blackHolePosition, Scene.schild,
                                new Vector(0, 0, 1),
                                (new Vector(0, 1, 0)),
                                new ImageLambert("./assets/grid.png"));
                // Surface s1 = new Sphere(Scene.blackHolePosition, Scene.schild, new
                // Lambert(new Color(0, 0, 0)));
                s.addSurface(s1);
                Surface d1 = new Ring(Scene.blackHolePosition,
                                Scene.schild * (frame / 6) + Scene.schild, 0,
                                (new Vector(0, 1, .05)),
                                new ImageLambert("./assets/image23.png"));
                s.addSurface(d1);

                Light lt1 = new PointLight(new Color(1, 1.0, 1.0), new Point(0, 200000,
                                0));
                s.addLight(lt1);
                Light lt2 = new PointLight(new Color(1.0, 1.0, 1), new Point(0, -200000,
                                0));
                s.addLight(lt2);
                // Light lt3 = new PointLight(new Color(1.0, 1, 1.0), new Point(0, 0,
                // Scene.blackHolePosition.getZ() * 2));
                // s.addLight(lt3);
                return s;
        }

        public static Scene alterAccretion2(double xResolution, double yResolution, double frame) {
                PerspectiveCamera cam = new PerspectiveCamera(new Point(0, 0, 0), // camera location
                                new Vector(0, .01, -1), // forward vector/view direction
                                new Vector(0, 1, 0), // up vector
                                20, // field of view
                                xResolution / yResolution); // aspect ratio
                Scene s = new Scene(cam);

                Surface s1 = new Sphere(Scene.blackHolePosition, Scene.schild,
                                new Vector(0, 0, 1),
                                (new Vector(0, 1, 0)),
                                new ImageLambert("./assets/grid.png"));
                // Surface s1 = new Sphere(Scene.blackHolePosition, Scene.schild, new
                // Lambert(new Color(0, 0, 0)));
                s.addSurface(s1);
                Surface d1 = new Ring(Scene.blackHolePosition,
                                Scene.schild * 5, Scene.schild * (frame / 6) + Scene.schild,
                                (new Vector(0, 1, .05)),
                                new ImageLambert("./assets/image23.png"));
                s.addSurface(d1);

                Light lt1 = new PointLight(new Color(1, 1.0, 1.0), new Point(0, 200000,
                                0));
                s.addLight(lt1);
                Light lt2 = new PointLight(new Color(1.0, 1.0, 1), new Point(0, -200000,
                                0));
                s.addLight(lt2);
                // Light lt3 = new PointLight(new Color(1.0, 1, 1.0), new Point(0, 0,
                // Scene.blackHolePosition.getZ() * 2));
                // s.addLight(lt3);
                return s;
        }

}
