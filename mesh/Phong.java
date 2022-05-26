package mesh;

import images.Color;
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
}