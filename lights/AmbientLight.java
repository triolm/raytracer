package lights;

import geometry.Point;
import geometry.Vector;
import images.Color;

public class AmbientLight extends Light {
    private Color color;

    public AmbientLight(Color c) {
        color = c;
    }

    public Vector computeLightDirection(Point surfacePoint) {
        return null;
    }

    public Color computeLightColor(Point surfacePoint) {
        return color;
    }

    public double computeLightDistance(Point surfacePoint) {
        return 0;
    }
}
