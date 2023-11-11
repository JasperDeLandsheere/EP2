import java.awt.*;

// This class represents a sparse 2D raster of RGB color entries.
//
// This class is efficient for representing images where only a small fraction of pixels is
// non-empty, meaning they have a color different from (0,0,0) corresponding to Color.BLACK.
// The class uses internally an object of 'HashPointColorMap' to associate each non-empty
// pixel position (x,y) to the corresponding color. Only pixels with non-zero values are stored.
// Positions that are not in the set of keys of the map are considered to have value (0,0,0).
//
public class HashSparseRasterRGB implements RasterizedRGB {

    // TODO: declare variables.
    private int width;
    private int height;
    private HashPointColorMap map;

    // Initialises this raster of the specified size as an empty
    // raster (all pixels being black, i.e. (R,G,B) = (0,0,0)).
    // Preconditions: height > 0, width > 0
    public HashSparseRasterRGB(int width, int height) {

        // TODO: implement constructor.
        this.width = width;
        this.height = height;
        map = new HashPointColorMap();
    }

    // Returns the color of the specified pixel.
    // Preconditions: (x,y) is a valid coordinate of the raster
    public Color getPixelColor(int x, int y) {

        // TODO: implement method.
        if (map.get(new Point(x, y)) == null) {
            return Color.BLACK;
        }
        return map.get(new Point(x, y));
    }

    // Sets the color of the specified pixel. (If 'color' is 'Color.BLACK', the method
    // ensures that the pixel is not contained in the internal map.)
    // Preconditions: (x,y) is a valid coordinate of the raster, color != null
    public void setPixelColor(int x, int y, Color color) {

        // TODO: implement method.
        Point point = new Point(x, y);
        if (color.equals(Color.BLACK)) {
            map.remove(point);
        } else {
            map.put(point, color);
        }
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

    // Performs the convolution of 'this' with the specified filter kernel. 'this' is the result of
    // the operation.
    // The implementation of this method exploits the sparse structure of the raster by
    // calculating the convolution only at non-empty pixel positions.
    // Preconditions:
    // filterKernel != null && filterKernel.length > 0 &&
    // filterKernel.length % 2 == 1 &&
    // filterKernel.length == filterKernel[i].length (for valid i) &&
    // filterKernel.length < this.getWidth() &&
    // filterKernel.length < this.getHeight().
    public void convolve(double[][] filterKernel) {

        // TODO: implement method.
        int filterSideLength = filterKernel.length / 2;
        HashSparseRasterRGB result = new HashSparseRasterRGB(width, height);
        SimplePointQueue queue = map.keys();

        while (queue.size() > 0) {
            Point p = queue.poll();
            int x = p.getX();
            int y = p.getY();
            double[] temp_result = new double[3];

            for (int xx1 = -filterSideLength; xx1 <= filterSideLength; xx1++) {
                for (int yy1 = -filterSideLength; yy1 <= filterSideLength; yy1++) {
                    int xi1 = x + xx1;
                    int yj1 = y + yy1;
                    for (int xx2 = -filterSideLength; xx2 <= filterSideLength; xx2++) {
                        for (int yy2 = -filterSideLength; yy2 <= filterSideLength; yy2++) {
                            int xi2 = xi1 + xx2;
                            int yj2 = yj1 + yy2;
                            if (xi2 >= 0 && xi2 < width && yj2 >= 0 && yj2 < height) {
                                Color c2 = getPixelColor(xi2, yj2);
                                temp_result[0] += c2.getRed() * filterKernel[xx2 + filterSideLength][yy2 + filterSideLength];
                                temp_result[1] += c2.getGreen() * filterKernel[xx2 + filterSideLength][yy2 + filterSideLength];
                                temp_result[2] += c2.getBlue() * filterKernel[xx2 + filterSideLength][yy2 + filterSideLength];
                            }
                        }
                    }
                    result.setPixelColor(xi1, yj1, new Color((int) temp_result[0], (int) temp_result[1], (int) temp_result[2]));
                    temp_result[0] = 0;
                    temp_result[1] = 0;
                    temp_result[2] = 0;
                }
            }
        }
        this.map = result.map;
    }

    // Crops 'this' to the rectangular region with upper left coordinates (0,0)
    // and lower right coordinates (width-1, height-1).
    // Precondition: width <= this.getWidth() && height <= this.getHeight().
    public void crop(int width, int height) {

        // TODO: implement method.
        HashSparseRasterRGB result = new HashSparseRasterRGB(width, height);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                result.setPixelColor(x, y, this.getPixelColor(x, y));
            }
        }
        this.width = width;
        this.height = height;
        this.map = result.map;
    }

    @Override
    public RasterizedRGB newLayer() {
        return null;
    }
}
