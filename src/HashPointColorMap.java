import java.awt.*;

// A map that associates a position (objects of type 'Point') with a color (objects of type 'Color').
// The number of key-value pairs is not limited.
// The map is implemented as hash map. The map does not contain any keys or values being 'null'.
//
public class HashPointColorMap {

    //TODO: declare variables.
    private int size;
    private int capacity;
    private Entry[] entries;

    public HashPointColorMap() {
        capacity = 100000;
        entries = new Entry[capacity];
        size = 0;
    }
    private static class Entry {
        private Point key;
        private Color value;

        public Entry(Point key, Color value) {
            this.key = key;
            this.value = value;
        }
    }

    // Adds a new key-value association to this map. If the key already exists in this map,
    // the value is replaced and the old value is returned. Otherwise, 'null' is returned.
    // Precondition: key != null && value != null.
    public Color put(Point key, Color value) {

        //TODO: implement method.
        if (this.containsKey(key)) {
            Color oldColor = this.get(key);
            for (int i = 0; i < size; i++) {
                if (key.equals(entries[i].key)) {
                    entries[i].value = value;
                    return oldColor;
                }
            }
        } else {
            entries[size] = new Entry(key, value);
            size++;
        }
        return null;
    }

    // Returns the value associated with the specified key, i.e. the method returns the color
    // associated with the specified point.
    // More formally, if this map contains a mapping from a key k to a value v such that
    // key.equals(k) == true, then this method returns v.
    // (There can be at most one such mapping.)
    // Returns 'null' if the key is not contained in this map.
    // Precondition: key != null.
    public Color get(Point key) {

        //TODO: implement method.
        for (int i =0; i < size; i++) {
            if (key.equals(entries[i].key)) {
                return entries[i].value;
            }
        }
        return null;
    }

    // Removes the mapping for a key from this map if it is present. More formally, if this map
    // contains a mapping from key k to value v such that key.equals(k) == true,
    // that mapping is removed. (The map can contain at most one such mapping.)
    // Returns the value to which this map previously associated the key, or 'null' if the map
    // contained no mapping for the key.
    // Precondition: key != null.
    public Color remove(Point key) {

        //TODO: implement method.
        if (this.containsKey(key)) {
            Color oldColor = this.get(key);
            for (int i = 0; i < size; i++) {
                if (key.equals(entries[i].key)) {
                    entries[i] = entries[size - 1];
                    size--;
                    return oldColor;
                }
            }
        }
        return null;
    }

    // Returns a queue with all keys of this map (ordering is not specified).
    public SimplePointQueue keys() {

        //TODO: implement method.
        SimplePointQueue queue = new SimplePointQueue(size);
        for (int i = 0; i < size; i++) {
            queue.add(entries[i].key);
        }
        return queue;
    }

    // Returns 'true' if the specified key is contained in this map.
    // Returns 'false' otherwise.
    public boolean containsKey(Point key) {

        //TODO: implement method.
        for (int i = 0; i < size; i++) {
            if (entries[i].key.compareTo(key) == 0) {
                return true;
            }
        }
        return false;
    }

    // Returns 'true' if the specified value is contained at least once in this map.
    // Returns 'false' otherwise.
    public boolean containsValue(Color value) {

        //TODO: implement method.
        for (int i = 0; i < size; i++) {
            if (entries[i].value.equals(value)) {
                return true;
            }
        }
        return false;
    }

    // Returns a string representation of this map with key-value pairs in parentheses, separated
    // by commas (order is not specified).
    // Example: {([9, 2], java.awt.Color[r=255,g=255,b=0]), ([7, 1], java.awt.Color[r=255,g=0,b=0])}
    public String toString() {

        //TODO: implement method.
        String s = "{";
        for (int i = 0; i < size; i++) {
            s += "(" + entries[i].key.toString() + ", " + entries[i].value.toString() + ")";
            if (i < size - 1) {
                s += ", ";
            }
        }
        return s + "}";
    }

    // Returns 'true' if 'this' and 'o' are equal, meaning 'o' is of class 'HashPointColorMap'
    // and 'this' and 'o' contain the same key-value pairs, i.e. the number of key-value pairs is
    // the same in both maps and every key-value pair in 'this' equals one key-value pair in 'o'.
    // Two key-value pairs are equal if the two keys are equal and the two values are equal.
    // Otherwise, 'false' is returned.
    public boolean equals(Object o) {

        //TODO: implement method.
        if (o instanceof HashPointColorMap) {
            HashPointColorMap other = (HashPointColorMap) o;
            if (this.size != other.size) {
                return false;
            }
            for (int i = 0; i < size; i++) {
                if (!this.containsKey(other.entries[i].key)) {
                    return false;
                }
                if (!this.containsValue(other.entries[i].value)) {
                    return false;
                }
            }
        } else {
            return false;
        }
        return true;
    }

    // Returns the hash code of 'this'.
    public int hashCode() {

        //TODO: implement method.
        return this.hashCode();
    }
}

