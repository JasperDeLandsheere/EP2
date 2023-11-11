import java.awt.*;

// This class represents a convolution operation.
public class UnsafeConvolveOperation implements UnsafeOperation {

    //TODO: declare variables.
    private double[][] filterKernel;

    // Initializes this convolution operation with the specified filter kernel.
    // Precondition:
    // filterKernel != null && filterKernel.length > 0 &&
    // filterKernel.length % 2 == 1 &&
    // filterKernel.length == filterKernel[i].length (for valid i).
    public UnsafeConvolveOperation(double[][] filterKernel) {

        // TODO: implement constructor.
        this.filterKernel = filterKernel;
    }

    // Returns the filter kernel of this convolution operation.
    public double[][] getKernel() {

        // TODO: implement method.
        return this.filterKernel;
    }

    @Override
    // Executes the convolution of 'raster' with the filter kernel getKernel().
    // The specified object is directly modified by this method call.
    // The returned raster is identical to the specified 'raster'.
    // Precondition:
    // filterKernel.length < raster.getWidth() &&
    // filterKernel.length < raster.getHeight().
    public RasterizedRGB execute(RasterizedRGB raster) {

        // TODO: implement method.
        int filterSideLength = getKernel().length / 2;
        RasterRGBA map = new RasterRGBA(raster.getWidth(), raster.getHeight());
        for (int x = filterSideLength; x < raster.getWidth() - filterSideLength; x++) {
            for (int y = filterSideLength; y < raster.getHeight() - filterSideLength; y++) {
                double[] temp_result = new double[3];
                for (int xx = -filterSideLength; xx <= filterSideLength; xx++) {
                    for (int yy = -filterSideLength; yy <= filterSideLength; yy++) {
                        if (x + xx >= 0 && x + xx < raster.getWidth() && y + yy >= 0 && y + yy < raster.getHeight()) {
                            temp_result[0] += raster.getPixelColor(x + xx, y + yy).getRed() * filterKernel[xx + filterSideLength][yy + filterSideLength];
                            temp_result[1] += raster.getPixelColor(x + xx, y + yy).getGreen() * filterKernel[xx + filterSideLength][yy + filterSideLength];
                            temp_result[2] += raster.getPixelColor(x + xx, y + yy).getBlue() * filterKernel[xx + filterSideLength][yy + filterSideLength];
                        }
                    }
                }
                map.setPixelColor(x, y, new Color((int) temp_result[0], (int) temp_result[1], (int) temp_result[2]));
            }
        }
        for (int x = 0; x < raster.getWidth(); x++) {
            for (int y = 0; y < raster.getHeight(); y++) {
                raster.setPixelColor(x,y, map.getPixelColor(x,y));
            }
            }
        return raster;
    }
}
