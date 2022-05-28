package shapes;
import geometry.Intersection;
import geometry.Ray;

public abstract class Surface {
    public Surface() {

    }

    public abstract Intersection intersect(Ray ray);
}