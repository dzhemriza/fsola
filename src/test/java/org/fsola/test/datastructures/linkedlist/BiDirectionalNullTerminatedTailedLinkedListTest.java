/*
 * org.fsola
 *
 * File Name: BiDirectionalNullTerminatedTailedLinkedListTest.java
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

import org.fsola.datastructures.linkedlist.BiDirectionalNullTerminatedTailedLinkedList;
import org.junit.Assert;
import org.junit.Test;

import static org.fsola.datastructures.linkedlist.BiDirectionalNullTerminatedTailedLinkedList.Node;

public class BiDirectionalNullTerminatedTailedLinkedListTest {

    @Test
    public void testList1() {
        BiDirectionalNullTerminatedTailedLinkedList<Integer> list =
                new BiDirectionalNullTerminatedTailedLinkedList<>();
        Assert.assertTrue(list.isEmpty());
        list.push(1);
        list.push(2);
        list.push(3);
        list.push(4);
        list.push(5);
        Assert.assertFalse(list.isEmpty());

        int pos = 5;
        int count = 0;
        for (Node<Integer> i = list.getHead().getNext(); i != list.getTail();
             i = i.getNext()) {
            Assert.assertEquals(Integer.valueOf(pos--), i.getData());
            count++;
        }
        Assert.assertEquals(5, count);

        pos = 1;
        count = 0;
        for (Node<Integer> i = list.getTail().getPrev(); i != list.getHead();
             i = i.getPrev()) {
            Assert.assertEquals(Integer.valueOf(pos++), i.getData());
            count++;
        }
        Assert.assertEquals(5, count);
    }

    @Test
    public void testList2() {
        BiDirectionalNullTerminatedTailedLinkedList<Integer> list =
                new BiDirectionalNullTerminatedTailedLinkedList<>();
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
        BiDirectionalNullTerminatedTailedLinkedList<Integer> list =
                new BiDirectionalNullTerminatedTailedLinkedList<>();
        list.put(1);
        list.put(2);
        list.put(3);
        list.put(4);
        list.put(5);

        int pos = 1;
        int count = 0;
        for (Node<Integer> i = list.getHead().getNext(); i != list.getTail();
             i = i.getNext()) {
            Assert.assertEquals(Integer.valueOf(pos++), i.getData());
            count++;
        }
        Assert.assertEquals(5, count);
    }

    @Test
    public void testList4() {
        BiDirectionalNullTerminatedTailedLinkedList<Integer> list =
                new BiDirectionalNullTerminatedTailedLinkedList<>();
        list.put(1);
        list.put(2);
        list.put(3);
        list.put(4);
        list.put(5);

        Assert.assertEquals(Integer.valueOf(3),
                list.delete(list.getHead().getNext().getNext().getNext()));

        int[] e = new int[]{1, 2, 4, 5};
        int pos = 0;
        int count = 0;
        for (Node<Integer> i = list.getHead().getNext(); i != list.getTail();
             i = i.getNext()) {
            Assert.assertEquals(Integer.valueOf(e[pos++]), i.getData());
            count++;
        }
        Assert.assertEquals(4, count);
    }

    @Test
    public void testList5() {
        BiDirectionalNullTerminatedTailedLinkedList<Integer> list =
                new BiDirectionalNullTerminatedTailedLinkedList<>();
        list.put(1);
        list.put(2);
        list.put(4);
        list.put(5);

        list.insertAfter(list.getHead().getNext().getNext(), 3);

        int pos = 1;
        int count = 0;
        for (Node<Integer> i = list.getHead().getNext(); i != list.getTail();
             i = i.getNext()) {
            Assert.assertEquals(Integer.valueOf(pos++), i.getData());
            count++;
        }
        Assert.assertEquals(5, count);
    }

    @Test
    public void testList6() {
        BiDirectionalNullTerminatedTailedLinkedList<Integer> list =
                new BiDirectionalNullTerminatedTailedLinkedList<>();
        list.put(1);
        list.put(2);
        list.put(4);
        list.put(5);

        list.insertBefore(list.getHead().getNext().getNext().getNext(), 3);

        int pos = 1;
        int count = 0;
        for (Node<Integer> i = list.getHead().getNext(); i != list.getTail();
             i = i.getNext()) {
            Assert.assertEquals(Integer.valueOf(pos++), i.getData());
            count++;
        }
        Assert.assertEquals(5, count);
    }

    @Test
    public void testList7() {
        BiDirectionalNullTerminatedTailedLinkedList<Integer> list =
                new BiDirectionalNullTerminatedTailedLinkedList<>();
        list.put(1);
        list.put(2);
        list.put(3);
        list.put(4);
        list.put(5);

        list.reverse();

        int pos = 5;
        int count = 0;
        for (Node<Integer> i = list.getHead().getNext(); i != list.getTail();
             i = i.getNext()) {
            Assert.assertEquals(Integer.valueOf(pos--), i.getData());
            count++;
        }
        Assert.assertEquals(5, count);
    }

    @Test
    public void testList8() {
        BiDirectionalNullTerminatedTailedLinkedList<Integer> list =
                new BiDirectionalNullTerminatedTailedLinkedList<>();
        list.put(1);
        list.put(2);
        list.put(3);
        list.put(4);

        list.reverse();

        int pos = 4;
        int count = 0;
        for (Node<Integer> i = list.getHead().getNext(); i != list.getTail();
             i = i.getNext()) {
            Assert.assertEquals(Integer.valueOf(pos--), i.getData());
            count++;
        }
        Assert.assertEquals(4, count);
    }

    @Test
    public void testList9() {
        BiDirectionalNullTerminatedTailedLinkedList<Integer> list1 =
                new BiDirectionalNullTerminatedTailedLinkedList<>();
        list1.put(1);
        list1.put(2);
        list1.put(3);
        list1.put(4);
        list1.put(5);

        BiDirectionalNullTerminatedTailedLinkedList<Integer> list2 =
                new BiDirectionalNullTerminatedTailedLinkedList<>();
        list2.put(6);
        list2.put(7);
        list2.put(8);
        list2.put(9);
        list2.put(10);

        list1.destructiveAdd(list2);

        int pos = 1;
        int count = 0;
        for (Node<Integer> i = list1.getHead().getNext(); i != list1.getTail();
             i = i.getNext()) {
            Assert.assertEquals(Integer.valueOf(pos++), i.getData());
            count++;
        }
        Assert.assertEquals(10, count);

        pos = 10;
        count = 0;
        for (Node<Integer> i = list1.getTail().getPrev(); i != list1.getHead();
             i = i.getPrev()) {
            Assert.assertEquals(Integer.valueOf(pos--), i.getData());
            count++;
        }
        Assert.assertEquals(10, count);
    }

    @Test
    public void testList10() {
        BiDirectionalNullTerminatedTailedLinkedList<Integer> list1 =
                new BiDirectionalNullTerminatedTailedLinkedList<>();
        list1.put(1);
        list1.put(2);
        list1.put(3);
        list1.put(4);
        list1.put(5);

        BiDirectionalNullTerminatedTailedLinkedList<Integer> list2 =
                new BiDirectionalNullTerminatedTailedLinkedList<>();

        list1.destructiveAdd(list2);

        int pos = 1;
        int count = 0;
        for (Node<Integer> i = list1.getHead().getNext(); i != list1.getTail();
             i = i.getNext()) {
            Assert.assertEquals(Integer.valueOf(pos++), i.getData());
            count++;
        }
        Assert.assertEquals(5, count);

        pos = 5;
        count = 0;
        for (Node<Integer> i = list1.getTail().getPrev(); i != list1.getHead();
             i = i.getPrev()) {
            Assert.assertEquals(Integer.valueOf(pos--), i.getData());
            count++;
        }
        Assert.assertEquals(5, count);
    }

    @Test
    public void testList11() {
        BiDirectionalNullTerminatedTailedLinkedList<Integer> list1 =
                new BiDirectionalNullTerminatedTailedLinkedList<>();

        BiDirectionalNullTerminatedTailedLinkedList<Integer> list2 =
                new BiDirectionalNullTerminatedTailedLinkedList<>();
        list2.put(1);
        list2.put(2);
        list2.put(3);
        list2.put(4);
        list2.put(5);

        list1.destructiveAdd(list2);

        int pos = 1;
        int count = 0;
        for (Node<Integer> i = list1.getHead().getNext(); i != list1.getTail();
             i = i.getNext()) {
            Assert.assertEquals(Integer.valueOf(pos++), i.getData());
            count++;
        }
        Assert.assertEquals(5, count);

        pos = 5;
        count = 0;
        for (Node<Integer> i = list1.getTail().getPrev(); i != list1.getHead();
             i = i.getPrev()) {
            Assert.assertEquals(Integer.valueOf(pos--), i.getData());
            count++;
        }
        Assert.assertEquals(5, count);
    }
}
