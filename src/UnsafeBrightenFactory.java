import java.util.Scanner;

// A factory that creates a 'UnsafeCropOperation' object.
//
public class UnsafeBrightenFactory implements UnsafeFactory {

    @Override
    // Returns a new 'UnsafeCropOperation' object. The 'width' and 'height' parameters of the
    // returned object are provided by the scanner object 'sc'.
    public UnsafeOperation create(Scanner sc) {
        int N = sc.nextInt();

        return new UnsafeBrightenOperation(N);
    }
}
