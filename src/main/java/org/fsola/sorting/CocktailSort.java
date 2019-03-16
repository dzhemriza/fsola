/*
 * org.fsola
 *
 * File Name: CocktailSort.java
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
 * Implementation of shake sort algorithm.
 * <p/>
 * Used Resources: README file resources section line 19
 */
public class CocktailSort {

    public static void cocktail(int[] a) {
        boolean swapped;

        int left = 0;
        int right = a.length;

        do {
            swapped = false;

            for (int i = left; i < right - 1; ++i) {
                if (a[i + 1] < a[i]) {
                    int tmp = a[i + 1];
                    a[i + 1] = a[i];
                    a[i] = tmp;
                    swapped = true;
                }
            }
            right--;

            for (int i = right - 1; i > left; --i) {
                if (a[i] < a[i - 1]) {
                    int tmp = a[i - 1];
                    a[i - 1] = a[i];
                    a[i] = tmp;
                    swapped = true;
                }
            }
            left++;

        } while (swapped);
    }
}
