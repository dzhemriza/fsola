/*
 * org.fsola
 *
 * File Name: BiDirectionalNullTerminatedTailedLinkedList.java
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
 * Bidirectional null terminated tailed linked list implementation
 *
 * @param <T>
 */
public class BiDirectionalNullTerminatedTailedLinkedList<T> {
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

        public Node<T> getNext() {
            return next;
        }

        public Node<T> getPrev() {
            return prev;
        }
    }

    private Node<T> head;
    private Node<T> tail;
    private int count;

    public BiDirectionalNullTerminatedTailedLinkedList() {
        head = new Node<>(null, null, null);
        tail = new Node<>(null, null, null);

        clear();

        count = 0;
    }

    /**
     * Deletes the content of the list
     */
    public void clear() {
        head.next = tail;
        head.prev = null;
        tail.next = null;
        tail.prev = head;
    }

    /**
     * Add new item in the begging of the list
     *
     * @param data
     */
    public void push(T data) {
        linkAfter(head, data);
    }

    /**
     * Add new element in the end of the list
     *
     * @param data
     */
    public void put(T data) {
        linkAfter(tail.prev, data);
    }

    /**
     * Remove first element from list and return it's value
     *
     * @return
     */
    public T pop() {
        T result = null;

        if (!isEmpty()) {
            Node<T> node = unlink(head.next);
            result = node.getData();
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

        if (node == head || node == tail) {
            throw new IllegalStateException(
                    "Tail or Head element deletion is forbidden!");
        }

        unlink(node);

        return result;
    }

    /**
     * Insert new node after current one.
     *
     * @param node
     * @param data
     */
    public Node<T> insertAfter(Node<T> node, T data) {
        if (node == tail) {
            throw new IllegalStateException(
                    "Adding new element after tail is forbidden!");
        }

        Node<T> newNode = linkAfter(node, data);

        return newNode;
    }

    /**
     * Add new node before current one.
     *
     * @param node
     * @param data
     * @return
     */
    public Node<T> insertBefore(Node<T> node, T data) {
        if (node == head) {
            throw new IllegalStateException(
                    "Adding new element before head is forbidden!");
        }

        Node<T> newNode = linkAfter(node.prev, data);

        return newNode;
    }

    /**
     * Reverse linked list order
     */
    public void reverse() {
        if (isEmpty()) {
            return;
        }

        // We have only one element no need to reverse
        if (head.next.next == tail) {
            return;
        }

        Node<T> left = head.next;
        Node<T> right = tail.prev;

        while (left != right) {
            // Swap data between nodes
            T tmp = left.data;
            left.data = right.data;
            right.data = tmp;

            if (left.next == right) {
                // This is in case when we have even number of nodes between
                // head and tail
                break;
            }

            left = left.next;
            right = right.prev;
        }
    }

    /**
     * Concatenate contents of a given list in this list and destroys other
     * list.
     *
     * @param list
     */
    public void destructiveAdd(
            BiDirectionalNullTerminatedTailedLinkedList<T> list) {
        if (list.isEmpty()) {
            return;
        }

        if (isEmpty()) {
            head.next = list.head.next;
            list.head.next.prev = head;

            tail.prev = list.tail.prev;
            list.tail.prev.next = tail;
        } else {
            tail.prev.next = list.head.next;
            list.head.next.prev = tail.prev;

            tail.prev = list.tail.prev;
            list.tail.prev.next = tail;
        }

        list.clear();
    }

    /**
     * Unlink current node
     *
     * @param node
     * @return
     */
    private Node<T> unlink(Node<T> node) {
        node.next.prev = node.prev;
        node.prev.next = node.next;

        node.prev = null;
        node.next = null;

        --count;

        return node;
    }

    /**
     * Creates new node after provided one.
     *
     * @param node
     * @return
     */
    private Node<T> linkAfter(Node<T> node, T data) {
        Node<T> newNode = new Node<>(node, node.next, data);

        node.next.prev = newNode;
        node.next = newNode;

        ++count;

        return newNode;
    }

    /**
     * @return {@code true} if list is empty
     */
    public boolean isEmpty() {
        return (head.next == tail);
    }

    /**
     * @return Count of the elements in linked list
     */
    public int getCount() {
        return count;
    }

    /**
     * @return Head node
     */
    public Node<T> getHead() {
        return head;
    }

    /**
     * @return Tail node
     */
    public Node<T> getTail() {
        return tail;
    }
}
