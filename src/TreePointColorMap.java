import java.awt.*;

// A map that associates a position (objects of type 'Point') with a color (objects of type 'Color'). The number of
// key-value pairs is not limited.
// The map is implemented as a binary tree. The keys are ordered based on the 'compareTo' method of 'Point'.
// The map does not contain any keys being 'null'.
//
// TODO: define further classes and methods for the implementation of the binary search tree,
//  if needed.
//
public class TreePointColorMap {

    //TODO: declare variables.
    private TreeNode root;

    private class TreeNode {
        private Point key;
        private Color value;
        private TreeNode left;
        private TreeNode right;

        public TreeNode(Point key, Color value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    // Adds a new key-value association to this map. If the key already exists in this map,
    // the value is replaced and the old value is returned. Otherwise, 'null' is returned.
    // Precondition: key != null.
    public Color put(Point key, Color value) {

        //TODO: implement method.
        if (root == null) {
            root = new TreeNode(key, value);
            return null;
        }
        TreeNode current = root;
        while (true) {
            int cmp = current.key.compareTo(key);
            if (cmp == 0) {
                Color oldValue = current.value;
                current.value = value;
                return oldValue;
            } else if (cmp < 0) {
                if (current.right == null) {
                    current.right = new TreeNode(key, value);
                    return null;
                }
                current = current.right;
            } else {
                if (current.left == null) {
                    current.left = new TreeNode(key, value);
                    return null;
                }
                current = current.left;
            }
        }
    }


    // Returns the value associated with the specified key, i.e. the method returns the color
    // associated with coordinates specified by key (the key must have the same coordinates as the
    // specified 'key'). Returns 'null' if the key is not contained in this map.
    // Precondition: key != null.
    public Color get(Point key) {

        //TODO: implement method.
        TreeNode current = root;
        while (current != null) {
            int cmp = current.key.compareTo(key);
            if (cmp == 0) {
                return current.value;
            } else if (cmp < 0) {
                current = current.right;
            } else {
                current = current.left;
            }
        }
        return null;
    }

    // Returns 'true' if this map contains a mapping for the specified key, this means
    // for a point with the same coordinates as the specified 'key'.
    // Precondition: key != null.
    public boolean containsKey(Point key) {

        //TODO: implement method.
        TreeNode current = root;
        while (current != null) {
            int cmp = current.key.compareTo(key);
            if (cmp == 0) {
                return true;
            } else if (cmp < 0) {
                current = current.right;
            } else {
                current = current.left;
            }
        }
        return false;
    }

    // Returns a list with all keys of this map ordered ascending according to the
    // key order relation.
    public PointLinkedList keys() {

        //TODO: implement method
        PointLinkedList list = new PointLinkedList();
        addKeysToList(root, list);
        return list;
    }

    private void addKeysToList(TreeNode node, PointLinkedList result) {
        if (node != null) {
            addKeysToList(node.left, result);
            result.addLast(node.key);
            addKeysToList(node.right, result);
        }

    }

    // Returns a new raster representing a region with the specified size from this
    // map. The upper left corner of the region is (0,0) and the lower right corner is (width-1, height-1).
    // All pixels outside the specified region are cropped (not included).
    // Preconditions: width > 0 && height > 0
    public SimpleRasterRGB asRasterRGB(int width, int height) {

        //TODO: implement method.
        SimpleRasterRGB raster = new SimpleRasterRGB(width, height);

        PointLinkedList keys = keys();
        int maxX = width - 1;
        int maxY = height - 1;
        int i = 0;
        while (i < keys.size()) {
            int x = (int) keys.get(i).getX();
            int y = (int) keys.get(i).getY();
            if (x >= 0 && x <= maxX && y >= 0 && y <= maxY) {
                Color color = get(keys.get(i));
                raster.setPixelColor(x, y, color);
            }
            i++;
        }
        return raster;
        }
}

// TODO: define further classes, if needed (either here or in a separate file).

