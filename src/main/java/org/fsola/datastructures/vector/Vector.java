/*
 * org.fsola
 *
 * File Name: Vector.java
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
package org.fsola.datastructures.vector;

/**
 * Resizable array similar to C++ std::vector class.
 *
 * @author driza
 */
public class Vector<V> {
    private final int INITIAL_SIZE = 10;
    private final float GROW = 1.2f;

    /**
     * Holder array.
     */
    private V[] elements = (V[]) new Object[0];

    /**
     * Number of elements in Vector.
     */
    private int N = 0;

    public Vector() {
        resize(INITIAL_SIZE);
    }

    /**
     * Add element in the end of the vector.
     *
     * @param v
     */
    public void pushBack(V v) {
        growIfNeeded();
        elements[N++] = v;
    }

    /**
     * Remove element at the end of the list.
     *
     * @return
     */
    public V popBack() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Vector is empty");
        }

        V result = elements[--N];
        elements[N] = null;

        return result;
    }

    /**
     * Delete element in front of vector.
     *
     * @return
     */
    public V popFront() {
        return deleteAt(0);
    }

    /**
     * Reverse elements in vector.
     */
    public void reverse() {
        for (int i = 0, j = N - 1; i < j; ++i, --j) {
            V t = elements[i];
            elements[i] = elements[j];
            elements[j] = t;
        }
    }

    /**
     * Delete element at specific index.
     *
     * @param pos
     * @return
     */
    public V deleteAt(int pos) {
        if (isEmpty()) {
            throw new IllegalArgumentException("Vector is empty");
        }

        if ((0 <= pos) && (pos < N)) {
            V result = elements[pos];
            elements[pos] = null;

            shiftRight(pos);

            N--;
            return result;

        } else {
            throw new IllegalArgumentException("Index out of bounds - " + pos);
        }
    }

    private void shiftRight(int pos) {
        for (int i = pos; i < N - 1; ++i) {
            elements[i] = elements[i + 1];
        }

        elements[N - 1] = null;
    }

    /**
     * Insert element at specific position.
     *
     * @param pos
     * @param v
     */
    public void insertAt(int pos, V v) {
        growIfNeeded();
        shiftLeft(pos);
        elements[pos] = v;
        N++;
    }

    /**
     * Add element in the begin of the vector.
     *
     * @param v
     */
    public void pushFront(V v) {
        insertAt(0, v);
    }

    /**
     * Shift left elements at specific position.
     *
     * @param pos
     */
    private void shiftLeft(int pos) {
        for (int i = N; i > pos; --i) {
            elements[i] = elements[i - 1];
        }
        elements[pos] = null;
    }

    /**
     * @param i
     * @return Element at specific index.
     */
    public V get(int i) {
        if (0 <= i && i < N) {
            return elements[i];
        } else {
            throw new IllegalArgumentException("Invalid index - " + i);
        }
    }

    /**
     * Update existing value in array.
     *
     * @param i
     * @param v
     * @return return existing value on index {@code i}
     */
    public V set(int i, V v) {
        if (0 <= i && i < N) {
            V result = elements[i];
            elements[i] = v;
            return result;
        } else {
            throw new IllegalArgumentException("Invalid index - " + i);
        }
    }

    /**
     * @return number of elements in vector
     */
    public int size() {
        return N;
    }

    /**
     * @return {@code true} if vector is empty {@code false} otherwise.
     */
    public boolean isEmpty() {
        return (N == 0);
    }

    /**
     * Remove all elements in vector
     */
    public void clear() {
        for (int i = 0; i < N; ++i) {
            elements[i] = null; // Remove references
        }
        N = 0;
        resize(INITIAL_SIZE);
    }

    /**
     * @return Allocated array size
     */
    public int allocatedArraySize() {
        return elements.length;
    }

    /**
     * Reduce allocated array size if vector have lot of inserts and lot
     * of deletes.
     */
    public void reduceAllocatedArraySize() {
        if (((int) (N * GROW)) < elements.length) {
            resizeImpl((int) (N * GROW));
        }
    }

    /**
     * Resize vector with specific size. Vector size is only increasing.
     *
     * @param size
     */
    public void resize(int size) {
        if (size < 0) {
            throw new IllegalArgumentException("Size is negative");
        }

        if (size < N) {
            throw new IllegalArgumentException("Size must be increasing");
        }

        resizeImpl(size);
    }

    private void resizeImpl(int size) {
        V[] newElements = (V[]) new Object[size];

        for (int i = 0; i < Math.min(size, N); ++i) {
            newElements[i] = elements[i];
        }

        elements = newElements;
    }

    private void growIfNeeded() {
        if (N == elements.length) {
            resize((int) (elements.length * GROW));
        }
    }
}
