/*
 * org.fsola
 *
 * File Name: CoalescedHashTable.java
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
 * Coalesced hash table implementation.
 * <p/>
 * Used Resources: README file resources section line 12, line 13
 *
 * @param <K>
 * @param <V>
 */
public class CoalescedHashTable<K, V> {
    private static int INITIAL_TABLE_SIZE = 11;
    private static float OCCUPANCY = 0.75f;
    private static float INC = 1.25f;

    /**
     * Internal hash table entry
     *
     * @param <K>
     * @param <V>
     */
    private static class Entry<K, V> {
        boolean isActive;
        int next;
        K key;
        V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;

            isActive = true;
            next = -1;
        }
    }

    /**
     * Hash table
     */
    private Entry<K, V>[] table;
    private int elementsCount;

    public CoalescedHashTable() {
        allocate(INITIAL_TABLE_SIZE);
    }

    /**
     * Calculates place of the element in table
     *
     * @param key
     * @return
     */
    private int place(K key) {
        return Math.abs(key.hashCode()) % table.length;
    }

    /**
     * Add/update new element in hash table
     *
     * @param key
     * @param value
     */
    public void put(K key, V value) {
        if (null == key) {
            throw new IllegalStateException("Null value for key is " +
                    "unsupported!");
        }

        int place = place(key);

        int existingItem = findIndex(key);
        if (existingItem != -1) {
            table[existingItem].value = value;
            table[existingItem].isActive = true;
            return;
        }

        if (table[place] == null) {
            table[place] = new Entry<>(key, value);
        } else {
            int cursor = table.length - 1;

            // Find empty element in table
            while ((table[cursor] != null) && (cursor >= 0)) {
                cursor--;
            }

            // Find last element in chain
            int last = place;
            while (table[last].next != -1) {
                last = table[last].next;
            }

            // Link last node to empty element
            table[last].next = cursor;

            table[cursor] = new Entry<>(key, value);
        }

        if (++elementsCount > table.length * OCCUPANCY) {
            rehash();
        }
    }

    /**
     * Lookup in hash table to find item based on key
     *
     * @param key
     * @return
     */
    public int findIndex(K key) {

        int result = -1;
        for (int place = place(key); place != -1 && table[place] != null;
             place = table[place].next) {

            if (key.equals(table[place].key)) {
                result = place;
                break;
            }
        }

        return result;
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

        int place = findIndex(key);
        if (place != -1 && table[place].isActive) {
            result = table[place].value;
        }

        return result;
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

        int place = findIndex(key);
        if (place != -1 && table[place].isActive) {
            result = table[place].value;
            table[place].isActive = false;
        }

        return result;
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
     * Allocate array used for hash table
     *
     * @param size
     */
    public void allocate(int size) {
        int N = PrimeNumbers.nextPrime(size);
        table = new Entry[N];
        elementsCount = 0;

        for (int i = 0; i < table.length; ++i) {
            table[i] = null;
        }
    }
}
