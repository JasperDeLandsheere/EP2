public class ReversedRasterIterator implements RasterizedRGBIterator {
    private MultiLayerRasterRGBA layers;
    private int iter;
    private final int size;

    public ReversedRasterIterator(MultiLayerRasterRGBA layers) {
        this.iter = 0;
        // Get new layers by iterating using rasterIterator
        // and then reversing the order of the layers.
        RasterizedRGBIterator it = new rasterIterator(layers);
        // Store first layer
        SingleLayer firstLayer = (SingleLayer) it.next();
        // Store second layer
        SingleLayer secondLayer = (SingleLayer) it.next();
        // Create new MultiLayerRasterRGBA with second layer as foreground
        // and first layer as background.
        this.layers = new MultiLayerRasterRGBA(secondLayer, firstLayer);
        // Iterate over the rest of the layers and add them
        while (it.hasNext()) {
            this.layers = new MultiLayerRasterRGBA((SingleLayer) it.next(), this.layers);
        }
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
