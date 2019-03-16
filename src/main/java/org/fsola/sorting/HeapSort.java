/*
 * org.fsola
 *
 * File Name: CombSortTest.java
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
package org.fsola.sorting;

/**
 * Implementation of heap sort algorithm.
 * <p/>
 * Used Resources: README file resources section line 1, line 22
 */
public class HeapSort {

    /**
     * Heapify array using shift up algorithm
     *
     * @param a
     */
    private static void heapify1(int[] a) {
        for (int end = 1; end < a.length; ++end) {
            shiftUp(a, 0, end);
        }
    }

    /**
     * Heapify array using shift down algorithm
     *
     * @param a
     */
    private static void heapify2(int[] a) {
        for (int start = (int) Math.floor((a.length - 2) / 2); start >= 0;
             --start) {
            shiftDown(a, start, a.length - 1);
        }
    }

    /**
     * Swap 2 elements in array
     *
     * @param a
     * @param x
     * @param y
     */
    private static void swap(int[] a, int x, int y) {
        int tmp = a[x];
        a[x] = a[y];
        a[y] = tmp;
    }

    /**
     * Shift element up in array heap
     *
     * @param a
     * @param start
     * @param end
     */
    private static void shiftUp(int[] a, int start, int end) {
        for (int child = end; child > start; ) {
            int parent = (int) Math.floor((child - 1) / 2);

            if (a[parent] < a[child]) {
                swap(a, parent, child);
                child = parent;
            } else {
                break;
            }
        }
    }

    /**
     * Shift element down in array heap
     *
     * @param a
     * @param start
     * @param end
     */
    private static void shiftDown(int[] a, int start, int end) {
        int root = start;

        // root * 2 + 1 => left child
        while (root * 2 + 1 <= end) {
            int child = root * 2 + 1;
            // child + 1 => right child
            int swap = root;

            if (a[swap] < a[child]) {
                swap = child;
            }

            if (child + 1 <= end && a[swap] < a[child + 1]) {
                swap = child + 1;
            }

            if (swap != root) {
                swap(a, root, swap);
                root = swap;
            } else {
                break;
            }
        }
    }

    /**
     * Heap sort using shift down algorithm for building the heap. The advantage
     * of this approach is that building the heap is more readable than the
     * shift down approach in {@link #heap2(int[])} implementation.
     *
     * @param a
     */
    public static void heap1(int[] a) {
        heapify1(a);

        for (int end = a.length - 1; end > 0; --end) {
            swap(a, end, 0); // swap last and first element
            shiftDown(a, 0, end - 1);
        }
    }

    /**
     * Heap sort using shift down algorithm for building the heap
     *
     * @param a
     */
    public static void heap2(int[] a) {
        heapify2(a);

        for (int end = a.length - 1; end > 0; --end) {
            swap(a, end, 0); // swap last and first element
            shiftDown(a, 0, end - 1);
        }
    }
}
