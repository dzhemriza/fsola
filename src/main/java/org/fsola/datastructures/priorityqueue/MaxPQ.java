/*
 * org.fsola
 *
 * File Name: MaxPQ.java
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
package org.fsola.datastructures.priorityqueue;

/**
 * Implementation of max priority queue using heap.
 * <p/>
 * Used Resources: README file resources section line 1, line 57, line 61.
 */
public class MaxPQ<V extends Comparable<V>> extends HeapPQBase<V> {

    @Override
    protected boolean comp(V lhs, V rhs) {
        return isGreater(lhs, rhs);
    }

    /**
     * Return top element from priority queue.
     *
     * @return
     */
    public V getMax() {
        return getTop();
    }

    /**
     * Delete min element from priority queue.
     *
     * @return
     */
    public V deleteMax() {
        return deleteTop();
    }
}
