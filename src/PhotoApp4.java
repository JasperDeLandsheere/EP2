import codedraw.CodeDraw;
import java.awt.*;
import java.util.Scanner;

public class PhotoApp4 {

    public static void main(String[] args) {

        // TODO: implement method according to 'Aufgabenblatt4.md'.
        LayeredRasterRGBA lRaster = new LayeredRasterRGBA(20, 30);
        int cellSize = 10;
        CodeDraw cd = new CodeDraw(lRaster.getWidth() * cellSize, lRaster.getHeight() * cellSize);
        cd.clear(Color.BLACK);

        Scanner sc = new Scanner(System.in);

        /*
        String input =
              "line 2 3 10 20\n" +
              "newlayer\n" +
              "setcolor 255 0 255 255\n" +
              "line 0 10 17 7\n" +
              "filter\n" +
              "setcolor 255 0 0 150\n" +
              "line 0 21 19 0\n";

        sc = new Scanner(input);
        */

        // set default color.
        Color c = Color.GREEN;

        // set the filter kernel for blurring.
        double[][] filterKernel = new double[][]{
                {0.077847, 0.123317, 0.077847},
                {0.123317, 0.195346, 0.123317},
                {0.077847, 0.123317, 0.077847}
        };

        // loop for interactive drawing.
        while(sc.hasNext()) {
            String command = sc.next();

            if (command.equals("line")) {

                // draw a line in the raster.
                int x1 = sc.nextInt();
                int y1 = sc.nextInt();
                int x2 = sc.nextInt();
                int y2 = sc.nextInt();
                lRaster.drawLine(x1,y1,x2,y2,c);

            } else if (command.equals("filter")) {

                // filter the raster using the above kernel.
                lRaster.convolve(filterKernel);

            } else if (command.equals("crop")) {

                // crop the raster to the rectangular region with upper left coordinates (0,0)
                // and lower right coordinates (width-1, height-1).
                lRaster.crop(sc.nextInt(), sc.nextInt());

            } else if (command.equals("setcolor")) {

                // set a new color for following drawing.
                c = new Color(sc.nextInt(),sc.nextInt(),sc.nextInt(), sc.nextInt());
            } else if (command.equals("newlayer")) {

                lRaster.newLayer();
            } else if (command.equals("activelayer")) {

                lRaster.setActiveLayer(sc.nextInt());
            }

            RasterRGBA sRaster = lRaster.asRasterRGBA();
            cd.clear(Color.BLACK);

            // draw a square of size 'cellSize' for each pixel
            for (int j = 0; j < sRaster.getHeight(); j++) {
                for (int i = 0; i < sRaster.getWidth(); i++) {
                    int x = i * cellSize;
                    int y = j * cellSize;
                    cd.setColor(sRaster.getPixelColor(i, j));
                    cd.fillSquare(x, y, cellSize);
                }
            }
            cd.show();
        }
    }
}
