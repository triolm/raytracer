package mesh;

import images.Color;
import images.ColorImage;
import lights.Light;
import geometry.*;

public class ImageLambert extends Material {
    private ColorImage texture;

    /*
     * This class extends the Material class, and represents a Lambertian
     * (non-shiny) surface material. The only thing it needs to store is the
     * object's natural diffuse color.
     * Methods:
     */
    public ImageLambert(String filename) {
        this.texture = ColorImage.fromFile(filename);
    }

    // Initializes the object's diffuse color.
    public Color computeLighting(Intersection i, Ray viewingRay, Light li) {
        Color base = texture.getColor((int) (i.getImageX() * texture.getWidth()),
                (int) (i.getImageY() * texture.getHeight()));

        Vector direction = li.computeLightDirection(i.getPosition());
        double product = i.getNormal().dot(direction);
        if (product < 0) {
            return new Color(0, 0, 0);
        }
        Color dimmed = base.shade(new Color(product, product, product));
        dimmed = dimmed.shade(li.computeLightColor(i.getPosition()));
        return dimmed;

    }

    public Color getColor(Intersection i) {
        return texture.getColor((int) (i.getImageX() * texture.getWidth()),
                (int) (i.getImageY() * texture.getHeight()));

    }
}