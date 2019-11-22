package datastructures.worklists;

import cse332.exceptions.NotYetImplementedException;
import cse332.interfaces.worklists.FixedSizeFIFOWorkList;

import java.util.NoSuchElementException;

/**
 * See cse332/interfaces/worklists/FixedSizeFIFOWorkList.java
 * for method specifications.
 */
public class CircularArrayFIFOQueue<E> extends FixedSizeFIFOWorkList<E> {
    private E[] arr;
    private int first;
    private int last;
    private int size;

    public CircularArrayFIFOQueue(int capacity) {
        super(capacity);
        arr = (E[]) new Object[capacity];
        first = 0;
        last = 0;
        size = 0;
    }

    @Override
    public void add(E work) {
        if (isFull())
            throw new IllegalStateException("already full");
        arr[last++ % capacity()] = work;
        size++;
    }

    @Override
    public E peek() {
        if (!hasWork())
            throw new NoSuchElementException();
        return arr[first];
    }
    
    @Override
    public E peek(int i) {
        if (!hasWork())
            throw new NoSuchElementException();
        if (i < 0 || i >= size())
            throw new IndexOutOfBoundsException();
        return arr[first + i];
    }
    
    @Override
    public E next() {
        if (!hasWork())
            throw new NoSuchElementException();
        size--;
        E ans = arr[first];
        first = (first + 1) % capacity();
        return ans;
    }
    
    @Override
    public void update(int i, E value) {
        if (!hasWork())
            throw new NoSuchElementException();
        if (i < 0 || i >= size())
            throw new IndexOutOfBoundsException();
        arr[first + i] = value;
    }
    
    @Override
    public int size() {
        return this.size;
    }
    
    @Override
    public void clear() {
        first = -1;
        last = -1;
        size = 0;
        arr = (E[]) new Object[capacity()];
    }

    @Override
    public int compareTo(FixedSizeFIFOWorkList<E> other) {
        // You will implement this method in project 2. Leave this method unchanged for project 1.
        throw new NotYetImplementedException();
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(Object obj) {
        // You will finish implementing this method in project 2. Leave this method unchanged for project 1.
        if (this == obj) {
            return true;
        }
        else if (!(obj instanceof FixedSizeFIFOWorkList<?>)) {
            return false;
        }
        else {
            FixedSizeFIFOWorkList<E> other = (FixedSizeFIFOWorkList<E>) obj;

            // Your code goes here

            throw new NotYetImplementedException();
        }
    }

    @Override
    public int hashCode() {
        // You will implement this method in project 2. Leave this method unchanged for project 1.
        throw new NotYetImplementedException();
    }
}
