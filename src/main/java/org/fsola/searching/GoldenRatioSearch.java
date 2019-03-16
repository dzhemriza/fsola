/*
 * org.fsola
 *
 * File Name: GoldenRatioSearch.java
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
 * Implementation of golden ratio search algorithm.
 * <p/>
 * Used Resources: README file resources section line 30, line 31
 */
public class GoldenRatioSearch {

    /**
     * Golden ratio calculation
     */
    public static float GOLDEN_RATIO = (float) ((1 + Math.sqrt(5)) / 2);
    /**
     * Converted golden ratio in order to avoid divide operation below
     */
    public static float ZERO = GOLDEN_RATIO - 1;

    public static int search(int[] a, int x) {
        int l = 0;
        int r = a.length - 1;

        while (l <= r) {
            int offset = (int) ((r - l) * ZERO);
            int m = l + offset;

            if (a[m] < x) {
                l = m + 1;
            } else if (x < a[m]) {
                r = m - 1;
            } else {
                return m;
            }
        }

        return -1;
    }
}
