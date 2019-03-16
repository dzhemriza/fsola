/*
 * org.fsola
 *
 * File Name: MaxPQTest.java
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
package org.fsola.test.datastructures.priorityqueue;

import org.fsola.datastructures.priorityqueue.MaxPQ;
import org.fsola.datastructures.vector.Vector;
import org.fsola.test.utils.Utils;
import org.junit.Assert;
import org.junit.Test;

public class MaxPQTest {

    @Test
    public void test1() {
        MaxPQ<Integer> h = new MaxPQ<>();
        Assert.assertTrue(h.isEmpty());
        h.put(10);
        Assert.assertEquals(Integer.valueOf(10), h.getMax());
        h.put(9);
        Assert.assertEquals(Integer.valueOf(10), h.getMax());
    }

    @Test
    public void test2() {
        MaxPQ<Integer> h = new MaxPQ<>();

        for (int i = 100; i > 0; --i) {
            h.put(i);
        }

        Assert.assertEquals(Integer.valueOf(100), h.getMax());
    }

    @Test
    public void test3() {
        MaxPQ<Integer> h = new MaxPQ<>();
        int N = 1000;

        for (int i = 1; i <= N; ++i) {
            h.put(i);
        }

        h.printHeap();

        for (int i = N; i > 0; --i) {
            Assert.assertEquals(Integer.valueOf(i), h.deleteMax());
            //h.printHeap();
        }
    }

    @Test
    public void test4() {
        int N = 1000;
        Vector<Integer> v = new Vector<>();

        for (int i = 0; i < N; ++i) {
            if (i % 2 == 0) {
                v.pushBack(Utils.RAND.nextInt());
            } else {
                v.pushBack(-Utils.RAND.nextInt());
            }
        }

        MaxPQ<Integer> heap = new MaxPQ<>();
        heap.heapify(v);

        int last = heap.getMax();
        while (!heap.isEmpty()) {
            int del = heap.deleteMax();
            Assert.assertTrue(del <= last);
            last = del;
        }
    }
}
