package lights;
import geometry.Vector;
import geometry.Point;
import images.Color;

public abstract class Light {
    public abstract Vector computeLightDirection(Point surfacePoint);

    public abstract Color computeLightColor(Point surfacePoint);

    public abstract double computeLightDistance(Point surfacePoint);

}
