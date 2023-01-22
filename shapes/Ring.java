package shapes;

import geometry.*;
import mesh.*;

/**
 * Represents a flat 2D torus (like Saturn's rings) in 3D space.
 * This can also represent a disc by setting the inner radius to 0 when
 * constructing.
 * 
 * @author Ben Farrar
 * @version 2022.05.11
 */
public class Ring extends Surface {
    private double r1; // outer radius
    private double r2; // inner radius (of the hole in the middle)
    private Vector normal;
    private Material mat;
    private Point center;
    // Minimum distance for a valid collision. This prevents the disc's rays from
    // colliding with itself.
    public static double EPSILON = 1e-6;

    public Ring(Point position, double outerRadius, double innerRadius, Vector norm, Material m) {
        r1 = outerRadius;
        r2 = innerRadius;
        normal = norm;
        mat = m;
        center = position;
    }

    public Intersection intersect(Ray ray) {
        double d = new Point(0, 0, 0).subtract(center).dot(normal);
        Point rayOrigin = ray.getPosition();
        Vector rayOriginVec = new Vector(rayOrigin.getX(), rayOrigin.getY(), rayOrigin.getZ());
        double distance = -(rayOriginVec.dot(normal) + d) / (ray.getDirection().dot(normal));
        if (distance > EPSILON) {
            Point inter = ray.evaluate(distance);
            double length = center.subtract(inter).length();

            // polar coords
            Vector polarVector = inter.subtract(center);
            double dist = polarVector.length() / r1;
            Vector rot = new Vector(-1, 0, 0);
            double angle = Math.acos(polarVector.normalize().dot(rot));
            double X = (Math.cos(angle) * dist + 1) / 2;
            double Y = (Math.sin(angle) * dist + 1) / 2;

            // System.out.println(angle);

            if (length < r1 && length > r2) {
                if (normal.dot(ray.getDirection()) > 0) {
                    return new Intersection(inter, normal.scale(-1), distance, X, Y, mat);
                } else {
                    return new Intersection(inter, normal, distance, X, Y, mat);
                }
            }
        }
        return null;
    }

}