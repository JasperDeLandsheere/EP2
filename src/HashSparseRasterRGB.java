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

    // Initialises this raster of the specified size as an empty
    // raster (all pixels being black, i.e. (R,G,B) = (0,0,0)).
    // Preconditions: height > 0, width > 0

    private HashPointColorMap map = new HashPointColorMap();
    private int width;
    private int height;

    public HashSparseRasterRGB(int width, int height) {

        this.width = width;
        this.height = height;

    }

    // Returns the color of the specified pixel.
    // Preconditions: (x,y) is a valid coordinate of the raster
    public Color getPixelColor(int x, int y) {

        Color col = map.get(new Point(x,y));
        return col == null ? Color.BLACK : col;
    }

    // Sets the color of the specified pixel. (If 'color' is 'Color.BLACK', the method
    // ensures that the pixel is not contained in the internal map.)
    // Preconditions: (x,y) is a valid coordinate of the raster, color != null
    public void setPixelColor(int x, int y, Color color) {
        if (color.equals(Color.BLACK)) {
            map.remove(new Point(x,y));
        } else {
            map.put(new Point(x, y), color);
        }
    }

    // Returns the width of this raster.
    public int getWidth() {

        return width;
    }

    // Returns the height of this raster.
    public int getHeight() {

        return height;
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

        // queue of points to calculate the convolution to
        SimplePointQueue toConvolveQueue = getPointsToConvolve(filterKernel);
        // map to store the new colors
        HashPointColorMap temp = new HashPointColorMap();

        while (toConvolveQueue.size() > 0) {
            double r = 0, g = 0, b = 0;
            Point p = toConvolveQueue.poll();
            for (int i = 0; i < filterKernel.length; i++) {
                for (int j = 0; j < filterKernel.length; j++) {
                    int x = p.getX() + i - filterKernel.length / 2;
                    int y = p.getY() + j - filterKernel.length / 2;
                    if (x >= 0 && x < this.width && y >= 0 && y < this.height) {
                        Color c = this.getPixelColor(x, y);
                        r += c.getRed() * filterKernel[i][j];
                        g += c.getGreen() * filterKernel[i][j];
                        b += c.getBlue() * filterKernel[i][j];
                    }
                }
            }
            temp.put(p, new Color((int) r, (int) g, (int) b));
        }

        this.map = temp;
    }

    private SimplePointQueue getPointsToConvolve(double[][] filterKernel) {
        int filterSize = filterKernel.length;
        SimplePointQueue nonBlack = this.map.keys();
        HashPointColorMap toConvolve = new HashPointColorMap();

        // add neighboring points to a map according to filter size
        while (nonBlack.size() > 0) {
            Point p = nonBlack.poll();
            for (int i = 0; i < filterSize; i++) {
                for (int j = 0; j < filterSize; j++) {
                    int x = p.getX() + i - filterSize / 2;
                    int y = p.getY() + j - filterSize / 2;
                    if (x >= 0 && x < width && y >= 0 && y < height) {
                        toConvolve.put(new Point(x, y), Color.GREEN);
                    }
                }
            }
        }
        return toConvolve.keys();
    }

    // Crops 'this' to the rectangular region with upper left coordinates (0,0)
    // and lower right coordinates (width-1, height-1).
    // Precondition: width <= this.getWidth() && height <= this.getHeight().
    public void crop(int width, int height) {

        HashPointColorMap temp = new HashPointColorMap();
        SimplePointQueue nonBlack = this.map.keys();

        while (nonBlack.size() > 0) {
            Point p = nonBlack.poll();
            if (p.getX() < width && p.getY() < height) {
                temp.put(p, this.getPixelColor(p.getX(), p.getY()));
            }
        }

        this.map = temp;
        this.width = width;
        this.height = height;
    }

    public void floodFill(Point p, Color c) {
        SimplePointQueue q = new SimplePointQueue(1000);

        Color originalColor = this.getPixelColor(p.getX(), p.getY());
        q.add(p);

        while (q.size() > 0) {
            Point current = q.poll();
            if (current.getX() < 0 || current.getX() >= this.getWidth()
                    || current.getY() < 0 || current.getY() >= this.getHeight()) {
                continue;
            }
            if (this.getPixelColor(current.getX(), current.getY()).equals(originalColor)) {
                this.setPixelColor(current.getX(), current.getY(), c);
                q.add(new Point(current.getX() + 1, current.getY()));
                q.add(new Point(current.getX() - 1, current.getY()));
                q.add(new Point(current.getX(), current.getY() + 1));
                q.add(new Point(current.getX(), current.getY() - 1));
            }
        }
    }

    public void drawLine(Point p1, Point p2, Color color) {
        int x1 = p1.getX();
        int y1 = p1.getY();
        int x2 = p2.getX();
        int y2 = p2.getY();

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

    public void brighten(int N) {
        SimplePointQueue toBrighten = this.map.keys();

        while (toBrighten.size() > 0) {
            Point p = toBrighten.poll();
            Color tempCol = this.getPixelColor(p.getX(), p.getY());
            for (int i = 0; i < N; ++i) {
                tempCol = tempCol.brighter();
            }
            this.setPixelColor(p.getX(), p.getY(), tempCol);

        }

    }

    @Override
    public RasterizedRGB newLayer() {
        return null;
    }
}
