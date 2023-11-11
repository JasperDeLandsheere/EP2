import java.util.Scanner;

// A factory object for creating and initializing operation objects.
//
public interface SafeFactory {

    // Creates the operation object using the parameters provided by the specified
    // scanner object 'sc'.
    // Precondition: sc != null,
    // 'sc' provides required valid parameters in the format required by
    // the specific implementation of 'SafeFactory'.
    // Throws a 'FactoryException' if the operation object can not be created.
    SafeOperation create(Scanner sc) throws FactoryException;
}
