import javax.swing.*;
import java.awt.*;
// Represents a raster consisting of at least two layers.
//
public class MultiLayerRasterRGBA implements Layered //TODO: activate clause.
{

    // TODO: define missing parts of this class.
    private SingleLayer foreground;
    private RasterizedRGB background;
    private int size = 0;
    private int width;
    private int height;

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
        if(background instanceof Layered){
            this.foreground = foreground;
            this.background = (Layered) background;

            this.size = 1 + ((Layered) background).numberOfLayers();
        }else{
            this.foreground = foreground;
            this.background = new TreeSparseRasterRGBA(foreground.getWidth(), foreground.getHeight());
            background.copyTo(this.background);
            this.size = 2;
        }
    }

    @Override
    public Layered newLayer() {

        return new MultiLayerRasterRGBA(new TreeSparseRasterRGBA(this.foreground.getWidth(), this.foreground.getHeight()), this);
    }

    @Override
    public int numberOfLayers() {
        return this.size;
    }

    @Override
    public SingleLayer getForeground() {
        return this.foreground;
    }

    @Override
    public Layered getBackground() {
        return (Layered) this.background;
    }

    @Override
    public int getWidth() {
        return this.width;
    }

    @Override
    public int getHeight() {
        return this.height;
    }

    @Override
    public Color getPixelColor(int x, int y) {
        Color color = this.foreground.getPixelColor(x, y);
        if (background != null){
            color = RasterRGBA.over(color, this.background.getPixelColor(x, y));
        }
        return color;
    }

    @Override
    public void setPixelColor(int x, int y, Color color) {
        this.foreground.setPixelColor(x, y, color);
    }

    @Override
    public void convolve(double[][] filterKernel) {
        this.foreground.convolve(filterKernel);

    }

    @Override
    public void crop(int width, int height) {
        this.width = width;
        this.height = height;
    }
    //The iterator should iterate over all levels of the grid, starting with the
    //top level. Each iteration returns the next level down, down to the bottom
    //Level. If there is no further level, the next iteration returns 'null'.
    @Override
    public RasterizedRGBIterator iterator() {
        return new rasterIterator(this);
    }

    // Returns an iterator that iterates over the layers of this raster in a bottom-to-top order.
    // (The first iteration returns the bottom-most layer.)
    public RasterizedRGBIterator reversedIterator() {

        // TODO: implement method.
        return new ReversedRasterIterator(this);
    }

    // Returns a new list with all the layers of this raster in top-to-bottom order. The size of the
    // list equals the value returned by 'numberOfLayers()'.
    public java.util.ArrayList<RasterizedRGB> asList() {

        // TODO: implement method.
        java.util.ArrayList<RasterizedRGB> list = new java.util.ArrayList<>();
        RasterizedRGBIterator it = this.iterator();
        while(it.hasNext()){
            list.add(it.next());
        }
        return list;
    }
}
