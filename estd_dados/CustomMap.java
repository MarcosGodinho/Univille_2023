import java.util.NoSuchElementException;

public class CustomMap<K, V> {
    private MapNode<K, V>[] table;
    private int capacity;
    private int size;

    public CustomMap(int capacity) {
        this.capacity = capacity;
        this.table = new MapNode[capacity];
    }

    public void put(K key, V value) {
        int index = getIndex(key);
        MapNode<K, V> newNode = new MapNode<>(key, value);

        if (table[index] == null) {
            table[index] = newNode;
        } else {
            MapNode<K, V> current = table[index];
            while (current.next != null) {
                if (current.key.equals(key)) {
                    current.value = value;
                    return;
                }
                current = current.next;
            }
            current.next = newNode;
            newNode.prev = current;
        }
        size++;
    }

    public V get(K key) {
        int index = getIndex(key);
        MapNode<K, V> current = table[index];

        while (current != null) {
            if (current.key.equals(key)) {
                return current.value;
            }
            current = current.next;
        }
        throw new NoSuchElementException("Chave não encontrada: " + key);
    }

    public boolean containsKey(K key) {
        int index = getIndex(key);
        MapNode<K, V> current = table[index];

        while (current != null) {
            if (current.key.equals(key)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public void remove(K key) {
        int index = getIndex(key);
        MapNode<K, V> current = table[index];

        while (current != null) {
            if (current.key.equals(key)) {
                if (current.prev != null) {
                    current.prev.next = current.next;
                } else {
                    table[index] = current.next;
                }
                if (current.next != null) {
                    current.next.prev = current.prev;
                }
                size--;
                return;
            }
            current = current.next;
        }
    }

    public int size() {
        return size;
    }

    public int getIndex(K key) {
        if (key == null) {
            return 0;
        }
        return Math.abs(key.hashCode() % capacity);
    }
}
