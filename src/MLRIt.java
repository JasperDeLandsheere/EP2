public class MLRIt implements RasterizedRGBIterator {
    private MultiLayerRasterRGBA mlr;
    private int i = 0;
    private final int n;

    public MLRIt(MultiLayerRasterRGBA mlr) {
        this.mlr = mlr;
        this.n = mlr.numberOfLayers();
    }

    @Override
    public RasterizedRGB next() {
        SingleLayer sl = mlr.getForeground();
        if (i++ < n - 2) {
            mlr = (MultiLayerRasterRGBA) mlr.getBackground();
            return sl;
        }
        mlr = new MultiLayerRasterRGBA((SingleLayer) mlr.getBackground(), sl);
        return sl;
    }

    @Override
    public boolean hasNext() {
        return i < n;
    }
}
