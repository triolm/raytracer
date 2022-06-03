package shapes.complex;

import geometry.*;
import mesh.*;
import shapes.Surface;
import shapes.Triangle;

public class Quadrilateral extends Surface {

    private Surface[] surfaces;

    public Quadrilateral(Point a, Point b, Point c, Point d, Material m) {
        surfaces = new Surface[2];
        //to make sure rays won't go between the two triangles
        Vector correction = d.subtract(a).normalize().scale(.000000000001);
        surfaces[0] = new Triangle(a, b.add(correction), c.add(correction), m);
        surfaces[1] = new Triangle(c, d, a, m);

    }

    public Intersection intersect(Ray r) {
        Intersection smallest = null;
        for (Surface i : surfaces) {
            Intersection sect = i.intersect(r);
            if (sect != null && (smallest == null || sect.getDistance() < smallest.getDistance())) {
                smallest = sect;
            }
        }
        return smallest;
    }
}