import images.ColorImage;
import geometry.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RaytracerDriver {

    // Run UI.JAVA for UI
    public static void main(String[] args) throws Exception {

        // Image size
        int xResolution = 288;
        int yResolution = 216;
        int aa = 1;
        for (int i = 0; i < 30; i++) {
            System.out.println("Creating scene...");
            Scene s = SceneCreator.rotateBlackHole(xResolution, yResolution, i);

            System.out.println("Rendering images...");

            ColorImage arr = s.render(xResolution, yResolution, aa, true);

            String filename = "_output/rotate/" + i + ".png";

            System.out.println("Saving files...");
            ColorImage.save(filename, arr);
        }

        System.out.println("Done");

    }
}