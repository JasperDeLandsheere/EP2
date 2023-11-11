import java.util.Scanner;

// A factory that creates a 'UnsafeCropOperation' object.
//
public class UnsafeCropFactory implements UnsafeFactory {

    //TODO: declare variables (if needed).
    private int width;
    private int height;

    @Override
    // Returns a new 'UnsafeCropOperation' object. The 'width' and 'height' parameters of the
    // returned object are provided by the scanner object 'sc'.
    public UnsafeOperation create(Scanner sc) {

        // TODO: implement method.
        return new UnsafeCropOperation(sc.nextInt(), sc.nextInt());
    }
}
