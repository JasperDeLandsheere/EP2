// Iterable objects with 'RasterizedRGB' elements.
//
public interface RasterizedRGBIterable extends Iterable<RasterizedRGB> {

    @Override
    // Returns an iterator over elements of 'RasterizedRGB'.
    RasterizedRGBIterator iterator();
}
