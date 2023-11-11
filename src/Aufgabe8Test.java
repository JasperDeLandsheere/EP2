import java.awt.*;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Aufgabe8Test {

    public static void main(String[] args) {

        // TODO: uncomment this block:

        System.out.println("Test 'reversedIterator':");
        Layered r1 = new TreeSparseRasterRGBA(20,30);
        r1.setPixelColor(5,5, Color.ORANGE);
        r1.setPixelColor(6,6, Color.ORANGE);
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
        RasterizedRGBIterator iterator = ((MultiLayerRasterRGBA) r1).reversedIterator();
        testEquals(iterator.hasNext(), true);
        RasterizedRGB r;
        r = iterator.next();
        testEquals(r.getPixelColor(4, 4),  new Color(0,0,0, 0));
        testEquals(r.getPixelColor(5, 5),  new Color(255,200,0, 255));
        testEquals(iterator.hasNext(), true);
        r = iterator.next();
        testEquals(r.getPixelColor(4, 4),  new Color(0,0,0, 0));
        testEquals(r.getPixelColor(5, 5),  new Color(0,255,0, 62));
        testEquals(iterator.hasNext(), true);
        r = iterator.next();
        testEquals(r.getPixelColor(4, 4),  new Color(255,255,255, 100));
        testEquals(r.getPixelColor(5, 5),  new Color(0,0,0, 0));
        testEquals(iterator.hasNext(), false);

        System.out.println("Test 'newlayer':");
        try {
            r1 = (Layered) new SafeNewLayerFactory().create(new Scanner("")).execute(r1);
            testValue(r1.numberOfLayers(), 4);
        } catch (OperationException ex) {
            System.out.println("Test NOT successful! This statement should not be reached!");
        }
        System.out.println("Test 'line':");
        try {
            r1 = (Layered) new SafeLineFactory(new Color[]{Color.YELLOW})
                    .create(new Scanner("0 10 3 8")).execute(r1);
            testEquals(r1.getPixelColor(1,9), new Color(255, 255, 0, 255));
            testEquals(r1.getPixelColor(2,9), new Color(255, 255, 0, 255));
            testEquals(r1.getPixelColor(4,1), new Color(0,0,0,0));
            testEquals(r1.getPixelColor(4,4), new Color(255,255,255,100));

        } catch (FactoryException | OperationException ex) {
            System.out.println("Test NOT successful! This statement should not be reached!");
        }
        System.out.println("Test 'create':");
        try {
            r1 = (Layered) new SafeCreateFactory()
                    .create(new Scanner("15 10")).execute(r1);
            testValue(r1.getWidth(),15);
            testValue(r1.getHeight(),10);
            testEquals(r1.getPixelColor(1,9), new Color(0, 0, 0, 0));
            testEquals(r1.getPixelColor(2,9), new Color(0, 0, 0, 0));
            r1 = (Layered) new SafeCreateFactory()
                    .create(new Scanner("15")).execute(r1);
            System.out.println("Test NOT successful! This statement should not be reached!");
        } catch (FactoryException | OperationException ex) {
            System.out.println("Successful test (exception caught): " + ex);
        }
        HashMap<String, SafeFactory> commandMap = new HashMap<String, SafeFactory>();

        Color[] c = {Color.ORANGE};
        commandMap.put("line", new SafeLineFactory(c));
        commandMap.put("create", new SafeCreateFactory());
        commandMap.put("load", new SafeLoadSequenceFactory(commandMap));
        commandMap.put("newlayer", new SafeNewLayerFactory());
        try {
            SafeOperation ops = new SafeLoadSequenceFactory(commandMap)
                    .create(new Scanner("data/Image.txt"));
            r1 = (Layered) ops.execute(r1);
            testValue(r1.getWidth(),50);
            testValue(r1.getHeight(),40);
            testValue(r1.numberOfLayers(),3);
            testEquals(r1.getPixelColor(1,9), new Color(0, 0, 0, 0));
            testEquals(r1.getPixelColor(2,9), new Color(0, 0, 0, 0));
            testEquals(r1.getPixelColor(5,22), new Color(255, 200, 0, 255));
            testEquals(r1.getPixelColor(9,8), new Color(255, 200, 0, 255));
            testEquals(r1.getPixelColor(16,4), new Color(255, 200, 0, 255));
        } catch (FactoryException | OperationException ex) {
            System.out.println("Test NOT successful! This statement should not be reached!" + ex);
        }

        System.out.println("Test 'load' and class 'SafeOperationSequence':");
        try {
            SafeOperation ops = new SafeLoadSequenceFactory(commandMap)
                    .create(new Scanner("data/Image.txt"));
            ops = new SafeOperationSequence(
                    new SafeOperationSequence(
                            new SafeNewLayerOperation(),
                            new SafeLineOperation(5, 6, 7, 8, Color.BLUE)
                    ),
                    new SafeOperationSequence(
                            new SafeNewLayerOperation(),
                            new SafeLineOperation(6, 7, 8, 9, Color.RED)
                    )
            ).after(ops);
            SafeOperationIterator iter = ((SafeOperationSequence) ops).iterator();
            testEquals(iter.hasNext(), true);
            testEquals(iter.next().toString(), "create 50 40");
            testEquals(iter.hasNext(), true);
            testEquals(iter.next().toString(), "line 2 3 10 20");
            testEquals(iter.hasNext(), true);
            testEquals(iter.next().toString(), "newlayer");
            testEquals(iter.hasNext(), true);
            testEquals(iter.next().toString(), "line 0 10 17 7");
            testEquals(iter.hasNext(), true);
            testEquals(iter.next().toString(), "newlayer");
            testEquals(iter.hasNext(), true);
            testEquals(iter.next().toString(), "line 0 29 19 0");
            testEquals(iter.hasNext(), true);
            testEquals(iter.next().toString(), "newlayer");
            testEquals(iter.hasNext(), true);
            testEquals(iter.next().toString(), "line 5 6 7 8");
            testEquals(iter.hasNext(), true);
            testEquals(iter.next().toString(), "newlayer");
            testEquals(iter.hasNext(), true);
            testEquals(iter.next().toString(), "line 6 7 8 9");
            testEquals(iter.hasNext(), false);
            iter.next();
            System.out.println("Test NOT successful! This statement should not be reached!");
        } catch (FactoryException ex) {
            System.out.println("Test NOT successful! This statement should not be reached!");
        } catch (NoSuchElementException ex) {
            System.out.println("Successful test");
        }

        System.out.println("Test 'load' with invalid files:");
        try {
            SafeOperation ops = new SafeLoadSequenceFactory(commandMap)
                    .create(new Scanner("data/Invalid1.txt")); //first command is not "create"
            System.out.println("Test NOT successful! This statement should not be reached!");
        } catch (FactoryException ex) {
            System.out.println("Successful test: " + ex.toString());
        }

        try {
            SafeOperation ops = new SafeLoadSequenceFactory(commandMap)
                    .create(new Scanner("data/Invalid2.txt")); //negative line coordinate
            ops.execute(new TreeSparseRasterRGBA(100, 100));
            System.out.println("Test NOT successful! This statement should not be reached!");
        } catch (FactoryException | OperationException ex) {
            System.out.println("Successful test: " + ex.toString());
        }

        try {
            SafeOperation ops = new SafeLoadSequenceFactory(commandMap)
                    .create(new Scanner("data/Invalid3.txt")); //line coordinates are outside of
                                                               //raster frame
            ops.execute(new TreeSparseRasterRGBA(100, 100));
            System.out.println("Test NOT successful! This statement should not be reached!");
        } catch (FactoryException | OperationException ex) {
            System.out.println("Successful test: " + ex.toString());
        }

        // TODO: end of block to uncomment. */

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
