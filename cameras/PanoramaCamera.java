package cameras;

import geometry.*;

public class PanoramaCamera extends Camera {
    private Point position;
    private Vector forward;
    private Vector up;
    private Vector right;
    private double xFoV;
    private double yFoV;

    public PanoramaCamera(Point position, Vector forward, Vector up, double FOV, double aspectRatio) {
        super();
        this.position = position;
        this.forward = forward.normalize();
        this.up = up;
        right = forward.cross(up);
        this.xFoV = Math.toRadians(FOV);
        yFoV = Math.atan(xFoV / aspectRatio);
    }

    public Point imagePlanePoint(double u, double v) {
        return position.add(forward.scale(Math.sin((u - 0.5) * 2
                * xFoV))).add(
                        right.scale(Math.cos((u - 0.5) * 2
                                * xFoV)))
                .add(
                        up.scale(2 * (v - 0.5) * Math.tan(yFoV)));
    }

    public Ray generateRay(double u, double v) {
        Point ipp = imagePlanePoint(u, v);
        Vector rayVector = ipp.subtract(position);
        return new Ray(position, rayVector, Math.random());
    }

    public Vector getForward() {
        return this.forward;
    }

    public Vector getRight() {
        return this.right;
    }

}
