package geometry;

public class Vector {
    private double dx, dy, dz;

    public Vector(double dx, double dy, double dz) {
        this.dx = dx;
        this.dy = dy;
        this.dz = dz;
    }

    public double getDX() {
        return this.dx;
    }

    public double getDY() {
        return this.dy;
    }

    public double getDZ() {
        return this.dz;
    }

    public Vector scale(double scalar) {
        double newdx = dx * scalar;
        double newdy = dy * scalar;
        double newdz = dz * scalar;
        return new Vector(newdx, newdy, newdz);
    }

    public double dot(Vector v) {
        return dx * v.getDX() + dy * v.getDY() + dz * v.getDZ();
    }

    public Vector cross(Vector v) {
        double newDX = dy * v.getDZ() - dz * v.getDY();
        double newDY = dz * v.getDX() - dx * v.getDZ();
        double newDZ = dx * v.getDY() - dy * v.getDX();
        return new Vector(newDX, newDY, newDZ);
    }

    public Vector subtract(Vector v) {
        double newDX = dx - v.getDX();
        double newDY = dy - v.getDY();
        double newDZ = dz - v.getDZ();
        return new Vector(newDX, newDY, newDZ);
    }

    public Vector add(Vector v) {
        double newDX = dx + v.getDX();
        double newDY = dy + v.getDY();
        double newDZ = dz + v.getDZ();
        return new Vector(newDX, newDY, newDZ);
    }

    public double length() {
        return Math.sqrt(this.dot(this));
    }

    public Vector normalize() {
        return this.scale(1 / this.length());
    }

    // https://matthew-brett.github.io/teaching/rotation_2d.html

    public Vector rotateY(double angle) {
        return new Vector(dx * Math.cos(angle) - dz * Math.sin(angle), dy, dx * Math.sin(angle) + dz * Math.cos(angle))
                .normalize();
    }

    public Vector rotateX(double angle) {
        return new Vector(dx, dy * Math.sin(angle) + dz * Math.cos(angle), dy * Math.cos(angle) - dz * Math.sin(angle))
                .normalize();
    }

    public Vector rotateZ(double angle) {
        return new Vector(dx * Math.sin(angle) + dy * Math.cos(angle), dx * Math.cos(angle) - dy * Math.sin(angle),dz)
                .normalize();
    }

    public String toString() {
        return this.getDX() + " " + this.getDY() + " " + this.getDZ();
    }
}
