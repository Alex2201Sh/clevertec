package by.shumilov.clevertec.cache;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public final class LFUCache<K, V> implements Cache{

    private final LinkedHashMap<K, Node> storage;
    private final int capacity;

    public LFUCache(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity should be more than 0");
        }
        this.capacity = capacity;
        this.storage = new LinkedHashMap<>(capacity, 1);
    }

    @Override
    public V get(Object key) {
        Node node = storage.get(key);
        if (node == null) {
            return null;
        }
        return node.incrementFrequency().getValue();
    }

    @Override
    public void put(Object key, Object value) {
        doEvictionIfNeeded((K) key);
    }

    private void doEvictionIfNeeded(K putKey) {
        if (storage.size() < capacity) {
            return;
        }
        long minFrequency = Long.MAX_VALUE;
        K keyToRemove = null;
        for (Map.Entry<K, Node> entry : storage.entrySet()) {
            if (Objects.equals(entry.getKey(), putKey)) {
                //no eviction required cause element already exists, we just need to replace it
                return;
            }
            if (minFrequency >= entry.getValue().getFrequency()) {
                minFrequency = entry.getValue().getFrequency();
                keyToRemove = entry.getKey();
            }
        }
        storage.remove(keyToRemove);
    }

    private class Node {
        private final V value;
        private long frequency;

        public Node(V value) {
            this.value = value;
            this.frequency = 1;
        }

        public V getValue() {
            return value;
        }

        public long getFrequency() {
            return frequency;
        }

        public Node incrementFrequency() {
            ++frequency;
            return this;
        }

        @Override
        public String toString() {
            return "Node [value=" + value + ", frequency=" + frequency + "]";
        }

    }
    @Override
    public String toString() {
        return "storage = "+ storage + ", capacity=" + capacity ;
    }
}
