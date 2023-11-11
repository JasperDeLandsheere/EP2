import java.util.Iterator;

// An iterator over elements of 'RasterizedRGB'.
//
public interface RasterizedRGBIterator extends Iterator<RasterizedRGB> {

    @Override
    // Returns the next element in the iteration.
    // (Returns 'null' if the iteration has no more elements.)
    RasterizedRGB next();

    @Override
    // Returns 'true' if the iteration has more elements.
    boolean hasNext();
}
