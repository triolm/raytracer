package mesh;

import images.Color;

public class MirrorPhong extends Phong {

    private double reflect;
    private double maxDist;

    public MirrorPhong(Color diff, Color spec, double exp, double refPwr, double maxDist) {
        super(diff, spec, exp);
        this.reflect = refPwr;
        this.maxDist = maxDist;
    }

    public double getReflectiveness() {
        return reflect;
    }

    public double getDeviance() {
        return this.maxDist;
    }

}
