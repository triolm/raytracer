package cameras;

import geometry.*;

public class Camera {
    // private Point position;
    // private Vector forward;
    // private Vector up;
    // private Vector right;
    // private double xFoV;
    // private double yFoV;

    public Camera() {
    }

    public Ray generateRay(double u, double v) {
        return new Ray(new Point(0, 0, 0), new Vector(0, 0, 0), Math.random());
    }

}
