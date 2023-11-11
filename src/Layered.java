import java.awt.*;

// A raster which may consist of multiple layers. Pixel operations are carried out
// in the top-most layer of this raster.
public interface Layered extends RasterizedRGB, RasterizedRGBIterable {

    // Returns a new 'Layered' object with an additional top-most layer (above the
    // top-most layer of 'this') of size this.getWidth() times this.getHeight() and all
    // pixels set to color (0, 0, 0, 0). All layers (except the
    // new top-most layer) of the returned object are identical to the layers of 'this'.
    Layered newLayer();

    // Returns the number of layers of this raster.
    int numberOfLayers();

    // Returns the top-most layer of this raster.
    SingleLayer getForeground();

    // Returns a raster consisting of all but the top-most layer.
    Layered getBackground();

    @Override
    // Returns the color of the specified pixel, which is the result of
    // compositing the pixel colors at (x,y) from all layers of this raster.
    // (Hint: The method may use 'RasterRGBA.over' to compute the compositing.)
    // Precondition: (x,y) is a valid coordinate of the raster.
    Color getPixelColor(int x, int y);

    @Override
    // Sets the color of the specified pixel in the top-most layer.
    // Precondition: (x,y) is a valid coordinate of the raster, color != null.
    void setPixelColor(int x, int y, Color color);

    @Override
    // Crops all layers of this raster.
    // Precondition: 0 < width <= this.getWidth(), 0 < height <= this.getHeight().
    void crop(int width, int height);

    @Override
    // Returns an iterator that iterates over the layers of this raster in a top-to-bottom order.
    // (The first iteration returns the top-most layer.)
    RasterizedRGBIterator iterator();
}
