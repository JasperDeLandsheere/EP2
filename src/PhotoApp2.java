import codedraw.CodeDraw;
import java.awt.*;

public class PhotoApp2 {

    public static void main(String[] args) {

        SimpleSparseRasterRGB r1 = new SimpleSparseRasterRGB(40, 60);
        draw(r1);

        SimplePointQueue q = new SimplePointQueue(3);
        q.add(new Point(0, 0));
        q.add(new Point(10, 10));
        q.add(new Point(20, 10));

        q.add(new Point(20, 20));
        q.add(new Point(30, 30));

        r1.drawLines(q, Color.BLUE);
        draw(r1);



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
