import java.awt.*;
// This class represents a line operation. More specifically, it allows to draw a line into
// a raster.
//
public class UnsafeLineOperation implements UnsafeOperation {

    // Initialises this line operation with starting point (x1, y1) and endpoint (x2, y2)
    // and the color of the line.
    private Point p1;
    private Point p2;
    private Color color;

    public UnsafeLineOperation(int x1, int y1, int x2, int y2, Color color) {

        this.p1 = new Point(x1, y1);
        this.p2 = new Point(x2, y2);
        this.color = color;
    }

    // Returns the starting point of this line operation.
    public Point getStart() {

        return p1;
    }

    // Returns the end point of this line operation.
    public Point getEnd() {

        return p2;
    }

    // Returns the color of this line operation.
    public Color getColor() {

        return color;
    }

    // Executes the operation. More specifically, this method draws the line with from
    // getStart() to getEnd() with color getColor() into the specified raster
    // using the Bresenham algorithm.
    // The specified object is directly modified by this method call.
    // The returned raster is identical to the specified 'raster'.
    // Precondition: getStart() and getEnd() are valid positions of 'raster'.
    @Override
    public RasterizedRGB execute(RasterizedRGB raster) {

        raster.drawLine(p1, p2, color);
        return raster;
    }
}
