/*
 * org.fsola
 *
 * File Name: LinkedListStack.java
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
package org.fsola.datastructures.stack;

import org.fsola.datastructures.linkedlist.OneDirectionalNullTerminatedLinkedList;

/**
 * Stack implementation using linked list.
 *
 * @param <K>
 */
public class LinkedListStack<K> {
    private OneDirectionalNullTerminatedLinkedList<K> stack = new
            OneDirectionalNullTerminatedLinkedList<>();

    public void push(K item) {
        stack.push(item);
    }

    public K pop() {
        return stack.pop();
    }

    public K top() {
        K result = null;

        if (!stack.isEmpty()) {
            result = stack.getHead().getData();
        }

        return result;
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }
}
