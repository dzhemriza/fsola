/*
 * org.fsola
 *
 * File Name: ShellSort.java
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
 * Implementation of shell sort algorithm.
 * <p/>
 * Used Resources: README file resources section line 1, 20
 */
public class ShellSort {
    private static int[] GAPS = {701, 301, 132, 57, 23, 10, 4, 1};

    public static void shell(int[] a) {
        for (int gap : GAPS) {

            for (int i = gap; i < a.length; ++i) {
                int tmp = a[i];

                int j = i;
                while (j >= gap && tmp < a[j - gap]) {
                    a[j] = a[j - gap];
                    j -= gap;
                }

                a[j] = tmp;
            }
        }
    }
}
