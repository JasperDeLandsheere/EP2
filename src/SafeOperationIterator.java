import java.util.Iterator;

// An iterator over elements of 'SafeOperationSingle'.
//
public interface SafeOperationIterator extends Iterator<SafeOperationSingle> {

    @Override
    // Returns the next element in the iteration.
    // Throws a 'java.util.NoSuchElementException' if the iteration has no more elements.
    SafeOperationSingle next();

    @Override
    // Returns 'true' if the iteration has more elements.
    boolean hasNext();
}