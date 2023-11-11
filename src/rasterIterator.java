public class rasterIterator implements RasterizedRGBIterator {
    private MultiLayerRasterRGBA layers;
    private int iter;
    private final int size;

    public rasterIterator(MultiLayerRasterRGBA layers) {
        this.iter = 0;
        this.layers = layers;
        this.size = layers.numberOfLayers();
    }

    @Override
    public RasterizedRGB next() {
        SingleLayer layer = layers.getForeground();
        if (iter++ < size - 2) {
            layers = (MultiLayerRasterRGBA) layers.getBackground();
            return layer;
        }
        layers = new MultiLayerRasterRGBA((SingleLayer) layers.getBackground(), layer);
        return layer;
    }

    @Override
    public boolean hasNext() {
        return iter < size;
    }
}