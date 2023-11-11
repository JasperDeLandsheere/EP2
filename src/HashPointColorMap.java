import java.awt.*;

// A map that associates a position (objects of type 'Point') with a color (objects of type 'Color').
// The number of key-value pairs is not limited.
// The map is implemented as hash map. The map does not contain any keys or values being 'null'.
//
public class HashPointColorMap {

    private Point[] keys = new Point[65];
    private Color[] values = new Color[65];
    private int count = 0;

    // TODO: would really need to revise this method. In the theory slides, iteration through keys is always up to length-1
    // TODO: undersand how the null values work and how the hashes are used to find the correct index

    // Adds a new key-value association to this map. If the key already exists in this map,
    // the value is replaced and the old value is returned. Otherwise, 'null' is returned.
    // Precondition: key != null && value != null.
    public Color put(Point key, Color value) {
        int i = findKey(key);
        Color oldVal = values[i];
        values[i] = value;
        if (keys[i] == null) {
            keys[i] = key;
            if (++count >= keys.length * 0.75) rehash();
        }

        return oldVal;
    }

    private void rehash() {
        Point[] oldKeys = keys;
        Color[] oldValues = values;
        keys = new Point[(oldKeys.length << 1) - 1];
        values = new Color[(oldValues.length << 1) - 1];
        for (int j = 0; j < oldKeys.length; j++) {
            if (oldKeys[j] != null) {
                int i = findKey(oldKeys[j]);
                keys[i] = oldKeys[j];
                values[i] = oldValues[j];
            }
        }
    }

    private int findKey(Point key) {
        int i = key.hashCode() & (keys.length - 2);
        while (keys[i] != null && !keys[i].equals(key)) {
            i = (i + 1) & (keys.length - 2);
        }
        return i;
    }

    // Returns the value associated with the specified key, i.e. the method returns the color
    // associated with the specified point.
    // More formally, if this map contains a mapping from a key k to a value v such that
    // key.equals(k) == true, then this method returns v.
    // (There can be at most one such mapping.)
    // Returns 'null' if the key is not contained in this map.
    // Precondition: key != null.
    public Color get(Point key) {

        return values[findKey(key)];
    }

    // Removes the mapping for a key from this map if it is present. More formally, if this map
    // contains a mapping from key k to value v such that key.equals(k) == true,
    // that mapping is removed. (The map can contain at most one such mapping.)
    // Returns the value to which this map previously associated the key, or 'null' if the map
    // contained no mapping for the key.
    // Precondition: key != null.
    public Color remove(Point key) {
        int i = findKey(key);
        Color oldVal = values[i];
        if (keys[i] != null) {
            keys[i] = null;
            values[i] = null;
            count--;
            return oldVal;
        }
        return null;
    }

    // Returns a queue with all keys of this map (ordering is not specified).
    public SimplePointQueue keys() {
        SimplePointQueue q = new SimplePointQueue(count);
        for (Point key : keys) {
            if (key != null) {
                q.add(key);
            }
        }

        return q;
    }

    // Returns 'true' if the specified key is contained in this map.
    // Returns 'false' otherwise.
    public boolean containsKey(Point key) {

        return keys[findKey(key)] != null;
    }

    // Returns 'true' if the specified value is contained at least once in this map.
    // Returns 'false' otherwise.
    public boolean containsValue(Color value) {

        for (Color color : values) {
            if (color != null && color.equals(value)) return true;
        }
        return false;
    }

    // Returns a string representation of this map with key-value pairs in parentheses, separated
    // by commas (order is not specified).
    // Example: {([9, 2], java.awt.Color[r=255,g=255,b=0]), ([7, 1], java.awt.Color[r=255,g=0,b=0])}
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < keys.length; i++) {
            if (keys[i] != null) {
                s.append((s.length() == 0) ? "(" : ", (").append(keys[i]).append(", ").append(values[i]).append(")");
            }
        }
        return "{" + s.toString() + "}";
    }

    // Returns 'true' if 'this' and 'o' are equal, meaning 'o' is of class 'HashPointColorMap'
    // and 'this' and 'o' contain the same key-value pairs, i.e. the number of key-value pairs is
    // the same in both maps and every key-value pair in 'this' equals one key-value pair in 'o'.
    // Two key-value pairs are equal if the two keys are equal and the two values are equal.
    // Otherwise, 'false' is returned.
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || o.getClass() != HashPointColorMap.class) return false;
        HashPointColorMap that = (HashPointColorMap) o;
        if (count != that.count) return false;
        for (int i = 0; i < keys.length; i++) {
            if (keys[i] != null) {
                if (values[i] == null && (!that.containsKey(keys[i]) || that.get(keys[i]) != null)) return false;
                if (values[i] != null && !values[i].equals(that.get(keys[i]))) return false;
            }
        }

        return true;
    }

    // Returns the hash code of 'this'.
    public int hashCode() {
        int h = count;
        for (int i = 0; i < keys.length; i++) {
            if (keys[i] != null) {
                h += keys[i].hashCode();
                if (values[i] != null) h += values[i].hashCode();
            }
        }
        return h;
    }
}

