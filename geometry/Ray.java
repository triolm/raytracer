package geometry;

public class Ray {
    private Point point;
    private Vector vector;
    private double time;

    public Ray(Point p, Vector v, double time) {
        point = p;
        vector = v.normalize();
        this.time = time;
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

    public double gettime(){
        return time;
    }
}
