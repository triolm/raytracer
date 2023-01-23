import images.ColorImage;
import geometry.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RaytracerDriver {

    // Run UI.JAVA for UI
    public static void main(String[] args) throws Exception {

        // Image size
        int imageScale = 30;
        int xResolution = 16 * imageScale;
        int yResolution = 9 * imageScale;
        int aa = 1;
        // for (int i = 0; i < 30; i++) {
        System.out.println("Creating scene...");
        Scene s = SceneCreator.scene1(xResolution, yResolution);

        System.out.println("Rendering images...");

        ColorImage arr = s.render(xResolution, yResolution, aa, true);

        String filename = "_output/control.png";

        System.out.println("Saving files...");
        ColorImage.save(filename, arr);
        // }

        System.out.println("Done");

    }
}