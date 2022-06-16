import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import geometry.*;
import mesh.Material;
import shapes.Triangle;

public class OBJParser {
    // adds OBJ to scene
    public static Scene parse(String filename, Scene s, double scale, Vector shift, Material m) {
        ArrayList<Point> pts = new ArrayList<Point>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));

            // https://stackoverflow.com/questions/17678862/reading-lines-with-bufferedreader-and-checking-for-end-of-file
            String ln;
            while ((ln = br.readLine()) != null) {
                // ignores textures
                if (ln.startsWith("vn") || ln.startsWith("vt")) {
                    continue;
                }
                // v is a vertex
                if (ln.startsWith("v")) {
                    // https://stackoverflow.com/questions/225337/how-to-split-a-string-with-any-whitespace-chars-as-delimiters
                    String[] arr = ln.substring(1).trim().split("\\s+");
                    // applies scale and shift to every point
                    // Y and Z axes are reversed
                    pts.add(new Point(Double.parseDouble(arr[0].trim()) * scale,
                            Double.parseDouble(arr[2].trim()) * scale,
                            Double.parseDouble(arr[1].trim()) * scale).add(shift));
                    // f is a shape (only works on triangulated models)
                } else if (ln.startsWith("f")) {
                    String[] arr = ln.substring(1).trim().split("\\s+");
                    s.addSurface(new Triangle(
                            // OBJs start index at 1
                            // everything after the slash is texture info
                            pts.get(Integer.parseInt(arr[0].trim().split(("/"))[0]) - 1),
                            pts.get(Integer.parseInt(arr[1].trim().split(("/"))[0]) - 1),
                            pts.get(Integer.parseInt(arr[2].trim().split(("/"))[0]) - 1),
                            m));
                }
            }
            br.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return s;

    }

}