import java.util.ArrayList;

import cameras.Camera;
import geometry.*;
import images.*;
import shapes.Surface;
import lights.*;

public class Scene {
    final double maxIterations = 600;
    // final double timeScale = 1;
    final double timeScale = 1700000;
    final double speedOfLight = 299792458;
    final double G = 6.6743e-11d;
    final double blackHoleMass = 8.26e36d * 1e50;
    final double blackHoleRadius = 2800;
    final Point blackHolePosition = new Point(0, -100, -3000);

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
            // if it's an ambient light. don't calculate distance & direction
            if (i instanceof AmbientLight) {
                c = c.tint(
                        smallest.getMaterial().getColor(smallest).shade(i.computeLightColor(smallest.getPosition())));
            } else if (!isShadowed(smallest.getPosition(), i)) {
                c = c.tint((smallest.getMaterial().computeLighting(smallest, r, i)));
            }
        }
        if (bouncesLeft <= 0) {
            return c;
        }
        double dev = smallest.getMaterial().getDeviance();
        Vector ogDir = new Vector(smallest.getNormal().getDX() + Math.random() * dev * 2 - dev,
                smallest.getNormal().getDY() + Math.random() * dev * 2 - dev,
                smallest.getNormal().getDZ() + Math.random() * dev * 2 - dev);

        // reflection
        Vector reflect = ogDir.scale((ogDir.dot(r.getDirection().scale(-1))) * 2)
                .subtract(r.getDirection().scale(-1)).normalize();
        Ray reflectRay = new Ray(smallest.getPosition(), reflect, Math.random());
        Color reflectColor = computeVisibleColor(reflectRay, bouncesLeft - 1);
        double reflectivness = smallest.getMaterial().getReflectiveness();
        return c.tint(reflectColor.shade(new Color(reflectivness, reflectivness, reflectivness)));
    }

    public boolean isShadowed(Point p, Light li) {
        Ray shadowRay = new Ray(p, li.computeLightDirection(p), Math.random());
        for (Surface i : surfaces) {
            Intersection sect = i.intersect(shadowRay);
            if (sect != null && sect.getDistance() < li.computeLightDistance(p)) {
                return true;
            }
        }
        return false;

    }

    public void progress(int c, int t) {
        if (c % ((int) t / 20) == 0) {
            System.out.print("\b".repeat(20) + "█".repeat((int) c * 20 / t + 1)
                    + "░".repeat((int) 20 - (c * 20 / t + 1)));
        }
    }

    // overloaded to omit progress bar param
    public ColorImage render(int xRes, int yRes, int numSamples) {
        return render(xRes, yRes, numSamples, false);
    }

    public ColorImage render(int xRes, int yRes, int numSamples, boolean showProgress) {
        int aaRes = (int) Math.sqrt(numSamples);
        ColorImage img = new ColorImage(xRes, yRes);
        for (int x = 0; x < xRes; x++) {
            // progress bar
            if (showProgress) {
                progress(x, xRes);
            }
            // loop through pixels
            for (int y = 0; y < yRes; y++) {
                Color c = new Color(0, 0, 0);

                // loop thruough anti aliasing
                for (int i = 0; i < aaRes; i++) {
                    for (int j = 0; j < aaRes; j++) {

                        // anti aliasing position
                        double u = (x + (i + 0.5) / aaRes) / xRes;
                        double v = (y + (i + 0.5) / aaRes) / yRes;

                        // cast ray
                        Ray ray = camera.generateRay(u, v);
                        Color sample = null;
                        for (int t = 0; t < maxIterations; t++) {
                            for (Surface s : surfaces) {
                                Intersection sect = s.intersect(ray);
                                // System.out.println(sect);
                                if (sect != null) {
                                    if (ray.getPosition().getDist(sect.getPosition()) <= speedOfLight / timeScale) {
                                        sample = computeVisibleColor(ray, 3);
                                        break;
                                    }
                                }
                            }
                            // System.out.println(ray.getDirection() + " " + t);
                            Vector rayVector = ray.getDirection();
                            // .normalize().scale(speedOfLight / timeScale);
                            Point rayPoint = ray.getPosition();

                            double dist = rayPoint.getDist(blackHolePosition);
                            double gravity = (blackHoleMass / Math.pow(dist, 2.0)) / timeScale;
                            Vector gravityVector = blackHolePosition.subtract(rayPoint)
                                    .normalize().scale(gravity);

                            Vector newRayVector = rayVector.normalize().scale(speedOfLight / timeScale)
                                    .cross(gravityVector);

                            Point newRayPoint = rayPoint.add(newRayVector);

                            ray = new Ray(newRayPoint, newRayVector, 0);
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
        if (showProgress) {
            System.out.println();
        }
        return img;
    }
}