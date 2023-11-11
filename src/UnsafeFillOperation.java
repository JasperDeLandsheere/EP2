import java.awt.*;
// This class represents a flood fill operation. More specifically, it allows to set the area of
// contiguous pixels of the same color to a specified color, starting from an initial position and
// using 4-neighborhood.
//
public class UnsafeFillOperation implements UnsafeOperation {

    private Point p;
    private Color c;

    public UnsafeFillOperation(Point p, Color[] c) {
        this.p = p;
        this.c = c[0];
    }

    @Override
    public RasterizedRGB execute(RasterizedRGB raster) {

        raster.floodFill(p, c);

        return raster;
    }
}
