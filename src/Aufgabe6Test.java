import java.awt.*;
import java.util.Scanner;

public class Aufgabe6Test {

    public static void main(String[] args) {

        // TODO: uncomment this block:

        System.out.println("Test 'TreeSparseRasterRGBA':");
        Layered r1 = new TreeSparseRasterRGBA(20,30);
        r1.setPixelColor(5,5, Color.ORANGE);
        r1.setPixelColor(6,6, Color.ORANGE);
        testEquals(r1.getPixelColor(5, 5),  Color.ORANGE);
        testEquals(r1.getPixelColor(6, 6),  Color.ORANGE);
        testEquals(r1.getPixelColor(6, 5),  new Color(0,0,0,0));
        testValue(r1.numberOfLayers(), 1);

        System.out.println("Test 'TreeSparseRasterRGBA' and 'MultiLayerRasterRGBA':");
        r1 = r1.newLayer();
        r1.setPixelColor(6,5, Color.GREEN);
        r1.setPixelColor(5,6, Color.GREEN);
        r1.convolve(new double[][]{
                {0.077847, 0.123317, 0.077847},
                {0.123317, 0.195346, 0.123317},
                {0.077847, 0.123317, 0.077847}
        });
        r1 = r1.newLayer();
        r1.setPixelColor(4,4, new Color(255,255,255,100));
        testValue(r1.numberOfLayers(), 3);

        testEquals(r1.getPixelColor(5, 5),  new Color(193,213,0, 255));
        testEquals(r1.getPixelColor(6, 6),  new Color(193,213,0, 255));
        testEquals(r1.getPixelColor(6, 5),  new Color(0,255,0, 69));
        testEquals(r1.getPixelColor(5, 6),  new Color(0,255,0, 69));
        testEquals(r1.getPixelColor(4, 4),  new Color(255,255,255, 100));
        testEquals(r1.getPixelColor(7, 7),  new Color(0,0,0, 0));
        testEquals(r1.getPixelColor(7, 5),  new Color(0,255,0, 31));

        System.out.println("Test 'TreeSparseRasterRGBA' as 'RasterizedRGBIterable':");
        RasterizedRGBIterator iterator = r1.iterator();
        testEquals(iterator.hasNext(), true);
        RasterizedRGB r;
        r = iterator.next();
        testEquals(r.getPixelColor(4, 4),  new Color(255,255,255, 100));
        testEquals(r.getPixelColor(5, 5),  new Color(0,0,0, 0));
        testEquals(iterator.hasNext(), true);
        r = iterator.next();
        testEquals(r.getPixelColor(4, 4),  new Color(0,0,0, 0));
        testEquals(r.getPixelColor(5, 5),  new Color(0,255,0, 62));
        testEquals(iterator.hasNext(), true);
        r = iterator.next();
        testEquals(r.getPixelColor(4, 4),  new Color(0,0,0, 0));
        testEquals(r.getPixelColor(5, 5),  new Color(255,200,0, 255));
        testEquals(iterator.hasNext(), false);

        System.out.println("Test 'LayeredRasterRGBA' as 'RasterizedRGBIterable':");
        LayeredRasterRGBA r2 = new LayeredRasterRGBA(20, 30);
        r2.setPixelColor(5,5, Color.ORANGE);
        r2.setPixelColor(6,6, Color.ORANGE);
        r2.newLayer();
        r2.setPixelColor(6,5, Color.GREEN);
        r2.setPixelColor(5,6, Color.GREEN);
        r2.convolve(new double[][]{
                {0.077847, 0.123317, 0.077847},
                {0.123317, 0.195346, 0.123317},
                {0.077847, 0.123317, 0.077847}
        });
        r2.newLayer();
        r2.setPixelColor(4,4, new Color(255,255,255,100));
        iterator = r2.iterator();
        testEquals(iterator.hasNext(), true);
        r = iterator.next();
        testEquals(r.getPixelColor(4, 4),  new Color(255,255,255, 100));
        testEquals(r.getPixelColor(5, 5),  new Color(0,0,0, 0));
        testEquals(iterator.hasNext(), true);
        r = iterator.next();
        testEquals(r.getPixelColor(4, 4),  new Color(0,0,0, 0));
        testEquals(r.getPixelColor(5, 5),  new Color(0,255,0, 62));
        testEquals(iterator.hasNext(), true);
        r = iterator.next();
        testEquals(r.getPixelColor(4, 4),  new Color(0,0,0, 255));
        testEquals(r.getPixelColor(5, 5),  new Color(255,200,0, 255));
        testEquals(iterator.hasNext(), false);

        System.out.println("Test 'newlayer':");
        r1 = (Layered) new UnsafeNewLayerFactory().create(new Scanner("")).execute(r1);
        testValue(r1.numberOfLayers(),4);

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
