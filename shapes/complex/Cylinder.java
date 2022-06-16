package shapes.complex;

import geometry.*;
import mesh.*;
import shapes.*;

public class Cylinder extends Surface {

    private Surface[] surfaces;

    //two circular sides and one tube
    public Cylinder(Point v1, Point v2, double radius, Material m) {
        surfaces = new Surface[3];
        surfaces[0] = new Tube(v1, v2, radius, m);
        surfaces[1] = (new Ring(v1, radius, 0, v1.subtract(v2).normalize(), m));
        surfaces[2] = (new Ring(v2, radius, 0, v1.subtract(v2).normalize(), m));
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