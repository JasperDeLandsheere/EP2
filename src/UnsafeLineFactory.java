import java.awt.*;
import java.util.Scanner;

// A factory that creates an 'UnsafeLineOperation' object.
//
public class UnsafeLineFactory implements UnsafeFactory {

    //TODO: declare variables.
    private Color[] color;

    // Initializes 'this' with an array 'color'.
    // 'color' contains the default color as an array entry (color[0]). An array is
    // used because it enables the default color to be changed by other classes after 'this'
    // has been initialized. Other entries in the specified array (except color[0]) are ignored.
    // Precondition: color != null && color.length > 0.
    public UnsafeLineFactory(Color[] color) {

        //TODO: implement constructor.
        this.color = color;
    }

    // Returns a new 'UnsafeLineOperation' object. The coordinates for the starting point and end
    // point are provided by the scanner object 'sc'.
    @Override
    public UnsafeOperation create(Scanner sc) {

        // TODO: implement method.
        return new UnsafeLineOperation(sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt(), color[0]);
    }
}
