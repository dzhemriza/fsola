/*
 * org.fsola
 *
 * File Name: OneDirectionalNullTerminatedLinkedListTest.java
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

import org.fsola.datastructures.linkedlist.OneDirectionalNullTerminatedLinkedList;
import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;

import static org.fsola.datastructures.linkedlist.OneDirectionalNullTerminatedLinkedList.Node;

public class OneDirectionalNullTerminatedLinkedListTest {

    @Test
    public void testList1() {
        OneDirectionalNullTerminatedLinkedList<Integer> list =
                new OneDirectionalNullTerminatedLinkedList<>();

        Assert.assertNull(list.getHead());
        list.push(1);
        list.push(2);
        list.push(3);
        list.push(4);
        list.push(5);

        OneDirectionalNullTerminatedLinkedList.Node<Integer> head =
                list.getHead();
        Assert.assertNotNull(list.getHead());
        int pos = 5;
        for (; head.hasNext(); head = head.getNextNode()) {
            Assert.assertEquals(Integer.valueOf(pos--), head.getData());
        }
    }

    @Test
    public void testList2() {
        OneDirectionalNullTerminatedLinkedList<Integer> list =
                new OneDirectionalNullTerminatedLinkedList<>();
        list.push(1);
        list.push(2);
        list.push(3);
        list.push(4);
        list.push(5);

        int pos = 5;
        while (!list.isEmpty()) {
            Assert.assertEquals(Integer.valueOf(pos--), list.pop());
        }
    }

    @Test
    public void testList3() {
        OneDirectionalNullTerminatedLinkedList<Integer> list =
                new OneDirectionalNullTerminatedLinkedList<>();

        Assert.assertTrue(list.isEmpty());
        Assert.assertFalse(list.iterator().hasNext());
        try {
            list.iterator().next();
        } catch (IllegalStateException e) {
            // List is empty
        }

        list.push(1);
        list.push(2);
        list.push(3);
        list.push(4);
        list.push(5);
        Assert.assertFalse(list.isEmpty());
        Assert.assertTrue(list.iterator().hasNext());

        int i = 5;
        Iterator<Integer> it = list.iterator();
        while (it.hasNext()) {
            Integer item = it.next();
            Assert.assertEquals(Integer.valueOf(i--), item);
        }

        list.clear();
        Assert.assertTrue(list.isEmpty());
        Assert.assertFalse(list.iterator().hasNext());
        try {
            list.iterator().next();
        } catch (IllegalStateException e) {
            // List is empty
        }
    }

    @Test
    public void testList4() {
        OneDirectionalNullTerminatedLinkedList<Integer> list =
                new OneDirectionalNullTerminatedLinkedList<>();
        list.push(1);
        list.push(2);
        list.push(4);
        list.push(5);

        OneDirectionalNullTerminatedLinkedList.Node<Integer> node =
                list.getHead().getNextNode();
        list.insertAfter(node, 3);

        int pos = 5;
        for (OneDirectionalNullTerminatedLinkedList.Node<Integer> head =
                list.getHead(); head.hasNext(); head = head.getNextNode()) {
            Assert.assertEquals(Integer.valueOf(pos--), head.getData());
        }
    }

    @Test
    public void testList5() {
        OneDirectionalNullTerminatedLinkedList<Integer> list =
                new OneDirectionalNullTerminatedLinkedList<>();
        list.push(1);
        list.push(2);
        list.push(4);
        list.push(5);

        OneDirectionalNullTerminatedLinkedList.Node<Integer> node =
                list.getHead().getNextNode().getNextNode();
        list.insertBefore(node, 3);

        int pos = 5;
        for (Node<Integer> head = list.getHead(); head.hasNext();
                head = head.getNextNode()) {
            Assert.assertEquals(Integer.valueOf(pos--), head.getData());
        }
    }

    @Test
    public void testList6() {
        OneDirectionalNullTerminatedLinkedList<Integer> list =
                new OneDirectionalNullTerminatedLinkedList<>();
        list.push(1);
        list.push(2);
        list.push(3);
        list.push(4);
        list.push(5);
        list.reverse();

        int pos = 1;
        for (Node<Integer> head = list.getHead(); head.hasNext();
                head = head.getNextNode()) {
            Assert.assertEquals(Integer.valueOf(pos++), head.getData());
        }
    }

    @Test
    public void testList7() {
        OneDirectionalNullTerminatedLinkedList<Integer> list =
                new OneDirectionalNullTerminatedLinkedList<>();
        list.push(1);
        list.push(2);
        list.push(3);
        list.push(4);
        list.push(5);

        Assert.assertEquals(Integer.valueOf(1), list.deleteAfter(
                list.getHead().getNextNode().getNextNode().getNextNode()));
    }
}
