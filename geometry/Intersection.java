package geometry;

import mesh.Material;

public class Intersection {
    private Point pos;
    private Vector norm;
    private double dist;
    private Material material;

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
