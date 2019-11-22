package datastructures.worklists;

import cse332.exceptions.NotYetImplementedException;
import cse332.interfaces.worklists.FIFOWorkList;

import java.util.NoSuchElementException;

/**
 * See cse332/interfaces/worklists/FIFOWorkList.java
 * for method specifications.
 */
public class ListFIFOQueue<E> extends FIFOWorkList<E> {
    private FIFONode last;
    private FIFONode first;
    private int size;

    public ListFIFOQueue() {
        first = null;
        last = null;
        size = 0;
    }

    @Override
    public void add(E work) {
        if (size == 0) {
            first = new FIFONode(work);
            last = first;
        } else {
            last.next = new FIFONode(work);
            last = last.next;
        }
        size++;
    }

    @Override
    public E peek() {
        if (!hasWork())
            throw new NoSuchElementException();
        return first.o;
    }

    @Override
    public E next() {
        if (!hasWork())
            throw new NoSuchElementException();
        if (size == 0) return null;
        if (size == 1) last = null;
        E ans = first.o;
        first = first.next;
        size--;
        return ans;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        first = null;
        last = null;
        size = 0;
    }

    class FIFONode {
        FIFONode next;
        E o;
        FIFONode(E o) {
            this.o = o;
            next = null;
        }

        FIFONode() {
            this(null);
        }
    }
}
