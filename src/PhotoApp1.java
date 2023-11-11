import codedraw.CodeDraw;
import java.awt.*;

public class PhotoApp1 {

    public static void main(String[] args) {

        // TODO: change this method according to 'Aufgabenblatt1.md'.

        SimpleRasterRGB raster = new SimpleRasterRGB(40, 60);

        raster.drawLine(0, 1, 35, 9, new Color(20, 25, 250));
        raster.drawLine(30, 5, 0, 30, Color.ORANGE);
        raster.drawLine(2, 0, 7, 40, Color.GREEN);
        draw(raster);

        raster = raster.convolve(new double[][]{
                {0.077847, 0.123317, 0.077847},
                {0.123317, 0.195346, 0.123317},
                {0.077847, 0.123317, 0.077847}
        });

        raster.enlarge();

        draw(raster);


    }

    // Draws the image with fixed pixel size in a new window.
    public static void draw(SimpleRasterRGB raster) {

        // TODO: change this method according to 'Aufgabenblatt1.md'.
        int cellSize = 10;
        CodeDraw cd = new CodeDraw(raster.getWidth() * cellSize, raster.getHeight() * cellSize);

        // draw a square of size 'cellSize' for each pixel
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

// What is data encapsulation? Give an example of where this is used in this project?
    // Encapsulation is the process by which data (variables) and the code that acts upon them (methods)
    // are integrated as a single unit. By encapsulating a class's variables, other classes cannot access them,
    // and only the methods of the class can access them.
    // Example: The class SimpleRasterRGB is an example of data encapsulation. The variables of the class are
    // private and can only be accessed by the methods of the class. Such as the method getPixelColor().

// What is meant by data hiding? Give an example where this concept is applied in this assignment.
    // Data hiding refers to the practice of restricting access to certain data members or methods of a class
    // so that they can only be accessed and modified by the class itself and not by external code.
    // On the other side, Encapsulation is a broader concept that refers to the practice of bundling data
    // and methods that operate on that data into a single unit, called a class.
    // Data hiding is achieved through the use of access modifiers such as private, protected, and public.
    // While, Encapsulation is achieved by defining the data members of a class as private and providing public methods,
    // known as getters and setters, to access and modify the data.
    // Example: The class SimpleRasterRGB is an example of data hiding. The variables of the class are
    // private and can only be accessed by the methods of the class. So, making the variables of SimpleRasterRGB private
    // hides the data from the outside world.
// What does a method call say to the left of the . (e.g., SimpleRasterRGB.getPixelColor() or r1.getPixelColor())? How can you recognize object methods?
    // The method call says that the method getPixelColor() is called on the object SimpleRasterRGB or r1.
    // You can recognize object methods by the fact that they are not static.
// What does "this" stand for?
    // this stands for the object on which the method is called.
// Explain the difference between the methods public static SimpleRasterRGB convolve(SimpleRasterRGB toBeFiltered, double[][] filterKernel) and public SimpleRasterRGB convolve(double[][] filterKernel).
    // The first method is a static method and the second one is an object method. The first method is
    // called on the class SimpleRasterRGB and the second one is called on an object of the class
    // SimpleRasterRGB. The first method is called with two parameters, the second one is called with
    // one parameter.
// Can the private variables of another SimpleRasterRGB object be accessed within an object method of SimpleRasterRGB (e.g. this.width = r.width;)?
    // No, because the private variables of another SimpleRasterRGB object are not visible to the object
    // method of SimpleRasterRGB.

// Food for thought (without evaluation)

// Would it make sense for two images (SimpleRasterRGB type objects) to use the same SimpleDataBufferInt object to store the pixels?
    // No, because then the pixels of both images would be the same.