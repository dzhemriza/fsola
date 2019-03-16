/*
 * org.fsola
 *
 * File Name: LinearProbingHashTable.java
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

/**
 * Has table implementation using linear probing.
 * <p/>
 * Used Resources: README file resources section line 4
 *
 * @param <K>
 * @param <V>
 */
public class LinearProbingHashTable<K, V> {
    private static int INITIAL_TABLE_SIZE = 256;

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
     * Marker for deleted item in table
     */
    private static Entry DEL = new Entry<>(null, null);

    private Entry<K, V>[] table = new Entry[INITIAL_TABLE_SIZE];
    private int N = 0; // Number of elements in hash table
    private int Q = 0; // Non null entities in hash table
    private int D = 8; // t.length = 2^d

    public LinearProbingHashTable() {
        // Initialize hash table
        for (int i = 0; i < table.length; ++i) {
            table[i] = null;
        }
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
     * Search for element in hash table based on key
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
     * Find entry based on given key.
     *
     * @param key
     * @return
     */
    private Entry<K, V> find(K key) {
        Entry<K, V> result = null;

        int place = place(key, table);

        while (table[place] != null) {
            if (table[place] != DEL && key.equals(table[place].key)) {
                result = table[place];
                break;
            }

            if (place == table.length - 1) {
                // We are at the end of the array
                place = 0;
            } else {
                place++;
            }
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

        Entry<K, V> entry = find(key);

        if (entry != null) {
            // Update existing element value and exit
            entry.value = value;
        } else {
            // Add new element
            if (2 * (Q + 1) > table.length) {
                // max 50% occupancy
                resize();
            }

            // Find null position in table resize method guaranties that
            // there will be null elements
            int place = place(key, table);
            while (table[place] != null && table[place] != DEL) {
                if (place == table.length - 1) {
                    // Start from beginning of the array
                    place = 0;
                } else {
                    place++;
                }
            }

            Q++;
            N++;
            table[place] = new Entry<>(key, value);
        }
    }

    /**
     * Remove element from hash table
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
        int place = place(key, table);

        while (table[place] != null) {
            if (table[place] != DEL && key.equals(table[place].key)) {
                N--;
                result = table[place].value;
                table[place] = DEL;

                if (8 * N < table.length) {
                    // min 12.5% occupancy
                    resize();
                }

                break;
            }

            if (place == table.length - 1) {
                // Start from beginning of the array
                place = 0;
            } else {
                place++;
            }
        }

        return result;
    }

    /**
     * Resize hash table
     */
    private void resize() {
        // Find smallest non-negative integer D such that 2^d >= 3n
        D = 1;
        while ((1 << D) < 3 * N) {
            D++;
        }

        Entry<K, V>[] old = table;
        Q = N;
        table = new Entry[1 << D];

        // Insert everything from old
        for (int k = 0; k < old.length; ++k) {
            if (old[k] != null && old[k] != DEL) {
                int place = place(old[k].key, table);

                // Find null place
                while (table[place] != null) {
                    if (place == table.length - 1) {
                        place = 0;
                    } else {
                        place++;
                    }
                }

                table[place] = old[k];
            }
        }
    }
}
