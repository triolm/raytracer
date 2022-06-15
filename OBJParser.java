import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import geometry.*;
import images.Colors;
import mesh.Lambert;
import mesh.Material;
import shapes.Triangle;

public class OBJParser {

    public static Scene parse(String filename, Scene s, double scale, Vector shift, Material m) {
        ArrayList<Point> pts = new ArrayList<Point>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));

            // https://stackoverflow.com/questions/17678862/reading-lines-with-bufferedreader-and-checking-for-end-of-file
            String ln;
            while ((ln = br.readLine()) != null) {
                if (ln.startsWith("vn") || ln.startsWith("vt")) {
                    continue;
                }
                if (ln.startsWith("v")) {
                    String[] arr = ln.substring(1).trim().split("\\s+");
                    pts.add(new Point(Double.parseDouble(arr[0].trim()) * scale,
                            Double.parseDouble(arr[2].trim()) * scale,
                            Double.parseDouble(arr[1].trim()) * scale).add(shift));
                } else if (ln.startsWith("f")) {
                    String[] arr = ln.substring(1).trim().split("\\s+");
                    s.addSurface(new Triangle(
                            pts.get(Integer.parseInt(arr[0].trim().split(("/"))[0]) - 1),
                            pts.get(Integer.parseInt(arr[1].trim().split(("/"))[0]) - 1),
                            pts.get(Integer.parseInt(arr[2].trim().split(("/"))[0]) - 1),
                            m));
                }
            }
            // System.out.println(pts.toString());
            br.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return s;

    }

}