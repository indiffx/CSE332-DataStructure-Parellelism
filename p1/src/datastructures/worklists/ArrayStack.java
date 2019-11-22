package datastructures.worklists;

import cse332.exceptions.NotYetImplementedException;
import cse332.interfaces.worklists.LIFOWorkList;

import java.util.NoSuchElementException;

/**
 * See cse332/interfaces/worklists/LIFOWorkList.java
 * for method specifications.
 */
public class ArrayStack<E> extends LIFOWorkList<E> {
    private int capacity = 10;
    private E[] arr;
    private int last;

    public ArrayStack() {
        arr = (E[]) new Object[capacity];
        last = -1;
    }

    @Override
    public void add(E work) {
        if (size() == capacity)
            resize();
        arr[++last] = work;
    }

    @Override
    public E peek() {
        if (!hasWork())
            throw new NoSuchElementException();
        if (size() == 0) return null;
        return arr[last];
    }

    @Override
    public E next() {
        if (!hasWork())
            throw new NoSuchElementException();
        if (size() == 0) return null;
        return arr[last--];
    }

    @Override
    public int size() {
        return last + 1;
    }

    @Override
    public void clear() {
        last = -1;
        arr = (E[]) new Object[capacity];
    }

    private void resize() {
        capacity *= 2;
        E[] temp = (E[]) new Object[capacity];
        for (int i = 0; i < arr.length; i++) {
            temp[i] = arr[i];
        }
        arr = temp;
    }
}
