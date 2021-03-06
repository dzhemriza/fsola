/*
 * org.fsola
 *
 * File Name: SelectionSortTest.java
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

import org.fsola.sorting.SelectionSort;
import org.fsola.test.utils.Utils;
import org.junit.Test;

public class SelectionSortTest {

    @Test
    public void test1() {
        int[] a = Utils.randomIntArray(2048, 2048);
        SelectionSort.selection(a);
        Utils.verifySortedIntArrayAsc(a);
    }
}
