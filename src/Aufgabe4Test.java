import java.awt.*;

// A class with test cases.
public class Aufgabe4Test {

    public static void main(String[] args) {

        RasterRGBA r1 = new RasterRGBA(10, 10),
                r2 = new RasterRGBA(10, 10),
                r3 = new RasterRGBA(15, 20),
                r4 = new RasterRGBA(10, 15),
                r5 = new RasterRGBA(5, 5);

        LinkedListRasterRGBA pl = new LinkedListRasterRGBA();

        System.out.println("Checking basic functions of 'LinkedListRasterRGBA':");
        System.out.println("Test1:");
        pl.addLast(r1);
        pl.addLast(r2);
        pl.addLast(r2);
        pl.addLast(r3);
        testValue(pl.size(), 4);

        testEquals(pl.getFirst(), r1);
        testEquals(pl.getLast(), r3);

        testEquals(pl.get(0), r1);
        testEquals(pl.get(1), r2);
        testEquals(pl.get(2), r2);
        testEquals(pl.get(3), r3);

        System.out.println("Test2:");
        testValue(pl.lastIndexOf(r1), 0);
        testValue(pl.lastIndexOf(r2), 2);
        testValue(pl.lastIndexOf(r3), 3);

        System.out.println("Test3:");
        testEquals(pl.pollFirst(), r1);
        testEquals(pl.pollLast(), r3);
        testEquals(pl.pollFirst(), r2);
        testEquals(pl.pollFirst(), r2);

        testValue(pl.size(), 0);
        testIdentical(pl.getFirst(), null);

        System.out.println("Test4:");
        pl.addFirst(r1);
        pl.addFirst(r2);
        pl.addFirst(r3);
        pl.add(1, r4);
        pl.add(4, r5);

        testValue(pl.size(), 5);

        testEquals(pl.get(0), r3);
        testEquals(pl.get(1), r4);
        testEquals(pl.get(2), r2);
        testEquals(pl.get(3), r1);
        testEquals(pl.get(4), r5);

        System.out.println("Test5:");
        testEquals(pl.remove(2), r2);
        testValue(pl.size(), 4);
        testEquals(pl.remove(0), r3);
        testValue(pl.size(), 3);

        System.out.println("Test6:");
        testEquals(pl.set(2, r2), r5);
        testEquals(pl.get(2), r2);

        System.out.println("Checking basic functions of 'LayeredRasterRGBA':");
        System.out.println("Test7:");
        LayeredRasterRGBA raster = new LayeredRasterRGBA(20,25);
        raster.drawLine(2,3,10,20, new Color(255, 0, 0, 255));
        testEquals(raster.getPixelColor(6, 11), new Color(255, 0, 0, 255));
        System.out.println("Test8:");
        raster.newLayer();
        raster.drawLine(0,15,17,5, new Color(0, 255, 0, 255));
        testEquals(raster.getPixelColor(6, 11), new Color(0, 255, 0, 255));
        System.out.println("Test9:");
        raster.drawLine(0,15,17,5, new Color(0, 255, 0, 100));
        testEquals(raster.getPixelColor(6, 11), new Color(155, 100, 0, 255));
        testValue(raster.getPixelColor(6, 11).getAlpha(), 255);
        testEquals(raster.getPixelColor(2, 14), new Color(0, 100, 0, 255));
        System.out.println("Test10:");
        RasterRGBA r = raster.asRasterRGBA();
        testEquals(r.getPixelColor(6, 11), new Color(155, 100, 0, 255));
        System.out.println("Test11:");
        raster.newLayer();
        raster.setPixelColor(6, 11, new Color(0, 0, 255, 100));
        testEquals(raster.getPixelColor(6, 11), new Color(94, 61, 100, 255));
        System.out.println("Test12:");
        // set the filter kernel for blurring.
        double[][] filterKernel = new double[][]{
                {0.077847, 0.123317, 0.077847},
                {0.123317, 0.195346, 0.123317},
                {0.077847, 0.123317, 0.077847}
        };
        raster.setActiveLayer(1);
        raster.convolve(filterKernel);
        testEquals(raster.getPixelColor(13, 7), new Color(0, 39, 0, 255));
        System.out.println("Test13:");
        raster.removeLayer(1);
        testEquals(raster.getPixelColor(13, 7), new Color(0, 0, 0, 255));
        testValue(raster.numberOfLayers(), 2);

        System.out.println("Checking functions of 'LayeredRasterRGBA':");
        System.out.println("Test14:");
        TreeSetRasterRGBA set = new TreeSetRasterRGBA();
        testTrue(set.add(r1));
        testTrue(set.add(r2));
        testTrue(set.add(r3));
        testTrue(set.add(r4));
        testTrue(!set.add(r3));
        System.out.println("Test15:");
        testTrue(set.contains(r2));
        testTrue(set.contains(r3));
        testTrue(!set.contains(new RasterRGBA(10, 10)));
    }

    public static void testIdentical(Object given, Object expected) {
        if (given == expected) {
            System.out.println("Successful test");
        } else {
            System.out.println("Test NOT successful! Expected value: " + expected + " / Given value: " + given);
        }
    }

    public static void testNotIdentical(Object given, Object expected) {
        if (given != expected) {
            System.out.println("Successful test");
        } else {
            System.out.println("Test NOT successful! Expected value: " + expected + " / Given value: " + given);
        }
    }

    public static void testEquals(Object given, Object expected) {
        if (given == expected) {
            System.out.println("Successful test");
            return;
        } else {
            if (given == null) {
                System.out.println("Test NOT successful! Expected value: " +
                        expected +
                        " / Given value: null");
                return;
            }
            if (expected == null) {
                System.out.println("Test NOT successful! Expected value: null / " +
                        "Given value: " + given);
                return;
            }
        }
        if (given.equals(expected)) {
            System.out.println("Successful test");
        } else {
            System.out.println("Test NOT successful! Expected value: " + expected.toString() + " / Given " +
                    "value: " + given.toString());
        }
    }

    public static void testTrue(boolean expectedTrue) {
        if (expectedTrue) {
            System.out.println("Successful test");
        } else {
            System.out.println("Test NOT successful! Expression should be 'true' but is 'false'.");
        }
    }

    public static void testEquals(Point given, Point expected) {
        if (given == expected) {
            System.out.println("Successful test");
            return;
        } else {
            if (given == null) {
                System.out.println("Test NOT successful! Expected value: " +
                        "(" + expected.getX() + "," + expected.getY() + ")" +
                        " / Given value: null");
                return;
            }
            if (expected == null) {
                System.out.println("Test NOT successful! Expected value: null / " +
                        "Given value: " + "(" + given.getX() + "," + given.getY() + ")");
                return;
            }
        }

        if (given.compareTo(expected) == 0) {
            System.out.println("Successful test");
        } else {
            System.out.println("Test NOT successful! Expected value: (" + expected.getX() + "," + expected.getY() +
                    ") / " +
                    "Given value: (" + given.getX() + "," + given.getY() + ")");
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