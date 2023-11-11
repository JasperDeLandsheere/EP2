// Represents an operation performed on a 'RasterizedRGB'-object.
//
public interface SafeOperation {

    // Returns the result of this operation on the specified 'raster'.
    // The specification of this method allows implementations where the specified 'raster'
    // is directly modified or where it is not changed by the method call.
    // In cases where the specified 'raster' object is directly modified the returned object
    // can be identical to the specified object.
    // The method throws an 'OperationException' if the execution fails, including the case
    // where raster == null.
    RasterizedRGB execute(RasterizedRGB raster) throws OperationException;

    // Returns a new operation representing the composed operation in which 'this' is applied to
    // the result of 'op'.
    // Precondition: op != null.
    default SafeOperation after(SafeOperation op) {

        if (op == SafeDoNothing.DO_NOTHING) return this;
        if (this == SafeDoNothing.DO_NOTHING) return op;
        return new SafeOperationSequence(op, this);
    }

    // Returns a readable representation of this operation. It corresponds to the
    // command that generated the operation. If 'this' is a sequence of operations
    // then every command is represented in a separate line in the corresponding order.
    String toString();
}
