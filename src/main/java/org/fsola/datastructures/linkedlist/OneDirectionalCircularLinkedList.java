/*
 * org.fsola
 *
 * File Name: OneDirectionalCircularLinkedList.java
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
 * Just enough implementation of one directional circular linked list.
 *
 * @param <T>
 */
public class OneDirectionalCircularLinkedList<T> {

    /**
     * Data structure that holds linked position chain.
     *
     * @param <T>
     */
    public static class Node<T> {
        private Node<T> next;
        private T data;

        public Node(Node<T> next, T data) {
            this.next = next;
            this.data = data;
        }

        public T getData() {
            return data;
        }

        public Node<T> getNext() {
            return next;
        }
    }

    private Node<T> head = null;

    /**
     * Add new element in the beginning of the position.
     *
     * @param data
     */
    public void push(T data) {
        Node<T> newItem = new Node<>(head, data);
        head = newItem;
    }

    /**
     * Remove first element in position and return it's value.
     *
     * @return
     */
    public T pop() {
        T result = head.data;

        if (head != null) {
            if (head == head.next) {
                // Last element
                head.next = null;
                head = null;
            } else {
                Node<T> oldHead = head;
                head = head.next;
                oldHead.next = null;
            }
        }

        return result;
    }

    /**
     * Delete element after this node
     *
     * @param node
     * @return
     */
    public T deleteAfter(Node<T> node) {
        // Always have next element as this is cyclic linked list
        T result = node.next.data;

        if (node == node.next) {
            // This is the last element in list so delete head
            head.next = null;
            head = null;
        } else {
            Node<T> next = node.next;
            node.next = next.next;
            next.next = null;
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
        Node<T> newNode = new Node<>(node.next, data);
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
        T temp = node.data;
        node.data = newNode.data;
        newNode.data = temp;
        return node;
    }

    /**
     * Reverse list
     */
    public void reverse() {
        Node<T> tmp = null;
        Node<T> oldHead = head;
        for (Node<T> i = head; i != null; ) {
            Node<T> c = i;
            i = i.next;
            c.next = tmp;
            tmp = c;
        }
        // Create circular link
        if (oldHead != null) {
            oldHead.next = tmp;
        }
        head = tmp;
    }

    /**
     * @return {@code true} if list is empty
     */
    public boolean isEmpty() {
        return (null == head);
    }

    /**
     * @return Head element
     */
    public Node<T> getHead() {
        return head;
    }
}
