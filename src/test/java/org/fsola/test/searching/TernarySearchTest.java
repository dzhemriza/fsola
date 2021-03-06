/*
 * org.fsola
 *
 * File Name: TernarySearchTest.java
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
package org.fsola.test.searching;

import org.fsola.searching.TernarySearch;
import org.fsola.sorting.QuickSort;
import org.fsola.test.utils.Utils;
import org.junit.Assert;
import org.junit.Test;

public class TernarySearchTest {

    @Test
    public void test1() {
        int[] a = Utils.randomIntArray(2048, 2048);
        QuickSort.quick1(a);

        for (int i = 0; i < a.length; ++i) {
            int result = TernarySearch.search(a, a[i]);
            Assert.assertEquals(a[i], a[result]);
        }

        for (int i = 2048; i < 4098; ++i) {
            Assert.assertEquals(-1, TernarySearch.search(a, i));
        }
    }
}
