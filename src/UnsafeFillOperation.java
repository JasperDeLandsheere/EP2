import java.awt.*;

// This class represents a flood fill operation. More specifically, it allows to set the area of
// contiguous pixels of the same color to a specified color, starting from an initial position and
// using 4-neighborhood.
//
public class UnsafeFillOperation implements UnsafeOperation {

    // TODO: define missing parts of this class.
    private int x;
    private int y;
    private Color color;

    public UnsafeFillOperation(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    // TODO: add method specification.
    public RasterizedRGB execute(RasterizedRGB raster) {

        // TODO: implement method.
        Color oldColor = raster.getPixelColor(x, y);
        SimplePointQueue queue = new SimplePointQueue(1);
        queue.add(new Point(x, y));
        while (queue.size() > 0) {
            Point p = queue.poll();
            int xi = p.getX();
            int yi = p.getY();
            if (raster.getPixelColor(xi, yi).equals(oldColor)) {
                raster.setPixelColor(xi, yi, color);
                if (xi > 0) {
                    queue.add(new Point(xi - 1, yi));
                }
                if (xi < raster.getWidth() - 1) {
                    queue.add(new Point(xi + 1, yi));
                }
                if (yi > 0) {
                    queue.add(new Point(xi, yi - 1));
                }
                if (yi < raster.getHeight() - 1) {
                    queue.add(new Point(xi, yi + 1));
                }
            }
        }
        return raster;
    }
}
