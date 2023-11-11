import java.util.Scanner;

// A factory that creates a 'UnsafeNewLayerOperation' object.
//
public class UnsafeNewLayerFactory implements UnsafeFactory {
    @Override
    // TODO: specification of the method.
    public UnsafeOperation create(Scanner sc) {

        // TODO: implement method.
        return new UnsafeNewLayerOperation();
    }
}
