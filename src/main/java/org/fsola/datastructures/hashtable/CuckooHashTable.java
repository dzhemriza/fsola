/*
 * org.fsola
 *
 * File Name: CuckooHashTable.java
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

import org.fsola.hash.HashFunctions;
import org.fsola.introduction.PrimeNumbers;

/**
 * Implementation of hash table using cuckoo hashing collision resolution.
 * <p/>
 * Used Resources: README file resources section line 8, line 9
 *
 * @param <K>
 * @param <V>
 */
public class CuckooHashTable<K, V> {
    private static int ROUNDS = 3;
    private static int INITIAL_TABLE_SIZE = 11;
    private static float INC = 1.25f;

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

    private Entry<K, V>[] table;

    public CuckooHashTable() {
        allocate(INITIAL_TABLE_SIZE);
    }

    /**
     * Add new item in hash table
     *
     * @param key
     * @param value
     */
    public void put(K key, V value) {
        if (null == key) {
            throw new IllegalStateException("Null value for key is " +
                    "unsupported!");
        }

        Entry<K, V> entry = find(key);

        if (entry != null) {
            entry.value = value;
            return;
        }

        entry = new Entry<>(key, value);

        insert(entry);
    }

    /**
     * Delete element from hash table
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

        int hash = hash1(key);

        if (table[hash] != null && key.equals(table[hash].key)) {
            result = table[hash].value;
            table[hash] = null;
            return result;
        }

        hash = hash2(key);
        if (table[hash] != null && key.equals(table[hash].key)) {
            result = table[hash].value;
            table[hash] = null;
            return result;
        }

        return result;
    }

    /**
     * Insert new entry in hash table
     *
     * @param entry
     */
    private void insert(Entry<K, V> entry) {
        int pos = hash1(entry.key);

        // Control loop for number of iterations to avoid infinite cycle
        for (int i = 0; i < table.length; ++i) {
            if (table[pos] == null) {
                table[pos] = entry;
                return;
            }

            // entry <-swap-> table[pos]
            Entry<K, V> tmp = table[pos];
            table[pos] = entry;
            entry = tmp;

            if (pos == hash1(entry.key)) {
                pos = hash2(entry.key);
            } else {
                pos = hash1(entry.key);
            }
        }

        // If we are unable to find a place for this entry we just rehash whole
        // table and insert stored in memory entry
        rehash();
        insert(entry);
    }

    /**
     * Rehash whole hash table
     */
    private void rehash() {
        Entry<K, V>[] old = table;
        allocate((int) (old.length * INC));

        for (int i = 0; i < old.length; ++i) {
            if (old[i] != null) {
                put(old[i].key, old[i].value);
            }
        }
    }

    /**
     * Retrieve element by key
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
        if (entry != null) {
            result = entry.value;
        }

        return result;
    }

    /**
     * Internal method that searches in hash table.
     *
     * @param key
     * @return
     */
    private Entry<K, V> find(K key) {
        int hash = hash1(key);

        if (table[hash] != null && key.equals(table[hash].key)) {
            return table[hash];
        }

        hash = hash2(key);
        if (table[hash] != null && key.equals(table[hash].key)) {
            return table[hash];
        }

        return null;
    }

    /**
     * Calculates first hash
     *
     * @param key
     * @return
     */
    private int hash1(K key) {
        return Math.abs(key.hashCode()) % table.length;
    }

    /**
     * Calculates second hash
     *
     * @param key
     * @return
     */
    private int hash2(K key) {
        return Math.abs(HashFunctions.hashBasedOnRandom(key, ROUNDS,
                table.length));
    }

    /**
     * Allocate array used for hash table
     *
     * @param size
     */
    public void allocate(int size) {
        int N = PrimeNumbers.nextPrime(size);
        table = new Entry[N];

        for (int i = 0; i < table.length; ++i) {
            table[i] = null;
        }
    }
}
