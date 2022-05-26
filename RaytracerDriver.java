import images.ColorImage;

public class RaytracerDriver {
    public static void main(String[] args) throws Exception {

        // UI.launch();

        long time = System.currentTimeMillis();
        // Image size
        int xResolution = 800;
        int yResolution = 600;

        System.out.println("Creating scene...");
        Scene s = SceneCreator.scene2(xResolution, yResolution);

        System.out.println("Rendering images...");
        ColorImage image = s.render(xResolution, yResolution, 1);

        System.out.println("Saving files...");
        String filename = "_output/test.png";
        ColorImage.save(filename, image);

        System.out.println("Done");

        Long end = System.currentTimeMillis();
        System.out.println("Time elapsed: " + (end - time) + " milliseconds");

    }
}