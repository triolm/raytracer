import images.ColorImage;

public class RaytracerDriver {
    public static void main(String[] args) throws Exception {

        // UI.launch();

        long time = System.currentTimeMillis();
        // Image size
        int xResolution = 1024;
        int yResolution = 768;
        // int xResolution = 800;
        // int yResolution = 600;

        // for (int i = 0; i < 20; i++) {
        System.out.println("Creating scene...");
        Scene s = SceneCreator.scene3(xResolution, yResolution);

        System.out.println("Rendering images...");
        ColorImage image = s.render(xResolution, yResolution, 128, true);

        System.out.println("Saving files...");
        String filename = "_output/test.png";
        ColorImage.save(filename, image);

        System.out.println("Done");
        // }

        Long end = System.currentTimeMillis();
        System.out.println("Time elapsed: " + (end - time) + " milliseconds");

    }
}