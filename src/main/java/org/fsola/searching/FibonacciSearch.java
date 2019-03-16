/*
 * org.fsola
 *
 * File Name: FibonacciSearch.java
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

import java.util.ArrayList;

/**
 * Implementation of Fibonacci search algorithm.
 * <p/>
 * Used Resources: README file resources section line 1, line 25, line 26
 */
public class FibonacciSearch {
    private static ArrayList<Integer> F = new ArrayList<>();

    static {
        // Base numbers
        F.add(0); // F0
        F.add(1); // F1
        F.add(1); // F1
    }

    /**
     * Calculate the closest to k Fibonacci number. Using pre-computed array of
     * numbers.
     *
     * @param k
     */
    private static int buildNumbers(int k) {
        if (k < 0) {
            throw new IllegalArgumentException("Negative K = " + k);
        }

        for (int i = 0; ; ++i) {
            int kth;

            if (F.size() == i) {
                // K-th fib number (k1 => k - 1 th number ...)
                int k1 = F.get(F.size() - 1);
                int k2 = F.get(F.size() - 2);

                kth = k2 + k1;
                F.add(kth);
            } else {
                kth = F.get(i);
            }

            if (k <= kth) {
                return i;
            }
        }
    }

    /**
     * Fibonacci Search algorithm
     *
     * @param a
     * @param x
     * @return
     */
    public static int fibSearch(int[] a, int x) {
        int k = buildNumbers(a.length) - 1;

        for (int offset = 0; k > 0; ) {
            int index = offset + F.get(--k);

            if (index >= a.length || x < a[index]) {
                // If index is out of bounds or current item is greater than X
                continue;
            } else if (a[index] < x) {
                offset = index;
            } else {
                // Find a match
                return index;
            }
        }

        return -1;
    }
}
