/*
 * org.fsola
 *
 * File Name: PermSort.java
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
 * Implementation of perm sort algorithm. Please be aware that this
 * implementation works only with positive integers in [0, N].
 * <p/>
 * Used Resources: README file resources section line 1
 */
public class PermSort {

    private static void swap(int[] a, int x, int y) {
        int tmp = a[x];
        a[x] = a[y];
        a[y] = tmp;
    }

    public static void sort(int[] a) {
        for (int i = 0; i < a.length; ++i) {
            while (a[i] != i) {
                swap(a, a[i], a[a[i]]);
            }
        }
    }
}
