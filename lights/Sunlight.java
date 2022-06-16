package lights;

import images.Color;
import geometry.*;

public class Sunlight extends DirectionalLight {

    public Sunlight(double time) {
        super(getColorFromTime(time), getVectorFromTime(time), 0);

    }

    // converts time double to a color for sunset
    public static Color getColorFromTime(double time) {
        double r = .1;
        double g = .1;
        double b = .3;
        time = time % 24;
        if (time > 12) {
            time = 24 - time;
        }
        if (time < 6) {
            b += (6 - time) / 6;
        } else {
            b = 1.5 / time;
        }
        if (time < 7) {
            g += (7 - time) / 7;
        } else {
            g = .5 / time;
        }
        if (time < 8) {
            r += (8 - time) / 8;
        } else {
            r = 1 / time;
        }
        return new Color(r, g, b);

    }

    // places light in accurate position
    public static Vector getVectorFromTime(double time) {
        double dx;
        if (time < 18)
            dx = (Math.abs(time - 6) - 6) / 6;
        else
            dx = -1 * ((time / 6) - 4);
        double dy = (Math.abs(time - 12) - 6) / 6;
        return new Vector(dx, dy, 0);
    }
}
