/*
 * org.fsola
 *
 * File Name: InsertionSort.java
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
 * Implementation of insertion sort algorithm.
 * <p/>
 * Used Resources: README file resources section line 1
 */
public class InsertionSort {

    /**
     * Straight insertion sort.
     *
     * @param a
     */
    public static void straightInsertion(int[] a) {
        for (int i = 1; i < a.length; ++i) {
            int x = a[i];

            int j = i - 1;
            while (j >= 0 && x < a[j]) {
                a[j + 1] = a[j];
                --j;
            }

            a[j + 1] = x;
        }
    }

    /**
     * Insertion sort using binary search to identify element position.
     *
     * @param a
     */
    public static void binaryInsertion(int[] a) {
        for (int i = 1; i < a.length; ++i) {
            int x = a[i];

            int l = 0;
            int r = i - 1;

            while (l <= r) {
                int med = (l + r) / 2;

                if (x < a[med]) {
                    r = med - 1;
                } else {
                    l = med + 1;
                }
            }

            for (int j = i - 1; j >= 1; --j) {
                a[j + 1] = a[j];
            }

            a[l] = x;
        }
    }
}
