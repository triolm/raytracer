package geometry;

import mesh.Material;

public class Intersection {
    private Point pos;
    private Vector norm;
    private double dist;
    private Material material;
    private double imageX, imageY;

    public Intersection(Point pos, Vector norm, double dist, Material material) {
        this.pos = pos;
        this.norm = norm;
        this.dist = dist;
        this.material = material;
    }

    public Intersection(Point pos, Vector norm, double dist, double imageX, double imageY, Material material) {
        this.pos = pos;
        this.norm = norm;
        this.dist = dist;
        this.material = material;
        this.imageX = imageX;
        this.imageY = imageY;
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

    public double getImageX() {
        return imageX;
    }

    public double getImageY() {
        return imageY;
    }
}
