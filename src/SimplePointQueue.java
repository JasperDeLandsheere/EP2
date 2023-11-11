public class SimplePointQueue {

    // queue will be an array with length = 2 * initial capacity
    // rear multiplied by 2 points to the empty position where the next point would be stored

    private int[] q;
    private final int initialCapacity;
    private int currentCapacity, size;

    // Initializes this queue with an initial capacity (length of internal array).
    // Precondition: initialCapacity > 0.
    public SimplePointQueue(int initialCapacity) {

        this.initialCapacity = this.currentCapacity = initialCapacity;
        this.q = new int[this.initialCapacity * 2];

        this.size = 0;

    }

    // Adds the specified point 'p' to this queue.
    // Precondition: p != null.
    public void add(Point p) {

        // Queue is full, so we need to increase capacity
        // Add space for this.initialCapacity more points
        if (this.size == this.currentCapacity) {
            int[] extended = new int[2 * this.size +  2 * this.initialCapacity];
            this.currentCapacity = this.size + this.initialCapacity;
            System.arraycopy(this.q, 0, extended, 0, this.q.length);
            this.q = extended;
        }

        this.q[2 * this.size] = p.getX();
        this.q[2 * this.size + 1] = p.getY();
        this.size += 1;

    }

    // Retrieves and removes the head of this queue, or returns 'null'
    // if this queue is empty.
    public Point poll() {

        if (this.size() == 0) {
            return null;
        }
        Point toReturn = this.peek();

        // First point was deleted, so move all points one position to the front
        for (int i = 0; i < 2 * (this.size - 1); i += 2) {
            this.q[i] = this.q[i + 2];
            this.q[i + 1] = this.q[i + 3];
        }
        this.size -= 1;

        return toReturn;
    }

    // Retrieves, but does not remove the head of this queue, or returns 'null'
    // if this queue is empty.
    public Point peek() {

        if (this.size == 0) {
            return null;
        }

        return new Point(this.q[0], this.q[1]);
    }

    // Returns the number of entries in this queue.
    public int size() {

        return this.size;
    }
}
