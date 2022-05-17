package mesh;

import images.Color;
import geometry.*;
import lights.Light;

public abstract class Material {

    public Material() {

    }

    public abstract Color computeLighting(Intersection i, Ray viewingRay, Light li);

}