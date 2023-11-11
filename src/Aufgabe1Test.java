import java.awt.*;

public class Aufgabe1Test {

    public static void main(String[] args) {

        //test classes 'SimpleRasterRGB' and 'SimpleDataBufferInt'

        SimpleRasterRGB r1 = new SimpleRasterRGB(40, 60);
        r1.drawLine(0, 1, 35, 9, new Color(20, 25, 250));
        r1.drawLine(30, 5, 0, 30, Color.ORANGE);
        r1.drawLine(2, 0, 7, 40, Color.GREEN);

        testEquals(r1.getPixelColor(8, 3), new Color(20, 25, 250));
        testEquals(r1.getPixelColor(32, 8), new Color(20, 25, 250));
        testEquals(r1.getPixelColor(25, 9), new Color(255, 200, 0));

        SimpleRasterRGB r2 = r1.convolve(new double[][]{
                {0.077847, 0.123317, 0.077847},
                {0.123317, 0.195346, 0.123317},
                {0.077847, 0.123317, 0.077847}
        });

        testEquals(r2.getPixelColor(25, 8), new Color(66, 54, 50));
        testEquals(r2.getPixelColor(33, 10), new Color(4, 5, 50));
        testEquals(r2.getPixelColor(5, 26), new Color(39, 143, 0));

        //test static convolve method
        SimpleRasterRGB r3 = SimpleRasterRGB.convolve(r2, new double[][]{
                {1. / 25, 1. / 25, 1. / 25, 1. / 25, 1. / 25},
                {1. / 25, 1. / 25, 1. / 25, 1. / 25, 1. / 25},
                {1. / 25, 1. / 25, 1. / 25, 1. / 25, 1. / 25},
                {1. / 25, 1. / 25, 1. / 25, 1. / 25, 1. / 25},
                {1. / 25, 1. / 25, 1. / 25, 1. / 25, 1. / 25}
        });

        testEquals(r3.getPixelColor(25, 8), new Color(42, 34, 34));
        testEquals(r3.getPixelColor(33, 10), new Color(2, 3, 34));
        testEquals(r3.getPixelColor(5, 26), new Color(35, 78, 0));

    }

    public static void testIdentity(Object given, Object expected) {
        if (given == expected) {
            System.out.println("Successful test");
        } else {
            System.out.println("Test NOT successful! Expected value: " + expected + " / Given value: " + given);
        }
    }

    public static void testEquals(Object given, Object expected) {
        if (given.equals(expected)) {
            System.out.println("Successful test");
        } else {
            System.out.println("Test NOT successful! Expected value: " + expected.toString() + " / Given " +
                    "value: " + given.toString());
        }
    }

    public static void testValue(double given, double expected) {
        if (given < expected + (expected + 1) / 1e12 && given > expected - (expected + 1) / 1e12) {
            System.out.println("Successful test");
        } else {
            System.out.println("Test NOT successful! Expected value: " + expected + " / Given value: " + given);
        }
    }
}
