import java.util.Scanner;

// A factory object for creating and initializing operation objects without
// checking validity of input.
//
public interface UnsafeFactory {

    // Creates the operation object and initializes it with the
    // parameters read by the specified scanner object 'sc'.
    // Precondition: sc != null,
    // 'sc' provides required valid parameters in the format required by
    // the specific implementation of 'UnsafeFactory'.
    UnsafeOperation create(Scanner sc);
}
