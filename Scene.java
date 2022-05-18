import java.util.ArrayList;
import geometry.*;
import images.*;
import mesh.*;
import lights.*;

public class Scene {
    private Camera camera;
    private ArrayList<Surface> surfaces;
    private ArrayList<Light> lights;

    public Scene(Camera camera) {
        this.camera = camera;
        surfaces = new ArrayList<Surface>();
        lights = new ArrayList<Light>();
    }

    public void addLight(Light li) {
        lights.add(li);
    }

    public void setCamera(Camera newCam) {
        this.camera = newCam;
    }

    public void addSurface(Surface s) {
        surfaces.add(s);
    }

    public Color computeVisibleColor(Ray r) {
        Intersection smallest = null;
        for (Surface i : surfaces) {
            Intersection sect = i.intersect(r);
            if (sect != null && (smallest == null || sect.getDistance() < smallest.getDistance())) {
                smallest = sect;
            }
        }
        Color c = new Color(0, 0, 0);
        if (smallest == null) {
            return c;
        }
        for (Light i : lights) {
            c = c.tint((smallest.getMaterial().computeLighting(smallest, r, i)));
        }
        return c;

        /**
         * First, run through the list of surfaces looking for intersections using the
         * intersect method. Some will be null, indicating there wasn't a collision,
         * while some will return a valid intersection object. As you are iterating
         * through the list, keep track of & store the closest intersection, which is
         * the one with the smallest distance.
         * Next, once you have made it all the way through the list, if there were no
         * valid intersections, return the color black.
         * Otherwise, we have now selected one intersection which is the closest to the
         * ray's origin. Create a new color as black. For each light in the list of
         * lights, calculate the intersection point's lighting using the computeLighting
         * method we wrote in phase 4, and then tint your new black color by this
         * amount, updating the color each time. When you have made it through the
         * entire list of lights, return the new color as the final color for this ray.
         */
    }

    public ColorImage render(int xRes, int yRes) {
        ColorImage img = new ColorImage(xRes, yRes);
        for (int x = 0; x < xRes; x++) {
            for (int y = 0; y < yRes; y++) {
                double u = (x + 0.5) / xRes;
                double v = (y + 0.5) / yRes;

                Ray ray = camera.generateRay(u, v);
                for (Surface i : surfaces) {
                    Intersection sect = i.intersect(ray);
                    if (sect != null) {
                        img.setColor(x, y, computeVisibleColor(ray));
                    }
                }
            }
        }
        return img;
    }
}
/**
 * Use the camera to generate a ray passing through u,v.
 * For each surface in the surfaces list:
 * Find the intersection of the surface with the ray using the intersect method
 * If the intersection is not null, set the ColorImage at this column and row to
 * some new color, which should be different from your background (default)
 * color. (Maybe white for now?)
 * Finally, return the image you created.
 * 
 */