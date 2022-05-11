import java.util.ArrayList;

public class Scene {
    private Camera camera;
    private ArrayList<Surface> surfaces;

    public Scene(Camera camera) {
        this.camera = camera;
        surfaces = new ArrayList<Surface>();
    }

    public void setCamera(Camera newCam) {
        this.camera = newCam;
    }

    public void addSurface(Surface s) {
        surfaces.add(s);
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
                        img.setColor(x, y, new Color(1, 1, 1));
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