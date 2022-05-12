package images;

public class ColorImage {
    private Color[][] grid;

    public ColorImage(int width, int height) {
        grid = new Color[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                grid[i][j] = new Color(0, 0, 0);
            }
        }
    }

    public int getWidth() {
        return grid.length;
    }

    public int getHeight() {
        return grid[0].length;
    }

    public Color getColor(int x, int y) {
        return grid[x][y];
    }

    public void setColor(int x, int y, Color c) {
        grid[x][y] = c;
    }
}
