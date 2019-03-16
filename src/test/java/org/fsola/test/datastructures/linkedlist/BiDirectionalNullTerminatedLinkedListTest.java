/*
 * org.fsola
 *
 * File Name: BiDirectionalNullTerminatedLinkedListTest.java
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

import org.fsola.datastructures.linkedlist.BiDirectionalNullTerminatedLinkedList;
import org.junit.Assert;
import org.junit.Test;

import static org.fsola.datastructures.linkedlist.BiDirectionalNullTerminatedLinkedList.Node;

public class BiDirectionalNullTerminatedLinkedListTest {

    @Test
    public void testList1() {
        BiDirectionalNullTerminatedLinkedList<Integer> list =
                new BiDirectionalNullTerminatedLinkedList<>();
        Assert.assertTrue(list.isEmpty());
        Assert.assertNull(list.getHead());
        list.push(1);
        list.push(2);
        list.push(3);
        list.push(4);
        list.push(5);
        Assert.assertNotNull(list.getHead());
        Assert.assertFalse(list.isEmpty());

        int pos = 5;
        int count = 0;
        for (Node<Integer> i = list.getHead(); i != null; i = i.getNext()) {
            Assert.assertEquals(Integer.valueOf(pos--), i.getData());
            count++;
        }
        Assert.assertEquals(5, count);

        // Find last element
        Node<Integer> last = list.getHead();
        while (last.hasNext())
            last = last.getNext();
        Assert.assertEquals(Integer.valueOf(1), last.getData());

        pos = 1;
        count = 0;
        for (; last != null; last = last.getPrev()) {
            Assert.assertEquals(Integer.valueOf(pos++), last.getData());
            count++;
        }
        Assert.assertEquals(5, count);
    }

    @Test
    public void testList2() {
        BiDirectionalNullTerminatedLinkedList<Integer> list =
                new BiDirectionalNullTerminatedLinkedList<>();
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
        BiDirectionalNullTerminatedLinkedList<Integer> list =
                new BiDirectionalNullTerminatedLinkedList<>();
        list.push(1);
        list.push(2);
        list.push(3);
        list.push(4);
        list.push(5);

        int pos = 5;
        while (!list.isEmpty()) {
            Assert.assertEquals(Integer.valueOf(pos--),
                    list.delete(list.getHead()));
        }
    }

    @Test
    public void testList4() {
        BiDirectionalNullTerminatedLinkedList<Integer> list =
                new BiDirectionalNullTerminatedLinkedList<>();
        list.push(1);
        list.push(2);
        list.push(3);
        list.push(4);
        list.push(5);

        Assert.assertEquals(Integer.valueOf(3),
                list.delete(list.getHead().getNext().getNext()));
        int[] e = {1, 2, 4, 5};
        int pos = 3;
        int count = 0;
        for (Node<Integer> i = list.getHead(); i != null; i = i.getNext()) {
            Assert.assertEquals(Integer.valueOf(e[pos--]), i.getData());
            count++;
        }
        Assert.assertEquals(4, count);

        // Find last element
        Node<Integer> last = list.getHead();
        while (last.hasNext())
            last = last.getNext();
        Assert.assertEquals(Integer.valueOf(1), last.getData());

        pos = 0;
        count = 0;
        for (; last != null; last = last.getPrev()) {
            Assert.assertEquals(Integer.valueOf(e[pos++]), last.getData());
            count++;
        }
        Assert.assertEquals(4, count);
    }

    @Test
    public void testList5() {
        BiDirectionalNullTerminatedLinkedList<Integer> list =
                new BiDirectionalNullTerminatedLinkedList<>();
        list.push(1);
        list.push(2);
        list.push(4);
        list.push(5);

        list.insertAfter(list.getHead().getNext(), 3);
        int pos = 5;
        int count = 0;
        for (Node<Integer> i = list.getHead(); i != null; i = i.getNext()) {
            Assert.assertEquals(Integer.valueOf(pos--), i.getData());
            count++;
        }
        Assert.assertEquals(5, count);

        // Find last element
        Node<Integer> last = list.getHead();
        while (last.hasNext())
            last = last.getNext();
        Assert.assertEquals(Integer.valueOf(1), last.getData());

        pos = 1;
        count = 0;
        for (; last != null; last = last.getPrev()) {
            Assert.assertEquals(Integer.valueOf(pos++), last.getData());
            count++;
        }
        Assert.assertEquals(5, count);
    }

    @Test
    public void testList6() {
        BiDirectionalNullTerminatedLinkedList<Integer> list =
                new BiDirectionalNullTerminatedLinkedList<>();
        list.push(1);
        list.push(2);
        list.push(4);
        list.push(5);

        list.insertBefore(list.getHead().getNext().getNext(), 3);
        int pos = 5;
        int count = 0;
        for (Node<Integer> i = list.getHead(); i != null; i = i.getNext()) {
            Assert.assertEquals(Integer.valueOf(pos--), i.getData());
            count++;
        }
        Assert.assertEquals(5, count);

        // Find last element
        Node<Integer> last = list.getHead();
        while (last.hasNext())
            last = last.getNext();
        Assert.assertEquals(Integer.valueOf(1), last.getData());

        pos = 1;
        count = 0;
        for (; last != null; last = last.getPrev()) {
            Assert.assertEquals(Integer.valueOf(pos++), last.getData());
            count++;
        }
        Assert.assertEquals(5, count);
    }

    @Test
    public void testList7() {
        BiDirectionalNullTerminatedLinkedList<Integer> list =
                new BiDirectionalNullTerminatedLinkedList<>();
        list.push(1);
        list.push(2);
        list.push(3);
        list.push(4);
        list.push(5);

        list.reverse();

        int count = 0;
        int pos = 1;
        for (Node<Integer> i = list.getHead(); i != null; i = i.getNext()) {
            Assert.assertEquals(Integer.valueOf(pos++), i.getData());
            ++count;
        }
        Assert.assertEquals(5, count);

        // Find last element
        Node<Integer> last = list.getHead();
        while (last.hasNext())
            last = last.getNext();

        count = 0;
        pos = 5;
        for (Node<Integer> i = last; i != null; i = i.getPrev()) {
            Assert.assertEquals(Integer.valueOf(pos--), i.getData());
            ++count;
        }
        Assert.assertEquals(5, count);
    }
}
