package lights;

import images.Color;
import geometry.Point;
import geometry.Vector;

public class PointLight extends Light {
    private Color color;
    private Point location;

    public PointLight(Color c, Point location) {
        color = c;
        this.location = location;
    }

    public Vector computeLightDirection(Point surfacePoint) {
        return location.subtract(surfacePoint).normalize();
    }

    public Color computeLightColor(Point surfacePoint) {
        return color;
    }

    public double computeLightDistance(Point surfacePoint) {
        return location.subtract(surfacePoint).length();
    }
}