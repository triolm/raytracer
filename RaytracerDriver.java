import images.Color;
import images.ColorImage;
import images.Colors;
import mesh.Lambert;
import shapes.Triangle;
import geometry.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RaytracerDriver {
    public static void main(String[] args) throws Exception {

        // UI.launch();

        // Image size
        int xResolution = 800;
        int yResolution = 600;
        int cores = 4;
        int aa = 1;

        // for (int i = 0; i < 20; i++) {
        System.out.println("Creating scene...");
        Scene s = SceneCreator.pokemon(xResolution, yResolution);

        System.out.println("Rendering images...");

        ArrayList<Pair<Integer>> l = Pair.split(cores, xResolution);
        List<ColorImage> arr = l.parallelStream().map((Pair<Integer> p) -> s.renderParallel(p,
                xResolution, yResolution, aa, false))
                .collect(Collectors.toList());

        String filename = "_output/testobj.png";

        System.out.println("Saving files...");
        ColorImage.save(filename, ColorImage.merge(arr));

        System.out.println("Done");
        // }

    }
}