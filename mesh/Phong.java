package mesh;

import images.Color;

import java.io.Console;

import geometry.*;
import lights.*;

public class Phong extends Material {
    Color diffuse, specular;
    double exp;

    public Phong(Color diff, Color spec, double exp) {
        this.diffuse = diff;
        this.specular = spec;
        this.exp = exp;

    }

    public Color computeLighting(Intersection i, Ray viewingRay, Light li) {
        Vector direction = li.computeLightDirection(i.getPosition());
        double product = i.getNormal().dot(direction);
        if (product < 0) {
            return new Color(0, 0, 0);
        }
        Color dimmed = diffuse.shade(new Color(product, product, product));
        dimmed = dimmed.shade(li.computeLightColor(i.getPosition()));
        Vector normal = i.getNormal();
        Vector l = li.computeLightDirection(i.getPosition());
        Vector reflect = normal.scale((normal.dot(l)) * 2).subtract(l).normalize();
        double cosine = viewingRay.getDirection().scale(-1).dot(reflect);
        if (cosine < 0) {
            return dimmed;
        }
        double coeff = Math.pow(cosine, exp);
        Color hColor = li.computeLightColor(i.getPosition()).shade(new Color(coeff,
                coeff, coeff)).shade(specular);
        return dimmed.tint(hColor);
    }
    /**
     * 
     * If the cosine of the specular angle is less than 0, that means there is no
     * additional specular color being reflected. Simply stop here and return the
     * scaled diffuse color you calculated before as the final color.
     * Otherwise, keep going and calculate the specular coefficient. This number
     * indicates how much light is reflected. The formula for this is simply (C)e,
     * where C is the cosine of the specular angle, and e is the specular exponent.
     * Now we can finally find the highlight color itself. Start with the light's
     * color, and scale that by the specular coefficient. Then, shade it by the
     * material's base specular color. We now have the color of the highlight at
     * this location.
     * The final step is to combine the highlight color with the scaled diffuse
     * color from before. Use tinting to combine them together (order doesn't
     * matter) and return this final color.
     */
}