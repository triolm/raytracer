package shapes;

import geometry.*;
import mesh.*;

/**
 * Represents a sphere in 3D space.
 * 
 * @author Ben Farrar
 * @version 2019.05.22
 */
public class Sphere extends Surface {
    private Point center;
    private double r;
    private Material mat;

    // Minimum distance for a valid collision. This prevents the sphere's rays from
    // colliding with itself.
    public static double EPSILON = 1e-6;
    public Vector forward, up, right;

    public Sphere(Point position, double radius, Material m) {
        center = position;
        r = radius;
        mat = m;
        this.forward = new Vector(0, 0, 1).normalize();
        this.up = new Vector(0, 1, 0).normalize();
        this.right = forward.cross(up).normalize();
    }

    public Sphere(Point position, double radius, Vector forward, Vector up, Material m) {
        center = position;
        r = radius;
        mat = m;
        this.forward = forward.normalize();
        this.up = up.normalize();
        this.right = forward.cross(up).normalize();
    }

    public Intersection intersect(Ray ray) {
        Vector diff = ray.getPosition().subtract(center);
        double a = ray.getDirection().dot(ray.getDirection());
        double b = (ray.getDirection().scale(2)).dot(diff);
        double c = diff.dot(diff) - (r * r);
        // determinant
        double d = (b * b) - 4 * a * c;
        if (d >= 0) {
            // Confirmed collision
            double distance = ((-b) - Math.sqrt(d)) / (2 * a);
            if (distance < -EPSILON) {
                // Specific for being inside of a sphere (first solution would be behind you)
                distance = ((-b) + Math.sqrt(d)) / (2 * a);
            }
            if (distance > EPSILON) {
                Point collision = ray.evaluate(distance);
                Vector normal = collision.subtract(center).normalize();
                // If this is a collision with the inside of the sphere, make sure the normal
                // points inward as well
                if (normal.dot(ray.getDirection()) > 0) {
                    normal = normal.scale(-1);
                }

                double Y = 1 - (Math.acos(normal.dot(up)) / Math.PI);
                Vector E = normal.subtract(up.scale(normal.dot(up))).normalize();
                double X;
                if (E.dot(right) > 0) {
                    X = 0.5 - (Math.acos(E.dot(forward)) / (Math.PI * 2));
                } else {
                    X = 0.5 + (Math.acos(E.dot(forward)) / (Math.PI * 2));
                }
                return new Intersection(collision, normal, distance, X, Y, mat);
            }
        }
        return null;
    }
}