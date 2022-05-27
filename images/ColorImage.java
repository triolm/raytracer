package images;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;

public class ColorImage {
    private Color[][] grid;

    public ColorImage(int width, int height) {
        grid = new Color[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                grid[i][j] = new Color(0, 0, 0);
            }
        }
    }

    public int getWidth() {
        return grid.length;
    }

    public int getHeight() {
        return grid[0].length;
    }

    public Color getColor(int x, int y) {
        return grid[x][y];
    }

    public void setColor(int x, int y, Color c) {
        grid[x][y] = c;
    }

    public BufferedImage toBufferedImage() {
        BufferedImage bi = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < getWidth(); x++) {
            for (int y = 0; y < getHeight(); y++) {
                bi.setRGB(x, getHeight() - 1 - y, getColor(x, y).toARGB());
                // bi.setRGB(x,y,image.getColor(x,y).toARGB());
            }
        }
        return bi;
    }

    public static void save(String filename, ColorImage image) {
        try {

            BufferedImage bi = image.toBufferedImage();
            ImageIO.write(bi, "PNG", new File(filename));

        } catch (Exception e) {
            System.out.println("Problem saving image: " + filename);
            System.out.println(e);
            System.exit(1);
        }
    }

    public ColorImage toMonochrome() {
        ColorImage newImage = new ColorImage(this.getWidth(), this.getHeight());
        for (int i = 0; i < this.getWidth(); i++) {
            for (int ii = 0; ii < this.getHeight(); ii++) {
                double avg = 0;
                // averages all three values together
                avg += getColor(i, ii).getR();
                avg += getColor(i, ii).getG();
                avg += getColor(i, ii).getB();
                avg /= 3;
                newImage.setColor(i, ii, new Color(avg, avg, avg));
            }
        }
        return newImage;
    }

    public ColorImage contrast(double f) {
        ColorImage newImage = new ColorImage(this.getWidth(), this.getHeight());
        for (int i = 0; i < this.getWidth(); i++) {
            for (int ii = 0; ii < this.getHeight(); ii++) {
                double red = ((getColor(i, ii).getR() - .5) * f + .5);
                double green = ((getColor(i, ii).getG() - .5) * f + .5);
                double blue = ((getColor(i, ii).getB() - .5) * f + .5);
                newImage.setColor(i, ii, new Color(red, green, blue));
            }
        }
        return newImage;
    }

    public ColorImage toSepia() {
        ColorImage newImage = new ColorImage(this.getWidth(), this.getHeight());
        for (int i = 0; i < this.getWidth(); i++) {
            for (int ii = 0; ii < this.getHeight(); ii++) {
                // get colors from source image
                double red = (getColor(i, ii).getR());
                double green = (getColor(i, ii).getG());
                double blue = (getColor(i, ii).getB());

                // change colors to sepia per pixel
                double outputRed = ((red * .393) + (green * .769) + (blue * .189));
                double outputGreen = ((red * .349) + (green * .686) + (blue * .168));
                double outputBlue = ((red * .272) + (green * .534) + (blue * .131));

                newImage.setColor(i, ii, new Color(outputRed, outputGreen, outputBlue));
            }
        }
        return newImage;
    }

    public static ColorImage fromBufferedImage(BufferedImage img) {
        ColorImage cImage = new ColorImage(img.getWidth(), img.getHeight());
        for (int i = 0; i < img.getWidth(); i++) {
            for (int j = 0; j < img.getHeight(); j++) {
                cImage.setColor(i, j, Color.fromARGB(img.getRGB(i, j)));
            }
        }
        return cImage;
    }

    public static ColorImage fromFile(String path) {
        try {
            return fromBufferedImage(ImageIO.read(new File(path)));
        } catch (Exception e) {
            return null;
        }
    }
}
