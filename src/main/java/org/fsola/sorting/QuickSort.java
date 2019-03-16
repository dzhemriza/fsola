/*
 * org.fsola
 *
 * File Name: QuickSort.java
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

import org.fsola.datastructures.linkedlist.BiDirectionalNullTerminatedLinkedList;

/**
 * Implementation of quick sort algorithm.
 * <p/>
 * Used Resources: README file resources section line 1, line 21
 */
public class QuickSort {

    private static int partition1(int[] a, int l, int r) {

        int firstHigh = l;
        int p = r;
        for (int i = l; i < r; ++i) {
            if (a[i] < a[p]) {
                int tmp = a[i];
                a[i] = a[firstHigh];
                a[firstHigh] = tmp;

                firstHigh++;
            }
        }

        int tmp = a[p];
        a[p] = a[firstHigh];
        a[firstHigh] = tmp;

        return firstHigh;
    }

    private static void quick1Impl(int[] a, int l, int r) {
        if (l < r) {
            int p = partition1(a, l, r);
            quick1Impl(a, l, p - 1);
            quick1Impl(a, p + 1, r);
        }
    }

    private static <T extends Comparable<T>> int partition1(T[] a, int l, int r) {

        int firstHigh = l;
        int p = r;
        for (int i = l; i < r; ++i) {
            if (a[i].compareTo(a[p]) == -1) {
                T tmp = a[i];
                a[i] = a[firstHigh];
                a[firstHigh] = tmp;

                firstHigh++;
            }
        }

        T tmp = a[p];
        a[p] = a[firstHigh];
        a[firstHigh] = tmp;

        return firstHigh;
    }

    public static void quick1(int[] a) {
        quick1Impl(a, 0, a.length - 1);
    }

    private static <T extends Comparable<T>> void quick1Impl(T[] a, int l, int r) {
        if (l < r) {
            int p = partition1(a, l, r);
            quick1Impl(a, l, p - 1);
            quick1Impl(a, p + 1, r);
        }
    }

    public static <T extends Comparable<T>> void quick1(T[] a) {
        quick1Impl(a, 0, a.length - 1);
    }

    public static int partition2(int[] a, int l, int r) {
        int x = a[r];
        do {
            while (a[l] < x) l++;
            while (x < a[r]) r--;

            if (l <= r) {
                int tmp = a[l];
                a[l] = a[r];
                a[r] = tmp;

                l++;
                r--;
            }
        } while (l <= r);

        return r;
    }

    public static void quick2Impl(int[] a, int l, int r) {
        if (l < r) {
            int p = partition2(a, l, r);
            quick2Impl(a, l, p);
            quick2Impl(a, p + 1, r);
        }
    }

    public static void quick2(int[] a) {
        quick2Impl(a, 0, a.length - 1);
    }

    /**
     * Data holder class of left and right site
     */
    private static class Pair {
        int l;
        int r;

        public Pair(int l, int r) {
            this.l = l;
            this.r = r;
        }
    }

    /**
     * Non recursive implementation of quick sort algorithm using stack.
     *
     * @param a
     */
    public static void quick3(int[] a) {
        BiDirectionalNullTerminatedLinkedList<Pair> stack = new
                BiDirectionalNullTerminatedLinkedList<>();

        stack.push(new Pair(0, a.length - 1));
        while (!stack.isEmpty()) {
            Pair pair = stack.pop();

            if (pair.l < pair.r) {
                int p = partition1(a, pair.l, pair.r);
                stack.push(new Pair(pair.l, p - 1));
                stack.push(new Pair(p + 1, pair.r));
            }
        }
    }
}
