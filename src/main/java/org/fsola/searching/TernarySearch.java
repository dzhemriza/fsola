/*
 * org.fsola
 *
 * File Name: TernarySearch.java
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
 * Implementation of ternary search algorithm.
 * <p/>
 * Used Resources: README file resources section line 27, line 28, line 29
 */
public class TernarySearch {

    public static int search(int[] a, int x) {
        int l = 0;
        int r = a.length - 1;

        while (l <= r) {
            int offset = (r - l) / 3;

            int mid1 = l + offset; // 1/3
            int mid2 = l + 2 * offset; // 2/3

            if (a[mid1] == x) {
                return mid1;
            } else if (a[mid2] == x) {
                return mid2;
            } else if (x < a[mid1]) {
                r = mid1 - 1;
            } else if (a[mid2] < x) {
                l = mid2 + 1;
            } else { // mid1 < x < mid2
                l = mid1 + 1;
                r = mid2 - 1;
            }
        }

        return -1;
    }
}
