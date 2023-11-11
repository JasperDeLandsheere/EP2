import java.awt.*;

// This class represents a 2D raster of RGBA color entries (RGB values and additional alpha
// values controlling transparency of pixels). The class uses the class 'DataBufferInt' to store
// the entries.
public class RasterRGBA implements RasterizedRGB {

    private int width;
    private int height;
    private DataBufferInt dataBufferInt;

    // Initialises this raster of the specified size with
    // all pixels being fully transparent, i.e. (R,G,B, alpha) = (0,0,0,0).
    // Preconditions: height > 0, width > 0
    public RasterRGBA(int width, int height) {

        this.width = width;
        this.height = height;
        this.dataBufferInt = new DataBufferInt(4, width * height);
    }

    // Returns the sRGB color of the specified pixel.
    // Precondition: (x,y) is a valid coordinate of the raster.
    public Color getPixelColor(int x, int y) {

        return new Color(this.dataBufferInt.getElem(0, y * this.width + x),
                this.dataBufferInt.getElem(1,
                        y * this.width + x), this.dataBufferInt.getElem(2,
                y * this.width + x), this.dataBufferInt.getElem(3, y * this.width + x));
    }

    // Sets the sRGB color of the specified pixel.
    // Precondition: (x,y) is a valid coordinate of the raster, color != null.
    public void setPixelColor(int x, int y, Color color) {

        this.dataBufferInt.setElem(0, y * this.width + x, color.getRed());
        this.dataBufferInt.setElem(1, y * this.width + x, color.getGreen());
        this.dataBufferInt.setElem(2, y * this.width + x, color.getBlue());
        this.dataBufferInt.setElem(3, y * this.width + x, color.getAlpha());
    }

    // Convolves 'this' with the specified filter kernel 'filterKernel'. ('this' is the result of
    // the operation.)
    // Precondition:
    // filterKernel != null && filterKernel.length > 0 &&
    // filterKernel.length % 2 == 1 &&
    // filterKernel.length == filterKernel[i].length (for valid i) &&
    // filterKernel.length < this.getWidth() &&
    // filterKernel.length < this.getHeight().
    public void convolve(double[][] filterKernel) {

        DataBufferInt result = new DataBufferInt(4, width * height);

        int filterSideLength = filterKernel.length / 2;
        for (int x = filterSideLength; x < this.width - filterSideLength; x++) {
            for (int y = filterSideLength; y < this.height - filterSideLength; y++) {
                //at every array position, compute filter result
                double redSum = 0, greenSum = 0, blueSum = 0, alphaSum = 0;
                for (int xx = -filterSideLength; xx <= filterSideLength; xx++) {
                    for (int yy = -filterSideLength; yy <= filterSideLength; yy++) {
                        Color c = this.getPixelColor(x - xx, y - yy);
                        redSum += (c.getRed() * c.getAlpha() / 255d) * filterKernel[xx +
                                filterSideLength][yy + filterSideLength];
                        greenSum += (c.getGreen() * c.getAlpha() / 255d) * filterKernel[xx + filterSideLength][yy + filterSideLength];
                        blueSum += (c.getBlue() * c.getAlpha() / 255d) * filterKernel[xx + filterSideLength][yy + filterSideLength];
                        alphaSum += c.getAlpha() * filterKernel[xx + filterSideLength][yy + filterSideLength];
                    }
                }

                result.setElem(0, y * this.width + x,
                        (int) (redSum / alphaSum * 255));
                result.setElem(1, y * this.width + x,
                        (int) (greenSum / alphaSum * 255));
                result.setElem(2, y * this.width + x,
                        (int) (blueSum / alphaSum * 255));
                result.setElem(3, y * this.width + x, (int) alphaSum);
            }
        }
        this.dataBufferInt = result;
    }

    // Returns the width of this raster.
    public int getWidth() {

        return width;
    }

    // Returns the height of this raster.
    public int getHeight() {

        return height;
    }

    // Draws a line from (x1,y1) to (x2,y2) using the specified RGBA color in the raster using the
    // Bresenham algorithm. At pixels of the line the color values are replaced by the specified
    // color (without blending).
    // Preconditions: (x1,y1) and (x2,y2) are valid coordinates of the raster, color != null.
    public void drawLine(int x1, int y1, int x2, int y2, Color color) {

        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);
        int sx = x1 < x2 ? 1 : -1;
        int sy = y1 < y2 ? 1 : -1;
        int err = dx - dy;

        while (x1 != x2 || y1 != y2) {
            this.setPixelColor(x1, y1, color);

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

        this.setPixelColor(x1, y1, color);
    }

    // Clears the raster, i.e. fills the entire raster with the specified color.
    // Precondition: color != null.
    public void clear(Color color) {

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                this.setPixelColor(x, y, color);
            }
        }
    }

    // Crops the raster (without changing the underlying data buffer).
    // Precondition: 0 < width <= this.getWidth(), 0 < height <= this.getHeight().
    public void crop(int width, int height) {

        this.width = width;
        this.height = height;
    }

    @Override
    public void floodFill(Point p, Color color) {

    }

    @Override
    public void drawLine(Point p1, Point p2, Color color) {

    }

    @Override
    public void brighten(int N) {

    }

    @Override
    public RasterizedRGB newLayer() {
        return null;
    }


    // Returns the result of the alpha compositing of the 'this' (used as foreground)
    // over the specified background raster.
    // Precondition: background != null.
    public RasterRGBA over(RasterRGBA background) {

        RasterRGBA result = new RasterRGBA(this.width, this.height);
        for (int x = 0; x < this.width; x++) {
            for (int y = 0; y < this.height; y++) {
                Color c1 = this.getPixelColor(x, y);
                Color c2 = x < background.width && y < background.height ? background.getPixelColor(x, y) :
                        new Color(0, 0, 0, 0);
                result.setPixelColor(x, y, over(c1, c2));
            }
        }
        return result;
    }

    // Returns the resulting color of the alpha compositing of the foreground color over
    // the background color (assuming "straight alpha").
    // Precondition: foreground != null && background != null.
    public static Color over(Color foreground, Color background) {

        float alpha = foreground.getAlpha() / 255f;
        float red = foreground.getRed() / 255f;
        float green = foreground.getGreen() / 255f;
        float blue = foreground.getBlue() / 255f;

        float dstAlpha = background.getAlpha() / 255f;
        float dstRed = background.getRed() / 255f;
        float dstGreen = background.getGreen() / 255f;
        float dstBlue = background.getBlue() / 255f;

        float outAlpha = alpha + dstAlpha * (1f - alpha);
        float outRed = (red * alpha + dstRed * dstAlpha * (1f - alpha)) / outAlpha;
        float outGreen = (green * alpha + dstGreen * dstAlpha * (1f - alpha)) / outAlpha;
        float outBlue = (blue * alpha + dstBlue * dstAlpha * (1f - alpha)) / outAlpha;

        int outAlphaInt = Math.round(outAlpha * 255);
        int outRedInt = Math.round(outRed * 255);
        int outGreenInt = Math.round(outGreen * 255);
        int outBlueInt = Math.round(outBlue * 255);

        return new Color(outRedInt, outGreenInt, outBlueInt, outAlphaInt);
    }

    // Returns the number of pixels in 'this' having the specified color.
    // Precondition: color != null.
    public int countPixels(Color color) {
        int result = 0;
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                result += getPixelColor(x, y).equals(color) ? 1 : 0;
            }
        }
        return result;
    }

}

// A data buffer for a large number of elements (as in the case of large images),
// that are organized into separate arrays (data banks).
class DataBufferInt {

    private final int[][] data;

    // Initializes 'this' with a specified number of banks, all having the same length
    // given by 'bankSize'.
    // Preconditions: numberOfBanks > 0, bankSize > 0
    public DataBufferInt(int numberOfBanks, int bankSize) {

        data = new int[numberOfBanks][bankSize];
    }

    // Returns the element with index 'i' of the bank array with index 'bankIndex'.
    // Precondition: all indices are valid (needs not be checked).
    public int getElem(int bankIndex, int i) {

        return this.data[bankIndex][i];
    }

    // Sets the element with index 'i' of the bank array with index 'bankIndex'.
    // Precondition: all indices are valid (needs not be checked).
    public void setElem(int bankIndex, int i, int value) {

        this.data[bankIndex][i] = value;
    }

    // Returns the number of banks of this data buffer.
    public int numberOfBanks() {

        return this.data.length;
    }

    // Returns the size of a single bank of this data buffer.
    public int bankSize() {

        return this.data[0].length;
    }
}
