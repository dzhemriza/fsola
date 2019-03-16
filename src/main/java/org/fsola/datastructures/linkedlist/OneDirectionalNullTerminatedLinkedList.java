/*
 * org.fsola
 *
 * File Name: OneDirectionalNullTerminatedLinkedList.java
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
package org.fsola.datastructures.linkedlist;

import java.util.Iterator;

/**
 * Full implementation of simple one directional null terminated linked list.
 *
 * @param <T>
 */
public class OneDirectionalNullTerminatedLinkedList<T> implements Iterable<T> {

    /**
     * Data structure that holds linked position chain.
     *
     * @param <T>
     */
    public static class Node<T> {

        private T data;
        private Node<T> next;

        public Node(T data, Node<T> next) {
            this.data = data;
            this.next = next;
        }

        public T getData() {
            return this.data;
        }

        public boolean hasNext() {
            return (this.next != null);
        }

        public Node<T> getNextNode() {
            return this.next;
        }
    }

    /**
     * Iterator used to iterate through linked position.
     *
     * @param <T>
     */
    private static class LinkedListIteratorImpl<T> implements Iterator<T> {

        private Node<T> position;

        public LinkedListIteratorImpl(Node<T> list) {
            this.position = list;
        }

        @Override
        public boolean hasNext() {
            return (position != null);
        }

        @Override
        public T next() {
            if (null == position) {
                throw new IllegalStateException("List is empty!");
            }
            T result = position.data;
            position = position.next;

            return result;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException(
                    "Remove is not supported in iterator. " +
                            "Use deleteAfter method in class.");
        }
    }

    private Node<T> head = null;

    public boolean isEmpty() {
        return (head == null);
    }

    /**
     * Add new element in the beginning of the position.
     *
     * @param data
     * @return
     */
    public Node<T> push(T data) {
        Node<T> newItem = new Node<>(data, head);
        head = newItem;
        return newItem;
    }

    /**
     * Remove first element in position and return it's value.
     *
     * @return {@code null} if position is empty
     */
    public T pop() {
        T result = null;

        if (head != null) {
            Node<T> newHead = head.next;
            result = head.data;
            head.next = null; // To remove existing references
            head = newHead;
        }

        return result;
    }

    /**
     * Insert new element after current node.
     *
     * @param node
     * @param data
     * @return
     */
    public Node<T> insertAfter(Node<T> node, T data) {
        if (node == null) {
            throw new IllegalArgumentException("node parameter is null");
        }
        Node<T> newNode = new Node<>(data, node.next);
        node.next = newNode;
        return newNode;
    }

    /**
     * Insert new element before given node.
     *
     * @param node
     * @param data
     * @return
     */
    public Node<T> insertBefore(Node<T> node, T data) {
        Node<T> newNode = insertAfter(node, data);
        // Swap data between 2 nodes
        T temp = newNode.data;
        newNode.data = node.data;
        node.data = temp;
        return node;
    }

    /**
     * Reverse linked list
     */
    public void reverse() {
        Node<T> tmp = null;
        for (Node<T> i = head; i != null; ) {
            Node<T> c = i;
            i = i.next;
            c.next = tmp;
            tmp = c;
        }
        head = tmp;
    }

    /**
     * Delete element after this node
     *
     * @param node
     * @return
     */
    public T deleteAfter(Node<T> node) {
        if (node.next == null) {
            throw new IllegalArgumentException(
                    "Unable to delete element after end of list");
        }

        Node<T> nextNode = node.next;
        node.next = nextNode.next;
        nextNode.next = null; // Remove reference

        return nextNode.data;
    }

    /**
     * @return First element in linked list
     */
    public Node<T> getHead() {
        return head;
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIteratorImpl(head);
    }

    /**
     * Clear all elements in position
     */
    public void clear() {
        head = null;
    }
}
