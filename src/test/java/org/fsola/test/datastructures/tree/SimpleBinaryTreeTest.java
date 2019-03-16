/*
 * org.fsola
 *
 * File Name: SimpleBinaryTreeTest.java
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
package org.fsola.test.datastructures.tree;

import org.fsola.datastructures.tree.SimpleBinaryTree;
import org.fsola.introduction.SimpleAritmetics;
import org.junit.Assert;
import org.junit.Test;

public class SimpleBinaryTreeTest {

    private static int[] TREE_KEYS = new int[]{
            6, 5, 10, 3, 1, 8, 13, 7, 11, 14, 12};
    private static int[] TREE_VALUES = new int[]{
            1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};

    @Test
    public void testBinaryTree1() {
        SimpleBinaryTree<Integer, Integer> tree = newTree();

        tree.print();
    }

    @Test
    public void testBinaryTree2() {
        SimpleBinaryTree<Integer, Integer> tree = newTree();

        Assert.assertEquals(Integer.valueOf(3), tree.search(3).getKey());
        Assert.assertNull(tree.search(11111));
    }

    @Test
    public void testBinaryTree3() {
        SimpleBinaryTree<Integer, Integer> tree = newTree();

        Assert.assertEquals(Integer.valueOf(10), tree.search(10).getKey());
        tree.deleteKey(10);
        Assert.assertNull(tree.search(10));
        tree.print();
    }

    @Test(expected = IllegalStateException.class)
    public void testBinaryTree4() {
        SimpleBinaryTree<Integer, Integer> tree = new SimpleBinaryTree<>();
        tree.insert(6, 2);
        tree.insert(6, 2);
    }

    @Test
    public void testBinaryTree5() {
        for (int i = 0; i < TREE_KEYS.length; ++i) {
            SimpleBinaryTree<Integer, Integer> tree = newTree();

            tree.deleteKey(TREE_KEYS[i]);
            Assert.assertNull(tree.search(TREE_KEYS[i]));

            for (int j = 0; j < TREE_KEYS.length; ++j) {
                if (i == j) {
                    continue;
                }

                Assert.assertNotNull(tree.search(TREE_KEYS[j]));
                Assert.assertEquals(Integer.valueOf(TREE_KEYS[j]),
                        tree.search(TREE_KEYS[j]).getKey());
                Assert.assertEquals(Integer.valueOf(TREE_VALUES[j]),
                        tree.search(TREE_KEYS[j]).getValue());
            }
        }
    }

    @Test
    public void testBinaryTree6() {
        int[] newArr = SimpleAritmetics.shuffleArray(TREE_KEYS);

        for (int i = 0; i < newArr.length; ++i) {
            SimpleBinaryTree<Integer, Integer> tree = newTree();

            tree.deleteKey(newArr[i]);
            Assert.assertNull(tree.search(newArr[i]));

            for (int j = 0; j < newArr.length; ++j) {
                if (i == j) {
                    continue;
                }

                Assert.assertNotNull(tree.search(newArr[j]));
                Assert.assertEquals(Integer.valueOf(newArr[j]),
                        tree.search(newArr[j]).getKey());
            }
        }
    }

    @Test
    public void testBinaryTree7() {
        SimpleBinaryTree<Integer, Integer> tree = newTree();

        int[] newArr = SimpleAritmetics.shuffleArray(TREE_KEYS);

        for (int i = 0; i < newArr.length; ++i) {
            tree.deleteKey(newArr[i]);
            Assert.assertNull(tree.search(newArr[i]));

            for (int j = i + 1; j < newArr.length; ++j) {

                Assert.assertNotNull(tree.search(newArr[j]));
                Assert.assertEquals(Integer.valueOf(newArr[j]),
                        tree.search(newArr[j]).getKey());
            }
        }
    }

    @Test
    public void testBinaryTree8() {
        SimpleBinaryTree<Integer, Integer> tree = newTree();

        for (int i = 0; i < TREE_KEYS.length; ++i) {
            tree.deleteKey(TREE_KEYS[i]);
            Assert.assertNull(tree.search(TREE_KEYS[i]));

            for (int j = i + 1; j < TREE_KEYS.length; ++j) {

                Assert.assertNotNull(tree.search(TREE_KEYS[j]));
                Assert.assertEquals(Integer.valueOf(TREE_KEYS[j]),
                        tree.search(TREE_KEYS[j]).getKey());
                Assert.assertEquals(Integer.valueOf(TREE_VALUES[j]),
                        tree.search(TREE_KEYS[j]).getValue());
            }
        }
    }

    @Test
    public void testKthSmallest() {
        SimpleBinaryTree<Integer, Integer> tree = new SimpleBinaryTree<>();
        int MAX = 10;

        for (int i = 1; i <= MAX; ++i) {
            tree.insert(i, i);
        }

        for (int i = 1; i <= MAX; ++i) {
            SimpleBinaryTree.TreeNode<Integer, Integer> n = tree.findKthSmallestElement(i);
            Assert.assertNotNull(n);
            Assert.assertEquals(Integer.valueOf(i), n.getKey());
        }

        Assert.assertNull(tree.findKthSmallestElement(0));
        Assert.assertNull(tree.findKthSmallestElement(10000));
    }

    /**
     * Creates new tree with predefined structure
     *
     * @return
     */
    private SimpleBinaryTree<Integer, Integer> newTree() {
        SimpleBinaryTree<Integer, Integer> tree = new SimpleBinaryTree<>();
        Assert.assertEquals(TREE_KEYS.length, TREE_VALUES.length);
        for (int i = 0; i < TREE_KEYS.length; ++i) {
            tree.insert(TREE_KEYS[i], TREE_VALUES[i]);
        }
        return tree;
    }
}
