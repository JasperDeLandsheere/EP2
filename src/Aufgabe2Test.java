import java.awt.*;

public class Aufgabe2Test {

    public static void main(String[] args) {

        // TODO: remove this line

        System.out.println("Test 'SimplePointQueue':");
        SimplePointQueue q = new SimplePointQueue(3);
        q.add(new Point(0, 0));
        q.add(new Point(0, 1));
        q.add(new Point(0, 2));
        q.add(new Point(1, 0));
        q.add(new Point(1, 1));

        testValue(q.size(), 5);
        Point p = q.poll();
        testValue(p.compareTo(new Point(0, 0)), 0);
        testValue(q.size(), 4);
        p = q.poll();
        testValue(p.compareTo(new Point(0, 1)), 0);
        testValue(q.size(), 3);

        System.out.println("Test 'SimplePointColorMap':");
        SimplePointColorMap map = new SimplePointColorMap(3);
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

        System.out.println("Test 'SimpleSparseRasterRGB':");
        SimpleSparseRasterRGB r1 = new SimpleSparseRasterRGB(40, 60);
        r1.drawLine(0, 1, 35, 9, new Color(20, 25, 250));
        r1.drawLine(30, 5, 0, 30, Color.ORANGE);
        r1.drawLine(2, 0, 7, 40, Color.GREEN);

        testEquals(r1.getPixelColor(8, 3), new Color(20, 25, 250));
        testEquals(r1.getPixelColor(32, 8), new Color(20, 25, 250));
        testEquals(r1.getPixelColor(25, 9), new Color(255, 200, 0));
        testEquals(r1.getPixelColor(30, 50), new Color(0, 0, 0));

        r1.floodFill(7, 7, Color.CYAN);
        testEquals(r1.getPixelColor(10, 10), Color.CYAN);
        testEquals(r1.getPixelColor(3, 3), Color.CYAN);

        SimpleSparseRasterRGB r2 = r1.convolve(new double[][]{
                {0.077847, 0.123317, 0.077847},
                {0.123317, 0.195346, 0.123317},
                {0.077847, 0.123317, 0.077847}
        });

        testEquals(r2.getPixelColor(25, 8), new Color(66, 175, 171));
        testEquals(r2.getPixelColor(33, 10), new Color(4, 5, 50));
        testEquals(r2.getPixelColor(5, 26), new Color(39, 143, 0));


        r2.floodFill(7, 7, Color.BLACK);
        testEquals(r2.getPixelColor(3, 3), new Color(4, 208, 182));
        testEquals(r2.getPixelColor(10, 10), Color.BLACK);

        // TODO: remove this line */

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
