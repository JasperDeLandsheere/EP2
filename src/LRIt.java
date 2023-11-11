public class LRIt implements RasterizedRGBIterator {

    LayeredRasterRGBA lr;
    public LRIt(LayeredRasterRGBA lr) {
        this.lr = lr;
    }

    @Override
    public RasterizedRGB next() {
        return lr.getLayers().pollLast();
    }

    @Override
    public boolean hasNext() {
        return lr.getLayers().size() > 0;
    }
}
