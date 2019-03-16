/*
 * org.fsola
 *
 * File Name: SequentialSearch.java
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
package org.fsola.searching;

import org.fsola.datastructures.linkedlist.BiDirectionalNullTerminatedTailedLinkedList;

import static org.fsola.datastructures.linkedlist.BiDirectionalNullTerminatedTailedLinkedList.Node;

/**
 * Implementation of various sequential search algorithms.
 * <p/>
 * Used Resources: README file resources section line 1
 */
public class SequentialSearch {

    /**
     * Simple sequential search algorithm for unordered data in array.
     *
     * @param a
     * @return {@code -1} if array doesn't contain element. Otherwise index of
     * the element.
     */
    public static int searchUnorderedArray(int[] a, int x) {
        int r = -1;

        for (int i = 0; i < a.length; ++i) {
            if (x == a[i]) {
                r = i;
                break;
            }
        }

        return r;
    }

    /**
     * Simple sequential search algorithm for ordered data in array.
     *
     * @param a
     * @return {@code -1} if array doesn't contain element. Otherwise index of
     * the element.
     */
    public static int searchOrderedArray(int[] a, int x) {
        int r = -1;

        for (int i = 0; i < a.length && a[i] <= x; ++i) {
            if (x == a[i]) {
                r = i;
                break;
            }
        }

        return r;
    }

    /**
     * Sequential search algorithm for unordered data in array using a
     * statistical reordering.
     *
     * @param a
     * @param s
     * @param x
     * @return
     */
    public static int searchWithStatistics(int[] a, int[] s, int x) {
        int r = -1;

        for (int i = 0; i < a.length; ++i) {
            if (x == a[i]) {
                r = i;
                s[i]++;
                break;
            }
        }

        if (r != -1) {
            // Reorder based on statistics (simple insertion sort)
            int j = s[r];
            int k = a[r];

            int i;
            for (i = r; i > 0 && j > s[i - 1]; --i) {
                a[i] = a[i - 1];
                s[i] = s[i - 1];
            }

            a[i] = k;
            s[i] = j;

            // Update return index as now it's changed
            r = i;
        }

        return r;
    }

    /**
     * Sequential search algorithm for unordered data in array moving successful
     * tested element in the beginning of the array. Moving tested element in
     * the head of the array is with complexity in the worst case O(n) but this
     * could be done in O(C) complexity if we use linked list instead of array
     * as we just want to remove tested element and add it in the head of the
     * list.
     *
     * @param a
     * @param x
     * @return
     */
    public static int searchWithShiftLeft(int[] a, int x) {
        int r = -1;

        for (int i = 0; i < a.length; ++i) {
            if (x == a[i]) {
                r = i;
                break;
            }
        }

        if (r != -1) {
            // Shift left from position r
            int j = a[r];

            int i;
            for (i = r; i > 0; --i) {
                a[i] = a[i - 1];
            }

            // i is always 0
            a[i] = j;

            // Update return index as now it's changed
            r = 0;
        }

        return r;
    }

    /**
     * Sequential search algorithm for unordered data in linked list moving
     * successful tested element in the beginning of the list.
     *
     * @param l
     * @param x
     * @param <T>
     * @return
     */
    public static <T> Node<T> searchWithShiftLeft(
            BiDirectionalNullTerminatedTailedLinkedList<T> l, T x) {
        for (Node<T> p = l.getHead().getNext(); p != l.getTail(); p = p
                .getNext()) {
            if (x.equals(p.getData())) {
                // We found element that match

                // 1. Remove this element from linked list
                T e = l.delete(p);
                l.push(e);

                // The first element is the successful tested element
                return l.getHead().getNext();
            }
        }

        return null;
    }
}
