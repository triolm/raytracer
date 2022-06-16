package lights;

import images.Color;
import geometry.Point;
import geometry.Vector;

public class DirectionalLight extends Light {
    private Color color;
    private Vector v;
    private double maxShadowDist;

    public DirectionalLight(Color c, Vector v, double maxShadowDist) {
        color = c;
        this.v = v;
        this.maxShadowDist = maxShadowDist;
    }

    public Vector computeLightDirection(Point surfacePoint) {
        return v;
    }

    public Color computeLightColor(Point surfacePoint) {
        return color;
    }

    // return the max distance apart objects can be in order for a shadow to form.
    // this prevents the skybox from casting a shadow on objects at its center
    public double computeLightDistance(Point surfacePoint) {
        return maxShadowDist;
    }
}