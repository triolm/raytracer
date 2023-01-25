import images.ColorImage;
import geometry.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RaytracerDriver {

    // Run UI.JAVA for UI
    public static void main(String[] args) throws Exception {

        // Scales image while keeping 16:9 aspect ratio
        int imageScale = 30;

        int xResolution = 16 * imageScale;
        int yResolution = 9 * imageScale;
        int aa = 1;

        System.out.println("Creating scene...");

        // setup shapes and lights
        Scene s = SceneCreator.scene1(xResolution, yResolution);

        System.out.println("Rendering images...");

        // cast rays
        ColorImage arr = s.render(xResolution, yResolution, aa, true);

        String filename = "_output/control.png";

        System.out.println("Saving files...");
        ColorImage.save(filename, arr);

        System.out.println("Done");

    }
}