// Represents an operation performed on a 'RasterizedRGB'-object.
//
public interface UnsafeOperation {

    // Returns the result of this operation on the specified 'raster'.
    // The specification of this method allows implementations where the specified 'raster'
    // is directly modified or where it is not changed by the method call.
    // In cases where the specified 'raster' object is directly modified the returned object
    // can be identical to the specified object.
    RasterizedRGB execute(RasterizedRGB raster);
}
