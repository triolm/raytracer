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
                                40, // field of view
                                xResolution / yResolution); // aspect ratio
                Scene s = new Scene(cam);

                // Surface s1 = new Sphere(Scene.blackHolePosition, 2800, new Lambert(new
                // Color(.6, .65, 1)));
                Surface s1 = new Sphere(Scene.blackHolePosition, 2800, new ImageLambert("./assets/earth.jpg"));
                s.addSurface(s1);
                Surface d1 = new Ring(Scene.blackHolePosition, 4000, 0, new Vector(0, .5, .1),
                                new Lambert(new Color(1, 1, 1)));
                s.addSurface(d1);

                Light lt1 = new PointLight(new Color(1, .5, .5), new Point(0, 20000,
                                0));
                s.addLight(lt1);
                Light lt2 = new PointLight(new Color(.5, .5, .5), new Point(0, -20000,
                                0));
                s.addLight(lt2);
                return s;
        }

}
