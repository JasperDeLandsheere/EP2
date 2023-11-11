// A list of objects of 'RasterRGBA' implemented as a doubly linked list.
// The number of elements of the list is not limited. Entries of 'null' are allowed.
//
// TODO: define further classes and methods for the implementation of the doubly linked list, if
//  needed.
//
public class LinkedListRasterRGBA {

    //TODO: declare variables.
    private int size;
    private Node head;
    private Node tail;

    // Initializes 'this' as an empty list.
    public LinkedListRasterRGBA() {

        //TODO: define constructor.
        size = 0;
        head = null;
        tail = null;
    }

    // Inserts the specified element 'raster' at the beginning of this list.
    public void addFirst(RasterRGBA raster) {

        //TODO: implement method.
        Node newNode = new Node(raster, null, head);
        if (head == null) {
            tail = newNode;
        } else {
            head.prev = newNode;
        }
        head = newNode;
        size++;
    }

    // Appends the specified element 'raster' to the end of this list.
    public void addLast(RasterRGBA raster) {

        //TODO: implement method.
        Node newNode = new Node(raster, tail, null);
        if (tail == null) {
            head = newNode;
        } else {
            tail.next = newNode;
        }
        tail = newNode;
        size++;
    }

    // Returns the last element in this list.
    // Returns 'null' if the list is empty.
    public RasterRGBA getLast() {

        //TODO: implement method.
        if (tail == null) {
            return null;
        } else {
            return tail.raster;
        }
    }

    // Returns the first element in this list.
    // Returns 'null' if the list is empty.
    public RasterRGBA getFirst() {

        //TODO: implement method.
        if (head == null) {
            return null;
        } else {
            return head.raster;
        }
    }

    // Retrieves and removes the first element in this list.
    // Returns 'null' if the list is empty.
    public RasterRGBA pollFirst() {

        //TODO: implement method.
        if (head == null) {
            return null;
        } else {
            Node firstNode = head;
            head = firstNode.next;
            if (head == null) {
                tail = null;
            } else {
                head.prev = null;
            }
            size--;
            return firstNode.raster;
        }
    }

    // Retrieves and removes the last element in this list.
    // Returns 'null' if the list is empty.
    public RasterRGBA pollLast() {

        //TODO: implement method.
        if (tail == null) {
            return null;
        } else {
            Node lastNode = tail;
            tail = lastNode.prev;
            if (tail == null) {
                head = null;
            } else {
                tail.next = null;
            }
            size--;
            return lastNode.raster;
        }
    }

    // Inserts the specified element 'raster' at the specified position in this list.
    // More specifically, 'raster' is inserted as follows:
    // before insertion elements have indices from 0 to size()-1;
    // 'raster' is inserted immediately before the element with the given index 'i' (or as last
    // element if 'i == size()') such that 'raster' can be found at index 'i' after insertion.
    // Precondition: i >= 0 && i <= size().
    public void add(int i, RasterRGBA raster) {

        //TODO: implement method.
        if (i == 0) {
            addFirst(raster);
        } else if (i == size) {
            addLast(raster);
        } else {
            Node current = head;
            for (int j = 0; j < i; j++) {
                current = current.next;
            }
            Node newNode = new Node(raster, current.prev, current);
            current.prev.next = newNode;
            current.prev = newNode;
            size++;
        }
    }

    // Returns the element at the specified position in this list.
    // Precondition: i >= 0 && i < size().
    public RasterRGBA get(int i) {

        //TODO: implement method.
        Node current = head;
        for (int j = 0; j < i; j++) {
            current = current.next;
        }
        return current.raster;
    }

    // Replaces the element at the specified position in this list with the specified element.
    // Returns the element that was replaced.
    // Precondition: i >= 0 && i < size().
    public RasterRGBA set(int i, RasterRGBA raster) {

        //TODO: implement method.
        Node current = head;
        for (int j = 0; j < i; j++) {
            current = current.next;
        }
        RasterRGBA oldValue = current.raster;
        current.raster = raster;
        return oldValue;
    }

    // Removes the element at the specified position in this list. Shifts any subsequent
    // elements to the left (subtracts one from their indices). Returns the element that was
    // removed from the list.
    // Precondition: i >= 0 && i < size().
    public RasterRGBA remove(int i) {

        //TODO: implement method.
        Node current = head;
        for (int j = 0; j < i; j++) {
            current = current.next;
        }
        if (current == head) {
            head = current.next;
        } else if (current == tail) {
            tail = current.prev;
        } else {
            current.prev.next = current.next;
            current.next.prev = current.prev;
        }
        size--;
        return current.raster;
    }

    // Returns the index of the last occurrence of 'raster' in this list (the highest index with an
    // element equal to 'raster'), or -1 if this list does not contain the element.
    // Equality of elements is determined by object identity (== operator).
    public int lastIndexOf(RasterRGBA raster) {

        //TODO: implement method.
        int index = size - 1;
        Node currentNode = tail;
        while (currentNode != null) {
            if (currentNode.raster == raster) {
                return index;
            }
            currentNode = currentNode.prev;
            index--;
        }
        return -1;
    }

    // Returns the number of elements in this list.
    public int size() {

        //TODO: implement method.
        return size;
    }

    //TODO (optional): add more operations (e.g., floodfill).
}

// TODO: define further classes, if needed (either here or in a separate file).
class Node {
    RasterRGBA raster;
    Node prev;
    Node next;

    Node(RasterRGBA raster, Node prev, Node next) {
        this.raster = raster;
        this.prev = prev;
        this.next = next;
    }
}
