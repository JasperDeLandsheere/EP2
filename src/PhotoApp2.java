import codedraw.CodeDraw;
import java.awt.*;

public class PhotoApp2 {

    public static void main(String[] args) {

        // TODO: implement method according to 'Aufgabenblatt2.md'.

        SimpleSparseRasterRGB r1 = new SimpleSparseRasterRGB(40, 60);

        // draw 3 lines
        r1.drawLine(0, 1, 35, 9, new Color(20, 25, 250));
        r1.drawLine(30, 5, 0, 30, Color.ORANGE);
        r1.drawLine(2, 0, 7, 40, Color.GREEN);

        // fill the region
        r1.floodFill(7, 7, Color.CYAN);

        // draw the raster
        draw(r1);

        // apply the filter
        SimpleSparseRasterRGB r2 = r1.convolve(new double[][]{
                {0.077847, 0.123317, 0.077847},
                {0.123317, 0.195346, 0.123317},
                {0.077847, 0.123317, 0.077847}
        });

        // draw the raster
        draw(r2);

        // fill the region with black
        r2.floodFill(7, 7, Color.BLACK);

        // draw the raster
        draw(r2);

    }

    // Draws the image (specified by a sparse raster) with fixed pixel size in a new window.
    // Precondition: raster != null.
    public static void draw(SimpleSparseRasterRGB raster) {

        int cellSize = 10;
        CodeDraw cd = new CodeDraw(raster.getWidth() * cellSize, raster.getHeight() * cellSize);

        // draw a square of size 'cellSize' for each pixel
        for (int j = 0; j < raster.getHeight(); j++) {
            for (int i = 0; i < raster.getWidth(); i++) {
                int x = i * cellSize;
                int y = j * cellSize;
                cd.setColor(raster.getPixelColor(i, j));
                cd.fillSquare(x, y, cellSize);
            }
        }
        cd.show();
    }
}
