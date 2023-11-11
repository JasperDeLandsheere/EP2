import codedraw.CodeDraw;
import java.awt.*;

public class PhotoApp1 {

    public static void main(String[] args) {

        SimpleRasterRGB raster = new SimpleRasterRGB(40, 60);

        raster.drawLine(0, 1, 35, 9, new Color(20, 25, 250));
        raster.drawLine(30, 5, 0, 30, Color.ORANGE);
        raster.drawLine(2, 0, 7, 40, Color.GREEN);
        draw(raster);

        SimpleRasterRGB convolved = raster.convolve(new double[][]{
                {0.077847, 0.123317, 0.077847},
                {0.123317, 0.195346, 0.123317},
                {0.077847, 0.123317, 0.077847}
        });
        draw(convolved);
    }

    // Draws the image with fixed pixel size in a new window.
    public static void draw(SimpleRasterRGB raster) {

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


/*Beantworten Sie folgende Zusatzfragen als Kommentar in PhotoApp1.java:

 - Was versteht man unter Datenkapselung? Geben Sie ein Beispiel, wo dieses Konzept in dieser Aufgabenstellung angewendet wird.
Data encapsulation is the process of combining data and associated methods into a single unit.
By encapsulating a class's variables, other classes cannot access them, and only the methods of the class can access them.
For example, we encapsulate instances of SimpleDataBufferInt into instances of the class SimpleRasterRGB, and we will never
access SimpleDataBufferInt directly, but we will do it only inside SimpleDataBufferInt. The only object that provides functions
'to use' by the user will be SimpleRasterRGB, and the user do not have to worry about the underlying classes like SimpleDataBufferInt.

 - Was versteht man unter Data Hiding? Geben Sie ein Beispiel, wo dieses Konzept in dieser Aufgabenstellung angewendet wird.
Data hiding is a concept in Object Oriented Programming (OOP) which is used to protect the internal details of an object from outside access.
Data hiding can be achieved through the use of private or public variables and methods, which define the level of access
that other objects have to the object’s data and methods. The idea is that we want to grant just the minimal and necessary
access to the internal state of an object. If we do so, we normally define public getter or setter methods that will interact
with the private variables of the object.
For example, we hide the variables height and width of SimpleRasterRGB making them private. To access its information from
the outside of the class, we defined a getWidth() and getHeight() methods.

- Was steht bei einem Methodenaufruf links vom . (z.B. bei SimpleRasterRGB.getPixelColor() oder r1.getPixelColor())?
- Woran erkennt man dabei Objektmethoden?
- Wofür steht this?
On the left of the '.' we have the class (or instance of class) that the called method belongs to. If we have a class name
(z.B. SimpleRasterRGB.getPixelColor()), we know it's a static method, which means it does not belong to a particular instance
and behaves in the same way regardless of how instances of this class could be constructed.
If we have an instance (z.B. r1.getPixelColor(), where r1 instance of SimpleRasterRGB), then we know it's a non-static method,
which means that the method could depend on how the instance was instantiated (constructed).
Object methods in Java are recognized by the keywords public and static, followed by the return type of the method (or keyword void),
the name of the method, and finally the parameters of the method between brackets. Example:
    public int getElem(int bankIndex, int i) {

        return this.data[bankIndex][i];
    }
The keyword 'this' is used to access the internal state of the object (obj. methods and obj. variables), inside
the definition of the object. One could think that outside of the definition of the object, 'this' is replaced by the
name of the instance of the class.

- Erklären Sie den Unterschied zwischen den Methoden public static SimpleRasterRGB convolve(SimpleRasterRGB
toBeFiltered, double[][] filterKernel) und public SimpleRasterRGB convolve(double[][] filterKernel).
The first method is static, which means that does not depend or relate to a particular instance. It convolves a given
SimpleRasterRGB names toBeFiltered, and returns the resulting SimpleRasterRGB instance. The second method is non-static,
and it convolves the SimpleRasterRGB contained in the instance (.this) with the given filter. However, according to
the method description, we do not modify .this and we create and return a new SimpleRasterRGB instead.

- Kann innerhalb einer Objektmethode von SimpleRasterRGB auf die privaten Variablen eines anderen SimpleRasterRGB
Objekts zugegriffen werden (z.B. this.width = r.width;)?
Yes, because Java defines access control by class and not by instance. Therefore, we can access private variables and
private methods of an instance of the same class, inside the class definition.


*/