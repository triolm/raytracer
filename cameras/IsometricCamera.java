package cameras;

import geometry.*;

public class IsometricCamera extends Camera {
    private Point position;
    private Vector forward;
    private Vector up;
    private Vector right;
    private double xFoV;
    private double yFoV;

    public IsometricCamera(Point position, Vector forward, Vector up, double FOV, double aspectRatio) {
        super();
        this.position = position;
        this.forward = forward.normalize();
        this.up = up;
        right = forward.cross(up);
        this.xFoV = FOV;
        yFoV = (xFoV) / aspectRatio;
    }

    public Point imagePlanePoint(double u, double v) {
        return position.add(forward).add(
                right.scale(2 * (u - 0.5) * xFoV)).add(
                        up.scale(2 * (v - 0.5) * yFoV));
    }

    public Ray generateRay(double u, double v) {
        Point ipp = imagePlanePoint(u, v).add(forward.scale(-1));
        return new Ray(ipp, forward, Math.random());
    }

    public Vector getForward() {
        return this.forward;
    }

    public Vector getRight() {
        return this.right;
    }

}
