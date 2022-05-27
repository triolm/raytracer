package mesh;

import images.*;
import geometry.*;
import lights.*;

public class ImagePhong extends Material {
    Color specular;
    double exp;
    ColorImage texture;

    public ImagePhong(String filename, Color spec, double exp) {
        this.texture = ColorImage.fromFile(filename);
        this.specular = spec;
        this.exp = exp;

    }

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

    public Color getColor(Intersection i) {
        return texture.getColor((int) (i.getImageX() * texture.getWidth()),
                (int) (i.getImageY() * texture.getHeight()));

    }
}