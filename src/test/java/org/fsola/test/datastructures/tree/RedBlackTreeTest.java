/*
 * org.fsola
 *
 * File Name: RedBlackTreeTest.java
 *
 * Copyright 2015 Dzhem Riza
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
package org.fsola.test.datastructures.tree;

import org.fsola.datastructures.tree.RedBlackTree;
import org.fsola.introduction.SimpleAritmetics;
import org.junit.Assert;
import org.junit.Test;

public class RedBlackTreeTest {

    @Test
    public void test1() {
        RedBlackTree<Integer, Integer> rb = new RedBlackTree();
        Assert.assertTrue(rb.isEmpty());

        for (int i = 0; i < 10; ++i) {
            rb.insert(i, i);
            rb.validateRbProperties();
        }

        Assert.assertFalse(rb.isEmpty());
        Assert.assertEquals(Integer.valueOf(0), rb.getMin());
        Assert.assertEquals(Integer.valueOf(9), rb.getMax());

        for (int i = 0; i < 10; ++i) {
            Assert.assertEquals(Integer.valueOf(i), rb.find(i));
        }

        // Print the whole tree
        rb.print();
    }

    @Test
    public void test2() {
        RedBlackTree<Integer, Integer> rb = new RedBlackTree();

        // Huge insert into tree
        int N = 2048;

        for (int i = 0; i < N; ++i) {
            rb.insert(i, i);
            rb.validateRbProperties();
        }

        Assert.assertEquals(Integer.valueOf(0), rb.getMin());
        Assert.assertEquals(Integer.valueOf(N - 1), rb.getMax());

        for (int i = 0; i < N; ++i) {
            Assert.assertEquals(Integer.valueOf(i), rb.find(i));
        }
    }

    @Test
    public void test3() {
        RedBlackTree<Integer, Integer> rb = new RedBlackTree();

        // Huge insert into tree
        int N = 2048;

        for (int i = N - 1; i >= 0; --i) {
            rb.insert(i, i);
            rb.validateRbProperties();
        }

        Assert.assertEquals(Integer.valueOf(0), rb.getMin());
        Assert.assertEquals(Integer.valueOf(N - 1), rb.getMax());

        for (int i = 0; i < N; ++i) {
            Assert.assertEquals(Integer.valueOf(i), rb.find(i));
        }
    }

    @Test
    public void test4() {
        RedBlackTree<Integer, Integer> rb = new RedBlackTree();

        // Huge insert into tree
        int N = 2048;
        int[] A = new int[N];

        for (int i = 0; i < A.length; ++i) {
            A[i] = i;
        }

        SimpleAritmetics.shuffleArray(A);

        for (int i = 0; i < A.length; ++i) {
            rb.insert(A[i], A[i]);
            rb.validateRbProperties();
        }

        Assert.assertEquals(Integer.valueOf(0), rb.getMin());
        Assert.assertEquals(Integer.valueOf(N - 1), rb.getMax());

        for (int i = 0; i < N; ++i) {
            Assert.assertEquals(Integer.valueOf(i), rb.find(i));
        }
    }

    @Test
    public void test5() {
        RedBlackTree<Integer, Integer> rb = new RedBlackTree();

        // Huge insert into tree
        int N = 2048;

        for (int i = 0; i < N; ++i) {
            rb.insert(i, i);
            rb.validateRbProperties();
        }

        for (int i = 0; i < N; ++i) {
            Assert.assertEquals(Integer.valueOf(i), rb.delete(i));
            Assert.assertNull(rb.find(Integer.valueOf(i)));
            rb.validateRbProperties();
        }
        Assert.assertTrue(rb.isEmpty());
    }

    @Test
    public void test6() {
        RedBlackTree<Integer, Integer> rb = new RedBlackTree();

        // Huge insert into tree
        int N = 2048;

        for (int i = 0; i < N; ++i) {
            rb.insert(i, i);
            rb.validateRbProperties();
        }

        for (int i = N - 1; i >= 0; --i) {
            Assert.assertEquals(Integer.valueOf(i), rb.delete(i));
            Assert.assertNull(rb.find(Integer.valueOf(i)));
            rb.validateRbProperties();
        }
        Assert.assertTrue(rb.isEmpty());
    }

    @Test
    public void test7() {
        RedBlackTree<Integer, Integer> rb = new RedBlackTree();

        // Huge insert into tree
        int N = 2048;
        int[] A = new int[N];

        for (int i = 0; i < A.length; ++i) {
            A[i] = i;
        }

        SimpleAritmetics.shuffleArray(A);

        for (int i = 0; i < A.length; ++i) {
            rb.insert(A[i], A[i]);
        }

        for (int i = 0; i < N; ++i) {
            Assert.assertEquals(Integer.valueOf(i), rb.delete(i));
            Assert.assertNull(rb.find(Integer.valueOf(i)));
            rb.validateRbProperties();
        }
        Assert.assertTrue(rb.isEmpty());
    }
}
