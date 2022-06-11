package cameras;

import geometry.*;

public class PerspectiveCamera extends Camera {
    private Point position;
    private Vector forward;
    private Vector up;
    private Vector right;
    private double xFoV;
    private double yFoV;

    public PerspectiveCamera(Point position, Vector forward, 
    Vector up, double FOV, double aspectRatio) {
        super();
        this.position = position;
        this.forward = forward.normalize();
        this.up = up;
        right = forward.cross(up);
        this.xFoV = Math.toRadians(FOV);
        yFoV = Math.atan(Math.tan(xFoV) / aspectRatio);
    }

    public Point imagePlanePoint(double u, double v) {
        return position.add(forward).add(
                right.scale(2 * (u - 0.5) * Math.tan(xFoV))).add(
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

    public Vector getUp() {
        return this.up;
    }

    public Vector getRight() {
        return this.right;
    }

    public Point getPosition() {
        return this.position;
    }

    public double getXFoV() {
        return this.xFoV;
    }

    public double getYFoV() {
        return this.yFoV;
    }

}
