import java.awt.*;
import java.util.Scanner;

public class Aufgabe5Test {

    public static void main(String[] args) {

        System.out.println("Test 'HashPointColorMap':");
        HashPointColorMap map = new HashPointColorMap();
        map.put(new Point(5, 4), Color.BLUE);
        map.put(new Point(7, 1), Color.RED);
        map.put(new Point(6, 4), Color.BLUE);
        map.put(new Point(9, 2), Color.RED);
        map.put(new Point(9, 2), Color.YELLOW);
        testIdentity(map.get(new Point(1, 1)), null);
        testEquals(map.get(new Point(7, 1)), Color.RED);
        testEquals(map.keys().size(), 4);
        testEquals(map.remove(new Point(5, 4)), Color.BLUE);
        testIdentity(map.get(new Point(5, 4)), null);
        testEquals(map.get(new Point(6, 4)), Color.BLUE);
        testEquals(map.keys().size(), 3);

        System.out.println("Test 'HashSparseRasterRGB':");
        RasterRGBA r1 = new RasterRGBA(40, 60);
        r1.clear(Color.BLACK);
        r1.drawLine(0, 1, 35, 9, new Color(20, 25, 250));
        r1.drawLine(30, 5, 0, 30, Color.ORANGE);
        r1.drawLine(2, 0, 7, 40, Color.GREEN);

        HashSparseRasterRGB copy = new HashSparseRasterRGB(40, 60);

        for (int i = 0; i < copy.getWidth(); i++) {
            for (int j = 0; j < copy.getHeight(); j++) {
                copy.setPixelColor(i, j, r1.getPixelColor(i, j));
            }
        }

        copy.convolve(new double[][]{
                {0.077847, 0.123317, 0.077847},
                {0.123317, 0.195346, 0.123317},
                {0.077847, 0.123317, 0.077847}
        });
        testEquals(copy.getPixelColor(25, 8), new Color(66, 54, 50));
        testEquals(copy.getPixelColor(33, 10), new Color(4, 5, 50));
        testEquals(copy.getPixelColor(5, 26), new Color(39, 143, 0));

        System.out.println("Test 'line':");
        RasterizedRGB r2 = new HashSparseRasterRGB(40, 60);
        new UnsafeLineFactory(new Color[]{new Color(20, 25, 250)}).create(new Scanner("0 1 35 9")).execute(r2);
        new UnsafeLineFactory(new Color[]{Color.ORANGE}).create(new Scanner("30 5 0 30")).execute(r2);
        new UnsafeLineFactory(new Color[]{Color.GREEN}).create(new Scanner("2 0 7 40")).execute(r2);
        testEquals(r2.getPixelColor(13, 19),  Color.ORANGE);
        testEquals(r2.getPixelColor(12, 20),  Color.ORANGE);
        testEquals(r2.getPixelColor(6, 30),  Color.GREEN);
        testEquals(r2.getPixelColor(5, 27),  Color.GREEN);
        testEquals(r2.getPixelColor(20, 6), new Color(20, 25, 250));
        testEquals(r2.getPixelColor(26, 7), new Color(20, 25, 250));

        System.out.println("Test 'fill':");
        r2 = new UnsafeFillFactory(new Color[]{Color.WHITE}).create(new Scanner("10 12")).execute(r2);
        testEquals(r2.getPixelColor(5, 27),  Color.GREEN);
        testEquals(r2.getPixelColor(12, 20),  Color.ORANGE);
        testEquals(r2.getPixelColor(12, 18),  Color.WHITE);
        testEquals(r2.getPixelColor(7, 20),  Color.WHITE);
        testEquals(r2.getPixelColor(0, 0),  Color.BLACK);
        testEquals(r2.getPixelColor(30, 30),  Color.BLACK);

        System.out.println("Test 'filter':");
        new UnsafeConvolveFactory(new double[][]{
                {0.077847, 0.123317, 0.077847},
                {0.123317, 0.195346, 0.123317},
                {0.077847, 0.123317, 0.077847}
        }).create(new Scanner("")).execute(r2);
        testEquals(r2.getPixelColor(25, 8), new Color(187, 175, 171));
        testEquals(r2.getPixelColor(33, 10), new Color(4, 5, 50));
        testEquals(r2.getPixelColor(5, 26), new Color(39, 143, 0));

        System.out.println("Test 'crop':");
        new UnsafeCropFactory().create(new Scanner("30 20")).execute(r2);
        testValue(r2.getWidth(), 30);
        testValue(r2.getHeight(), 20);
        testEquals(r2.getPixelColor(25, 8), new Color(187, 175, 171));
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

    public static void testValue(int given, int expected) {
        if (given == expected) {
            System.out.println("Successful test");
        } else {
            System.out.println("Test NOT successful! Expected value: " + expected + " / Given value: " + given);
        }
    }
}
