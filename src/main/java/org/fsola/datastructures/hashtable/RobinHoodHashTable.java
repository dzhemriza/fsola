/*
 * org.fsola
 *
 * File Name: RobinHoodHashTable.java
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
 * Implementation of Robin Hood hash table.
 * <p/>
 * Used Resources: README file resources section line 14, line 15, line 16, line
 * 17
 *
 * @param <K>
 * @param <V>
 */
public class RobinHoodHashTable<K, V> {
    private static int INITIAL_TABLE_SIZE = 11;
    private static float OCCUPANCY = 0.90f;
    private static float INC = 1.25f;

    /**
     * Internal hash table entry
     *
     * @param <K>
     * @param <V>
     */
    private static class Entry<K, V> {
        K key;
        V value;
        int hash;
        boolean isActive;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;

            if (key != null) {
                this.hash = key.hashCode();
            } else {
                hash = 0;
            }

            this.isActive = true; // Flag indicating if this is a tombstone
        }
    }

    /**
     * Hash table
     */
    private Entry<K, V>[] table;
    private int elementsCount;
    private int longestProbe = 1;

    public RobinHoodHashTable() {
        allocate(INITIAL_TABLE_SIZE);
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

        int findIndex = findIndex(key);

        if (findIndex != -1 && table[findIndex].isActive) {
            // Update value only
            table[findIndex].value = value;
            return;
        }

        insert(key, value);

        if (++elementsCount > table.length * OCCUPANCY) {
            rehash();
        }
    }

    /**
     * Insert element in hash table
     *
     * @param key
     * @param value
     */
    private void insert(K key, V value) {
        int pos = place(key); // Desired position in hash table
        int dist = 0;

        for (; ; ++pos, ++dist) {
            if (pos == table.length) {
                pos = 0;
            }

            if (table[pos] == null) {
                // Find a empty place and just insert
                table[pos] = new Entry<>(key, value);
                longestProbe = Math.max(dist, longestProbe);
                break;
            }

            int existingDistance = distanceFromInitialBucket(table[pos], pos);

            if (existingDistance < dist) {
                // If the existing element has distance from initial bucket
                // less than current calculated distance so just swap elements
                // and continue searching for available position in hash table.

                if (!table[pos].isActive) {
                    // This is a tombstone just update this
                    table[pos] = new Entry<>(key, value);
                    longestProbe = Math.max(dist, longestProbe);
                }

                // Update longest probe
                longestProbe = Math.max(dist, longestProbe);

                // Swap key
                K tmpK = key;
                key = table[pos].key;
                table[pos].key = tmpK;
                // Swap value
                V tmpV = value;
                value = table[pos].value;
                table[pos].value = tmpV;
                // Set hash field
                table[pos].hash = table[pos].key.hashCode();

                // Update distance value
                dist = existingDistance;
            }
        }
    }

    /**
     * Calculates desired position in hash table.
     *
     * @param key
     * @return
     */
    private int place(K key) {
        return Math.abs(key.hashCode()) % table.length;
    }

    /**
     * Calculates distance from initial bucket of given element.
     *
     * @return
     */
    private int distanceFromInitialBucket(Entry<K, V> entry, int pos) {
        int initialPos = Math.abs(entry.hash) % table.length;

        if (pos < initialPos) {
            return table.length - initialPos + pos;
        } else {
            return pos - initialPos;
        }
    }

    /**
     * Internal method for searching for item in by key in hash table.
     *
     * @param key
     * @return
     */
    private int findIndex(K key) {
        int result = -1;

        int pos = place(key);
        int dist = 0;

        for (int i = 0; i <= longestProbe; ++i, ++dist, ++pos) {
            if (pos == table.length) {
                pos = 0;
            }

            if (table[pos] == null) {
                break;
            } else if (distanceFromInitialBucket(table[pos], pos) < dist) {
                break;
            } else if (key.equals(table[pos].key)) {
                result = pos;
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

        int findIndex = findIndex(key);

        if (findIndex != -1 && table[findIndex].isActive) {
            result = table[findIndex].value;
        }

        return result;
    }

    /**
     * Check if given key exists in hash table.
     *
     * @param key
     * @return
     */
    public boolean contains(K key) {
        if (null == key) {
            throw new IllegalStateException("Null value for key is " +
                    "unsupported!");
        }

        boolean result = false;

        int findIndex = findIndex(key);

        if (findIndex != -1 && table[findIndex].isActive) {
            result = true;
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


        int findIndex = findIndex(key);

        if (findIndex != -1 && table[findIndex].isActive) {
            result = table[findIndex].value;
            table[findIndex].isActive = false;
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
        longestProbe = 1;

        for (int i = 0; i < table.length; ++i) {
            table[i] = null;
        }
    }
}
