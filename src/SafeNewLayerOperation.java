public class SafeNewLayerOperation implements SafeOperationSingle // TODO: activate clause.
{
    @Override
    public RasterizedRGB execute(RasterizedRGB raster) throws OperationException {
        raster = raster.newLayer();
        return raster;
    }

    @Override
    public String toString() {
        return "newlayer";
    }

}
