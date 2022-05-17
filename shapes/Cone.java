package shapes;
import geometry.Intersection;
import geometry.Point;
import geometry.Ray;
import geometry.Vector;
import mesh.Material;
import mesh.Surface;

/**
 * Represents a cone in 3D space. This shape is uncapped (you can cap with a
 * ring or disc as required).
 *
 * @author Ben Farrar
 * @version 2022.05.12
 */
public class Cone extends Surface {
    private Point peak, base;
    private double r;
    private Material mat;
    private Vector axis;
    private double height;
    private double slope;

    // Minimum distance for a valid collision. This prevents the surface's rays from
    // colliding with itself.
    public static double EPSILON = 1e-6;

    public Cone(Point centerBase, Point top, double radius, Material m) {
        base = centerBase;
        peak = top;
        r = radius;
        mat = m;
        // axis starts at peak and points towards base
        // peak is considered the "origin" to make the r^2 = z^2 calculations work later
        Vector pole = base.subtract(peak);
        axis = pole.normalize();
        height = pole.length();
        slope = height / r;
    }

    public Intersection intersect(Ray ray) {
        // Adapted from Tube (uncapped cylinder) code
        // Original cylinder code sources:
        // Based on
        // https://math.stackexchange.com/questions/3248356/calculating-ray-cylinder-intersection-points
        // and https://www.cl.cam.ac.uk/teaching/1999/AGraphHCI/SMAG/node2.html
        // Build a new reference frame such that the cone axis is z,
        // and the shortest line from the axis to the ray is x
        // Shortest line is by definition perpendicular to the ray and the axis
        // Vector frameX = axis.cross(ray.getDirection()).normalize(); //this appears to
        // be left-hand rule
        Vector frameX = ray.getDirection().cross(axis).normalize();
        Vector frameZ = axis; // just aliasing
        Vector frameY = frameZ.cross(frameX).normalize();

        // Get a vector that connects two points on the axis and the ray, any will do.
        Vector anyConnection = ray.getPosition().subtract(peak);
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
        // This requires moving the origin to the peak of the cone first.
        // MATH NOTE: We can ignore lots of arithmetic during projection by ensuring all
        // frame vectors have length 1.
        Vector offsetFromPeak = ray.getPosition().subtract(peak);
        Point rayStartInFrame = new Point(
                offsetFromPeak.dot(frameX), offsetFromPeak.dot(frameY), offsetFromPeak.dot(frameZ));

        // Then, project into the new frame's axes. As mentioned above, the ray's x is
        // constant, so dx = 0.
        // rayDir.dot(frameX) always returns 0.
        Vector rayDir = ray.getDirection().normalize();
        Vector rayMoveInFrame = new Vector(
                0, rayDir.dot(frameY), rayDir.dot(frameZ)).normalize();

        Ray rayInFrame = new Ray(rayStartInFrame, rayMoveInFrame);

        // apply quadratic formula (for intersection of a ray with a circle) to the ray
        // I lifted these straight from the source above, but ultimately it's because
        // within this new reference frame,
        // whenever x^2 + y^2 = r^2, THAT IS A HIT by definition!
        // So we go get the parameterized ray equations and just plug them into the
        // formula above, and solve for t.
        // That gets an equation in the form at^2 + bt + c = 0, which is solvable via
        // the quadratic equation.
        // (radius/height) (aka 1/slope) is a scalar on z (and dz) in order to get the
        // radius at that z
        // Consider cone with h = 1 (and therefore at "base" z = 1), r = .5
        // slope is 2, so r = z / slope

        // a=dx^2+dy^2-(dz/slope)^2, b=2x*dx+2y*dy-2z*dz/(slope^2), and
        // c=x^2+y^2-(z/slope)^2
        // this simplifies out a little because all the dx terms are 0
        double a = Math.pow(rayMoveInFrame.getDY(), 2) - Math.pow(rayMoveInFrame.getDZ() / slope, 2);
        double b = 2 * rayStartInFrame.getY() * rayMoveInFrame.getDY()
                - 2 * rayStartInFrame.getZ() * rayMoveInFrame.getDZ() / Math.pow(slope, 2);
        double c = Math.pow(rayStartInFrame.getX(), 2) + Math.pow(rayStartInFrame.getY(), 2)
                - Math.pow(rayStartInFrame.getZ() / slope, 2);

        double determinant = (b * b) - 4 * a * c;

        // This should have already been covered by the shortest-distance check above
        // but it's cheap to double-check.
        if (determinant < 0) {
            return null;
        }

        double distanceInFrame = ((-b) - Math.sqrt(determinant)) / (2 * a);
        // System.out.println("HIT DISTANCE: " + distanceInFrame);

        if (distanceInFrame < -EPSILON) {
            // Specific check for being inside of a cone (first solution would be behind
            // you)
            distanceInFrame = ((-b) + Math.sqrt(determinant)) / (2 * a);
        }

        // If both solutions are behind you, or you're colliding with yourself, no
        // collision.
        if (distanceInFrame < EPSILON) {
            return null;
        }

        // At this point we have a confirmed collision with the infinite cone.
        // First locate it in-frame.
        Point collisionInFrame = rayInFrame.evaluate(distanceInFrame);

        // Because we set the origin, z=0 is the peak of the cone.
        // The height of the cone is the other.
        if (collisionInFrame.getZ() < 0 || collisionInFrame.getZ() > height) {

            // If the collision doesn't fit between those, check for a secondary collision
            // "through" an endcap.
            // (e.g. looking "down the funnel" from the side: first collision is z>height,
            // but second collision would be z<height.)
            distanceInFrame = ((-b) + Math.sqrt(determinant)) / (2 * a);
            // double-check distance because the new collision might be yourself
            if (distanceInFrame < EPSILON) {
                return null;
            }

            collisionInFrame = rayInFrame.evaluate(distanceInFrame);
            if (collisionInFrame.getZ() < 0 || collisionInFrame.getZ() > height) {
                // If the secondary collision isn't in the right z range either, you're looking
                // above, below, or through.
                return null;
            }
        }

        // Now that we have the distance, we can generate the return value based off the
        // original ray.
        // (This works because distance scaling is the same in both reference frames.)
        Point collision = ray.evaluate(distanceInFrame);

        // To calculate the normal, we can subtract off the position at the same height
        // in the center of the cone,
        // then apply some in-frame z movement based on the slope.
        // Start with vertex1 (base of the cylinder) and apply movement using the
        // in-frame Z coords.
        Point centeredCollision = peak.add(frameZ.scale(collisionInFrame.getZ()));
        Vector normal = collision.subtract(centeredCollision).normalize().add(frameZ.scale(-1 / slope)).normalize();

        // If this is a collision with the inside of the cone, make sure the normal
        // points inward as well
        if (normal.dot(ray.getDirection()) > 0) {
            normal = normal.scale(-1);
        }
        return new Intersection(collision, normal, distanceInFrame, mat);
    }
}
