/*
 * org.fsola
 *
 * File Name: HopscotchHashTableUsingBitmap.java
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
package org.fsola.datastructures.hashtable;

import org.fsola.introduction.PrimeNumbers;

/**
 * Implementation of hash table using bitmap hopscotch collision resolution
 * method.
 * <p/>
 * Used Resources: README file resources section line 10, line 11
 *
 * @param <K>
 * @param <V>
 */
public class HopscotchHashTableUsingBitmap<K, V> {
    private static int INITIAL_TABLE_SIZE = 11;
    private static float INC = 1.25f;
    private static float OCCUPATION = 0.50f;

    /**
     * Neighborhood size.
     */
    private static final int NEIGHBORHOOD_SIZE = 16;

    /**
     * Entry class used to store key value pair.
     *
     * @param <K>
     * @param <V>
     */
    private static class Entry<K, V> {
        K key;
        V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    /**
     * Bucket class is responsible for holding entry data and bitmap
     *
     * @param <K>
     * @param <V>
     */
    private static class Bucket<K, V> {
        private int bitmap;
        private Entry<K, V> entry;

        public Bucket() {
            this.bitmap = 0;
            this.entry = null;
        }
    }

    /**
     * Number of maximum probes for finding a empty bucket.
     */
    private int maxProbes;
    /**
     * Bucket size
     */
    private int bucketSize;
    /**
     * Number of elements in hash table
     */
    private int numberOfElements;
    /**
     * Hash table
     */
    private Bucket<K, V>[] table;

    public HopscotchHashTableUsingBitmap() {
        allocate(INITIAL_TABLE_SIZE);
    }

    /**
     * Remove element from hash table.
     *
     * @param key
     * @return
     */
    public V delete(K key) {
        if (null == key) {
            throw new IllegalStateException("Null value for key is " +
                    "unsupported!");
        }

        V result = null;
        int hash = Math.abs(key.hashCode());
        int initialIndex = hash % bucketSize;
        int place = initialIndex;
        int mask = 1 << (NEIGHBORHOOD_SIZE - 1);
        boolean found = false;

        for (int i = 0; i < NEIGHBORHOOD_SIZE; ++i, mask >>= 1) {
            place = (initialIndex + i) % bucketSize;

            if ((table[place].bitmap & mask) == mask) {
                if (key.equals(table[place].entry.key)) {
                    found = true;
                    break;
                }
            }
        }

        if (found) {
            result = table[place].entry.value;
            table[place].entry = null;
            table[initialIndex].bitmap &= ~mask;
        }

        return result;
    }

    /**
     * Retrieve element from hash table
     *
     * @param key
     * @return
     */
    public V get(K key) {
        if (null == key) {
            throw new IllegalStateException("Null value for key is " +
                    "unsupported!");
        }

        V result = null;
        int place = find(key);

        if (-1 != place) {
            result = table[place].entry.value;
        }

        return result;
    }

    /**
     * Add new element in hash table using hopscotch collision resolution
     *
     * @param key
     * @param value
     */
    public void put(K key, V value) {
        if (null == key) {
            throw new IllegalStateException("Null value for key is " +
                    "unsupported!");
        }

        int place = find(key);
        if (-1 != place) {
            // Update existing value
            table[place].entry.value = value;
            return;
        }

        int hash = Math.abs(key.hashCode());
        int initialIndex = hash % bucketSize;

        int emptyBucketIndex = findEmptyBucket(initialIndex);
        table[emptyBucketIndex].entry = new Entry<>(key, value);

        int mask;
        if (initialIndex <= emptyBucketIndex) {
            mask = 1 << (NEIGHBORHOOD_SIZE - ((emptyBucketIndex - initialIndex)
                    + 1));
        } else {
            mask = 1 << (NEIGHBORHOOD_SIZE - ((emptyBucketIndex + bucketSize -
                    initialIndex) + 1));
        }

        table[initialIndex].bitmap |= mask;

        if (++numberOfElements > bucketSize * OCCUPATION) {
            rehash();
        }
    }

    /**
     * Find empty bucket in hash table and reorganize neighborhood
     *
     * @param initialIndex
     * @return
     */
    private int findEmptyBucket(int initialIndex) {
        int emptyBucketIndex = findEmptyBucketUsingLinearProbing(initialIndex);

        if (-1 == emptyBucketIndex) {
            throw new IllegalStateException("Unable to find an empty bucket!");
        }

        // Until an empty bucket becomes part of the neighborhood
        while (((initialIndex <= emptyBucketIndex) && (NEIGHBORHOOD_SIZE <=
                (emptyBucketIndex - initialIndex))) || ((emptyBucketIndex <
                initialIndex) && (NEIGHBORHOOD_SIZE <= (emptyBucketIndex +
                bucketSize - initialIndex)))) {

            int initialBaseIndex = (bucketSize + emptyBucketIndex -
                    (NEIGHBORHOOD_SIZE - 1)) % bucketSize;

            // For each candidate base bucket
            boolean foundSwap = false;
            for (int i = 0; i < NEIGHBORHOOD_SIZE - 1; ++i) {

                int baseIndex = (initialBaseIndex + i) % bucketSize;
                int mask = 1 << (NEIGHBORHOOD_SIZE - 1);

                for (int j = 0; j < NEIGHBORHOOD_SIZE - i - 1; ++j,
                        mask = mask >> 1) {

                    if ((table[baseIndex].bitmap & mask) == mask) {
                        int candidateIndex = (baseIndex + j) % bucketSize;

                        // Swap candidate
                        table[emptyBucketIndex].entry = table[candidateIndex]
                                .entry;

                        // Update bitmap
                        table[baseIndex].bitmap &= ~mask;
                        int newMask = 1 << i;
                        table[baseIndex].bitmap |= newMask;

                        emptyBucketIndex = candidateIndex;

                        // Find candidate for swap so break the parent cycle
                        foundSwap = true;
                        break;
                    }
                }

                if (foundSwap) {
                    break;
                }
            }

            if (!foundSwap) {
                throw new IllegalStateException("Unable to find candidate for" +
                        " swap.");
            }
        }

        return emptyBucketIndex;
    }

    /**
     * Finds first available empty bucket
     *
     * @param initialIndex
     * @return
     */
    private int findEmptyBucketUsingLinearProbing(int initialIndex) {
        int emptyBucket = -1;

        // Using linear probing find next available empty bucket
        for (int i = 0; i < maxProbes; ++i) {
            int currentIndex = (i + initialIndex) % bucketSize;

            if (table[currentIndex].entry == null) {
                emptyBucket = currentIndex;
                break;
            }
        }

        return emptyBucket;
    }

    /**
     * Find element by given key
     *
     * @param key
     * @return Index in hash table
     */
    private int find(K key) {
        int hash = Math.abs(key.hashCode());
        int place = hash % bucketSize;
        int mask = 1 << (NEIGHBORHOOD_SIZE - 1);
        boolean found = false;

        for (int i = 0; i < NEIGHBORHOOD_SIZE; ++i, mask = mask >> 1) {

            if ((table[place].bitmap & mask) == mask) {
                int next = (place + i) % bucketSize;

                if ((table[next].entry != null) &&
                        key.equals(table[next].entry.key)) {
                    found = true;
                    place = next;
                    break;
                }
            }
        }

        if (!found) {
            place = -1;
        }

        return place;
    }

    /**
     * Rehash whole hash table
     */
    private void rehash() {
        Bucket<K, V>[] old = table;
        allocate((int) (old.length * INC));

        for (int i = 0; i < old.length; ++i) {
            if (old[i] != null && old[i].entry != null) {
                put(old[i].entry.key, old[i].entry.value);
            }
        }
    }

    /**
     * Allocate array used for hash table
     *
     * @param size
     */
    private void allocate(int size) {
        // Find next available prime
        int N = PrimeNumbers.nextPrime(size);

        bucketSize = N;
        maxProbes = bucketSize; // Max probes = bucketSize
        numberOfElements = 0;
        table = new Bucket[bucketSize];

        for (int i = 0; i < table.length; ++i) {
            table[i] = new Bucket<>();
        }
    }
}
