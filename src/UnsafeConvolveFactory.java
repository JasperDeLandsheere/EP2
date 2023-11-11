import java.util.Scanner;

// A factory that creates a 'UnsafeConvolveOperation' object.
public class UnsafeConvolveFactory implements UnsafeFactory {

    private double[][] filterKernel;

    // Initialises 'this' with the specified 'filterKernel' used in the convolution object
    // to be created by 'create'.
    // Precondition:
    // filterKernel != null && filterKernel.length > 0 &&
    // filterKernel.length % 2 == 1 &&
    // filterKernel.length == filterKernel[i].length (for valid i).
    public UnsafeConvolveFactory(double[][] filterKernel) {

        this.filterKernel = filterKernel;
    }

    // Returns a new 'UnsafeConvolveOperation'. The specified scanner is not used by this method.
    @Override
    public UnsafeOperation create(Scanner sc) {

        return new UnsafeConvolveOperation(filterKernel);
    }
}
