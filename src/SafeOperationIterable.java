// Iterable objects with 'SafeOperationSingle' elements.
//
public interface SafeOperationIterable extends Iterable<SafeOperationSingle> {

    @Override
    // Returns an iterator over elements of 'SafeOperationSingle'.
    SafeOperationIterator iterator();
}
