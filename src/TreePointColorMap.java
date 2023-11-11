import java.awt.*;

// A map that associates a position (objects of type 'Point') with a color (objects of type 'Color'). The number of
// key-value pairs is not limited.
// The map is implemented as a binary tree. The keys are ordered based on the 'compareTo' method of 'Point'.
// The map does not contain any keys being 'null'.
public class TreePointColorMap {

    private Node root;

    private static class Node {
        private final Point point;
        private Color color;
        private Node left;
        private Node right;

        public Node(Point point, Color color, Node left, Node right) {
            this.point = point;
            this.color = color;
            this.left = left;
            this.right = right;
        }
    }

    // Constructor
    public TreePointColorMap() {
        this.root = null;
    }

    // Adds a new key-value association to this map. If the key already exists in this map,
    // the value is replaced and the old value is returned. Otherwise, 'null' is returned.
    // Precondition: key != null.
    public Color put(Point key, Color value) {

        if (this.root == null) {
            this.root = new Node(key, value, null, null);
            return null;
        } else {
            Node current = this.root;
            while (true) {
                if (current.point.compareTo(key) == 0) {
                    Color oldColor = current.color;
                    current.color = value;
                    return oldColor;
                } else if (current.point.compareTo(key) < 0) {
                    if (current.left == null) {
                        current.left = new Node(key, value, null, null);
                        return null;
                    } else {
                        current = current.left;
                    }
                } else {
                    if (current.right == null) {
                        current.right = new Node(key, value, null, null);
                        return null;
                    } else {
                        current = current.right;
                    }
                }
            }

        }

    }

    // Returns the value associated with the specified key, i.e. the method returns the color
    // associated with coordinates specified by key (the key must have the same coordinates as the
    // specified 'key'). Returns 'null' if the key is not contained in this map.
    // Precondition: key != null.
    public Color get(Point key) {

        Node current = this.root;
        while (current != null) {
            if (current.point.compareTo(key) == 0) {
                return current.color;
            } else if (current.point.compareTo(key) < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        return null;
    }

    // Returns 'true' if this map contains a mapping for the specified key, this means
    // for a point with the same coordinates as the specified 'key'.
    // Precondition: key != null.
    public boolean containsKey(Point key) {

        return this.get(key) != null;
    }

    // Returns a list with all keys of this map ordered ascending according to the
    // key order relation.
    public PointLinkedList keys() {

        PointLinkedList list = new PointLinkedList();

        this.traverse(this.root, list);

        return list;

    }

    public void traverse(Node current, PointLinkedList list) {
        if (current.left != null) {
            this.traverse(current.left, list);
        }
        list.addFirst(current.point);
        if (current.right != null) {
            this.traverse(current.right, list);
        }
    }

    // Returns a new raster representing a region with the specified size from this
    // map. The upper left corner of the region is (0,0) and the lower right corner is (width-1, height-1).
    // All pixels outside the specified region are cropped (not included).
    // Preconditions: width > 0 && height > 0
    public SimpleRasterRGB asRasterRGB(int width, int height) {

        SimpleRasterRGB raster = new SimpleRasterRGB(width, height);
        PointLinkedList list = this.keys();
        for (int i = 0; i < list.size(); i++) {
            Point p = list.get(i);
            if (p.getX() < width && p.getY() < height) {
                raster.setPixelColor(p.getX(), p.getY(), this.get(p));
            }
        }

        return raster;
    }

    public int traverseForLeafs(Node root) {
        if (root.left == null && root.right == null) {
            return 1;
        }
        int counter = 0;
        if (root.left != null) {
            counter += this.traverseForLeafs(this.root.left);
        }
        if (root.right != null) {
            counter += this.traverseForLeafs(this.root.right);
        }
        return counter;
    }

    public int numberOfLeafNodes() {
        return this.traverseForLeafs(this.root);
    }
}
