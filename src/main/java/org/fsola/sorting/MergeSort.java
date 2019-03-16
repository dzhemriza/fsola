/*
 * org.fsola
 *
 * File Name: MergeSort.java
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
 * Implementation of merge sort algorithm.
 * <p/>
 * Used Resources: README file resources section line 21, line 24
 */
public class MergeSort {
    /**
     * Additional array to store merged result.
     */
    private static int[] B;

    /**
     * Copy contents of array B in given array.
     *
     * @param a
     * @param l
     * @param r
     */
    private static void copyArray(int[] a, int l, int r) {
        for (int i = l; i <= r; ++i) {
            a[i] = B[i];
        }
    }

    /**
     * Merge 2 sites into one additional array
     *
     * @param a
     * @param l
     * @param m
     * @param r
     */
    private static void topDownMerge(int[] a, int l, int m, int r) {
        int i = l;
        int j = m + 1;

        for (int k = l; k <= r; ++k) {
            if ((m - i + 1 > 0) && (r - j + 1 > 0)) {
                // we have elements in left and in right site
                if (a[i] < a[j]) {
                    B[k] = a[i++];
                } else {
                    B[k] = a[j++];
                }
            } else if (m - i + 1 > 0) {
                // we have elements only in left site add all this elements
                B[k] = a[i++];
            } else {
                // we have elements only in right site add all this elements
                B[k] = a[j++];
            }
        }
    }

    private static void topDownSplitMergeSort(int[] a, int l, int r) {
        if (r - l < 1)
            return;

        int m = (l + r) / 2;
        topDownSplitMergeSort(a, l, m);
        topDownSplitMergeSort(a, m + 1, r);
        topDownMerge(a, l, m, r);
        copyArray(a, l, r);
    }

    public static void topDownMergeSort(int[] a) {
        // Re-allocate additional array for merging results
        B = new int[a.length];
        topDownSplitMergeSort(a, 0, a.length - 1);
    }
}
