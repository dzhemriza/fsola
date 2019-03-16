/*
 * org.fsola
 *
 * File Name: ThreeSum.java
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
package org.fsola.misc;

import org.fsola.searching.BinarySearch;
import org.fsola.sorting.MergeSort;

/**
 * 3-Sum problem - find 3 items in array that sum to 0.
 * <p/>
 * Used Resources: README file resources section line 57.
 */
public class ThreeSum {

    /**
     * Brute force algorithm - O(N^3).
     *
     * @param A
     * @return
     */
    public static int sol1(int[] A) {
        int count = 0;

        for (int i = 0; i < A.length; ++i) {
            for (int j = i + 1; j < A.length; ++j) {
                for (int k = j + 1; k < A.length; ++k) {
                    if (A[i] + A[j] + A[k] == 0) {
                        count++;
                        System.out.println("Debug: " + A[i] + ", " + A[j] + ", " + A[k]);
                    }
                }
            }
        }

        return count;
    }

    /**
     * Optimization that uses binary search to find the last
     * element - O(N^2 * logN) + O(N * logN)
     *
     * @param A
     * @return
     */
    public static int sol2(int[] A) {
        int count = 0;
        MergeSort.topDownMergeSort(A);

        for (int i = 0; i < A.length; ++i) {
            for (int j = i + 1; j < A.length; ++j) {
                int k = BinarySearch.binarySearchIter(A, -(A[i] + A[j]));
                if (k != -1 && j < k) {
                    // (j < k) -> remove duplicates
                    count++;
                    System.out.println("Debug: " + A[i] + ", " + A[j] + ", " + A[k]);
                }
            }
        }

        return count;
    }
}
