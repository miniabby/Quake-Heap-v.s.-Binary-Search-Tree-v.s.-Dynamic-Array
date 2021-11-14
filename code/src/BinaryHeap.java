/*
 * NAME: Kechen Zhao
 * ID: 957398
 */


/**
 * This is the class of Binary Heap
 */

import java.util.ArrayList;


public class BinaryHeap {

    private ArrayList<Integer> heap = new ArrayList<Integer>();
    private static final int CHILD_INDEX    = 2;
    private static final int CHILD_LEFT    = 1;
    private static final int CHILD_RIGHT    = 2;


    // test-helper function
    public int size() {
        return heap.size();
    }

    // test-helper function
    public int get_elem(int i) {
        return heap.get(i);
    }


    public void insert(int x) {
        // first add the element at the end of heap array
        heap.add(x);
        int index = heap.indexOf(x);
        // bubble up this element until it finds its correct position
        bubble_Up(index);
    }

    public void delete_min() {
        int size = heap.size();
        int rootIndex = 0;
        if (size == 1) {
            heap.remove(rootIndex);
            return;
        }
        else {
            int replace = heap.get(size - 1);
            // remove the root and replace it with the last element in the heap array
            // set the last element as null
            // and reduce the number of elements by 1
            heap.set(rootIndex, replace);
            heap.remove(size - 1);
            // bubble down this element until it find its correct position
            bubble_Down(rootIndex);
        }
    }

    public void update_key(int u, int x) {
        if (heap.get(u) == x) {
            return;
        }
        else {
            if (x > heap.get(u)) {
                heap.set(u, x);
                bubble_Down(u);
            } else if (x < heap.get(u)) {
                heap.set(u, x);
                bubble_Up(u);
            }
        }
    }


    /**
     * Bubble up the element until the ordering property of the heap
     * is satisfied
     * @param index the index of the element to be bubbled up
     */
    private void bubble_Up(int index) {
        // get the parent index
        int parentIndex = (index - 1) / 2; // note: magic number
        // if the node is root or it's already in its correct position
        if (index == 0 || compareWrapper(parentIndex, index)) {
            return;
        } else {
            swap(parentIndex, index);
            bubble_Up(parentIndex);
        }
    }

    /**
     * bubble down the element until the ordering property of the heap
     * is satisfied
     * @param index the index of the element to be bubbled down
     */
    private void bubble_Down(int index) {
        int leftIndex = (CHILD_INDEX * index + CHILD_LEFT);
        int rightIndex = (CHILD_INDEX * index + CHILD_RIGHT);
        int bound = heap.size() - 1;
        // if the index is out of range (leaf node)
        if (leftIndex > bound || rightIndex > bound) {
            return;
            //or it's already in the correct position
        } else if (heap.get(leftIndex) != null && heap.get(rightIndex) != null
                && (compareWrapper(index, leftIndex) && compareWrapper(index, rightIndex))) {
            return;
        } else if (heap.get(leftIndex) != null && heap.get(rightIndex) != null) {
            int swapIndex;
            if (compareWrapper(leftIndex, rightIndex)) {
                swapIndex = leftIndex;
            } else {
                swapIndex = rightIndex;
            }
            swap(swapIndex, index);
            bubble_Down(swapIndex);
        } else if (heap.get(leftIndex) != null && compareWrapper(leftIndex, index)) {
            int swapIndex = leftIndex;
            swap(swapIndex, index);
            bubble_Down(swapIndex);
        } else if (heap.get(rightIndex) != null && compareWrapper(rightIndex, index)) {
            int swapIndex = rightIndex;
            swap(swapIndex, index);
            bubble_Down(swapIndex);
        }
    }


    /**
     * CompareWrapper helper method
     */
    private boolean compareWrapper(int index1, int index2) {
        if (heap.get(index1).compareTo(heap.get(index2)) <= 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * swap helper method
     */
    private void swap(int index1, int index2) {
        int temp = heap.get(index1);
        heap.set(index1, heap.get(index2));
        heap.set(index2, temp);
    }

}