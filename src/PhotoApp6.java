import codedraw.CodeDraw;

import java.awt.*;
import java.util.HashMap;
import java.util.Scanner;

public class PhotoApp6 {

    public static void main(String[] args) {

        // TODO: modify according to 'Aufgabenblatt6.md'.

        // TODO: declare and initialize 'Layered' variable.
        Layered raster = new MultiLayerRasterRGBA(
                new TreeSparseRasterRGBA(40,60),
                new TreeSparseRasterRGBA(40,60));

        // set default color.
        Color[] c = {Color.GREEN};
        // The variable c contains the default color as an array entry. An array is
        // used because it enables the default color to be changed by other classes after
        // it has been passed to a factory object (multiple objects use identical array).

        String input =
            "line 2 3 10 20\n" +
            "newlayer\n" +
            "setcolor 255 0 255 255\n" +
            "line 0 10 17 7\n" +
            "filter\n" +
            "newlayer\n" +
            "setcolor 255 0 0 255 \n" +
            "line 0 30 19 0\n" +
            "newlayer\n" +
            "setcolor 255 255 255 255\n" +
            "fill 10 11\n" +
            "crop 15 20\n";

        Scanner sc = new Scanner(input);

        // set the filter kernel for all blurring operations in this app.
        double[][] filterKernel = new double[][]{
                {0.077847, 0.123317, 0.077847},
                {0.123317, 0.195346, 0.123317},
                {0.077847, 0.123317, 0.077847}
        };

        // HashMap<String, UnsafeFactory> is a predefined associative data structure in the
        // Java libraries implemented using a hash table, where keys are of type 'String' and
        // associated values of type 'UnsafeFactory'.
        HashMap<String, UnsafeFactory> commandMap = new HashMap<String, UnsafeFactory>();
        commandMap.put("line", new UnsafeLineFactory(c));
        commandMap.put("filter", new UnsafeConvolveFactory(filterKernel));
        commandMap.put("crop", new UnsafeCropFactory());
        commandMap.put("fill", new UnsafeFillFactory(c));
        commandMap.put("brighten", new UnsafeBrightenFactory());
        commandMap.put("newlayer", new UnsafeNewLayerFactory());

        while (sc.hasNext()) {
            String command = sc.next();

            if(command.equals("setcolor")) {
                c[0] = new Color(sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt());
                commandMap.put("line", new UnsafeLineFactory(c));
                commandMap.put("fill", new UnsafeFillFactory(c));
            } else {
                // commands with a corresponding factory object creating an operation on a raster.
                raster = (Layered) commandMap.get(command).create(sc).execute(raster);
            }
        }

        //TODO: finally visualize all layers separately.
        int cellSize = 10;

        for (RasterizedRGB layer : raster) {    // we can do that because we implemented Iterable
            CodeDraw cd = new CodeDraw(raster.getWidth() * cellSize, raster.getHeight() * cellSize);
            cd.clear(Color.BLACK);
            for (int j = 0; j < raster.getHeight(); j++) {
                for (int i = 0; i < raster.getWidth(); i++) {
                    int x = i * cellSize;
                    int y = j * cellSize;
                    cd.setColor(layer.getPixelColor(i, j));
                    cd.fillSquare(x, y, cellSize);
                }
            }
            cd.show();
        }

        // Also visualize it alltogether.
        CodeDraw cd = new CodeDraw(raster.getWidth() * cellSize, raster.getHeight() * cellSize);
        cd.clear(Color.BLACK);
        for (int j = 0; j < raster.getHeight(); j++) {
            for (int i = 0; i < raster.getWidth(); i++) {
                int x = i * cellSize;
                int y = j * cellSize;
                cd.setColor(raster.getPixelColor(i, j));
                cd.fillSquare(x, y, cellSize);
            }
        }
        cd.show();
    }
}
