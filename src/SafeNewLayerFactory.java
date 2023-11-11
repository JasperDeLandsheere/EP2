import java.util.Scanner;

// A factory that creates a 'SafeNewLayerOperation' object.
//
public class SafeNewLayerFactory implements SafeFactory // TODO: activate clause.
{

    // TODO: define missing parts of this class.

    public SafeOperation create(Scanner sc) { // does not throw FactoryException

        // TODO: implement method.
        return new SafeNewLayerOperation();
    }
}
