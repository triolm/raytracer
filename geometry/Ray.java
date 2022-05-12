package geometry;

public class Ray {
    private Point point;
    private Vector vector;

    public Ray(Point p, Vector v) {
        point = p;
        vector = v.normalize();
    }

    public Point getPosition() {
        return this.point;
    }

    public Vector getDirection() {
        return this.vector;
    }

    public Point evaluate(double distance) {
        return point.add(vector.scale(distance));
    }
}
