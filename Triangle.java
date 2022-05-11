/**
 * Represents a triangle in 3D space.
 * 
 * @author Ben Farrar
 * @version 2019.05.22
 */
public class Triangle extends Surface {
    public Point v0;
    public Point v1;
    public Point v2;
    public Vector normal;
    public Material mat;
    
    //Minimum distance for a valid collision. This prevents the sphere's rays from colliding with itself.
    public static double EPSILON = 1e-6;
    
    public Triangle(Point p0, Point p1, Point p2, Material m){
        v0 = p0;
        v1 = p1;
        v2 = p2;
        normal = (v1.subtract(v0)).cross(v2.subtract(v0)).normalize();
        mat = m;
    }
    
    public Intersection intersect(Ray ray){
        double d = new Point(0,0,0).subtract(v0).dot(normal);
        Point rayOrigin = ray.getPosition();
        Vector rayOriginVec = new Vector(rayOrigin.getX(), rayOrigin.getY(), rayOrigin.getZ());
        double distance = -(rayOriginVec.dot(normal) + d)/(ray.getDirection().dot(normal));
        if (distance>EPSILON){
            Point inter = ray.evaluate(distance);
            double a = (v1.subtract(v0).cross(inter.subtract(v0))).dot(normal);
            double b = (v2.subtract(v1).cross(inter.subtract(v1))).dot(normal);
            double c = (v0.subtract(v2).cross(inter.subtract(v2))).dot(normal);
            if (a>0 && b>0 && c>0){
                if(normal.dot(ray.getDirection()) > 0){
                    return new Intersection(inter, normal.scale(-1), distance, mat);
                }
                else {
                    return new Intersection(inter, normal, distance, mat);
                }
            }
        }
        return null;
    }
    
}
