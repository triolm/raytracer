import cameras.*;
import geometry.*;
import images.*;
import lights.*;
import mesh.*;
import shapes.*;
import shapes.complex.*;

public class SceneCreator {

    // shapes floating in space
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

}
