/*
 * org.fsola
 *
 * File Name: PermSortTest.java
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
package org.fsola.test.sorting;

import org.fsola.sorting.PermSort;
import org.fsola.test.utils.Utils;
import org.junit.Test;

public class PermSortTest {

    @Test
    public void test1() {
        int[] a = generateRandomArray(2048);
        PermSort.sort(a);
        Utils.verifySortedIntArrayAsc(a);
    }

    /**
     * Generates random array in interval [0, N]
     *
     * @return
     */
    private int[] generateRandomArray(int N) {
        int[] a = new int[N + 1];

        for (int i = 0; i < a.length; ++i) {
            a[i] = i;
        }

        for (int i = 0; i < a.length; ++i) {
            int x = Utils.RAND.nextInt(a.length - i);
            Utils.swap(a, i, x);
        }

        return a;
    }
}
