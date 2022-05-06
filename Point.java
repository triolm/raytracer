public class Point {
    private double x, y, z;

    public Point(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public double getZ() {
        return this.z;
    }

    public Point add(Vector v) {
        return new Point(x + v.getDX(), y + v.getDY(), z + v.getDZ());
    }

    public Vector add(Point p) {
        return new Vector(x + p.getX(), y + p.getY(), z + p.getZ());
    }

}