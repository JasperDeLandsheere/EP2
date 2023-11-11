// A list of objects of 'RasterRGBA' implemented as a doubly linked list.
// The number of elements of the list is not limited. Entries of 'null' are allowed.
public class LinkedListRasterRGBA {

    private static class Node {
        private RasterRGBA raster;
        private Node next;
        private Node prev;

        public Node(RasterRGBA raster, Node next, Node prev) {
            this.raster = raster;
            this.next = next;
            this.prev = prev;
        }
    }

    // Define the head and tail of the doubly linked list.
    private Node head;
    private Node tail;
    private int size;

    // Initializes 'this' as an empty list.
    public LinkedListRasterRGBA() {

        head = null;
        tail = null;
        size = 0;

    }

    // Inserts the specified element 'raster' at the beginning of this list.
    public void addFirst(RasterRGBA raster) {

        if (head == null) {
            head = new Node(raster, null, null);
            tail = head;
        } else {
            Node temp = head;
            head = new Node(raster, temp, null);
            temp.prev = head;
        }
        size++;
    }

    // Appends the specified element 'raster' to the end of this list.
    public void addLast(RasterRGBA raster) {

        if (head == null) {
            head = new Node(raster, null, null);
            tail = head;
        } else {
            Node temp = tail;
            tail = new Node(raster, null, temp);
            temp.next = tail;
        }
        size++;
    }

    // Returns the last element in this list.
    // Returns 'null' if the list is empty.
    public RasterRGBA getLast() {

        if (size == 0) {
            return null;
        }
        return tail.raster;
    }

    // Returns the first element in this list.
    // Returns 'null' if the list is empty.
    public RasterRGBA getFirst() {

        if (size == 0) {
            return null;
        }
        return head.raster;
    }

    // Retrieves and removes the first element in this list.
    // Returns 'null' if the list is empty.
    public RasterRGBA pollFirst() {

        if (size == 0) {
            return null;
        }
        RasterRGBA temp = head.raster;
        head = head.next;
        size--;
        return temp;
    }

    // Retrieves and removes the last element in this list.
    // Returns 'null' if the list is empty.
    public RasterRGBA pollLast() {

        if (size == 0) {
            return null;
        }
        RasterRGBA temp = tail.raster;
        tail = tail.prev;
        size--;
        return temp;
    }

    // Inserts the specified element 'raster' at the specified position in this list.
    // More specifically, 'raster' is inserted as follows:
    // before insertion elements have indices from 0 to size()-1;
    // 'raster' is inserted immediately before the element with the given index 'i' (or as last
    // element if 'i == size()') such that 'raster' can be found at index 'i' after insertion.
    // Precondition: i >= 0 && i <= size().
    public void add(int i, RasterRGBA raster) {

        if (i == 0) {
            addFirst(raster);
        } else if (i == size) {
            addLast(raster);
        } else {
            Node temp = head;
            for (int j = 0; j < i; j++) {
                temp = temp.next;
            }
            Node newNode = new Node(raster, temp, temp.prev);
            temp.prev.next = newNode;
            temp.prev = newNode;
            size++;
        }
    }

    // Returns the element at the specified position in this list.
    // Precondition: i >= 0 && i < size().
    public RasterRGBA get(int i) {

        if (i == 0) {
            return getFirst();
        } else if (i == size - 1) {
            return getLast();
        } else {
            Node temp = head;
            for (int j = 0; j < i; j++) {
                temp = temp.next;
            }
            return temp.raster;
        }
    }

    // Replaces the element at the specified position in this list with the specified element.
    // Returns the element that was replaced.
    // Precondition: i >= 0 && i < size().
    public RasterRGBA set(int i, RasterRGBA raster) {

        if (i == 0) {
            RasterRGBA temp = head.raster;
            head.raster = raster;
            return temp;
        } else if (i == size - 1) {
            RasterRGBA temp = tail.raster;
            tail.raster = raster;
            return temp;
        } else {
            Node temp = head;
            for (int j = 0; j < i; j++) {
                temp = temp.next;
            }
            RasterRGBA temp2 = temp.raster;
            temp.raster = raster;
            return temp2;
        }
    }

    // Removes the element at the specified position in this list. Shifts any subsequent
    // elements to the left (subtracts one from their indices). Returns the element that was
    // removed from the list.
    // Precondition: i >= 0 && i < size().
    public RasterRGBA remove(int i) {

        if (i == 0) {
            return pollFirst();
        } else if (i == size - 1) {
            return pollLast();
        } else {
            Node temp = head;
            for (int j = 0; j < i; j++) {
                temp = temp.next;
            }
            temp.prev.next = temp.next;
            temp.next.prev = temp.prev;
            size--;
            return temp.raster;
        }
    }

    // Returns the index of the last occurrence of 'raster' in this list (the highest index with an
    // element equal to 'raster'), or -1 if this list does not contain the element.
    // Equality of elements is determined by object identity (== operator).
    public int lastIndexOf(RasterRGBA raster) {

        Node temp = tail;
        for (int i = size - 1; i >= 0; i--) {
            if (temp.raster == raster) {
                return i;
            }
            temp = temp.prev;
        }
        return -1;
    }

    // Returns the number of elements in this list.
    public int size() {

        return size;
    }

}
