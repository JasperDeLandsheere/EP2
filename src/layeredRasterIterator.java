public class layeredRasterIterator implements RasterizedRGBIterator {
    LayeredRasterRGBA layers;
    public layeredRasterIterator(LayeredRasterRGBA layers) {
        this.layers = layers;
    }

    @Override
    public RasterizedRGB next() {
        return layers.getLayers().pollLast();
    }

    @Override
    public boolean hasNext() {
        return layers.getLayers().size() > 0;
    }
}
