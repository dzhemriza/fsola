/*
 * org.fsola
 *
 * File Name: LinkedListQueue.java
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
package org.fsola.datastructures.queue;

import org.fsola.datastructures.linkedlist.BiDirectionalNullTerminatedTailedLinkedList;

/**
 * Queue implementation using linked list.
 *
 * @param <K>
 */
public class LinkedListQueue<K> {
    private BiDirectionalNullTerminatedTailedLinkedList<K> queue = new
            BiDirectionalNullTerminatedTailedLinkedList<>();

    public K peek() {
        K result = null;

        if (!queue.isEmpty()) {
            result = queue.getHead().getNext().getData();
        }

        return result;
    }

    public void put(K item) {
        queue.put(item);
    }

    public K get() {
        return queue.pop();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
}
