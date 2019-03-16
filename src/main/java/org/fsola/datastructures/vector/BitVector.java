/*
 * org.fsola
 *
 * File Name: BitVector.java
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
 * Self adjusting array of {@code long}'s used as bit vector.
 */
public class BitVector {
    private static int INITIAL_SIZE = 4;
    private long[] vector;

    public BitVector() {
        this(INITIAL_SIZE);
    }

    public BitVector(int initialLongArraySize) {
        vector = new long[initialLongArraySize];

        for (int i = 0; i < vector.length; ++i) {
            vector[i] = 0;
        }
    }

    /**
     * Set {@code true} at position.
     *
     * @param position
     */
    public void setTrue(int position) {
        setBit(position, true);
    }

    /**
     * Set {@code false} at position.
     *
     * @param position
     */
    public void setFalse(int position) {
        setBit(position, false);
    }

    /**
     * Set new value of specified bit. Please be aware that this method
     * self-adjust the size of the initial vector if needed.
     *
     * @param position
     * @param bit
     */
    public void setBit(int position, boolean bit) {
        if (position < 0) {
            throw new IllegalArgumentException("Negative index is not allowed");
        }

        int bucket = position / Long.SIZE;

        if (getSize() <= position) {
            // Re-size the vector
            allocate(bucket + 1); // +64 new bits
        }

        int pos = position % Long.SIZE;
        long mask = 1 << pos;

        if (bit) {
            vector[bucket] |= mask;
        } else {
            // Clear the flag
            vector[bucket] &= ~mask;
        }
    }

    /**
     * Get bit at specified position. If this position is outside of the bit
     * array return {@code false}.
     *
     * @param position
     * @return
     */
    public boolean getBit(int position) {
        if (position < 0) {
            throw new IllegalArgumentException("Negative index is not allowed");
        }

        boolean result = false;

        if (position < getSize()) {
            int bucket = position / Long.SIZE;
            int pos = position % Long.SIZE;

            long mask = 1 << pos;

            result = (vector[bucket] & mask) == mask;
        }

        return result;
    }

    /**
     * Return the size of the bit vector. The size is in bit's of 64.
     *
     * @return
     */
    public int getSize() {
        return Long.SIZE * vector.length;
    }

    /**
     * Allocate and copy data into new re-sized array.
     *
     * @param newSize
     */
    private void allocate(int newSize) {
        long[] v = new long[newSize];

        for (int i = 0; i < v.length; ++i) {
            v[i] = 0;
        }

        // Find minimum in cases when we want to compress the size of array
        int size = Math.min(newSize, vector.length);

        for (int i = 0; i < size; ++i) {
            // Copy all bits from one vector to another
            v[i] = vector[i];
        }

        vector = v;
    }
}
