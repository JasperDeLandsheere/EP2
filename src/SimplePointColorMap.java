import java.awt.*;

// A map that associates a position with a color. The number of key-value pairs is not limited.
// The map does not contain any keys or values being 'null'.
//
public class SimplePointColorMap {

    //TODO: declare variables.
    private Point[] points;
    private Color[] colors;
    private int size;
    private int capacity;

    // Initializes this map with an initial capacity (length of internal array).
    // Precondition: initialCapacity > 0.
    public SimplePointColorMap(int initialCapacity) {

        //TODO: define constructor.
        capacity = initialCapacity;
        points = new Point[capacity];
        colors = new Color[capacity];
        size = 0;
    }

    // Adds a new key-value association to this map. If the key already exists in this map,
    // the value is replaced and the old value is returned. Otherwise, 'null' is returned.
    // Precondition: key != null && value != null.
    public Color put(Point key, Color value) {

        //TODO: implement method.
        Color oldValue = null;
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (points[i].compareTo(key) == 0) {
                oldValue = colors[i];
                index = i;
                break;
            }
        }
        if (index == -1) {
            if (size == capacity) {
                capacity *= 2;
                Point[] newPoints = new Point[capacity];
                Color[] newColors = new Color[capacity];
                for (int i = 0; i < size; i++) {
                    newPoints[i] = points[i];
                    newColors[i] = colors[i];
                }
                points = newPoints;
                colors = newColors;
            }
            points[size] = key;
            colors[size] = value;
            size++;
        } else {
            colors[index] = value;
        }
        return oldValue;
    }

    // Returns the value associated with the specified key, i.e. the method returns the color
    // associated with the specified point.
    // More formally, if this map contains a mapping from a key k to a value v such that
    // key.compareTo(k) == 0, then this method returns v.
    // (There can be at most one such mapping.)
    // Returns 'null' if the key is not contained in this map.
    // Precondition: key != null.
    public Color get(Point key) {

        //TODO: implement method.
        for (int i = 0; i < size; i++) {
            if (points[i].compareTo(key) == 0) {
                return colors[i];
            }
        }
        return null;
    }

    // Removes the mapping for a key from this map if it is present. More formally, if this map
    // contains a mapping from key k to value v such that key.compareTo(k) == 0,
    // that mapping is removed. (The map can contain at most one such mapping.)
    // Returns the value to which this map previously associated the key, or 'null' if the map
    // contained no mapping for the key.
    // Precondition: key != null.
    public Color remove(Point key) {

        //TODO: implement method.
        Color oldValue = null;
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (points[i].compareTo(key) == 0) {
                oldValue = colors[i];
                index = i;
                break;
            }
        }
        if (index != -1) {
            for (int i = index; i < size - 1; i++) {
                points[i] = points[i + 1];
                colors[i] = colors[i + 1];
            }
            size--;
        }
        return oldValue;
    }

    // Returns a queue with all keys of this map (ordering is not specified).
    public SimplePointQueue keys() {

        //TODO: implement method.
        SimplePointQueue queue = new SimplePointQueue(size);
        for (int i = 0; i < size; i++) {
            queue.add(points[i]);
        }
        return queue;
    }
}