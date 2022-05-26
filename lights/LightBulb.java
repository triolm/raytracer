package lights;

import geometry.Point;
import geometry.Vector;
import images.Color;

public class LightBulb extends Light {
    private Color color;
    private Point location;
    private double radius;

    public LightBulb(Color c, Point location, double radius) {
        color = c;
        this.location = location;
        this.radius = radius;
    }

    public Vector computeLightDirection(Point surfacePoint) {
        return new Point(location.getX() + Math.random() * radius,
                location.getY() + Math.random() * radius,
                location.getZ() + Math.random() * radius).subtract(surfacePoint).normalize();
    }

    public Color computeLightColor(Point surfacePoint) {
        return color;
    }

    public double computeLightDistance(Point surfacePoint) {
        return location.subtract(surfacePoint).length();
    }
}
