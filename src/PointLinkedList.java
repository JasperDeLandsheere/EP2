// A list of 2D-points (objects of type 'Point') implemented as a linked list.
// The number of elements of the list is not limited.
//
// TODO: define further classes and methods for the implementation of the linked list,
//  if needed.
//
public class PointLinkedList {

    //TODO: declare variables.
    private Node head;
    private int size;

    private static class Node {
        private Point point;
        private Node next;

        public Node(Point point, Node next){
            this.point = point;
            this.next = next;
        }

        public Node(Point point) {
            this(point, null);
        }
    }

    // Initializes 'this' as an empty list.
    public PointLinkedList() {

        //TODO: define constructor.
        head = null;
        size = 0;

    }

    // Inserts the specified element 'point' at the beginning of this list.
    // Precondition: point != null.
    public void addFirst(Point point) {

        //TODO: implement method.
        Node newNode = new Node(point, head);
        head = newNode;
        size++;
    }

    // Appends the specified element 'point' to the end of this list.
    // Precondition: point != null.
    public void addLast(Point point) {

        //TODO: implement method.
        Node newNode = new Node(point);
        if (head == null) {
            head = newNode;
            size++;
        } else {
            Node currentNode = head;
            while (currentNode.next != null) {
                currentNode = currentNode.next;
            }
            currentNode.next = newNode;
            size++;
        }
    }

    // Returns the last element in this list.
    // Returns 'null' if the list is empty.
    public Point getLast() {

        //TODO: implement method.
        if (head == null) {
            return null;
        } else {
            Node currentNode = head;
            while (currentNode.next != null) {
                currentNode = currentNode.next;
            }
            return currentNode.point;
        }
    }

    // Returns the first element in this list.
    // Returns 'null' if the list is empty.
    public Point getFirst() {

        //TODO: implement method.
        if (head == null) {
            return null;
        } else {
            return head.point;
        }
    }

    // Retrieves and removes the first element in this list.
    // Returns 'null' if the list is empty.
    public Point pollFirst() {

        //TODO: implement method.
        if (head == null) {
            return null;
        }

        Point removedPoint = head.point;
        head = head.next;
        size--;

        return removedPoint;
    }

    // Retrieves and removes the last element in this list.
    // Returns 'null' if the list is empty.
    public Point pollLast() {

        //TODO: implement method.
        if (head == null) {
            return null;
        } else if (head.next == null) {
            Point removedPoint = head.point;
            head = null;
            size--;
            return removedPoint;
        } else {
            Node currentNode = head;
            while (currentNode.next.next != null) {
                currentNode = currentNode.next;
            }
            Point removedPoint = currentNode.next.point;
            currentNode.next = null;
            size--;
            return removedPoint;
        }
    }

    // Inserts the specified element 'point' at the specified position in this list.
    // Precondition: i >= 0 && i <= size() && point != null.
    public void add(int i, Point point) {

        //TODO: implement method.
        if (i == 0) {
            addFirst(point);
        }
        else if (i == size) {
            addLast(point);
        }
        else {
        Node newNode = new Node(point);
        Node currentNode = head;

        for (int j = 0; j < i - 1; j++) {
            currentNode = currentNode.next;
        }

        newNode.next = currentNode.next;
        currentNode.next = newNode;

        size++;
        }
    }

    // Returns the element at the specified position in this list.
    // Precondition: i >= 0 && i < size().
    public Point get(int i) {

        //TODO: implement method.
        Node currentNode = head;
        for (int j = 0; j < i; j++) {
            currentNode = currentNode.next;
        }
        return currentNode.point;
    }

    // Returns the index of the first occurrence (element with equal coordinates to 'point') of the
    // specified element in this list, or -1 if this list does not contain the element.
    // Precondition: point != null.
    public int indexOf(Point point) {

        //TODO: implement method.
        int index = 0;
        Node currentNode = head;
        while (currentNode != null) {
            if (currentNode.point.equals(point)) {
                return index;
            }
        index++;
        currentNode = currentNode.next;
        }
        return -1;
    }

    // Returns the number of elements in this list.
    public int size() {

        //TODO: implement method.
        return size;
    }
}

// TODO: define further classes, if needed (either here or in a separate file).
