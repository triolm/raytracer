import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import images.ColorImage;

import java.io.File;

/**
 * Driver for the various stages of the image generation and saving process.
 * 
 * @author Ben Farrar
 * @version 2019.05.22
 */
public class RaytracerDriver {
    public static void main(String[] args) {
        // Size of the final image. This will DRAMATICALLY affect the runtime.
        int xResolution = 800;
        int yResolution = 600;

        // Create the scene. You can change this when you make other scene creation
        // methods to select
        // which scene to render.
        System.out.println("Creating scene...");
        Scene s = SceneCreator.scene2(xResolution, yResolution);

        // Render the scene into a ColorImage
        System.out.println("Rendering image...");
        ColorImage image = s.render(xResolution, yResolution);

        // Save the image out as a png
        System.out.println("Saving file...");
        String filename = "_output/scene1.png";
        saveImage(filename, image);

        /*
         * Simple image write. Use this to test if image writing is broken.
         * If this doesn't work, that means something is wrong with your Java
         * installation.
         * If it DOES work, and you get a color gradient image written out as
         * "testGradient.png",
         * but the normal saveImage does not work, that means something is wrong with
         * your raytracing code.
         */
        // saveTestImage();

        System.out.println("Done");
    }

    /**
     * Reads in each pixel from a ColorImage, and then writes the image out to a PNG
     * file.
     */
    public static void saveImage(String filename, ColorImage image) {
        try {

            BufferedImage bi = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
            for (int x = 0; x < image.getWidth(); x++) {
                for (int y = 0; y < image.getHeight(); y++) {
                    // This line reverses the y axis. Use the following line instead if your image
                    // is upside down.
                    bi.setRGB(x, image.getHeight() - 1 - y, image.getColor(x, y).toARGB());
                    // bi.setRGB(x,y,image.getColor(x,y).toARGB());
                }
            }
            ImageIO.write(bi, "PNG", new File(filename));

        } catch (Exception e) {
            System.out.println("Problem saving image: " + filename);
            System.out.println(e);
            System.exit(1);
        }
    }

    /**
     * Simpler version of the saveImage method for testing. Doesn't require
     * integration with ColorImage, just writes
     * a gradient of colors out to an image to make sure the BufferedImage, ImageIO,
     * and File libraries are working
     * as expected.
     */
    public static void saveTestImage() {
        try {

            BufferedImage biTest = new BufferedImage(250, 200, BufferedImage.TYPE_INT_RGB);
            for (int x = 0; x < 250; x++) {
                for (int y = 0; y < 200; y++) {
                    biTest.setRGB(x, 200 - 1 - y, (x << 16) | (y << 8) | (0 << 0));
                }
            }
            ImageIO.write(biTest, "PNG", new File("testGradient.png"));

        } catch (Exception e) {
            System.out.println("Problem saving test gradient image");
            System.out.println(e);
            System.exit(1);
        }
    }
}