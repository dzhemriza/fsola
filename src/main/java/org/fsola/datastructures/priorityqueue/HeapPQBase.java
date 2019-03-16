/*
 * org.fsola
 *
 * File Name: PQBase.java
 *
 * Copyright 2014 Dzhem Riza
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.fsola.datastructures.priorityqueue;

import org.fsola.datastructures.vector.Vector;

/**
 * Base class for priority queue implemented as heap.
 * <p/>
 * Used Resources: README file resources section line 1, line 57, line 61.
 */
public abstract class HeapPQBase<V extends Comparable<V>> {

    /**
     * Heap is represented as array (in order to avoid duplicating
     * resizing code {@link org.fsola.datastructures.vector.Vector}
     * class is used.
     */
    protected Vector<V> heap = new Vector<>();

    /**
     * Add mew element in priority queue.
     *
     * @param v
     */
    public void put(V v) {
        heap.pushBack(v);
        bubbleUp(heap.size() - 1);
    }

    /**
     * Bubble up element
     *
     * @param i
     */
    private void bubbleUp(int i) {
        while (i > 0) {
            int parent = (i - 1) / 2;

            if (comp(heap.get(i), heap.get(parent))) {
                swap(i, parent);
                i = parent;
            } else {
                break;
            }
        }
    }

    /**
     * Bubble down element
     *
     * @param root
     */
    private void bubbleDown(int root) {
        while (root * 2 + 1 < heap.size()) {
            int left = root * 2 + 1;
            int right = left + 1;

            boolean isLeft = left < heap.size();
            boolean isRight = right < heap.size();

            int swap = left;

            if (isLeft && isRight &&
                    !comp(heap.get(left), heap.get(right))) {
                swap = right;
            }

            if (!comp(heap.get(root), heap.get(swap))) {
                swap(swap, root);
                root = swap;
            } else {
                break;
            }
        }
    }

    /**
     * Outputs heap in readable format
     */
    public void printHeap() {
        for (int i = 1, k = 0; k < heap.size(); i = i + i) {
            for (int j = 0; j < i && k < heap.size(); ++j) {
                System.out.print(" " + heap.get(k++));
            }
            System.out.println();
        }
    }

    /**
     * Reuse given Vector of elements in PQ.
     *
     * @param array
     */
    public void heapify(Vector<V> array) {
        heap = array;
        for (int i = (heap.size() - 2) / 2; i >= 0; --i) {
            bubbleDown(i);
        }
    }

    /**
     * Delete top element from priority queue.
     *
     * @return
     */
    protected V deleteTop() {
        V result = getTop();

        swap(heap.size() - 1, 0);
        heap.popBack();
        bubbleDown(0);

        return result;
    }

    /**
     * Swap two elements from array.
     *
     * @param a
     * @param b
     */
    private void swap(int a, int b) {
        V t = heap.get(a);
        heap.set(a, heap.get(b));
        heap.set(b, t);
    }

    /**
     * Return top element from priority queue.
     *
     * @return
     */
    protected V getTop() {
        if (isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }

        return heap.get(0);
    }

    /**
     * @return Is priority queue is empty
     */
    public boolean isEmpty() {
        return heap.isEmpty();
    }

    /**
     * Compare method used in derived classes for implementation of
     * min/max heap PQ.
     *
     * @param lhs
     * @param rhs
     * @return
     */
    protected abstract boolean comp(V lhs, V rhs);

    /**
     * {@code lhs < rhs}
     *
     * @param lhs
     * @param rhs
     * @return
     */
    protected boolean isLower(V lhs, V rhs) {
        return lhs.compareTo(rhs) < 0;
    }

    /**
     * {@code lhs > rhs}
     *
     * @param lhs
     * @param rhs
     * @return
     */
    protected boolean isGreater(V lhs, V rhs) {
        return lhs.compareTo(rhs) > 0;
    }
}
