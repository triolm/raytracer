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

    public Sphere(Point position, double radius, Material m) {
        center = position;
        r = radius;
        mat = m;
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
                return new Intersection(collision, normal, distance, mat);
            }
        }
        return null;
    }
}