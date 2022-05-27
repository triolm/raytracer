package mesh;

import images.Color;
import geometry.*;
import lights.*;

public class BlinnPhong extends Material {
    Color diffuse, specular;
    double exp;

    public BlinnPhong(Color diff, Color spec, double exp) {
        this.diffuse = diff;
        this.specular = spec;
        this.exp = exp;

    }

    public Color computeLighting(Intersection i, Ray viewingRay, Light li) {
        Vector L = li.computeLightDirection(i.getPosition());
        double product = i.getNormal().dot(L);
        if (product < 0) {
            return new Color(0, 0, 0);
        }
        Color dimmed = diffuse.shade(new Color(product, product, product));
        dimmed = dimmed.shade(li.computeLightColor(i.getPosition()));

        Vector normal = i.getNormal();

        Vector sum = L.add(viewingRay.getDirection().scale(-1));
        Vector H = sum.scale(1 / sum.length());

        double cosine = normal.dot(H);

        if (cosine < 0) {
            return dimmed;
        }
        double coeff = Math.pow(cosine, exp);
        Color hColor = li.computeLightColor(i.getPosition()).shade(new Color(coeff,
                coeff, coeff)).shade(specular);
        return dimmed.tint(hColor);
    }

    public Color getColor(Intersection i) {
        return diffuse;
    }
}