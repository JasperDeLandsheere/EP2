import java.awt.*;
// This class represents a line operation. More specifically, it allows to draw a line into
// a raster.
//
public class UnsafeLineOperation implements UnsafeOperation {

    //TODO: declare variables.
    private Point start;
    private Point end;
    private Color color;

    // Initialises this line operation with starting point (x1, y1) and endpoint (x2, y2)
    // and the color of the line.
    public UnsafeLineOperation(int x1, int y1, int x2, int y2, Color color) {

        // TODO: implement constructor.
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
        this.color = color;
    }

    // Returns the starting point of this line operation.
    public Point getStart() {

        // TODO: implement method.
        return this.start;
    }

    // Returns the end point of this line operation.
    public Point getEnd() {

        // TODO: implement method.
        return this.end;
    }

    // Returns the color of this line operation.
    public Color getColor() {

        // TODO: implement method.
        return this.color;
    }

    // Executes the operation. More specifically, this method draws the line with from
    // getStart() to getEnd() with color getColor() into the specified raster
    // using the Bresenham algorithm.
    // The specified object is directly modified by this method call.
    // The returned raster is identical to the specified 'raster'.
    // Precondition: getStart() and getEnd() are valid positions of 'raster'.
    @Override
    public RasterizedRGB execute(RasterizedRGB raster) {

        // TODO: implement method.
        int x1 = this.start.getX();
        int y1 = this.start.getY();
        int x2 = this.end.getX();
        int y2 = this.end.getY();
        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);
        int sx = x1 < x2 ? 1 : -1;
        int sy = y1 < y2 ? 1 : -1;
        int err = dx - dy;

        while (x1 != x2 || y1 != y2) {
            raster.setPixelColor(x1, y1, color);

            int err2 = 2 * err;
            if (err2 > -dy) {
                err -= dy;
                x1 += sx;
            }
            if (err2 < dx) {
                err += dx;
                y1 += sy;
            }
        }
        return raster;
    }
}
