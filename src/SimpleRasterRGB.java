import java.awt.*;


// This class represents a 2D raster of RGB color entries. The class uses
// the class 'SimpleDataBufferInt' to store the entries.
public class SimpleRasterRGB {

    // TODO: make these two variables 'private' and remove 'static'.
    private int width;
    private int height;

    // TODO: add more object variables, if needed.
    private SimpleDataBufferInt buffer;
    // Initialises this raster of the specified size with
    // all pixels being black, i.e. (R,G,B) = (0,0,0).
    // Preconditions: height > 0, width > 0
    public SimpleRasterRGB(int width, int height) {

        // TODO: implement constructor.
        this.width = width;
        this.height = height;
        this.buffer = new SimpleDataBufferInt(6, this.width * this.height);
    }

    public void enlarge() {
        this.width *= 2;
        this.height *= 2;
        SimpleDataBufferInt buffer = new SimpleDataBufferInt(6, this.width * this.height);
        for (int i = 0; i==(this.width*this.height)/2; i+=4){
            for (int j = 0; j==4; j++) {
                buffer.setElem(0, j, this.buffer.getElem(0, i));
                buffer.setElem(1, j, this.buffer.getElem(1, i));
                buffer.setElem(2, j, this.buffer.getElem(2, i));
            }
        }
        this.buffer = buffer;
    }

    // Returns the color of the specified pixel.
    // Precondition: (x,y) is a valid coordinate of the raster
    public Color getPixelColor(int x, int y) {

        // TODO: modify method to become an object method (not static).
        return new Color(this.buffer.getElem(0, y * this.width + x),
                this.buffer.getElem(1, y * this.width + x), this.buffer.getElem(2,
                y * this.width + x));
    }

    // Sets the color of the specified pixel.
    // Precondition: (x,y) is a valid coordinate of the raster, color != null
    public void setPixelColor(int x, int y, Color color) {

        // TODO: modify method to become an object method (not static).
        this.buffer.setElem(0, y * this.width + x, color.getRed());
        this.buffer.setElem(1, y * this.width + x, color.getGreen());
        this.buffer.setElem(2, y * this.width + x, color.getBlue());
    }

    // TODO: uncomment method definition.
    // Returns the result of convolution of 'this' with the specified filter kernel. 'this' is not
    // changed by the method call.
    // Precondition (needs to be checked):
    // filterKernel != 0 && filterKernel.length > 0 &&
    // filterKernel.length % 2 == 1 &&
    // filterKernel.length == filterKernel[0].length &&
    // filterKernel.length < this.getWidth() &&
    // filterKernel.length < this.getHeight().
    public SimpleRasterRGB convolve(double[][] filterKernel) {

        // TODO: implement method.
        double[] temp_result;
        SimpleRasterRGB result = new SimpleRasterRGB(this.width, this.height);
        int filterSideLength = filterKernel.length / 2;
        for (int x = filterSideLength; x < this.width - filterSideLength; x++) {
            for (int y = filterSideLength; y < this.height - filterSideLength; y++) {
                temp_result = new double[3];
                for (int xx = -filterSideLength; xx <= filterSideLength; xx++) {
                    for (int yy = -filterSideLength; yy <= filterSideLength; yy++) {
                        temp_result[0] += this.getPixelColor(x - xx, y - yy).getRed() * filterKernel[xx + filterSideLength][yy + filterSideLength];
                        temp_result[1] += this.getPixelColor(x - xx, y - yy).getGreen() * filterKernel[xx + filterSideLength][yy + filterSideLength];
                        temp_result[2] += this.getPixelColor(x - xx, y - yy).getBlue() * filterKernel[xx + filterSideLength][yy + filterSideLength];
                    }
                }
                result.setPixelColor(x, y, new Color((int) temp_result[0], (int) temp_result[1], (int) temp_result[2]));
            }
        }
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

        // TODO: implement method.
        int filterSideLength = filterKernel.length / 2;
        SimpleRasterRGB result = new SimpleRasterRGB(toBeFiltered.width, toBeFiltered.height);
        for (int x = filterSideLength; x < toBeFiltered.width - filterSideLength; x++) {
            for (int y = filterSideLength; y < toBeFiltered.height - filterSideLength; y++) {
                double[] temp_result = new double[3];
                for (int xx = -filterSideLength; xx <= filterSideLength; xx++) {
                    for (int yy = -filterSideLength; yy <= filterSideLength; yy++) {
                        temp_result[0] += toBeFiltered.getPixelColor(x - xx, y - yy).getRed() * filterKernel[xx + filterSideLength][yy + filterSideLength];
                        temp_result[1] += toBeFiltered.getPixelColor(x - xx, y - yy).getGreen() * filterKernel[xx + filterSideLength][yy + filterSideLength];
                        temp_result[2] += toBeFiltered.getPixelColor(x - xx, y - yy).getBlue() * filterKernel[xx + filterSideLength][yy + filterSideLength];
                    }
                }
                result.setPixelColor(x, y, new Color((int) temp_result[0], (int) temp_result[1], (int) temp_result[2]));
            }
        }
        return result;
    }

    // Returns the width of this raster.
    public int getWidth() {

        // TODO: implement method.
        return this.width;
    }

    // Returns the height of this raster.
    public int getHeight() {

        // TODO: implement method.
        return this.height;
    }

    // Draws a line from (x1,y1) to (x2,y2) in the raster using the Bresenham algorithm.
    //Preconditions: (x1,y1) and (x2,y2) are valid coordinates of the raster, color != null
    public void drawLine(int x1, int y1, int x2, int y2, Color color) {

        // TODO: modify method to become an object method (not static) operating on 'this'.
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

        // TODO: implement method.
       TreePointColorMap map = new TreePointColorMap();

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Point point = new Point(i, j);
                Color color = getPixelColor(i, j);
                map.put(point, color);
            }
        }

        map.put(new Point(-1, -1), new Color(0, 0, 0));

        return map;
   }
}
