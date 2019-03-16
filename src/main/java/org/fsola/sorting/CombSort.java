/*
 * org.fsola
 *
 * File Name: CombSort.java
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
 * Implementation of comb sort algorithm.
 * <p/>
 * Used Resources: README file resources section line 1
 */
public class CombSort {

    public static void comb(int[] a) {
        int gap = a.length;
        boolean swapped = false;

        do {
            swapped = false;

            gap = (int) (gap / 1.3);

            if (gap < 1) {
                gap = 1;
            }

            for (int i = 0; i < a.length - gap; ++i) {
                int j = i + gap;

                if (a[j] < a[i]) {
                    int tmp = a[i];
                    a[i] = a[j];
                    a[j] = tmp;

                    swapped = true;
                }
            }

        } while (swapped || gap > 1);
    }
}
