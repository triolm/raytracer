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

    public BufferedImage toBufferedImage(){
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
}
