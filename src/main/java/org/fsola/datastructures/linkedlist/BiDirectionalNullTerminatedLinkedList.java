/*
 * org.fsola
 *
 * File Name: BiDirectionalNullTerminatedLinkedList.java
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

/**
 * Just enough implementation of bidirectional null terminated linked list.
 *
 * @param <T>
 */
public class BiDirectionalNullTerminatedLinkedList<T> {

    /**
     * Data structure that holds linked position chain.
     *
     * @param <T>
     */
    public static class Node<T> {
        private Node<T> prev;
        private Node<T> next;
        private T data;

        public Node(Node<T> prev, Node<T> next, T data) {
            this.prev = prev;
            this.next = next;
            this.data = data;
        }

        public T getData() {
            return data;
        }

        public Node<T> getPrev() {
            return prev;
        }

        public Node<T> getNext() {
            return next;
        }

        public boolean hasNext() {
            return (next != null);
        }

        public boolean hasPrev() {
            return (prev != null);
        }
    }

    /**
     * Linked list head element
     */
    private Node<T> head = null;

    /**
     * Add new element at the beginning of the list
     *
     * @param data
     * @return
     */
    public Node<T> push(T data) {
        Node<T> newItem = new Node<>(null, head, data);

        if (head != null) {
            head.prev = newItem;
        }
        head = newItem;

        return newItem;
    }

    /**
     * Clear all elements in the list.
     */
    public void clear() {
        head = null;
    }

    /**
     * @return First element in linked list. {@code null} if list is empty.
     */
    public T pop() {
        T result = null;

        if (head != null) {
            result = head.data;
            Node<T> newHead = head.next;

            head.next = null;
            if (newHead != null) {
                newHead.prev = null;
            }

            head = newHead;
        }

        return result;
    }

    /**
     * Deletes current node
     *
     * @param node
     * @return
     */
    public T delete(Node<T> node) {
        T result = node.data;
        Node<T> prev = node.prev;
        Node<T> next = node.next;

        node.prev = null;
        node.next = null;

        if (prev != null) {
            prev.next = next;
        }

        if (next != null) {
            next.prev = prev;
        }

        if (node == head) {
            head = next;
        }

        return result;
    }

    /**
     * Add new element after current node
     *
     * @param node
     * @param data
     * @return
     */
    public Node<T> insertAfter(Node<T> node, T data) {
        Node<T> newItem = new Node<>(node, node.next, data);

        if (node.next != null) {
            node.next.prev = newItem;
        }
        node.next = newItem;

        return newItem;
    }

    /**
     * Add new element before current node
     *
     * @param node
     * @param data
     * @return
     */
    public Node<T> insertBefore(Node<T> node, T data) {
        Node<T> newItem = new Node<>(node.prev, node, data);

        if (node.prev != null) {
            node.prev.next = newItem;
        }
        node.prev = newItem;

        return newItem;
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
            c.prev = null;
            if (tmp != null) {
                tmp.prev = c;
            }
            tmp = c;
        }
        head = tmp;
    }

    /**
     * @return {@code true} if list is empty
     */
    public boolean isEmpty() {
        return (head == null);
    }

    /**
     * @return Head element
     */
    public Node<T> getHead() {
        return head;
    }
}
