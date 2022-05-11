public class Intersection {
    Point pos;
    Vector norm;
    double dist;
    Material material;

    public Intersection(Point pos, Vector norm, double dist, Material material) {
        this.pos = pos;
        this.norm = norm;
        this.dist = dist;
        this.material = material;
    }

    public Point getPosition() {
        return this.pos;
    }

    public Vector getNormal() {
        return this.norm;
    }

    public double getDistance() {
        return this.dist;
    }

    public Material getMaterial() {
        return this.material;
    }
}
