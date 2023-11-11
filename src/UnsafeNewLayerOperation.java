// This class represents an operation creating a new top-most layer.
//
public class UnsafeNewLayerOperation implements UnsafeOperation {

    // TODO: specification of the method.
    @Override
    public RasterizedRGB execute(RasterizedRGB raster) {

        // TODO: implement method.
        raster = raster.newLayer();
        return raster;
    }
}
