// Represents a succession of two operations. Each of which can be itself of type
// 'SafeOperationSequence' such that this class represents a recursive (tree-like)
// structure. The foundation (leafs of the tree) is represented by objects of
// 'SafeSingleOperation'.
public class SafeOperationSequence implements SafeOperation, SafeOperationIterable {

    //TODO: define missing parts of this class.
    private SafeOperation first;
    private SafeOperation second;

    public SafeOperationSequence(SafeOperation first, SafeOperation second) {

        //TODO: implement constructor.
        this.first = first;
        this.second = second;
    }

    @Override
    public RasterizedRGB execute(RasterizedRGB raster) throws OperationException {

        //TODO: implement method.
        return this.second.execute(this.first.execute(raster));
    }

    public SafeOperation getFirst() {

        //TODO: implement method.
        return this.first;
    }

    public SafeOperation getSecond() {

        //TODO: implement method.
        return this.second;
    }

    @Override
    public SafeOperationIterator iterator() {

        //TODO: implement method.
        return new mySafeOperationIterator(this);
    }

    @Override
    public String toString() {

        //TODO: implement method
        return this.first.toString();
    }

    public void setFirst(SafeOperation first) {
        this.first = first;
    }

    public void setSecond(SafeOperation second) {
        this.second = second;
    }
}

