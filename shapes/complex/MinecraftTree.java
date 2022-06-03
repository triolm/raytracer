package shapes.complex;

import geometry.*;
import mesh.Lambert;
import shapes.*;
import images.*;

public class MinecraftTree extends Surface {
    Surface[] surfaces;

    public MinecraftTree(Point origin, double trunkHeight) {
        surfaces = new Surface[5];

        Lambert greenLambert = new Lambert(new Color(10.0 / 255, 120.0 / 255, 10.0 / 255));

        surfaces[0] = new RectPrism(origin, .5, trunkHeight, .5, new Lambert(Colors.LTBROWN));
        surfaces[1] = new RectPrism(origin.add(new Vector(-1, trunkHeight, -1)), 2.5, 1, 2.5,
                greenLambert);
        surfaces[2] = new RectPrism(origin.add(new Vector(-.5, 1 + trunkHeight, -.5)), 1.5, .5,
                1.5, greenLambert);
        surfaces[3] = new RectPrism(origin.add(new Vector(0, 1.5 + trunkHeight, -.5)), .5, .5, 1.5,
                greenLambert);
        surfaces[4] = new RectPrism(origin.add(new Vector(-.5, 1.5 + trunkHeight, 0)), 1.5, .5, .5,
                greenLambert);
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
