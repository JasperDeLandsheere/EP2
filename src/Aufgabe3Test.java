import java.awt.*;

public class Aufgabe3Test {

    public static void main(String[] args) {

        Point p1 = new Point(2, 3),
                p2 = new Point(0, 0),
                p3 = new Point(1, 2),
                p4 = new Point(10, 1),
                p5 = new Point(5, 1);

        PointLinkedList pl = new PointLinkedList();

        System.out.println("Test1:");
        pl.addLast(p1);
        pl.addLast(p2);
        pl.addLast(p3);
        testValue(pl.size(), 3);

        testEquals(pl.getFirst(), p1);
        testEquals(pl.getLast(), p3);

        testEquals(pl.get(0), p1);
        testEquals(pl.get(1), p2);
        testEquals(pl.get(2), p3);

        System.out.println("Test2:");
        testValue(pl.indexOf(p1), 0);
        testValue(pl.indexOf(p2), 1);
        testValue(pl.indexOf(p3), 2);

        System.out.println("Test3:");
        testEquals(pl.pollFirst(), p1);
        testEquals(pl.pollLast(), p3);
        testEquals(pl.pollFirst(), p2);

        testValue(pl.size(), 0);
        testIdentical(pl.getFirst(), null);

        System.out.println("Test4:");
        pl.addFirst(p1);
        pl.addFirst(p2);
        pl.addFirst(p3);
        pl.add(1, p4);
        pl.add(4, p5);

        testValue(pl.size(), 5);

        testEquals(pl.get(0), p3);
        testEquals(pl.get(1), p4);
        testEquals(pl.get(2), p2);
        testEquals(pl.get(3), p1);
        testEquals(pl.get(4), p5);

        // check basic functions of 'TreePointForceMap'

        System.out.println("Test5:");
        TreePointColorMap pcm = new TreePointColorMap();
        pcm.put(p3, Color.BLUE);
        pcm.put(p2, Color.YELLOW);
        pcm.put(p4, Color.WHITE);
        pcm.put(p5, Color.CYAN);
        pcm.put(p1, Color.RED);

        int n = pcm.numberOfLeafNodes();
        System.out.println(n);

        testEquals(pcm.get(p4),Color.WHITE);
        testEquals(pcm.get(p2),Color.YELLOW);
        testEquals(pcm.get(p5),Color.CYAN);

        testEquals(pcm.put(p3, Color.GREEN),Color.BLUE);
        testEquals(pcm.get(p3),Color.GREEN);
        testEquals(pcm.get(p1),Color.RED);

        System.out.println("Test6:");
        pl = pcm.keys();
        testValue(pl.size(), 5);
        Point p = pl.pollFirst();
        while(pl.size() > 0) {
            testValue(pl.getFirst().compareTo(p), 1);
            p = pl.pollFirst();
        }

        System.out.println("Test7:");
        SimpleRasterRGB raster = pcm.asRasterRGB(15, 17);
        testEquals(raster.getPixelColor(10,1), Color.WHITE);
        testEquals(raster.getPixelColor(p5.getX(),p5.getY()), Color.CYAN);
        testEquals(raster.getPixelColor(3,3), Color.BLACK);

        System.out.println("Test8:");
        pcm = raster.asMap();
        testEquals(pcm.get(p5),Color.CYAN);
        testEquals(pcm.get(new Point(3,3)),Color.BLACK);


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

    public static void testEquals(Point given, Point expected) {
        if (given == expected) {
            System.out.println("Successful test");
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
