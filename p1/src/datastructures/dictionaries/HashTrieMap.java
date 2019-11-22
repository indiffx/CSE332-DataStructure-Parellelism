package datastructures.dictionaries;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;

import cse332.exceptions.NotYetImplementedException;
import cse332.interfaces.misc.BString;
import cse332.interfaces.trie.TrieMap;

/**
 * See cse332/interfaces/trie/TrieMap.java
 * and cse332/interfaces/misc/Dictionary.java
 * for method specifications.
 */
public class HashTrieMap<A extends Comparable<A>, K extends BString<A>, V> extends TrieMap<A, K, V> {
    protected TrieNode<Map<A, HashTrieNode>, HashTrieNode> root;
    public class HashTrieNode extends TrieNode<Map<A, HashTrieNode>, HashTrieNode> {
        public Map<A, HashTrieNode> pointers;
        public V value;
        protected int size;
        public HashTrieNode() {
            this(null);
        }

        public HashTrieNode(V value) {
            this.pointers = new HashMap<>();
            this.value = value;
            this.size = 0;
        }

        @Override
        public Iterator<Entry<A, HashTrieMap<A, K, V>.HashTrieNode>> iterator() {
            return pointers.entrySet().iterator();
        }
    }

    public HashTrieMap(Class<K> KClass) {
        super(KClass);
        this.root = new HashTrieNode();
    }

    @Override
    public V insert(K key, V value) {
        if (key == null || value == null)
            throw new IllegalArgumentException();
        Iterator<A> iterator = key.iterator();
        HashTrieNode cur = (HashTrieNode) root;
        while (iterator.hasNext()) {
            A next = iterator.next();
            cur.pointers.putIfAbsent(next, new HashTrieNode());
            cur = cur.pointers.get(next);
        }
        V prev = cur.value;
        if (prev == null)
            size++;
        cur.value = value;
        return prev;
    }

    @Override
    public V find(K key) {
        HashTrieNode cur = locateKey(key);
        return cur == null ? null : cur.value;
    }

    @Override
    public boolean findPrefix(K key) {
        HashTrieNode cur = locateKey(key);
        return cur != null;
    }

    @Override
    public void delete(K key) {
        checkKey(key);
        reverseDelete(key.iterator(), (HashTrieNode) root);
    }

    private boolean reverseDelete(Iterator<A> iterator, HashTrieNode cur) {
        if (cur == null)
            return false;
        if (iterator.hasNext()) {
            A ch = iterator.next();
            if (reverseDelete(iterator, cur.pointers.get(ch))) {
                cur.pointers.remove(ch);
                if (cur.pointers.isEmpty() && cur.value == null)
                    return true;
            }
        } else {
            if (cur.value != null) {
                cur.value = null;
                size--;
            }
            if (cur.pointers.isEmpty())
                return true;
        }
        return false;
    }

    @Override
    public void clear() {
        root = new HashTrieNode();
        size = 0;
    }

    private HashTrieNode locateKey(K key) {
        checkKey(key);
        Iterator<A> iterator = key.iterator();
        HashTrieNode cur = (HashTrieNode) root;
        while (cur != null && iterator.hasNext()) {
            cur = cur.pointers.get(iterator.next());
        }
        return cur;
    }

    private void checkKey(K key) {
        if (key == null)
            throw new IllegalArgumentException();
    }
}
