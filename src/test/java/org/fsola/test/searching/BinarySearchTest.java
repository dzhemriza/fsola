/*
 * org.fsola
 *
 * File Name: BinarySearchTest.java
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

import org.fsola.searching.BinarySearch;
import org.fsola.sorting.QuickSort;
import org.fsola.test.utils.Utils;
import org.junit.Assert;
import org.junit.Test;

public class BinarySearchTest {

    @Test
    public void test1() {
        int[] a = Utils.randomIntArray(2048, 2048);
        QuickSort.quick1(a);

        for (int i = 0; i < a.length; ++i) {
            int result = BinarySearch.binarySearchRec(a, 0, a.length - 1, a[i]);
            Assert.assertEquals(a[i], a[result]);
        }

        for (int i = 2048; i < 4098; ++i) {
            Assert.assertEquals(-1,
                    BinarySearch.binarySearchRec(a, 0, a.length - 1, i));
        }
    }

    @Test
    public void test2() {
        int[] a = Utils.randomIntArray(2048, 2048);
        QuickSort.quick1(a);

        for (int i = 0; i < a.length; ++i) {
            int result = BinarySearch.binarySearchIter(a, a[i]);
            Assert.assertEquals(a[i], a[result]);
        }

        for (int i = 2048; i < 4098; ++i) {
            Assert.assertEquals(-1,
                    BinarySearch.binarySearchIter(a, i));
        }
    }

    @Test
    public void test3() {
        int[] a = Utils.randomIntArray(2048, 2048);
        QuickSort.quick1(a);

        for (int i = 0; i < a.length; ++i) {
            int result = BinarySearch.binarySearchPower2(a, a[i]);
            Assert.assertEquals(a[i], a[result]);
        }

        for (int i = 2048; i < 4098; ++i) {
            Assert.assertEquals(-1,
                    BinarySearch.binarySearchPower2(a, i));
        }
    }

    @Test
    public void test4() {
        int[] a = Utils.randomIntArray(400, 2048);
        QuickSort.quick1(a);

        for (int i = 0; i < a.length; ++i) {
            int result = BinarySearch.binarySearchPower2(a, a[i]);
            Assert.assertEquals(a[i], a[result]);
        }

        for (int i = 2048; i < 4098; ++i) {
            Assert.assertEquals(-1,
                    BinarySearch.binarySearchPower2(a, i));
        }
    }

    @Test
    public void test5() {
        int[] a = Utils.randomIntArray(1000, 2048);
        QuickSort.quick1(a);

        for (int i = 0; i < a.length; ++i) {
            int result = BinarySearch.binarySearchPower2_OptimizedFor1000(a,
                    a[i]);
            Assert.assertEquals(a[i], a[result]);
        }

        for (int i = 2048; i < 4098; ++i) {
            Assert.assertEquals(-1,
                    BinarySearch.binarySearchPower2_OptimizedFor1000(a, i));
        }
    }
}
