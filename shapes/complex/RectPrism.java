package shapes.complex;

import geometry.*;
import mesh.Material;
import shapes.*;

public class RectPrism extends Surface {
    Surface[] shapes;

    // unrotated rectangular prism
    public RectPrism(Point o, double width, double height, double depth, Material m) {
        shapes = new Surface[6];
        Point w = o.add(new Vector(width, 0, 0));
        Point wh = o.add(new Vector(width, height, 0));
        Point wd = o.add(new Vector(width, 0, depth));
        Point whd = o.add(new Vector(width, height, depth));
        Point h = o.add(new Vector(0, height, 0));
        Point hd = o.add(new Vector(0, height, depth));
        Point d = o.add(new Vector(0, 0, depth));

        shapes[0] = new Quadrilateral(o, w, wh, h, m);
        shapes[1] = new Quadrilateral(o, w, wd, d, m);
        shapes[2] = new Quadrilateral(o, d, hd, h, m);

        shapes[3] = new Quadrilateral(w, wh, whd, wd, m);
        shapes[4] = new Quadrilateral(d, wd, whd, hd, m);
        shapes[5] = new Quadrilateral(h, wh, whd, hd, m);
    }

    public Intersection intersect(Ray r) {
        Intersection smallest = null;
        for (Surface i : shapes) {
            Intersection sect = i.intersect(r);
            if (sect != null && (smallest == null || sect.getDistance() < smallest.getDistance())) {
                smallest = sect;
            }
        }
        return smallest;
    }

}
