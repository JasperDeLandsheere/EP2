import java.awt.*;
import java.util.Scanner;

// A factory that creates a 'UnsafeFillOperation' object.
//
public class UnsafeFillFactory implements UnsafeFactory {

    private Color[] c;

    public UnsafeFillFactory(Color[] c) {

        this.c = c;
    }

    @Override
    public UnsafeOperation create(Scanner sc) {

        int x = sc.nextInt();
        int y = sc.nextInt();

        return new UnsafeFillOperation(new Point(x, y), c);
    }
}
