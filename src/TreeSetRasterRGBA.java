import java.awt.*;

// A set with elements of 'RasterRGBA', implemented as a binary search tree. The number of elements
// is not limited. The set does not contain 'null'. The implementation uses a
// binary search tree, where the key is the number of pixels with the color (0, 0, 0, 0) in the
// raster object, and the value is the raster object itself. Note that the tree can contain
// multiple objects with the same key (for example, a subtree of a node can contain not only
// smaller, but also equal keys). However, the tree does not contain the same object
// multiple times (see the specification of the 'contains' method).
//
// TODO: define further classes and methods for the implementation of the binary search tree,
//  if needed.
//
public class TreeSetRasterRGBA {

    //TODO: declare variables.
    private TreeSetNode root;

    // Initialises 'this' as an empty set.
    public TreeSetRasterRGBA() {

        //TODO: implement constructor.
        root = null;
    }

    // Ensures that the specified element is contained in this set. If the element already
    // existed in this set, the method does not change the set and returns 'false'. Returns
    // 'true' if the set was changed as a result of the call.
    // Precondition: element != null.
    public boolean add(RasterRGBA element) {

        //TODO: implement method.

        TreeSetNode newNode = new TreeSetNode(element.countPixels(new Color(0,0,0,0)), element);
        if (root == null) {
            root = newNode;
            return true;
        }

        TreeSetNode currNode = root;
        while (true) {
            int cmp = Integer.compare(newNode.key, currNode.key);
            if (cmp < 0) {
                if (currNode.left == null) {
                    currNode.left = newNode;
                    return true;
                } else {
                    currNode = currNode.left;
                }
            } else if (cmp > 0) {
                if (currNode.right == null) {
                    currNode.right = newNode;
                    return true;
                } else {
                    currNode = currNode.right;
                }
            } else {
                // Same key, check if its value is equal to element
                if (element.equals(currNode.value)) {
                    return false;
                } else {
                    TreeSetNode leftNode = currNode.left;
                    currNode.left = newNode;
                    newNode.left = leftNode;
                    return true;
                    }
                }
        }
    }

    // Returns true if this set contains the specified element, as determined by
    // object identity. More formally, returns 'true' if and only if this set contains
    // an object 'e' such that element == e.
    // Precondition: element != null.
    public boolean contains(RasterRGBA element) {

        //TODO: implement method.

        TreeSetNode currNode = root;
        while (currNode != null) {
            int cmp = Integer.compare(element.countPixels(new Color(0,0,0,0)), currNode.key);
            if (cmp < 0) {
                currNode = currNode.left;
            } else if (cmp > 0) {
                currNode = currNode.right;
            } else {
                // Same key, traverse subtree to check for element
                TreeSetNode subNode = currNode.left;
                while (subNode != null) {
                    if (element.equals(subNode.value)) {
                        return true;
                    } else {
                        subNode = subNode.left;
                    }
                }
                if (element.equals(currNode.value)) {
                    return true;
                }
                subNode = currNode.right;
                while (subNode != null) {
                    if (element.equals(subNode.value)) {
                        return true;
                    } else {
                        subNode = subNode.left;
                    }
                }
                return false;
            }
        }
        return false;
    }

}
class TreeSetNode {
    int key;
    RasterRGBA value;
    TreeSetNode left;
    TreeSetNode right;

    public TreeSetNode(int key, RasterRGBA value) {
        this.key = key;
        this.value = value;
    }
}

// TODO: define further classes, if needed (either here or in a separate file).
