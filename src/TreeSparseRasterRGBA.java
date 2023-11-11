import java.awt.*;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;

// This class represents a sparse 2D raster of RGB color entries. It has the same functionality
// as the class 'SimpleRasterRGB', but its implementation differs. It additionally has a
// flood-fill method.
//
// This class is efficient for representing images where only a small fraction of pixels is
// non-empty, meaning they have a color different from (0,0,0,0).
// The class uses internally an object of 'TreeMap<Point, Color>' to associate each non-empty
// pixel position (x,y) to the corresponding color. Only pixels with non-zero values are stored.
// Positions that are not in the set of keys of the map are considered to have value (0,0,0,0).
//
public class TreeSparseRasterRGBA implements SingleLayer // TODO: activate clause.
{
    private int width;
    private int height;
    private TreeMap<Point, Color> map;

    //TODO: define missing parts of this class.

    // Initialises this raster of the specified size as an empty
    // raster (all pixels being black, i.e. (R,G,B,A) = (0,0,0,0)).
    // Preconditions: height > 0, width > 0
    public TreeSparseRasterRGBA(int width, int height) {

        this.width = width;
        this.height = height;
        map = new TreeMap<Point, Color>();
    }

    //TODO: define missing parts of this class.
    public HorizontalLineView getTopLineView() {
        for (int i = 0; i <= height; i++){
        }
    }

    // Returns a new 'Layered' object with an additional top-most layer (above the
    // top-most layer of 'this') of size this.getWidth() times this.getHeight() and all
    // pixels set to color (0, 0, 0, 0). All layers (except the
    // new top-most layer) of the returned object are identical to the layers of 'this'.
    @Override
    public Layered newLayer() {
        TreeSparseRasterRGBA newLayer = new TreeSparseRasterRGBA(this.width, this.height);
        Layered newMulti = new MultiLayerRasterRGBA(newLayer, this);
        return newMulti;

    }

    @Override
    public SingleLayer getForeground() {
        return this;
    }

    // Returns the color of the specified pixel.
    // Preconditions: (x,y) is a valid coordinate of the raster
    public Color getPixelColor(int x, int y) {

        Color c = map.get(new Point(x, y));
        if (c != null) {
            return c;
        }
        return new Color(0, 0, 0, 0);
    }

    // Sets the color of the specified pixel. (If 'color' is 'Color.BLACK', the method
    // ensures that the pixel is not contained in the internal map.)
    // Preconditions: (x,y) is a valid coordinate of the raster, color != null
    public void setPixelColor(int x, int y, Color color) {

        if (!color.equals(new Color(0,0,0,0))) {
            map.put(new Point(x, y), color);
        } else {
            map.remove(new Point(x, y));
        }
    }

    // Returns the result of convolution of 'this' with the specified filter kernel. 'this' is not
    // changed by the method call.
    // The implementation of this method exploits the sparse structure of the raster by
    // calculating the convolution only at non-empty pixel positions.
    // Preconditions:
    // filterKernel != null && filterKernel.length > 0 &&
    // filterKernel.length % 2 == 1 &&
    // filterKernel.length == filterKernel[i].length (for valid i) &&
    // filterKernel.length < this.getWidth() &&
    // filterKernel.length < this.getHeight().
    public void convolve(double[][] filterKernel) {

        int filterSideLength = filterKernel.length / 2;
        TreeSparseRasterRGBA resultRaster = new TreeSparseRasterRGBA(this.width, this.height);

        //get all non-black pixel coordinates and the neighborhood to a new ColorMap
        Set<Point> keys = map.keySet();
        Set<Point> computablePoints = new HashSet<Point>();

        for (Point p: keys) {
            int x = p.getX();
            int y = p.getY();
            for (int i = x - filterSideLength; i <= x + filterSideLength ; i++) {
                for (int j = y - filterSideLength; j <= y  + filterSideLength ; j++) {
                    computablePoints.add(new Point(i, j));
                }
            }
        }

        //process all points of the ColorMap to compute convolution result only for required pixels
        for (Point p: computablePoints) {
            //at every position, compute filter result
            int x = p.getX();
            int y = p.getY();
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
            resultRaster.setPixelColor(x, y, new Color((int) (redSum / alphaSum * 255),
                    (int) (greenSum / alphaSum * 255),
                    (int) (blueSum / alphaSum * 255), (int) alphaSum));
        }
        this.map = resultRaster.map;
    }

    // Returns the width of this raster.
    public int getWidth() {

        return width;
    }

    // Returns the height of this raster.
    public int getHeight() {

        return height;
    }

    // Crops 'this' to the rectangular region with upper left coordinates (0,0)
    // and lower right coordinates (width-1, height-1).
    // Precondition: width <= this.getWidth() && height <= this.getHeight().
    public void crop(int width, int height) {

        this.width = width;
        this.height = height;
        Set<Point> keys = map.keySet();
        Set<Point> toRemove = new HashSet<Point>();
        for (Point p: keys) {
            if (p.getX() >= width || p.getY() >= height) {
                toRemove.add(p);
            }
        }
        for (Point p: toRemove) {
            map.remove(p);
        }
    }

    @Override
    public int numberOfLayers() {
        return 1;
    }

    @Override
    public Layered getBackground() {
        return null;
    }

    @Override
    public RasterizedRGBIterator iterator() {
        return null;
    }
}

