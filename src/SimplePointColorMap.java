import java.awt.*;

// A map that associates a position with a color. The number of key-value pairs is not limited.
// The map does not contain any keys or values being 'null'.
//
public class SimplePointColorMap {

    private Point[] ks;
    private Color[] vs;

    private int lastIndex;
    private int size;

    // We just use the .x component of the points
    private final SimplePointQueue emptyIndices;

    // Initializes this map with an initial capacity (length of internal array).
    // Precondition: initialCapacity > 0.
    public SimplePointColorMap(int initialCapacity) {

        this.size = initialCapacity;

        this.ks = new Point[initialCapacity];
        this.vs = new Color[initialCapacity];

        this.emptyIndices = new SimplePointQueue(initialCapacity);

    }

    // Adds a new key-value association to this map. If the key already exists in this map,
    // the value is replaced and the old value is returned. Otherwise, 'null' is returned.
    // Precondition: key != null && value != null.
    public Color put(Point key, Color value) {

        int index = this.findKey(key);
        if (index != -1) {
            // Already exists
            Color oldVal = this.vs[index];
            this.vs[index] = value;

            return oldVal;
        }

        // If key does not exist in the map
        if (this.emptyIndices.size() > 0) {
            // Set kv pair in the empty index position
            Point indexpt = this.emptyIndices.poll();
            index = indexpt.getX();

            this.ks[index] = key;
            this.vs[index] = value;

            return null;
        }
        if (this.emptyIndices.size() == 0 & this.lastIndex == this.size) {
            // Need to expand the keys and values lists
            Point[] extendedKeys = new Point[2 * this.size];
            Color[] extendedVals = new Color[2 * this.size];
            System.arraycopy(this.ks, 0, extendedKeys, 0, this.ks.length);
            System.arraycopy(this.vs, 0, extendedVals, 0, this.vs.length);
            this.ks = extendedKeys;
            this.vs = extendedVals;

            this.size *= 2;
        }

        // Set kv pair in the lastIndex position
        this.ks[this.lastIndex] = key;
        this.vs[this.lastIndex] = value;
        this.lastIndex += 1;

        return null;
    }

    // Looks for a key in the key map. If exists, returns index of the key. Otherwise, returns -1
    // Precondition: key != null.
    private int findKey(Point key) {
        for (int i = 0; i < this.lastIndex; ++i) {
            if (this.ks[i] != null && this.ks[i].compareTo(key) == 0) {
                return i;
            }
        }
        return -1;
    }

    // Returns the value associated with the specified key, i.e. the method returns the color
    // associated with the specified point.
    // More formally, if this map contains a mapping from a key k to a value v such that
    // key.compareTo(k) == 0, then this method returns v.
    // (There can be at most one such mapping.)
    // Returns 'null' if the key is not contained in this map.
    // Precondition: key != null.
    public Color get(Point key) {

        int index = this.findKey(key);
        return (index == -1) ? null : this.vs[index];

    }

    // Removes the mapping for a key from this map if it is present. More formally, if this map
    // contains a mapping from key k to value v such that key.compareTo(k) == 0,
    // that mapping is removed. (The map can contain at most one such mapping.)
    // Returns the value to which this map previously associated the key, or 'null' if the map
    // contained no mapping for the key.
    // Precondition: key != null.
    public Color remove(Point key) {

        int index = this.findKey(key);
        if (index == -1) {
            return null;
        }
        Color toReturn = this.vs[index];
        this.ks[index] = null;
        this.vs[index] = null;
        this.emptyIndices.add(new Point(index, 0));

        return toReturn;
    }

    // Returns a queue with all keys of this map (ordering is not specified).
    public SimplePointQueue keys() {

        SimplePointQueue queue = new SimplePointQueue(this.size);
        for (int i = 0; i < this.lastIndex; ++i) {
            if (this.ks[i] != null) {
                queue.add(this.ks[i]);
            }
        }

        return queue;
    }
}