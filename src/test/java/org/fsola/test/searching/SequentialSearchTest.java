/*
 * org.fsola
 *
 * File Name: SequentialSearchTest.java
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

import org.fsola.datastructures.linkedlist.BiDirectionalNullTerminatedTailedLinkedList;
import org.fsola.searching.SequentialSearch;
import org.fsola.sorting.QuickSort;
import org.fsola.test.utils.Utils;
import org.junit.Assert;
import org.junit.Test;

import static org.fsola.datastructures.linkedlist.BiDirectionalNullTerminatedTailedLinkedList.Node;

public class SequentialSearchTest {

    @Test
    public void test1() {
        int[] a = Utils.randomIntArray(2048, 2048);

        for (int i = 0; i < a.length; ++i) {
            int result = SequentialSearch.searchUnorderedArray(a, a[i]);
            Assert.assertEquals(a[i], a[result]);
        }

        for (int i = 2048; i < 4098; ++i) {
            Assert.assertEquals(-1,
                    SequentialSearch.searchUnorderedArray(a, i));
        }
    }

    @Test
    public void test2() {
        int[] a = Utils.randomIntArray(2048, 2048);
        QuickSort.quick1(a);

        for (int i = 0; i < a.length; ++i) {
            int result = SequentialSearch.searchOrderedArray(a, a[i]);
            Assert.assertEquals(a[i], a[result]);
        }

        for (int i = 2048; i < 4098; ++i) {
            Assert.assertEquals(-1,
                    SequentialSearch.searchOrderedArray(a, i));
        }
    }

    @Test
    public void test3() {
        int[] a = Utils.randomIntArray(32, 2048);
        int[] s = new int[a.length];
        for (int i = 0; i < s.length; ++i) s[i] = 0;

        int[] original = Utils.copyAndSort(a);

        for (int i = 0; i < a.length; ++i) {
            int x = a[i];
            int result = SequentialSearch.searchWithStatistics(a, s, x);
            Assert.assertEquals(x, a[result]);

            int[] afterReordering = Utils.copyAndSort(a);
            Assert.assertArrayEquals(original, afterReordering);
            Utils.verifySortedIntArrayDesc(s);
        }

        for (int i = 2048; i < 4098; ++i) {
            Assert.assertEquals(-1,
                    SequentialSearch.searchWithStatistics(a, s, i));
        }
    }

    @Test
    public void test4() {
        int[] a = Utils.randomIntArray(32, 2048);

        int[] original = Utils.copyAndSort(a);

        for (int i = 0; i < a.length; ++i) {
            int x = a[i];
            int result = SequentialSearch.searchWithShiftLeft(a, x);
            Assert.assertEquals(x, a[result]);

            int[] afterReordering = Utils.copyAndSort(a);
            Assert.assertArrayEquals(original, afterReordering);
        }

        for (int i = 2048; i < 4098; ++i) {
            Assert.assertEquals(-1,
                    SequentialSearch.searchWithShiftLeft(a, i));
        }
    }

    @Test
    public void test5() {
        BiDirectionalNullTerminatedTailedLinkedList<Integer> l = Utils
                .randomIntList(2048, 2048);

        Integer[] a = new Integer[2048];
        Utils.populateArrayFromList(l, a);

        for (int i = 0; i < a.length; ++i) {
            Integer x = a[i];
            Node<Integer> result = SequentialSearch.searchWithShiftLeft(l, x);
            Assert.assertEquals(x, result.getData());
        }

        for (int i = 2048; i < 4098; ++i) {
            Assert.assertNull(SequentialSearch.searchWithShiftLeft(l, i));
        }
    }
}
