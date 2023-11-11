public class SafeCreateOperation implements SafeOperationSingle // TODO: activate clause.
{
    private int width;
    private int height;

    public SafeCreateOperation(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public RasterizedRGB execute(RasterizedRGB raster) throws OperationException {
        return new TreeSparseRasterRGBA(this.width, this.height);
    }

    @Override
    public String toString() {
        return "create" + " " + this.width + " " + this.height;
    }


}
