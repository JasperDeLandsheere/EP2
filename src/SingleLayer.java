// One layer of a 'Layered' object.
public interface SingleLayer extends Layered {

    Layered newLayer();
    // Returns 1.
    @Override
    int numberOfLayers();

    // Returns 'null'.
    @Override
    Layered getBackground();

    // An Iterator with one iteration.
    @Override
    RasterizedRGBIterator iterator();
}
