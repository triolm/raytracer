import images.Color;
import images.ColorImage;
import geometry.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RaytracerDriver {
    public static void main(String[] args) throws Exception {

        // UI.launch();

        // Image size
        int xResolution = 1920;
        int yResolution = 1080;
        int cores = 4;
        int aa = 1;

        // for (int i = 0; i < 20; i++) {
        System.out.println("Creating scene...");
        Scene s = SceneCreator.scene4(xResolution, yResolution);

        System.out.println("Rendering images...");

        ArrayList<Pair<Integer>> l = Pair.split(cores, xResolution);
        List<ColorImage> arr = l.parallelStream().map((Pair<Integer> p) -> s.renderParallel(p,
                xResolution, yResolution, aa, false))
                .collect(Collectors.toList());

        String filename = "_output/test.png";

        System.out.println("Saving files...");
        ColorImage.save(filename, ColorImage.merge(arr));

        System.out.println("Done");
        // }

    }
}