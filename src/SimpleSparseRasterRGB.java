import java.awt.*;

// This class represents a sparse 2D raster of RGB color entries. It has the same functionality
// as the class 'SimpleRasterRGB', but its implementation differs. It additionally has a
// flood-fill method.
//
// This class is efficient for representing images where only a small fraction of pixels is
// non-empty, meaning they have a color different from (0,0,0) corresponding to Color.BLACK.
// The class uses internally an object of 'SimplePointColorMap' to associate each non-empty
// pixel position (x,y) to the corresponding color. Only pixels with non-zero values are stored.
// Positions that are not in the set of keys of the map are considered to have value (0,0,0).
//
public class SimpleSparseRasterRGB {

    private int width;
    private int height;
    private SimplePointColorMap map;

    // Initialises this raster of the specified size as an empty
    // raster (all pixels being black, i.e. (R,G,B) = (0,0,0)).
    // Preconditions: height > 0, width > 0
    public SimpleSparseRasterRGB(int width, int height) {

        // TODO: implement constructor.
        this.width = width;
        this.height = height;
        map = new SimplePointColorMap(1);
    }

    // Returns the color of the specified pixel.
    // Preconditions: (x,y) is a valid coordinate of the raster
    public Color getPixelColor(int x, int y) {

        // TODO: implement method.
        Point point = new Point(x, y);
        Color c = map.get(point);
        if (c == null) {
            return Color.BLACK;
        }
        return c;
    }

    // Sets the color of the specified pixel. (If 'color' is 'Color.BLACK', the method
    // ensures that the pixel is not contained in the internal map.)
    // Preconditions: (x,y) is a valid coordinate of the raster, color != null
    public void setPixelColor(int x, int y, Color color) {

        // TODO: implement method.
        Point point = new Point(x, y);
        if (color.equals(color.BLACK)) {
            map.remove(point);
        } else {
            map.put(point, color);
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
    public SimpleSparseRasterRGB convolve(double[][] filterKernel) {

        // TODO: implement method.
        int filterSideLength = filterKernel.length / 2;
        SimpleSparseRasterRGB result = new SimpleSparseRasterRGB(width, height);
        SimplePointQueue queue = map.keys();



        while (queue.size() > 0) {
            Point p = queue.poll();

            int x = p.getX();
            int y = p.getY();
            Color c = getPixelColor(x, y);
            double[] temp_result = new double[3];
            for (int xx = -filterSideLength; xx <= filterSideLength; xx++) {
                for (int yy = -filterSideLength; yy <= filterSideLength; yy++) {
                    int xi = x + xx;
                    int yj = y + yy;
                    if (xi >= 0 && xi < width && yj >= 0 && yj < height) {
                        Color c2 = getPixelColor(xi, yj);
                        temp_result[0] += c2.getRed() * filterKernel[xx + filterSideLength][yy + filterSideLength];
                        temp_result[1] += c2.getGreen() * filterKernel[xx + filterSideLength][yy + filterSideLength];
                        temp_result[2] += c2.getBlue() * filterKernel[xx + filterSideLength][yy + filterSideLength];
                        // add the neighboring points to the queue
                        queue.add(new Point(xi, yj));

                    }
                }
            }
            result.setPixelColor(x, y, new Color((int) temp_result[0], (int) temp_result[1], (int) temp_result[2]));
        }
        return result;
    }

    // Returns the width of this raster.
    public int getWidth() {

        // TODO: implement method.
        return width;
    }

    // Returns the height of this raster.
    public int getHeight() {

        // TODO: implement method.
        return height;
    }

    // Sets the area of contiguous pixels of the same color to the specified color 'color', starting from
    // the initial pixel with position (x,y) and using 4-neighborhood. The method is not
    // recursive, instead it internally uses an object of 'SimplePointQueue' to which unprocessed
    // neighboring positions of the current position are added (the queue stores positions
    // that are still waiting to be processed).
    // 'floodFill' does nothing if the pixel at position (x,y) already has color 'color'.
    // Preconditions: (x,y) are valid coordinates of the raster, color != null.
    public void floodFill(int x, int y, Color color) {

        // TODO: implement method.
        Color oldColor = getPixelColor(x, y);
        if (oldColor.equals(color)) {
            return;
        }
        SimplePointQueue queue = new SimplePointQueue(1);
        queue.add(new Point(x, y));
        while (queue.size() > 0) {
            Point p = queue.poll();
            int xi = p.getX();
            int yi = p.getY();
            if (getPixelColor(xi, yi).equals(oldColor)) {
                setPixelColor(xi, yi, color);
                if (xi > 0) {
                    queue.add(new Point(xi - 1, yi));
                }
                if (xi < width - 1) {
                    queue.add(new Point(xi + 1, yi));
                }
                if (yi > 0) {
                    queue.add(new Point(xi, yi - 1));
                }
                if (yi < height - 1) {
                    queue.add(new Point(xi, yi + 1));
                }
            }
        }
    }


    // Draws a line from (x1,y1) to (x2,y2) in the raster using the Bresenham algorithm.
    // Preconditions:
    // (x1,y1) and (x2,y2) are valid coordinates of the raster, color != null.
    public void drawLine(int x1, int y1, int x2, int y2, Color color) {

        // TODO: implement method.
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
}
