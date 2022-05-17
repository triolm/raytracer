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
        Color dimmed = new Color(color.getR() * product, color.getG() * product, color.getB() * product);
        dimmed = dimmed.shade(li.computeLightColor(i.getPosition()));
        return dimmed;

    }
    /*
     * First, get the direction pointing towards the light source using
     * computeLightDirection. (That method takes a surface point as a parameter,
     * which you can get from the Intersection i.)
     * Next, find the dot product of the intersection's normal vector and the light
     * direction you just got.
     * Then, if the dot product is less than 0, simply return the color black:
     * (0,0,0). (No light is hitting this point, because it's facing away from the
     * light.)
     * Otherwise, create a partly-dimmed color by scaling the diffuse color by the
     * dot product above. This accounts for the AMOUNT of light that is hitting the
     * surface, but not the light's color.
     * Finally, we need to shade this dimmed color by the color of the light itself,
     * which you can get from the light object. Return this final resulting color.
     */
}