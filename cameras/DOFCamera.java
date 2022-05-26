package cameras;

import geometry.*;

public class DOFCamera extends Camera {
    private Point position;
    private Vector forward;
    private Vector up;
    private Vector right;
    private double xFoV;
    private double yFoV;
    private double fLength;
    private double size;

    public DOFCamera(Point position, Vector forward, Vector up, double FOV, double aspectRatio, double size,
            double fLength) {
        super();
        this.position = position;
        this.forward = forward.normalize();
        this.up = up;
        right = forward.cross(up);
        this.xFoV = Math.toRadians(FOV);
        yFoV = Math.atan(Math.tan(xFoV) / aspectRatio);
        this.fLength = fLength;
        this.size = size;
    }

    public Point imagePlanePoint(double u, double v) {
        return position.add(forward.scale(fLength)).add(
                right.scale(2 * (u - 0.5) * Math.tan(xFoV)).scale(fLength))
                .add(up.scale(2 * (v - 0.5) * Math.tan(yFoV)).scale(fLength));
    }

    public Ray generateRay(double u, double v) {
        Point ipp = imagePlanePoint(u, v);
        Point randPos = position.add(right.scale(Math.random() * size))
                .add(up.scale(Math.random() * size));
        Vector rayVector = ipp.subtract(randPos);
        return new Ray(randPos, rayVector);
    }

    public Vector getForward() {
        return this.forward;
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
