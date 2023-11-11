import java.awt.*;

// A set with elements of 'RasterRGBA', implemented as a binary search tree. The number of elements
// is not limited. The set does not contain 'null'. The implementation uses a
// binary search tree, where the key is the number of pixels with the color (0, 0, 0, 0) in the
// raster object, and the value is the raster object itself. Note that the tree can contain
// multiple objects with the same key (for example, a subtree of a node can contain not only
// smaller, but also equal keys). However, the tree does not contain the same object
// multiple times (see the specification of the 'contains' method).
public class TreeSetRasterRGBA {

    private final Node root;
    private static class Node {
        private final int key;
        private RasterRGBA raster;
        private Node left;
        private Node right;

        public Node(int key, RasterRGBA raster, Node left, Node right) {
            this.key = key;
            this.raster = raster;
            this.left = left;
            this.right = right;
        }
    }

    // Initialises 'this' as an empty set.
    public TreeSetRasterRGBA() {
        root = new Node(0, null, null, null);

    }

    // Ensures that the specified element is contained in this set. If the element already
    // existed in this set, the method does not change the set and returns 'false'. Returns
    // 'true' if the set was changed as a result of the call.
    // Precondition: element != null.
    public boolean add(RasterRGBA element) {

        if (root.raster == null) {
            root.raster = element;
            return true;
        }
        Node temp = root;
        while (temp != null) {
            if (element.countPixels(Color.BLACK) < temp.key) {
                if (temp.left == null) {
                    temp.left = new Node(element.countPixels(Color.BLACK), element, null, null);
                    return true;
                } else {
                    temp = temp.left;
                }
            } else if (element.countPixels(Color.BLACK) > temp.key) {
                if (temp.right == null) {
                    temp.right = new Node(element.countPixels(Color.BLACK), element, null, null);
                    return true;
                } else {
                    temp = temp.right;
                }
            } else {
                if (temp.raster.equals(element)) {
                    return false;
                } else {
                    if (temp.left == null) {
                        temp.left = new Node(element.countPixels(Color.BLACK), element, null, null);
                        return true;
                    } else {
                        temp = temp.left;
                    }
                }
            }
        }
        return false;
    }

    // Returns true if this set contains the specified element, as determined by
    // object identity. More formally, returns 'true' if and only if this set contains
    // an object 'e' such that element == e.
    // Precondition: element != null.
    public boolean contains(RasterRGBA element) {

        Node temp = root;
        while (temp != null) {
            if (element.countPixels(Color.BLACK) < temp.key) {
                temp = temp.left;
            } else if (element.countPixels(Color.BLACK) > temp.key) {
                temp = temp.right;
            } else {
                if (temp.raster.equals(element)) {
                    return true;
                } else {
                    temp = temp.left;
                }
            }
        }
        return false;
    }
}

