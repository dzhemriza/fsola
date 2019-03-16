/*
 * org.fsola
 *
 * File Name: SimpleArrayStack.java
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

/**
 * Implementation of simple stack using array.
 * <p/>
 * Used Resources: README file resources section line 1
 *
 * @param <T>
 */
public class SimpleArrayStack<T> {

    /**
     * Number of max elements in stack.
     */
    private final int MAX_N = 100;

    /**
     * Data holder array
     */
    private T[] stack = (T[]) new Object[MAX_N];

    private int top = 0;

    public boolean isEmpty() {
        return (top == 0);
    }

    /**
     * Add new item in stack
     *
     * @param e
     */
    public void put(T e) {
        if (stack.length <= top) {
            throw new IllegalStateException("Stack is full");
        }

        stack[top++] = e;
    }

    /**
     * Get top item from stack
     *
     * @return
     */
    public T pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }

        T result = stack[--top];
        stack[top] = null;

        return result;
    }
}
