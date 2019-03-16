/*
 * org.fsola
 *
 * File Name: JumpSearch.java
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
 * Implementation of jump search algorithm.
 * <p/>
 * Used Resources: README file resources section line 1
 */
public class JumpSearch {

    /**
     * Sequential search algorithm in given range.
     *
     * @param a
     * @param l
     * @param r
     * @param x
     * @return
     */
    private static int sequentialSearch(int[] a, int l, int r, int x) {
        for (; l <= r; l++) {
            if (a[l] == x) {
                return l;
            }
        }
        return -1;
    }

    /**
     * Jump search algorithm requires sorted array.
     *
     * @param a
     * @param x
     * @param step
     * @return
     */
    public static int jumpSearch(int[] a, int x, int step) {
        int ind;

        // Calculate a index
        for (ind = 0; ind < a.length && a[ind] < x; ind += step) ;

        int l = 0;
        int r = a.length - 1;

        if (step <= ind + 1)
            l = ind + 1 - step;

        if (ind < a.length)
            r = ind;

        return sequentialSearch(a, l, r, x);
    }
}
