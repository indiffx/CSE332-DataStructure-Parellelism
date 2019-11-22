package datastructures.worklists;

import cse332.interfaces.worklists.PriorityWorkList;
import cse332.exceptions.NotYetImplementedException;

import java.util.NoSuchElementException;

/**
 * See cse332/interfaces/worklists/PriorityWorkList.java
 * for method specifications.
 */
public class MinFourHeap<E extends Comparable<E>> extends PriorityWorkList<E> {
    /* Do not change the name of this field; the tests rely on it to work correctly. */
    private E[] data;
    private int capacity = 10;
    private int size;
    
    public MinFourHeap() {
        data = (E[]) new Comparable[capacity];
        size = 0;
    }

    @Override
    public void add(E work) {
        if (size >= capacity)
            resize();
        data[percolateUp(size++, work)] = work;
    }

    @Override
    public E peek() {
        if (!hasWork())
            throw new NoSuchElementException();
        return data[0];
    }

    @Override
    public E next() {
        if (!hasWork())
            throw new NoSuchElementException();
        E ans = data[0];
        E replacement = data[size - 1];
        data[percolateDown(0, replacement)] = replacement;
        size--;
        return ans;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void clear() {
        data = (E[]) new Comparable[capacity];
        size = 0;
    }

    private int percolateDown(int hollow, E replacemnet) {
        int minChildIndex = findMin(0);
        while (minChildIndex < size && replacemnet.compareTo(data[minChildIndex]) > 0) {
            data[hollow] = data[minChildIndex];
            hollow = minChildIndex;
            minChildIndex = findMin(hollow);
        }
        return hollow;
    }

    private int findMin(int hollow) {
        int min = 4 * hollow + 1;
        if (min < size) {
            int other = min + 1;
            int count = 0;
            while (other < size && count < 3) {
                if (data[min].compareTo(data[other]) > 0)
                    min = other;
                other++;
                count++;
            }
        }
        return min;
    }

    private int percolateUp(int hollow, E val) {
        int parentIndex = (hollow - 1) / 4;
        while (hollow > 0 && data[parentIndex].compareTo(val) > 0) {
            data[hollow] = data[parentIndex];
            hollow = parentIndex;
            parentIndex = (hollow - 1) / 4;
        }
        return hollow;
    }

    private void resize() {
        capacity *= 2;
        E[] temp = (E[]) new Comparable[capacity];
        System.arraycopy(data, 0, temp, 0, size);
        data = temp;
    }
}
