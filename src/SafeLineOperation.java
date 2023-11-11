import java.awt.*;

// This class represents a line operation. More specifically, it allows to draw a line into
// a raster.
//
public class SafeLineOperation implements SafeOperationSingle // TODO: activate clause.
{
    private Point start;
    private Point end;
    private Color color;

    // Initialises this line operation with starting point (x1, y1) and endpoint (x2, y2)
    // and the color of the line.
    public SafeLineOperation(int x1, int y1, int x2, int y2, Color color) {

        // TODO: implement constructor.
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
        this.color = color;
    }
    @Override
    public RasterizedRGB execute(RasterizedRGB raster) throws OperationException {
        // TODO: implement method.
        try {
            int x1 = this.start.getX();
            int y1 = this.start.getY();
            int x2 = this.end.getX();
            int y2 = this.end.getY();
            // throw exception for negative coordinates
            if (x1 < 0 || x2 < 0 || y1 < 0 || y2 < 0) {
                throw new OperationException("Negative coordinates are not allowed.");
            }
            // throw exception for coordinates out of bounds
            if (x1 >= raster.getWidth() || x2 >= raster.getWidth() || y1 >= raster.getHeight() || y2 >= raster.getHeight()) {
                throw new OperationException("Coordinates out of bounds.");
            }
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
        } catch (Exception e) {
            throw new OperationException(e.getMessage());
        }
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

    @Override
    public String toString() {
        return "line " + this.start.getX() + " " + this.start.getY() + " " + this.end.getX() + " " + this.end.getY();
    }
}



