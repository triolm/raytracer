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

    public Color computeVisibleColor(Ray r, int bouncesLeft) {
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
        if (bouncesLeft <= 0) {
            return c;
        }
        Vector reflect = smallest.getNormal().scale((smallest.getNormal().dot(r.getDirection().scale(-1))) * 2)
                .subtract(r.getDirection().scale(-1)).normalize();
        Ray reflectRay = new Ray(smallest.getPosition(), reflect);
        Color reflectColor = computeVisibleColor(reflectRay, bouncesLeft - 1);
        double reflectivness = smallest.getMaterial().getReflectiveness();
        return c.tint(reflectColor.shade(new Color(reflectivness, reflectivness, reflectivness)));
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

    public ColorImage render(int xRes, int yRes, int numSamples) {
        int aaRes = (int) Math.sqrt(numSamples);
        ColorImage img = new ColorImage(xRes, yRes);
        for (int x = 0; x < xRes; x++) {
            for (int y = 0; y < yRes; y++) {
                Color c = new Color(0, 0, 0);

                for (int i = 0; i < aaRes; i++) {
                    for (int j = 0; j < aaRes; j++) {

                        double u = (x + (i + 0.5) / aaRes) / xRes;
                        double v = (y + (i + 0.5) / aaRes) / yRes;

                        Ray ray = camera.generateRay(u, v);
                        Color sample = null;
                        for (Surface s : surfaces) {
                            Intersection sect = s.intersect(ray);
                            if (sect != null) {
                                sample = computeVisibleColor(ray, 3);
                            }
                        }
                        if (sample != null) {
                            c = c.add(sample);
                        }
                    }
                }
                c = c.scale(1 / Math.pow(aaRes, 2));
                img.setColor(x, y, c);
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