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
                                10, // field of view
                                xResolution / yResolution); // aspect ratio
                Scene s = new Scene(cam);

                Surface s1 = new Sphere(new Point(0, -100, -30000), 2800, new Lambert(new Color(.4, .6, .8)));
                s.addSurface(s1);
                Surface d1 = new Ring(new Point(0, -100, -30000), 4000, 0, new Vector(0, 1, .5),
                                new Lambert(new Color(1, 1, 1)));
                s.addSurface(d1);

                Light lt2 = new PointLight(new Color(1, 1, 1), new Point(-20, 20,
                                100));
                s.addLight(lt2);
                Light lt1 = new PointLight(new Color(.175, .175, .175), new Point(20, 20,
                                100));
                s.addLight(lt1);
                return s;
        }

}
