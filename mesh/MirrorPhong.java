package mesh;

import images.Color;

public class MirrorPhong extends Phong {

    public double reflect;

    public MirrorPhong(Color diff, Color spec, double exp, double refPwr) {
        super(diff, spec, exp);
        this.reflect = refPwr;
    }

    public double getReflectiveness() {
        return reflect;
    }
}
