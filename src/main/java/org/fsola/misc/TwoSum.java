/*
 * org.fsola
 *
 * File Name: TwoSum.java
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

import org.fsola.datastructures.hashtable.RobinHoodHashTable;
import org.fsola.searching.BinarySearch;
import org.fsola.sorting.MergeSort;

/**
 * 2-Sum problem - find 2 elements in array that sum is 0.
 * <p/>
 * Used Resources: README file resources section line 57.
 */
public class TwoSum {

    /**
     * Brute force algorithm runs in O(N^2).
     *
     * @param A
     * @return
     */
    public static int sol1(int[] A) {
        int c = 0;

        for (int i = 0; i < A.length; ++i) {
            for (int j = i + 1; j < A.length; ++j) {
                if (A[i] + A[j] == 0) {
                    c++;
                    System.out.println("Debug: " + A[i] + ", " + A[j]);
                }
            }
        }

        return c;
    }

    /**
     * Improvement using binary search
     * O(N*logN + N*logN)
     *
     * @param A
     * @return
     */
    public static int sol2(int[] A) {
        MergeSort.topDownMergeSort(A);

        int c = 0;
        for (int i = 0; i < A.length; ++i) {
            int j = BinarySearch.binarySearchIter(A, -A[i]);

            if (0 <= j && i < j) {
                c++;
                System.out.println("Debug: " + A[i] + ", " + A[j]);
            }
        }

        return c;
    }

    /**
     * Improved using additional memory for hash table.
     * If we assume that contains and put operations in
     * hash table are in O(C) complexity this means
     * that current running time is O(N) and using O(M)
     * additional memory for hash table.
     *
     * @param A
     * @return
     */
    public static int sol3(int[] A) {
        RobinHoodHashTable<Integer, Integer> hashTable = new RobinHoodHashTable<>();

        for (int i = 0; i < A.length; ++i) {
            hashTable.put(A[i], i);
        }

        int c = 0;

        for (int i = 0; i < A.length; ++i) {
            Integer pos = hashTable.get(-A[i]);

            if (pos != null && 0 < pos.compareTo(i)) {
                c++;
                System.out.println("Debug: " + i + ", " + -i);
            }
        }

        return c;
    }
}
