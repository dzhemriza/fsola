/*
 * org.fsola
 *
 * File Name: QuadraticProbingHashTable.java
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
 * Implementation of hash table using quadratic probing collision resolution.
 * <p/>
 * Used Resources: README file resources section line 5
 *
 * @param <K>
 * @param <V>
 */
public class QuadraticProbingHashTable<K, V> {
    private static int INITIAL_TABLE_SIZE = 11;

    /**
     * Entry class used to store key value pair.
     *
     * @param <K>
     * @param <V>
     */
    private static class Entry<K, V> {
        K key;
        V value;
        boolean isActive = true;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private Entry<K, V>[] table;
    private int currentSize;

    public QuadraticProbingHashTable() {
        allocate(INITIAL_TABLE_SIZE);
    }

    /**
     * Search for element in hash table
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

        Entry<K, V> entry = find(key);
        if (entry != null && entry.isActive) {
            result = entry.value;
        }

        return result;
    }

    /**
     * Add new element in hash table or update existing
     *
     * @param key
     * @param value
     */
    public void put(K key, V value) {
        if (null == key) {
            throw new IllegalStateException("Null value for key is " +
                    "unsupported!");
        }

        int place = findPos(key);
        if (isActive(place)) {
            table[place].value = value;
            return;
        }

        Entry<K, V> entry = new Entry<>(key, value);
        table[place] = entry;
        currentSize++;

        if (currentSize > table.length / 2) {
            rehash();
        }
    }

    /**
     * Remove element from hash table
     *
     * @param key
     * @return
     */
    public V delete(K key) {
        V result = null;

        int place = findPos(key);
        if (isActive(place)) {
            result = table[place].value;
            table[place].isActive = false;
        }

        return result;
    }

    /**
     * Rehash table.
     */
    private void rehash() {
        Entry<K, V>[] old = table;

        allocate(old.length * 2);

        for (int i = 0; i < old.length; ++i) {
            if (old[i] != null && old[i].isActive) {
                put(old[i].key, old[i].value);
            }
        }
    }

    /**
     * Verifies is this position is active or not.
     *
     * @param place
     * @return
     */
    private boolean isActive(int place) {
        return (table[place] != null) && (table[place].isActive);
    }

    /**
     * Calculates the place of the element in hash table.
     *
     * @param key
     * @return
     */
    private int place(K key, Entry<K, V>[] table) {
        return Math.abs(key.hashCode()) % table.length;
    }

    /**
     * Find entry in table
     *
     * @param key
     * @return
     */
    private Entry<K, V> find(K key) {
        int place = findPos(key);
        return table[place];
    }

    /**
     * Find entry in table represented as index
     *
     * @param key
     * @return
     */
    private int findPos(K key) {
        int offset = 1;
        int place = place(key, table);

        // This iteration do not take into account removed elements
        while ((table[place] != null) && (!key.equals(table[place].key))) {
            // Compute the ith probe
            place += offset;
            offset += 2;

            if (place >= table.length) {
                place -= table.length;
            }
        }

        return place;
    }

    /**
     * Allocate array for hash table
     *
     * @param size
     */
    private void allocate(int size) {
        currentSize = 0;

        // Find the next prime
        int n = PrimeNumbers.nextPrime(size);
        table = new Entry[n];

        for (int i = 0; i < table.length; ++i) {
            table[i] = null;
        }
    }
}
