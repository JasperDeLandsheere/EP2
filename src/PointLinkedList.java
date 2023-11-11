// A list of 2D-points (objects of type 'Point') implemented as a linked list.
// The number of elements of the list is not limited.
//

//
public class PointLinkedList {

    private Node head;
    private int size;

    private static class Node {
        private final Point point;
        private Node next;

        public Node(Point point, Node next) {
            this.point = point;
            this.next = next;
        }
    }

    // Initializes 'this' as an empty list.
    public PointLinkedList() {

        this.head = null;
        this.size = 0;
    }

    // Inserts the specified element 'point' at the beginning of this list.
    // Precondition: point != null.
    public void addFirst(Point point) {

        this.head = new Node(point, this.head);
        this.size++;
    }

    // Appends the specified element 'point' to the end of this list.
    // Precondition: point != null.
    public void addLast(Point point) {

        if (this.head == null) {
            this.head = new Node(point, null);
        } else {
            Node current = this.head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = new Node(point, null);
        }
        this.size++;
    }

    // Returns the last element in this list.
    // Returns 'null' if the list is empty.
    public Point getLast() {

        if (this.size == 0) {
            return null;
        }
        Node current = this.head;
        while (current.next != null) {
            current = current.next;
        }
        return current.point;
    }

    // Returns the first element in this list.
    // Returns 'null' if the list is empty.
    public Point getFirst() {

        if (this.size == 0) {
            return null;
        }
        return this.head.point;
    }

    // Retrieves and removes the first element in this list.
    // Returns 'null' if the list is empty.
    public Point pollFirst() {

        if (this.size == 0) {
            return null;
        }
        Point first = this.head.point;
        this.head = this.head.next;
        this.size--;
        return first;
    }

    // Retrieves and removes the last element in this list.
    // Returns 'null' if the list is empty.
    public Point pollLast() {

        if (this.size == 0) {
            return null;
        } else if (this.size == 1) {
            Point last = this.head.point;
            this.head = null;
            this.size--;
            return last;
        }
        Node current = this.head;
        while (current.next.next != null) {
            current = current.next;
        }
        Point last = current.next.point;
        current.next = null;
        this.size--;
        return last;
    }

    // Inserts the specified element 'point' at the specified position in this list.
    // Precondition: i >= 0 && i <= size() && point != null.
    public void add(int i, Point point) {

        if (i == 0) {
            this.head = new Node(point, this.head);
        } else {
            Node current = this.head;
            for (int j = 0; j < i - 1; j++) {
                current = current.next;
            }
            current.next = new Node(point, current.next);
        }
        this.size++;
    }

    // Returns the element at the specified position in this list.
    // Precondition: i >= 0 && i < size().
    public Point get(int i) {

        Node current = this.head;
        for (int j = 0; j < i; j++) {
            current = current.next;
        }
        return current.point;
    }

    // Returns the index of the first occurrence (element with equal coordinates to 'point') of the
    // specified element in this list, or -1 if this list does not contain the element.
    // Precondition: point != null.
    public int indexOf(Point point) {

        Node current = this.head;
        for (int i = 0; i < this.size; i++) {
            if (current.point.compareTo(point) == 0) {
                return i;
            }
            current = current.next;
        }
        return -1;
    }

    // Returns the number of elements in this list.
    public int size() {

        return this.size;
    }
}
