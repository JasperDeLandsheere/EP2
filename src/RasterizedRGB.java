import java.awt.*;

// A raster of color entries.
public interface RasterizedRGB {

    // Returns the width of this raster.
    int getWidth();

    // Returns the height of this raster.
    int getHeight();

    // Returns the color of the specified pixel.
    // Precondition: (x,y) is a valid coordinate of the raster.
    Color getPixelColor(int x, int y);

    // Sets the color of the specified pixel.
    // Precondition: (x,y) is a valid coordinate of the raster, color != null.
    void setPixelColor(int x, int y, Color color);

    // Convolves 'this' with the specified filter kernel.
    // Preconditions:
    // filterKernel != null && filterKernel.length > 0 &&
    // filterKernel.length % 2 == 1 &&
    // filterKernel.length == filterKernel[i].length (for valid i) &&
    // filterKernel.length < this.getWidth() &&
    // filterKernel.length < this.getHeight().
    void convolve(double[][] filterKernel);

    // Crops 'this' to the rectangular region with upper left coordinates (0,0)
    // and lower right coordinates (width-1, height-1).
    // Precondition: 0 < width && 0 < height && width <= this.getWidth()
    // && height <= this.getHeight().
    void crop(int width, int height);

    // Copies the content of this 'this' into 'copy'. More specifically, the method copies the
    // feasible region which is bounded by the rectangular region with the upper left corner [0,0]
    // and the lower left corner [min(copy.getWidth(), this.getWidth())-1, min(copy.getHeight(),
    // this.getHeight())-1].
    // Precondition: copy != null.
    default void copyTo(RasterizedRGB copy) {
        for (int i = 0; i < Math.min(copy.getWidth(), this.getWidth()); i++) {
            for (int j = 0; j < Math.min(copy.getHeight(), this.getHeight()); j++) {
                copy.setPixelColor(i, j, this.getPixelColor(i, j));
            }
        }
    }

    RasterizedRGB newLayer();

}
