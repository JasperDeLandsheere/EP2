import java.awt.*;


// This class represents a 2D raster of RGB color entries. The class uses
// the class 'SimpleDataBufferInt' to store the entries.
public class SimpleRasterRGB {

    private int width;
    private int height;

    private SimpleDataBufferInt buffer;

    // Initialises this raster of the specified size with
    // all pixels being black, i.e. (R,G,B) = (0,0,0).
    // Preconditions: height > 0, width > 0
    public SimpleRasterRGB(int width, int height) {

        this.width = width;
        this.height = height;
        this.buffer = new SimpleDataBufferInt(6, this.width * this.height);
    }

    // Returns the color of the specified pixel.
    // Precondition: (x,y) is a valid coordinate of the raster
    public Color getPixelColor(int x, int y) {

        return new Color(
                this.buffer.getElem(0, y * this.width + x),
                this.buffer.getElem(1, y * this.width + x),
                this.buffer.getElem(2, y * this.width + x)
        );
    }

    // Sets the color of the specified pixel.
    // Precondition: (x,y) is a valid coordinate of the raster, color != null
    public void setPixelColor(int x, int y, Color color) {

        this.buffer.setElem(0, y * this.width + x, color.getRed());
        this.buffer.setElem(1, y * this.width + x, color.getGreen());
        this.buffer.setElem(2, y * this.width + x, color.getBlue());
    }

    // Returns the result of convolution of 'this' with the specified filter kernel. 'this' is not
    // changed by the method call.
    // Precondition (needs to be checked):
    // filterKernel != 0 && filterKernel.length > 0 &&
    // filterKernel.length % 2 == 1 &&
    // filterKernel.length == filterKernel[0].length &&
    // filterKernel.length < this.getWidth() &&
    // filterKernel.length < this.getHeight().
    public SimpleRasterRGB convolve(double[][] filterKernel) {

        SimpleRasterRGB result = new SimpleRasterRGB(this.width, this.height);

        double[] temp_result;

        int filterSideLength = filterKernel.length / 2;
        for (int x = filterSideLength; x < result.width - filterSideLength; x++) {
            for (int y = filterSideLength; y < result.height - filterSideLength; y++) {
                //at every array position, compute filter result
                temp_result = new double[3];
                for (int xx = -filterSideLength; xx <= filterSideLength; xx++) {
                    for (int yy = -filterSideLength; yy <= filterSideLength; yy++) {
                        temp_result[0] += this.getPixelColor(x - xx, y - yy).getRed() *
                                filterKernel[xx + filterSideLength][yy + filterSideLength];
                        temp_result[1] += this.getPixelColor(x - xx, y - yy).getGreen() *
                                filterKernel[xx + filterSideLength][yy + filterSideLength];
                        temp_result[2] += this.getPixelColor(x - xx, y - yy).getBlue() *
                                filterKernel[xx + filterSideLength][yy + filterSideLength];
                    }
                }

                result.buffer.setElem(3, y * result.width + x, (int) temp_result[0]);
                result.buffer.setElem(4, y * result.width + x, (int) temp_result[1]);
                result.buffer.setElem(5, y * result.width + x, (int) temp_result[2]);
            }
        }
        result.buffer.setDataByBankIndex(0, result.buffer.getDataByBankIndex(3));
        result.buffer.setDataByBankIndex(1, result.buffer.getDataByBankIndex(4));
        result.buffer.setDataByBankIndex(2, result.buffer.getDataByBankIndex(5));

        return result;
    }

    // Returns the result of convolution of the specified raster 'toBeFiltered' with the specified
    // filter kernel 'filterKernel'.
    // Precondition (needs not be checked):
    // filterKernel != null && filterKernel.length > 0 &&
    // filterKernel.length % 2 == 1 &&
    // filterKernel.length == filterKernel[0].length &&
    // filterKernel.length < this.getWidth() &&
    // filterKernel.length < this.getHeight().
    public static SimpleRasterRGB convolve(SimpleRasterRGB toBeFiltered, double[][] filterKernel) {

        SimpleRasterRGB result = new SimpleRasterRGB(toBeFiltered.width, toBeFiltered.height);

        double[] temp_result;

        int filterSideLength = filterKernel.length / 2;
        for (int x = filterSideLength; x < toBeFiltered.width - filterSideLength; x++) {
            for (int y = filterSideLength; y < toBeFiltered.height - filterSideLength; y++) {
                //at every array position, compute filter result
                temp_result = new double[3];
                for (int xx = -filterSideLength; xx <= filterSideLength; xx++) {
                    for (int yy = -filterSideLength; yy <= filterSideLength; yy++) {
                        temp_result[0] += toBeFiltered.getPixelColor(x - xx, y - yy).getRed() *
                                filterKernel[xx + filterSideLength][yy + filterSideLength];
                        temp_result[1] += toBeFiltered.getPixelColor(x - xx, y - yy).getGreen() *
                                filterKernel[xx + filterSideLength][yy + filterSideLength];
                        temp_result[2] += toBeFiltered.getPixelColor(x - xx, y - yy).getBlue() *
                                filterKernel[xx + filterSideLength][yy + filterSideLength];
                    }
                }

                result.buffer.setElem(3, y * result.width + x, (int) temp_result[0]);
                result.buffer.setElem(4, y * result.width + x, (int) temp_result[1]);
                result.buffer.setElem(5, y * result.width + x, (int) temp_result[2]);

            }

        }

        result.buffer.setDataByBankIndex(0, result.buffer.getDataByBankIndex(3));
        result.buffer.setDataByBankIndex(1, result.buffer.getDataByBankIndex(4));
        result.buffer.setDataByBankIndex(2, result.buffer.getDataByBankIndex(5));

        return result;
    }

    // Returns the width of this raster.
    public int getWidth() { return this.width; }

    // Returns the height of this raster.
    public int getHeight() { return this.height; }

    // Draws a line from (x1,y1) to (x2,y2) in the raster using the Bresenham algorithm.
    //Preconditions: (x1,y1) and (x2,y2) are valid coordinates of the raster, color != null
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
    }

    // Returns a mapping from all width*height pixel positions (Point) to corresponding colors
    // (Color) of the pixels. Values of color (0,0,0) are also included in the mapping.
    public TreePointColorMap asMap() {

        TreePointColorMap map = new TreePointColorMap();

        for (int x = 0; x < this.width; x++) {
            for (int y = 0; y < this.height; y++) {
                map.put(new Point(x, y), this.getPixelColor(x, y));
            }
        }

        return map;
    }

}
