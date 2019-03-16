/*
 * org.fsola
 *
 * File Name: BitSort.java
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
 * Implementation of bit sort algorithm. Please be aware that this
 * implementation works only with positive integers.
 * <p/>
 * Used Resources: README file resources section line 1, 21
 */
public class BitSort {

    private static void swap(int[] a, int x, int y) {
        int tmp = a[x];
        a[x] = a[y];
        a[y] = tmp;
    }

    /**
     * Partition array based on leading bit
     *
     * @param a
     * @param l
     * @param r
     * @param bitPow2
     * @return
     */
    private static int partition(int[] a, int l, int r, int bitPow2) {
        int firstHigh = l;

        for (int i = l; i <= r; ++i) {
            if ((a[i] & bitPow2) != bitPow2) {
                swap(a, firstHigh, i);
                firstHigh++;
            }
        }

        return firstHigh;
    }

    /**
     * Bit sort algorithm using divide by conquer approach based on quick sort
     * implementation.
     *
     * @param a
     * @param l
     * @param r
     * @param leadingBitNumber
     */
    private static void sort(int[] a, int l, int r, int leadingBitNumber) {

        if (r > l && leadingBitNumber >= 0) {
            int mask = 1 << leadingBitNumber;

            int p = partition(a, l, r, mask);

            sort(a, l, p - 1, leadingBitNumber - 1);
            sort(a, p, r, leadingBitNumber - 1);
        }
    }

    public static void sort(int[] a) {
        sort(a, 0, a.length - 1, Integer.SIZE - 2);
    }
}
