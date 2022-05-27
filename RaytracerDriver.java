import images.ColorImage;

public class RaytracerDriver {
    public static void main(String[] args) throws Exception {

        // UI.launch();

        long time = System.currentTimeMillis();
        // Image size
        int xResolution = 1024;
        int yResolution = 768;
        System.out.println("Creating scene...");
        Scene s = SceneCreator.MotionBlur(xResolution, yResolution);

        System.out.println("Rendering images...");
        ColorImage image = s.render(xResolution, yResolution, 8);

        System.out.println("Saving files...");
        String filename = "_output/blinn.png";
        ColorImage.save(filename, image);

        System.out.println("Done");

        Long end = System.currentTimeMillis();
        System.out.println("Time elapsed: " + (end - time) + " milliseconds");

    }
}