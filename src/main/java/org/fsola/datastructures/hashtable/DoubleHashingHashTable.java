/*
 * org.fsola
 *
 * File Name: DoubleHashingHashTable.java
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
 * Implementation of hash table using double hashing collision resolution.
 * <p/>
 * Used Resources: README file resources section line 5, line 6
 *
 * @param <K>
 * @param <V>
 */
public class DoubleHashingHashTable<K, V> {
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

    public DoubleHashingHashTable() {
        allocate(INITIAL_TABLE_SIZE);
    }

    /**
     * Add new element in hash table.
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
        table[place] = new Entry<>(key, value);
        currentSize++;

        if (currentSize > table.length / 2) {
            rehash();
        }
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

        int place = findPos(key);
        if (isActive(place)) {
            result = table[place].value;
        }

        return result;
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
     * Find table position based on given key.
     *
     * @param key
     * @return
     */
    private int findPos(K key) {
        int place = place(key);
        int skip = skip(key);

        while (table[place] != null && !key.equals(table[place].key)) {
            place += skip;
            place %= table.length;
        }

        return place;
    }

    /**
     * Calculates the place of the element in hash table.
     *
     * @return
     */
    private int place(K key) {
        return Math.abs(key.hashCode()) % table.length;
    }

    /**
     * Calculates skip size of the key
     *
     * @param key
     * @return
     */
    private int skip(K key) {
        // Array size must be relatively prime to 5, 4, 3 and 2
        return 5 - key.hashCode() % 5;
    }

    /**
     * Allocate array used for hash table
     *
     * @param size
     */
    public void allocate(int size) {
        int N = PrimeNumbers.nextPrime(size);
        table = new Entry[N];
        currentSize = 0;

        for (int i = 0; i < table.length; ++i) {
            table[i] = null;
        }
    }
}
