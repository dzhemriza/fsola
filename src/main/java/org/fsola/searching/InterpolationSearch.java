/*
 * org.fsola
 *
 * File Name: InterpolationSearch.java
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
 * Implementation of interpolation search algorithm.
 * <p/>
 * Used Resources: README file resources section line 1
 */
public class InterpolationSearch {

    public static int search(int[] a, int x) {
        int l = 0;
        int r = a.length - 1;

        while (l <= r) {
            if (a[l] == a[r]) {
                if (a[l] == x) {
                    return l;
                } else {
                    return -1;
                }
            }

            float k = (x - a[l]) / (a[r] - a[l]);

            if (k < 0 || k > 1) {
                return -1;
            }

            int mid = (int) (l + k * (r - l) + 0.5);

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
}
