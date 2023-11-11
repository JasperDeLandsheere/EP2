import java.awt.*;

// A raster with multiple layers. Each layer can be modified separately. Each layer is
// represented by an object of 'RasterRGBA', such that transparent pixel colors are possible
// (colors with red, green, blue component and an alpha value controlling the transparency).
// The lowermost layer (representing the background layer) has index 0, the uppermost layer
// has index this.numberOfLayers() - 1.
// Only one layer is active at a time, on which operations can be performed.
// All layers have the same size.
public class LayeredRasterRGBA implements RasterizedRGBIterable {

    // Initialises this layered raster with one layer (lowermost background layer) of the specified
    // size with all pixels being fully opaque, i.e. (R,G,B, alpha) = (0, 0, 0, 255).
    // Preconditions: height > 0, width > 0.

    private final LinkedListRasterRGBA layers;
    private int activeLayer;


    public LayeredRasterRGBA(int width, int height) {

        layers = new LinkedListRasterRGBA();
        layers.addFirst(new RasterRGBA(width, height));
        activeLayer = 0;

        // Set all pixels to fully opaque.
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                this.setPixelColor(x, y, new Color(0, 0, 0, 255));
            }
        }
    }

    // Adds a new layer with size this.getWidth() times this.getHeight() and
    // all pixels set to color (0, 0, 0, 0) above the top-most layer
    // of this raster and sets it active.
    public void newLayer() {

        layers.addLast(new RasterRGBA(this.getWidth(), this.getHeight()));
        activeLayer = layers.size() - 1;

    }

    // Sets the layer with the specified index as active layer for future operations. All other
    // layers are set inactive.
    // Precondition: i >= 0 && i < this.numberOfLayers().
    public void setActiveLayer(int i) {

        activeLayer = i;
    }

    // Removes the layer with the specified index. If the removed layer was the active layer, the
    // uppermost layer after removal becomes the active layer.
    // The lowermost layer (with index 0) can not be removed.
    // Precondition: i > 0 && i < numberOfLayers().
    public void removeLayer(int i) {

        layers.remove(i);
        if (i == activeLayer) {
            activeLayer = layers.size() - 1;
        }
    }

    // Returns the number of layers of this raster (including layer 0).
    public int numberOfLayers() {

        return layers.size();
    }

    // Returns the color of the pixel at the specified (x, y) position in this layered raster, using
    // the 'over' compositing operator to blend the colors of the pixels in all the layers that
    // have this position. The method starts by blending the layer (index 1) above the bottom
    // most layer over the bottom-most layer (index 0). After compositing the pixels (x, y) of two
    // adjacent layers the resulting color is then compositing with the pixel (x, y) in the next
    // layer above it, and so on, until all layers have been combined into a single final pixel
    // color, which is returned by the method. The method does not change 'this'.
    // Precondition: (x, y) is a valid coordinate of the raster.
    public Color getPixelColor(int x, int y) {

        Color back = layers.get(0).getPixelColor(x, y);
        for (int i = 1; i < layers.size(); i++) {
            Color fore = layers.get(i).getPixelColor(x, y);
            back = RasterRGBA.over(fore, back);
        }

        return back;
    }

    // Sets the color of the specified pixel in the active layer.
    // Precondition: (x,y) is a valid coordinate of the raster, color != null.
    public void setPixelColor(int x, int y, Color color) {

        layers.get(activeLayer).setPixelColor(x, y, color);
    }

    // Filters the active layer by performing a convolution with the specified filter kernel.
    // Precondition:
    // filterKernel != null && filterKernel.length > 0 &&
    // filterKernel.length % 2 == 1 &&
    // filterKernel.length == filterKernel[i].length (for valid i) &&
    // filterKernel.length < this.getWidth() &&
    // filterKernel.length < this.getHeight().
    public void convolve(double[][] filterKernel) {

        layers.get(activeLayer).convolve(filterKernel);
    }

    // Returns the width of this raster.
    public int getWidth() {

        return layers.get(0).getWidth();
    }

    // Returns the height of this raster.
    public int getHeight() {

        return layers.get(0).getHeight();
    }

    // Returns the result of compositing of all layers.
    // The method applies the 'over' operator to each pair of adjacent layers, starting by blending
    // the layer (index 1) above the bottom most layer over the bottom-most layer (index 0).
    // After compositing two adjacent layers the resulting layer is then compositing with the
    // next layer above it, and so on, until all layers have been combined into a single final
    // image, which is returned by the method. The method does not change 'this'.
    public RasterRGBA asRasterRGBA() {

        RasterRGBA back = layers.get(0);
        for (int i = 1; i < layers.size(); i++) {
            RasterRGBA fore = layers.get(i);
            back = fore.over(back);
        }

        return back;
    }

    // Crops all layers of 'this' to the rectangular region with upper left coordinates (0,0)
    // and lower right coordinates (width-1, height-1).
    // Precondition: width <= this.getWidth() && height <= this.getHeight().
    public void crop(int width, int height) {

        for (int i = 0; i < layers.size(); i++) {
            layers.get(i).crop(width, height);
        }
    }

    // Draws a line from (x1,y1) to (x2,y2) in the active layer of the raster using the Bresenham
    // algorithm. At pixels of the line the color values of the active layer are replaced by the
    // specified color (no blending over).
    // Preconditions: (x1,y1) and (x2,y2) are valid coordinates of the raster, color != null.
    public void drawLine(int x1, int y1, int x2, int y2, Color color) {

        layers.get(activeLayer).drawLine(x1, y1, x2, y2, color);

    }

    public LinkedListRasterRGBA getLayers() {
        return layers;
    }

    @Override
    public RasterizedRGBIterator iterator() {
        return new LRIt(this);
    }
}

