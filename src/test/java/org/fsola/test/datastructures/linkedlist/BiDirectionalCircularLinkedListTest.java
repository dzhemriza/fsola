/*
 * org.fsola
 *
 * File Name: BiDirectionalCircularLinkedListTest.java
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

import org.fsola.datastructures.linkedlist.BiDirectionalCircularLinkedList;
import org.junit.Assert;
import org.junit.Test;

import static org.fsola.datastructures.linkedlist.BiDirectionalCircularLinkedList.Node;

public class BiDirectionalCircularLinkedListTest {

    @Test
    public void testList1() {
        BiDirectionalCircularLinkedList<Integer> list =
                new BiDirectionalCircularLinkedList<>();
        Assert.assertTrue(list.isEmpty());
        list.push(1);
        list.push(2);
        list.push(3);
        list.push(4);
        list.push(5);
        Assert.assertFalse(list.isEmpty());

        int count = 0;
        int pos = 5;
        boolean firstTime = true;
        for (Node<Integer> i = list.getHead();
                (i != null) && (firstTime || i != list.getHead());
                i = i.getNext(), firstTime = false) {
            Assert.assertEquals(Integer.valueOf(pos--), i.getData());
            ++count;
        }
        Assert.assertEquals(5, count);

        count = 0;
        pos = 0;
        int[] e = new int[]{5, 1, 2, 3, 4};
        firstTime = true;
        for (Node<Integer> i = list.getHead();
                (i != null) && (firstTime || i != list.getHead());
                i = i.getPrev(), firstTime = false) {
            Assert.assertEquals(Integer.valueOf(e[pos++]), i.getData());
            ++count;
        }
        Assert.assertEquals(5, count);
    }

    @Test
    public void testList2() {
        BiDirectionalCircularLinkedList<Integer> list =
                new BiDirectionalCircularLinkedList<>();
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
    public void testList3() {
        BiDirectionalCircularLinkedList<Integer> list =
                new BiDirectionalCircularLinkedList<>();
        list.push(1);
        list.push(2);
        list.push(3);
        list.push(4);
        list.push(5);

        int pos = 5;
        while (list.isEmpty()) {
            Assert.assertEquals(Integer.valueOf(pos--),
                    list.delete(list.getHead()));
        }
    }

    @Test
    public void testList4() {
        BiDirectionalCircularLinkedList<Integer> list =
                new BiDirectionalCircularLinkedList<>();
        list.push(1);
        list.push(2);
        list.push(3);
        list.push(4);
        list.push(5);

        int pos = 1;
        while (list.isEmpty()) {
            Assert.assertEquals(Integer.valueOf(pos++), list.get());
        }
    }

    @Test
    public void testList5() {
        BiDirectionalCircularLinkedList<Integer> list =
                new BiDirectionalCircularLinkedList<>();
        list.push(1);
        list.push(2);
        list.push(4);
        list.push(5);

        list.insertBefore(list.getHead().getNext().getNext(), 3);

        int count = 0;
        int pos = 5;
        boolean firstTime = true;
        for (Node<Integer> i = list.getHead();
                (i != null) && (firstTime || i != list.getHead());
                i = i.getNext(), firstTime = false) {
            Assert.assertEquals(Integer.valueOf(pos--), i.getData());
            ++count;
        }
        Assert.assertEquals(5, count);
    }

    @Test
    public void testList6() {
        BiDirectionalCircularLinkedList<Integer> list =
                new BiDirectionalCircularLinkedList<>();
        list.push(1);
        list.push(2);
        list.push(4);
        list.push(5);

        list.insertAfter(list.getHead().getNext(), 3);

        int count = 0;
        int pos = 5;
        boolean firstTime = true;
        for (Node<Integer> i = list.getHead();
                (i != null) && (firstTime || i != list.getHead());
                i = i.getNext(), firstTime = false) {
            Assert.assertEquals(Integer.valueOf(pos--), i.getData());
            ++count;
        }
        Assert.assertEquals(5, count);
    }

    @Test
    public void testList7() {
        BiDirectionalCircularLinkedList<Integer> list =
                new BiDirectionalCircularLinkedList<>();
        list.push(1);
        list.push(2);
        list.push(3);
        list.push(4);
        list.push(5);

        list.reverse();

        int count = 0;
        int pos = 1;
        boolean firstTime = true;
        for (Node<Integer> i = list.getHead();
                (i != null) && (firstTime || i != list.getHead());
                i = i.getNext(), firstTime = false) {
            Assert.assertEquals(Integer.valueOf(pos++), i.getData());
            ++count;
        }
        Assert.assertEquals(5, count);

        count = 0;
        pos = 0;
        int[] e = new int[]{1, 5, 4, 3, 2};
        firstTime = true;
        for (Node<Integer> i = list.getHead();
                (i != null) && (firstTime || i != list.getHead());
                i = i.getPrev(), firstTime = false) {
            Assert.assertEquals(Integer.valueOf(e[pos++]), i.getData());
            ++count;
        }
        Assert.assertEquals(5, count);
    }
}
