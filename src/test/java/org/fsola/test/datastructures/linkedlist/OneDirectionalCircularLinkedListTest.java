/*
 * org.fsola
 *
 * File Name: OneDirectionalCircularLinkedListTest.java
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
package org.fsola.test.datastructures.linkedlist;

import org.fsola.datastructures.linkedlist.OneDirectionalCircularLinkedList;
import org.junit.Assert;
import org.junit.Test;

import static org.fsola.datastructures.linkedlist.OneDirectionalCircularLinkedList.Node;

public class OneDirectionalCircularLinkedListTest {

    @Test
    public void testList1() {
        OneDirectionalCircularLinkedList<Integer> list =
                new OneDirectionalCircularLinkedList<>();
        Assert.assertTrue(list.isEmpty());
        list.push(1);
        list.push(2);
        list.push(3);
        list.push(4);
        list.push(5);
        Assert.assertFalse(list.isEmpty());
    }

    @Test
    public void testList2() {
        OneDirectionalCircularLinkedList<Integer> list =
                new OneDirectionalCircularLinkedList<>();
        list.push(1);
        list.push(2);
        list.push(3);
        list.push(4);
        list.push(5);

        // Iterate though all elements in cyclic linked list
        int pos = 5;
        int count = 0;
        boolean firstTime = true;
        for (Node<Integer> i = list.getHead();
                (i != null) && (firstTime || i != list.getHead());
                i = i.getNext(), firstTime = false, ++count) {
            Assert.assertEquals(Integer.valueOf(pos--), i.getData());
        }
        Assert.assertEquals(5, count);
    }

    @Test
    public void testList3() {
        OneDirectionalCircularLinkedList<Integer> list =
                new OneDirectionalCircularLinkedList<>();
        list.push(1);
        list.push(2);
        list.push(3);
        list.push(4);
        list.push(5);

        int pos = 5;
        while (list.isEmpty()) {
            Assert.assertEquals(Integer.valueOf(pos--), list.pop());
        }
    }

    @Test
    public void testList4() {
        OneDirectionalCircularLinkedList<Integer> list =
                new OneDirectionalCircularLinkedList<>();
        list.push(1);
        list.push(2);
        list.push(3);
        list.push(4);
        list.push(5);

        Assert.assertEquals(Integer.valueOf(3),
                list.deleteAfter(list.getHead().getNext()));
        int[] e = new int[]{1, 2, 4, 5};
        int pos = 3;
        boolean firstTime = true;
        for (Node<Integer> i = list.getHead();
                (i != null) && (firstTime || i != list.getHead());
                i = i.getNext(), firstTime = false) {
            Assert.assertEquals(Integer.valueOf(e[pos--]), i.getData());
        }
    }

    @Test
    public void testList5() {
        OneDirectionalCircularLinkedList<Integer> list =
                new OneDirectionalCircularLinkedList<>();
        list.push(1);
        list.push(2);
        list.push(4);
        list.push(5);

        list.insertAfter(list.getHead().getNext(), 3);

        int pos = 5;
        boolean firstTime = true;
        for (Node<Integer> i = list.getHead();
                (i != null) && (firstTime || i != list.getHead());
                i = i.getNext(), firstTime = false) {
            Assert.assertEquals(Integer.valueOf(pos--), i.getData());
        }
    }

    @Test
    public void testList6() {
        OneDirectionalCircularLinkedList<Integer> list =
                new OneDirectionalCircularLinkedList<>();
        list.push(1);
        list.push(2);
        list.push(4);
        list.push(5);

        list.insertBefore(list.getHead().getNext().getNext(), 3);

        int pos = 5;
        boolean firstTime = true;
        for (Node<Integer> i = list.getHead();
                (i != null) && (firstTime || i != list.getHead());
                i = i.getNext(), firstTime = false) {
            Assert.assertEquals(Integer.valueOf(pos--), i.getData());
        }
    }

    @Test
    public void testList7() {
        OneDirectionalCircularLinkedList<Integer> list =
                new OneDirectionalCircularLinkedList<>();
        list.push(1);
        list.push(2);
        list.push(3);
        list.push(4);
        list.push(5);

        list.reverse();

        // Iterate though all elements in cyclic linked list
        int pos = 1;
        int count = 0;
        boolean firstTime = true;
        for (Node<Integer> i = list.getHead();
                (i != null) && (firstTime || i != list.getHead());
                i = i.getNext(), firstTime = false, ++count) {
            Assert.assertEquals(Integer.valueOf(pos++), i.getData());
        }
        Assert.assertEquals(5, count);
    }
}
