/*
 * org.fsola
 *
 * File Name: CountSort.java
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
 * Implementation of count sort algorithm.
 * <p/>
 * Used Resources: README file resources section line 1
 */
public class CountSort {

    /**
     * Internal data structure used to hold min and max element of array
     */
    private static class MinMaxPair {
        int min;
        int max;

        public MinMaxPair(int min, int max) {
            this.min = min;
            this.max = max;
        }
    }

    /**
     * Find min and max element in given array
     *
     * @param a
     * @return
     */
    private static MinMaxPair findMinMax(int[] a) {
        int min = 0;
        int max = 0;

        for (int i = 0; i < a.length; ++i) {
            if (a[i] < a[min]) {
                min = i;
            }

            if (a[max] < a[i]) {
                max = i;
            }
        }

        return new MinMaxPair(a[min], a[max]);
    }

    /**
     * Implementation of count sort algorithm using additional array
     *
     * @param a
     */
    public static void count(int[] a) {
        MinMaxPair minMax = findMinMax(a);
        int shift = -minMax.min;

        // abs in case that all elements are with negative value
        int[] count = new int[Math.abs(minMax.max - minMax.min + 1)];

        for (int i = 0; i < count.length; ++i) {
            count[i] = 0;
        }

        // Count all elements
        for (int i : a) {
            count[i + shift]++;
        }

        // Create array from scratch
        for (int i = 0, k = 0; i < count.length; ++i) {
            for (int j = 0; j < count[i]; ++j) {
                a[k++] = i - shift;
            }
        }
    }
}
