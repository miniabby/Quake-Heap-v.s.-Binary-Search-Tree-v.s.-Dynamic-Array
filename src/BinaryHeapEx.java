/*
 * NAME: Kechen Zhao
 * PID: A16139826
 */

import java.util.NoSuchElementException;
import java.util.ArrayList;

/**
 * This is the class of Binary Heap
 * @param <T> Generics
 */
public class BinaryHeapEx<T extends Comparable<? super T>> {

    private static final int DEFAULT_CAPACITY = 5; // default initial capacity
    private static final int EXPAND_FACTOR    = 2; // resizing factor
    private static final int CHILD_INDEX    = 2;
    private static final int CHILD_LEFT    = 1;
    private static final int CHILD_RIGHT    = 2;


    private T[] heap;          // heap array
    private int nelems;        // number of elements
    private boolean isMaxHeap; // boolean to indicate whether heap is max or min

    /**
     * Initializes a binary max heap with capacity = 5
     */
    @SuppressWarnings("unchecked")
    public BinaryHeapEx() {
        // initializes a binary max heap with default initial capacity 5 of heap array
        this.heap = (T[]) new Comparable[DEFAULT_CAPACITY];
        this.nelems = 0;
        this.isMaxHeap = true;
    }
    /**
     * Initializes a binary max heap with a given initial capacity.
     *
     * @param heapSize The initial capacity of the heap.
     */
    @SuppressWarnings("unchecked")
    public BinaryHeapEx(int heapSize) {
        // initializes a binary max heap with heapSize of heap array
        this.heap = (T[]) new Comparable[heapSize];
        this.nelems = 0;
        this.isMaxHeap = true;
    }

    /**
     * Initializes a binary heap with a given initial capacity.
     * @param heapSize The initial capacity of the heap.
     * @param isMaxHeap indicates whether the heap should be max or min
     */
    @SuppressWarnings("unchecked")
    public BinaryHeapEx(int heapSize, boolean isMaxHeap) {
        this.heap = (T[]) new Comparable[heapSize];
        this.nelems = 0;
        this.isMaxHeap = isMaxHeap;
    }

    /**
     * Returns the number of elements stored in the heap.
     *
     * @return The number of elements stored in the heap.
     */
    public int size() {
        return this.nelems;
    }

    /**
     * Clears all the items in the heap
     * Heap will be empty after this call returns
     */
    @SuppressWarnings("unchecked")
    public void clear() {
        int length = heap.length;
        this.nelems = 0;
        this.heap = (T[]) new Comparable[length];
    }

    /**
     * Adds the specified element to the heap; data cannot be null.
     * Resizes the storage if full.
     * @param data The element to add.
     * @throws NullPointerException if o is null.
     */
    public void insert(T data) throws NullPointerException {
        if (data == null) {
            throw new NullPointerException();
        }
        // double the capacity before adding the element
        int size = this.size();
        if (size == heap.length) {
            resize();
        }
        // first add the element at the end of heap array
        heap[size] = data;
        this.nelems = this.nelems + 1;
        // bubble up this element until it finds its correct position
        bubbleUp(size);
    }

    /**
     * Removes and returns the element at the root. If the
     * heap is empty, then this method throws a NoSuchElementException.
     * @return The element at the root stored in the heap.
     * @throws NoSuchElementException if the heap is empty
     */
    public T delete_min() throws NoSuchElementException {
        int size = this.size();
        if (size == 0) {
            throw new NoSuchElementException();
        }
        int rootIndex = 0;
        T root = heap[rootIndex];
        T replace = heap[size - 1];
        // remove the root and replace it with the last element in the heap array
        // set the last element as null
        // and reduce the number of elements by 1
        heap[rootIndex] = replace;
        heap[size - 1] = null;
        this.nelems = this.size() - 1;
        // bubble down this element until it find its correct position
        bubbleDown(rootIndex);
        return root;
    }

    /**
     * Retrieves, but does not remove, the element at the root.
     * @return item at the root of the heap
     * @throws NoSuchElementException - if this heap is empty
     */
    public T element() throws NoSuchElementException {
        if (this.size() == 0) {
            throw new NoSuchElementException();
        }
        T root = heap[0];
        return root;
    }

    /**
     * Bubble up the element until the ordering property of the heap
     * is satisfied
     * @param index the index of the element to be bubbled up
     */
    private void bubbleUp(int index) {
        // get the parent index
        int parentIndex = (index - 1) / 2; // note: magic number
        // if the node is root or it's already in its correct position
        if (index == 0 || compareWrapper(parentIndex, index)) {
            return;
        } else {
            swap(parentIndex, index);
            bubbleUp(parentIndex);
        }
    }

    /**
     * bubble down the element until the ordering property of the heap
     * is satisfied
     * @param index the index of the element to be bubbled down
     */
    private void bubbleDown(int index) {
        int leftIndex = (CHILD_INDEX * index + CHILD_LEFT);
        int rightIndex = (CHILD_INDEX * index + CHILD_RIGHT);
        int bound = this.nelems - 1;
        // if the index is out of range (leaf node)
        if (leftIndex > bound && rightIndex > bound) {
            return;
            //or it's already in the correct position
        } else if (heap[leftIndex] != null && heap[rightIndex] != null
                && (compareWrapper(index, leftIndex) && compareWrapper(index, rightIndex))) {
            return;
        } else if (heap[leftIndex] != null && heap[rightIndex] != null) {
            int swapIndex = 0;
            if (compareWrapper(leftIndex, rightIndex)) {
                swapIndex = leftIndex;
            } else {
                swapIndex = rightIndex;
            }
            swap(swapIndex, index);
            bubbleDown(swapIndex);
        } else if (heap[leftIndex] != null && compareWrapper(leftIndex, index)) {
            int swapIndex = leftIndex;
            swap(swapIndex, index);
            bubbleDown(swapIndex);
        } else if (heap[rightIndex] != null && compareWrapper(rightIndex, index)) {
            int swapIndex = rightIndex;
            swap(swapIndex, index);
            bubbleDown(swapIndex);
        }
    }

    /**
     * Double the size of the heap
     */
    @SuppressWarnings("unchecked")
    private void resize() {
        int length = heap.length;
        // double the length
        T[] newheap = (T[]) new Comparable[EXPAND_FACTOR * length];
        for (int i = 0; i < this.nelems; i++) {
            newheap[i] = heap[i];
        }
        heap = newheap;
    }

    /**
     * CompareWrapper helper method
     */
    private boolean compareWrapper(int index1, int index2) {
        if (isMaxHeap) {
            if (heap[index1].compareTo(heap[index2]) >= 0) {
                return true;
            } else {
                return false;
            }
        } else {
            if (heap[index1].compareTo(heap[index2]) <= 0) {
                return true;
            } else {
                return false;
            }
        }
    }

    /**
     * swap helper method
     */
    private void swap(int index1, int index2) {
        T temp = heap[index1];
        heap[index1] = heap[index2];
        heap[index2] = temp;
    }
}
