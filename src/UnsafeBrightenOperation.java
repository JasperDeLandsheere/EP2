// This class represents a crop operation.
//
public class UnsafeBrightenOperation implements UnsafeOperation {

    private int N;

    // Initializes this operation with width and height of
    // the crop region. The crop region is bounded by the rectangular region with the upper left
    // corner [0,0] and the lower left corner [width-1, height-1].
    // Precondition: width >= 0 && height >= 0.
    public UnsafeBrightenOperation(int N) {

        this.N = N;
    }

    public int getN() {
        return N;
    }

    @Override
    // Executes the crop operation on the specified 'raster'.
    // The specified object is directly modified by this method call.
    // The returned raster is identical to the specified 'raster'.
    // Precondition:
    // this.getWidth() <= raster.getWidth() && this.getHeight() <= raster.getHeight().
    public RasterizedRGB execute(RasterizedRGB raster) {

        raster.brighten(this.N);
        return raster;
    }
}
