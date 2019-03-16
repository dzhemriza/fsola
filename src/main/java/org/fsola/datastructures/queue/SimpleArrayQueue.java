/*
 * org.fsola
 *
 * File Name: SimpleArrayQueue.java
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

/**
 * Simple array based implementation of queue
 * <p/>
 * Used Resources: README file resources section line 1
 *
 * @param <T>
 */
public class SimpleArrayQueue<T> {
    private static int MAX_N = 100;

    T[] queue = (T[]) new Object[MAX_N];

    private int front = 0;
    private int rear = 0;
    private boolean empty = true;

    public boolean isEmpty() {
        return empty;
    }

    /**
     * Put new element in queue
     *
     * @param e
     */
    public void put(T e) {
        if (front == rear && !empty) {
            throw new IllegalStateException("Queue is full");
        }

        queue[rear++] = e;
        if (rear >= queue.length) {
            rear = 0;
        }
        empty = false;
    }

    /**
     * Get first element from queue
     *
     * @return
     */
    public T get() {
        if (empty) {
            throw new IllegalStateException("Queue is empty");
        }

        T result = queue[front++];

        if (front >= MAX_N) {
            front = 0;
        }
        if (front == rear) {
            empty = true;
        }

        return result;
    }
}
