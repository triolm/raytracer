public class Color {
    double r, g, b;

    public Color(double r, double g, double b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public double getR() {
        return r;
    }

    public double getG() {
        return g;
    }

    public double getB() {
        return b;
    }

    public int toARGB() {
        int ir = (int) (Math.min(Math.max(r, 0), 1) * 255 + 0.1);
        int ig = (int) (Math.min(Math.max(g, 0), 1) * 255 + 0.1);
        int ib = (int) (Math.min(Math.max(b, 0), 1) * 255 + 0.1);
        return (ir << 16) | (ig << 8) | (ib << 0);
    }

}