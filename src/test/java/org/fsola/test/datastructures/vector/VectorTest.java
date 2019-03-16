/*
 * org.fsola
 *
 * File Name: VectorTest.java
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
package org.fsola.test.datastructures.vector;

import org.fsola.datastructures.vector.Vector;
import org.junit.Assert;
import org.junit.Test;

public class VectorTest {

    @Test
    public void test1() {
        Vector<Integer> v = new Vector<>();
        Assert.assertEquals(0, v.size());
        Assert.assertTrue(v.isEmpty());
        v.pushBack(1);
        Assert.assertEquals(1, v.size());
        Assert.assertFalse(v.isEmpty());
    }

    @Test
    public void test2() {
        Vector<Integer> v = new Vector<>();
        Assert.assertEquals(0, v.size());

        for (int i = 1; i <= 10000; ++i) {
            v.pushBack(i);
            Assert.assertEquals(i, v.size());
        }

        for (int i = 1, j = 0; i <= 10000; ++i, ++j) {
            Assert.assertEquals(Integer.valueOf(i), v.get(j));
        }

        System.out.println("Allocated memory: " + v.allocatedArraySize());
        v.reduceAllocatedArraySize();
        System.out.println("Allocated memory: " + v.allocatedArraySize());

        v.clear();
        Assert.assertEquals(0, v.size());
        System.out.println("Allocated memory: " + v.allocatedArraySize());
    }

    @Test
    public void test3() {
        Vector<Integer> v = new Vector<>();
        Assert.assertEquals(0, v.size());

        for (int i = 10; i > 0; --i) {
            v.insertAt(0, i);
        }

        for (int i = 1, j = 0; i <= 10; ++i, ++j) {
            Assert.assertEquals(Integer.valueOf(i), v.get(j));
        }
    }

    @Test
    public void test4() {
        Vector<Integer> v = new Vector<>();
        Assert.assertEquals(0, v.size());

        for (int i = 10; i > 0; --i) {
            v.pushFront(i);
        }

        for (int i = 1, j = 0; i <= 10; ++i, ++j) {
            Assert.assertEquals(Integer.valueOf(i), v.get(j));
        }
    }

    @Test
    public void test5() {
        Vector<Integer> v = new Vector<>();
        Assert.assertEquals(0, v.size());

        for (int i = 10; i > 0; --i) {
            v.pushFront(i);
        }

        for (int i = 0; i < 10; ++i) {
            for (int j = 0; j < 10 - i; ++j) {
                Assert.assertEquals(Integer.valueOf(j + 1), v.get(j));
            }
            v.popBack();
        }
    }

    @Test
    public void test6() {
        Vector<Integer> v = new Vector<>();
        Assert.assertEquals(0, v.size());

        for (int i = 10; i > 0; --i) {
            v.pushFront(i);
        }

        for (int i = 0; i < 10; ++i) {
            for (int j = 0; j < 10 - i; ++j) {
                Assert.assertEquals(Integer.valueOf(j + 1), v.get(j));
            }
            v.deleteAt(v.size() - 1);
        }
    }

    @Test
    public void test7() {
        Vector<Integer> v = new Vector<>();
        Assert.assertEquals(0, v.size());

        for (int i = 10; i > 0; --i) {
            v.pushFront(i);
        }

        for (int i = 0; i < 10; ++i) {
            for (int j = i, k = 0; j < 10; ++j, ++k) {
                Assert.assertEquals(Integer.valueOf(j + 1), v.get(k));
            }
            v.deleteAt(0);
        }
    }

    @Test
    public void test8() {
        Vector<Integer> v = new Vector<>();
        Assert.assertEquals(0, v.size());

        for (int i = 10; i > 0; --i) {
            v.pushFront(i);
        }

        for (int i = 0; i < 10; ++i) {
            for (int j = i, k = 0; j < 10; ++j, ++k) {
                Assert.assertEquals(Integer.valueOf(j + 1), v.get(k));
            }
            v.popFront();
        }
    }

    @Test
    public void test9() {
        Vector<Integer> v = new Vector<>();
        Assert.assertEquals(0, v.size());

        for (int i = 10; i > 0; --i) {
            v.pushFront(i);
        }

        for (int i = 1, j = 0; i <= 10; ++i, ++j) {
            Assert.assertEquals(Integer.valueOf(i), v.get(j));
        }

        v.reverse();

        for (int i = 10, j = 0; i > 0; --i, ++j) {
            Assert.assertEquals(Integer.valueOf(i), v.get(j));
        }
    }

    @Test
    public void test10() {
        Vector<Integer> v = new Vector<>();
        Assert.assertEquals(0, v.size());
        Assert.assertTrue(v.isEmpty());
        v.pushBack(1);
        Assert.assertEquals(1, v.size());
        Assert.assertFalse(v.isEmpty());
        Assert.assertEquals(Integer.valueOf(1), v.get(0));
        v.set(0, 2);
        Assert.assertEquals(Integer.valueOf(2), v.get(0));
    }
}
