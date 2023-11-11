// A class for representing points with integer coordinates in 2D.
public class Point implements Comparable<Point> {
    private final int x, y;

    // Initializes this point with its coordinates.
    public Point(int x, int y) {

        this.x = x;
        this.y = y;
    }

    // Returns the x-coordinate of this point.
    public int getX() {

        return x;
    }

    // Returns the y-coordinate of this point.
    public int getY() {

        return y;
    }

    // Compares this point with a specified point. Defines an order relation ("less-than"
    // relation) on objects of 'Point'. Returns 0 if and only if the two coordinates of 'this'
    // and 'p' are equal. Returns -1 if 'this' is less than 'p' or 1 otherwise.
    // Precondition: p != null
    public int compareTo(Point p) {

        // TODO: implement method.

        if (this.x == p.x && this.y == p.y) {
            return 0;
        } else if (this.x < p.x || (this.x == p.x && this.y < p.y)) {
            return -1;
        } else {
            return 1;
        }
    }

    @Override
    // Returns 'true' if 'o' is of class 'Point' and has coordinates equal to those of 'this'.
    // (This means that for two objects p1 and p2 of 'Point', p1.equals(p2) == true if and only if
    // p1.compareTo(p2) == 0.)
    // Return 'false' otherwise.
    public boolean equals(Object o) {

        //TODO: implement method.
        if (o instanceof Point) {
            return this.compareTo((Point) o) == 0;
        } else {
        return false;
        }
    }

    @Override
    // Returns the hash code of 'this'.
    public int hashCode() {

        //TODO: implement method.
        return ((Integer)x).hashCode() * ((Integer)y).hashCode();
    }

    @Override
    // Returns a string representation of 'this'.
    public String toString() {

        return "["+getX()+", "+getY()+"]";
    }
}