package shapes;

import geometry.*;
import mesh.*;

/**
 * Represents a tube (uncapped finite cylinder) in 3D space.
 * 
 * @author Ben Farrar
 * @version 2022.05.13
 */
public class Tube extends Surface {
    private Point vertex1, vertex2;
    private double r;
    private Material mat;
    private Vector axis;

    // Minimum distance for a valid collision. This prevents the surface's rays from
    // colliding with itself.
    public static double EPSILON = 1e-6;

    public Tube(Point v1, Point v2, double radius, Material m) {
        vertex1 = v1;
        vertex2 = v2;
        r = radius;
        mat = m;
        axis = vertex2.subtract(vertex1).normalize();
    }

    public Intersection intersect(Ray ray) {
        // Based on
        // https://math.stackexchange.com/questions/3248356/calculating-ray-cylinder-intersection-points
        // and https://www.cl.cam.ac.uk/teaching/1999/AGraphHCI/SMAG/node2.html
        // Build a new reference frame such that the cylinder axis is z,
        // and the shortest line from the axis to the ray is x
        // Shortest line is by definition perpendicular to the ray and the axis
        // Vector frameX = axis.cross(ray.getDirection()).normalize();
        Vector frameX = ray.getDirection().cross(axis).normalize();
        Vector frameZ = axis; // just aliasing
        Vector frameY = frameZ.cross(frameX).normalize();

        // Get a vector that connects two points on the axis and the ray, any will do.
        Vector anyConnection = ray.getPosition().subtract(vertex1);
        // The x distance of the ray is constant because the x axis is defined by the
        // ray being
        // perpendicular to it. So, project into x to quickly eliminate rays that don't
        // get close.
        double shortestDistance = anyConnection.dot(frameX);
        if (Math.abs(shortestDistance) > r) {
            return null;
        }

        // The ray can now be described in the new reference frame:
        // First, project the original start point into the new frame.
        // This requires moving the origin to the base of the cylinder first.
        Vector offsetFromV1 = ray.getPosition().subtract(vertex1);
        Point rayStartInFrame = new Point(
                offsetFromV1.dot(frameX), offsetFromV1.dot(frameY), offsetFromV1.dot(frameZ));

        // Then, project into the new frame's axes. As mentioned above, the ray's x is
        // constant, so dx = 0.
        // rayDir.dot(frameX) always returns 0.
        Vector rayDir = ray.getDirection().normalize();
        Vector rayMoveInFrame = new Vector(
                0, rayDir.dot(frameY), rayDir.dot(frameZ)).normalize();

        Ray rayInFrame = new Ray(rayStartInFrame, rayMoveInFrame);

        // apply quadratic formula (for intersection of a ray with a circle) to the ray
        // we don't care about dz yet because the cylinder is infinite for our purposes
        // right now
        // a=dx^2+dy^2, b=2x*dx+2y*dy, and c=x^2+y^2-r^2
        // this simplifies out a little because all the dx terms are 0
        double a = Math.pow(rayMoveInFrame.getDY(), 2);
        double b = 2 * rayStartInFrame.getY() * rayMoveInFrame.getDY();
        double c = Math.pow(rayStartInFrame.getX(), 2) + Math.pow(rayStartInFrame.getY(), 2) - (r * r);

        double determinant = (b * b) - 4 * a * c;

        // This should have already been covered by the shortest-distance check above
        // but it's cheap to double-check.
        if (determinant < 0) {
            return null;
        }

        double distanceInFrame = ((-b) - Math.sqrt(determinant)) / (2 * a);

        if (distanceInFrame < -EPSILON) {
            // Specific check for being inside of a cylinder (first solution would be behind
            // you)
            distanceInFrame = ((-b) + Math.sqrt(determinant)) / (2 * a);
        }

        // If both solutions are behind you, or you're colliding with yourself, no
        // collision.
        if (distanceInFrame < EPSILON) {
            return null;
        }

        // At this point we have a confirmed collision with the infinite cylinder.
        // First locate it in-frame.
        Point collisionInFrame = rayInFrame.evaluate(distanceInFrame);

        // Because we set the origin, the base z=0 is one end of the cylinder.
        // The height of the cylinder is the other.
        if (collisionInFrame.getZ() < 0 || collisionInFrame.getZ() > vertex2.subtract(vertex1).length()) {

            // If the collision doesn't fit between those, check for a secondary collision
            // "through" an endcap.
            // (e.g. looking up from underneath: first collision is z<0, but second
            // collision would be z>0.)
            distanceInFrame = ((-b) + Math.sqrt(determinant)) / (2 * a);
            // double-check distance because the new collision might be yourself
            if (distanceInFrame < EPSILON) {
                return null;
            }

            collisionInFrame = rayInFrame.evaluate(distanceInFrame);
            if (collisionInFrame.getZ() < 0 || collisionInFrame.getZ() > vertex2.subtract(vertex1).length()) {
                // If the secondary collision isn't in the right z range either, you're looking
                // above, below, or through.
                return null;
            }
        }

        // Now that we have the distance, we can generate the return value based off the
        // original ray.
        // (This works because distance scaling is the same in both reference frames.)
        Point collision = ray.evaluate(distanceInFrame);

        // To calculate the normal, we need the position at the same height in the
        // center of the cylinder.
        // Start with vertex1 (base of the cylinder) and apply movement using the
        // in-frame Z coords.
        Point centeredCollision = vertex1.add(frameZ.scale(collisionInFrame.getZ()));
        Vector normal = collision.subtract(centeredCollision).normalize();

        // If this is a collision with the inside of the tube, make sure the normal
        // points inward as well
        if (normal.dot(ray.getDirection()) > 0) {
            normal = normal.scale(-1);
        }
        return new Intersection(collision, normal, distanceInFrame, mat);
    }
}
