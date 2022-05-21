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
            if (!isShadowed(smallest.getPosition(), i)) {
                c = c.tint((smallest.getMaterial().computeLighting(smallest, r, i)));
            }
        }
        return c;
    }

    public boolean isShadowed(Point p, Light li) {
        Ray shadowRay = new Ray(p, li.computeLightDirection(p));
        for (Surface i : surfaces) {
            Intersection sect = i.intersect(shadowRay);
            if (sect != null && sect.getDistance() < li.computeLightDistance(p)) {
                return true;
            }
        }
        return false;

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