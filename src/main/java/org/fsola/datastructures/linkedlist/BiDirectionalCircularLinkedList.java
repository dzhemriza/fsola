/*
 * org.fsola
 *
 * File Name: BiDirectionalCircularLinkedList.java
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
 * Bi directional circular linked list implementation
 *
 * @param <T>
 */
public class BiDirectionalCircularLinkedList<T> {

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

    private Node<T> head = null;

    /**
     * Add new element in the beginning of the position.
     *
     * @param data
     */
    public void push(T data) {
        head = linkBefore(head, data);
    }

    /**
     * Create and link new node before current one.
     *
     * @param node
     * @param data
     * @return
     */
    private Node<T> linkBefore(Node<T> node, T data) {
        Node<T> newItem = new Node<>(null, null, data);

        if (node == null) {
            newItem.prev = newItem;
            newItem.next = newItem;
        } else {
            newItem.next = node;
            newItem.prev = node.prev;
            node.prev.next = newItem;
            node.prev = newItem;
        }

        return newItem;
    }

    /**
     * Insert new element before given node.
     *
     * @param node
     * @param data
     * @return
     */
    public Node<T> insertBefore(Node<T> node, T data) {
        return linkBefore(node, data);
    }

    /**
     * Insert new element after given node.
     *
     * @param node
     * @param data
     * @return
     */
    public Node<T> insertAfter(Node<T> node, T data) {
        return linkBefore(node.next, data);
    }

    /**
     * Remove first element in position and return it's value.
     *
     * @return
     */
    public T pop() {
        T result = null;

        if (head != null) {
            result = head.data;
            head = unlink(head);
        }

        return result;
    }

    /**
     * Reverse linked list
     */
    public void reverse() {
        if (head != null) {
            if (head != head.next) {
                Node<T> tail = head.prev;

                Node<T> i = head;
                boolean firstTime = true;
                while (firstTime || i != head) {
                    firstTime = false;

                    Node<T> c = i;
                    Node<T> tmp = c.next;
                    c.next = c.prev;
                    c.prev = tmp;

                    i = i.getNext();
                }

                head = tail;
            }
        }
    }

    /**
     * Unlink current node from circular list.
     *
     * @param node
     * @return
     */
    private Node<T> unlink(Node<T> node) {
        Node<T> nextNode = null;

        if (node == node.next) {
            // Last element
            head.next = null;
            head.prev = null;
            head = null;
        } else {
            nextNode = node.next;
            nextNode.prev = node.prev;
            node.prev.next = nextNode;
            node.next = null;
            node.prev = null;
        }

        return nextNode;
    }

    /**
     * Delete node and return it's value
     *
     * @param node
     * @return
     */
    public T delete(Node<T> node) {
        T result = node.data;

        unlink(node);

        return result;
    }

    /**
     * Remove last element in circular list and return it's value.
     *
     * @return
     */
    public T get() {
        T result = null;

        if (head != null) {
            result = delete(head.getPrev());
        }

        return result;
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
