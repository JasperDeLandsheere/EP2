// A class for representing points with integer coordinates in 2D.
public class Point implements Comparable<Point>{
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
    @Override
    public int compareTo(Point p) {

        return (this.x < p.x) ? -1 : ((this.x > p.x) ? 1 : Integer.compare(this.y, p.y));
    }

    @Override
    // Returns 'true' if 'o' is of class 'Point' and has coordinates equal to those of 'this'.
    // (This means that for two objects p1 and p2 of 'Point', p1.equals(p2) == true if and only if
    // p1.compareTo(p2) == 0.)
    // Return 'false' otherwise.
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return this.compareTo((Point) o) == 0;
    }

    @Override
    // Returns the hash code of 'this'.
    public int hashCode() {

        return x^3 + y^3;
    }

    @Override
    // Returns a string representation of 'this'.
    public String toString() {

        return "["+getX()+", "+getY()+"]";
    }
}