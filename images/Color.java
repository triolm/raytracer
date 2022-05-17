package images;

public class Color {
    private double r, g, b;

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

    public Color shade(Color c) {
        return new Color(r * c.getR(), g * c.getG(), b * c.getB());
    }

    public Color tint(Color c) {
        double rtinted = r + (1 - r) * c.getR();
        double gtinted = g + (1 - g) * c.getG();
        double btinted = b + (1 - b) * c.getB();
        return new Color(rtinted, gtinted, btinted);
    }

}