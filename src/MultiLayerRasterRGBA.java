import java.awt.*;
// Represents a raster consisting of at least two layers.
//
public class MultiLayerRasterRGBA implements Layered //TODO: activate clause.
{

    // TODO: define missing parts of this class.
    private SingleLayer foreground;
    private Layered background;
    private int width;
    private int height;
    private int nLayers = 0;

    // Initializes 'this' with top-layer 'foreground' and 'background'.
    // Performs dynamic type checking of 'background'. If 'background' is an instance of 'Layered'
    // this constructor initializes 'this' with top-layer 'foreground' and layers of the
    // 'background'.
    // If 'background' is not an instance of 'Layered', 'background' is copied to a new object of
    // 'SingleLayer' which is then used to initialize the background.
    // Width and height of this raster is determined by width and height of the 'foreground'
    // raster.
    // Pixels that are not defined in the 'background' are assumed to have color (0,0,0,0).
    public MultiLayerRasterRGBA(SingleLayer foreground, RasterizedRGB background) {
        this.width = foreground.getWidth();
        this.height = foreground.getHeight();

        // TODO: dynamic type checking.
        this.foreground = foreground;
        if (background instanceof Layered) {
            this.background = (Layered) background;
            this.nLayers = 1 + this.background.numberOfLayers();
        } else {
            this.background = new TreeSparseRasterRGBA(width, height);
            background.copyTo(this.background);
            this.nLayers = 2;
        }
    }

    @Override
    public Layered newLayer() {
        return new MultiLayerRasterRGBA(new TreeSparseRasterRGBA(width, height), this);
    }

    @Override
    public int numberOfLayers() {
        return nLayers;
    }

    @Override
    public SingleLayer getForeground() {
        return foreground;
    }

    @Override
    public Layered getBackground() {
        return background;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public Color getPixelColor(int x, int y) {
        Color color = foreground.getPixelColor(x, y);
        if (background != null) {
            color = RasterRGBA.over(color, background.getPixelColor(x, y));
        }
        return color;
    }

    @Override
    public void setPixelColor(int x, int y, Color color) {
        foreground.setPixelColor(x, y, color);
    }

    @Override
    public void convolve(double[][] filterKernel) {
        foreground.convolve(filterKernel);
    }

    @Override
    public void crop(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public void floodFill(Point p, Color color) {
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
                this.setPixelColor(current.getX(), current.getY(), color);
                q.add(new Point(current.getX() + 1, current.getY()));
                q.add(new Point(current.getX() - 1, current.getY()));
                q.add(new Point(current.getX(), current.getY() + 1));
                q.add(new Point(current.getX(), current.getY() - 1));
            }
        }

    }

    @Override
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

    @Override
    public void brighten(int N) {

    }

    @Override
    public RasterizedRGBIterator iterator() {

        return new MLRIt(this);
    }
}

