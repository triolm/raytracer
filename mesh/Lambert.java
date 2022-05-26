package mesh;

import images.Color;
import lights.Light;
import geometry.*;

public class Lambert extends Material {
    private Color color;

    /*
     * This class extends the Material class, and represents a Lambertian
     * (non-shiny) surface material. The only thing it needs to store is the
     * object's natural diffuse color.
     * Methods:
     */
    public Lambert(Color c) {
        this.color = c;
    }

    // Initializes the object's diffuse color.
    public Color computeLighting(Intersection i, Ray viewingRay, Light li) {
        Vector direction = li.computeLightDirection(i.getPosition());
        double product = i.getNormal().dot(direction);
        if (product < 0) {
            return new Color(0, 0, 0);
        }
        Color dimmed = color.shade(new Color(product, product, product));
        dimmed = dimmed.shade(li.computeLightColor(i.getPosition()));
        return dimmed;

    }

    public Color getColor() {
        return color;
    }
}