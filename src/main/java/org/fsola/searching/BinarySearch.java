/*
 * org.fsola
 *
 * File Name: BinarySearch.java
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

/**
 * Implementation of binary search algorithm.
 * <p/>
 * Used Resources: README file resources section line 1
 */
public class BinarySearch {

    /**
     * Recursive version of binary search algorithm.
     *
     * @param a
     * @param l
     * @param r
     * @param x
     * @return
     */
    public static int binarySearchRec(int[] a, int l, int r, int x) {
        if (l > r) {
            return -1;
        }

        int mid = (l + r) / 2;
        if (x < a[mid]) {
            return binarySearchRec(a, l, mid - 1, x);
        } else if (a[mid] < x) {
            return binarySearchRec(a, mid + 1, r, x);
        } else {
            return mid;
        }
    }

    /**
     * Iterative version of binary search algorithm.
     *
     * @param a
     * @param x
     * @return
     */
    public static int binarySearchIter(int[] a, int x) {
        int l = 0;
        int r = a.length - 1;

        return binarySearchIter(a, x, l, r);
    }

    /**
     * Iterative version of binary search algorithm for a given range.
     *
     * @param a
     * @param x
     * @param l
     * @param r
     * @return
     */
    public static int binarySearchIter(int[] a, int x, int l, int r) {
        while (l <= r) {
            int mid = (l + r) / 2;

            if (x < a[mid]) {
                r = mid - 1;
            } else if (a[mid] < x) {
                l = mid + 1;
            } else {
                return mid;
            }
        }

        return -1;
    }

    /**
     * Calculate such k^2 that is less than n.
     *
     * @param n
     * @return
     */
    private static int getMaxPower2(int n) {
        int pow2;
        for (pow2 = 1; pow2 < n; pow2 <<= 1) ;
        return pow2 >> 1;
    }

    /**
     * Binary search implementation using k^2 divide
     *
     * @param a
     * @param x
     * @return
     */
    public static int binarySearchPower2(int[] a, int x) {
        int i = getMaxPower2(a.length);
        int l = 0;

        if (a[i] < x)
            l = a.length - i + 1;

        // Check is current element is the searched one
        if (a[i] == x) {
            return i;
        }

        while (i > 0) {
            i >>= 1;

            int ind = l + i;

            if (ind < 0 || a.length <= ind) {
                break;
            }

            if (a[ind] == x) {
                return ind;
            } else if (a[ind] < x) {
                l = ind;
            }
        }

        return -1;
    }

    /**
     * Binary search implementation using k^2 divide optimized for array with
     * size 1000 (this variant is super effective in cases when array size is
     * almost known).
     *
     * @param a
     * @param x
     * @return
     */
    public static int binarySearchPower2_OptimizedFor1000(int[] a, int x) {
        int max = 1000;
        int l = 0;

        if (a[512] <= x) l = max - 512;
        if (a[l + 256] <= x) l += 256;
        if (a[l + 128] <= x) l += 128;
        if (a[l + 64] <= x) l += 64;
        if (a[l + 32] <= x) l += 32;
        if (a[l + 16] <= x) l += 16;
        if (a[l + 8] <= x) l += 8;
        if (a[l + 4] <= x) l += 4;
        if (a[l + 2] <= x) l += 2;
        if (a[l + 1] <= x) l += 1;

        return (0 <= l && l < max && a[l] == x ? l : -1);
    }
}
