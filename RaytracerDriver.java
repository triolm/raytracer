import images.ColorImage;
import geometry.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RaytracerDriver {
    public static void main(String[] args) throws Exception {

        // Image size
        int xResolution = 1024;
        int yResolution = 768;
        int cores = 4;
        int aa = 16;

        // for (int i = 0; i < 20; i++) {
        System.out.println("Creating scene...");
        Scene s = SceneCreator.scene3(xResolution, yResolution);

        System.out.println("Rendering images...");

        ArrayList<Pair<Integer>> l = Pair.split(cores, xResolution);
        List<ColorImage> arr = l.parallelStream().map((Pair<Integer> p) -> s.renderParallel(p,
                xResolution, yResolution, aa, false))
                .collect(Collectors.toList());

        String filename = "_output/baseline2.png";

        System.out.println("Saving files...");
        ColorImage.save(filename, ColorImage.merge(arr));

        System.out.println("Done");
        // }

    }
}