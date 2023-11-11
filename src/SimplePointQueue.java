public class SimplePointQueue {

    // TODO: declare variables.
    private Point[] points;
    private int size;
    private int capacity;

    // Initializes this queue with an initial capacity (length of internal array).
    // Precondition: initialCapacity > 0.
    public SimplePointQueue(int initialCapacity) {

        //TODO: define constructor.
        capacity = initialCapacity;
        points = new Point[capacity];
        size = 0;
    }
    

    // Adds the specified point 'p' to this queue.
    // Precondition: p != null.
    public void add(Point p) {

        // TODO: implement method.
        if (size == capacity) {
            capacity *= 2;
            Point[] newPoints = new Point[capacity];
            for (int i = 0; i < size; i++) {
                newPoints[i] = points[i];
            }
            points = newPoints;
        }
        points[size] = p;
        size++;
    }

    // Retrieves and removes the head of this queue, or returns 'null'
    // if this queue is empty.
    public Point poll() {

        // TODO: implement method.
        if (size == 0) {
            return null;
        }
        Point p = points[0];
        for (int i = 0; i < size - 1; i++) {
            points[i] = points[i + 1];
        }
        size--;
        return p;
    }

    // Retrieves, but does not remove the head of this queue, or returns 'null'
    // if this queue is empty.
    public Point peek() {

        // TODO: implement method.
        if (size == 0) {
            return null;
        }
        return points[0];
    }

    // Returns the number of entries in this queue.
    public int size() {

        // TODO: implement method.
        return size;
    }
}
