// Represents an operation that does not change the raster. This class
// has a singular instance 'SafeDoNothing.DO_NOTHING' that should be used instead of 'null'.
// Please, do not change this class definition.
//
public class SafeDoNothing implements SafeOperation {

    public static final SafeDoNothing DO_NOTHING = new SafeDoNothing();

    private SafeDoNothing() {}

    @Override
    public RasterizedRGB execute(RasterizedRGB raster) throws OperationException {

        return raster;
    }

    @Override
    public SafeOperation after(SafeOperation op) {

        return op;
    }

    @Override
    public String toString() {

        return "";
    }
}
