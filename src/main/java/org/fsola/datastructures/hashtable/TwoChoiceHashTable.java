/*
 * org.fsola
 *
 * File Name: TwoChoiceHashTable.java
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

import org.fsola.datastructures.linkedlist.BiDirectionalNullTerminatedTailedLinkedList;
import org.fsola.hash.HashFunctions;
import org.fsola.introduction.PrimeNumbers;

import static org.fsola.datastructures.linkedlist.BiDirectionalNullTerminatedTailedLinkedList.Node;

/**
 * Implementation of hash table using 2-choice hashing collision resolution.
 * <p/>
 * Used Resources: README file resources section line 18
 *
 * @param <K>
 * @param <V>
 */
public class TwoChoiceHashTable<K, V> {
    private static int ROUNDS_HASH_1 = 3;
    private static int ROUNDS_HASH_2 = 5;
    private static int INITIAL_TABLE_SIZE = 11;
    private static float INC = 1.25f;
    private static float OCCUPANCY = 0.80f;

    /**
     * Entry class used to store key value pair.
     *
     * @param <K>
     * @param <V>
     */
    private static class Pair<K, V> {
        K key;
        V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    /**
     * Hash table entry class
     *
     * @param <K>
     * @param <V>
     */
    private static class Entry<K, V> {
        BiDirectionalNullTerminatedTailedLinkedList<Pair<K, V>> list =
                new BiDirectionalNullTerminatedTailedLinkedList<>();
    }

    private Entry<K, V>[] table;

    public TwoChoiceHashTable() {
        allocate(INITIAL_TABLE_SIZE);
    }

    private int elementsCount;

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

        Node<Pair<K, V>> node = findItem(key);

        if (node != null) {
            node.getData().value = value;
            return;
        }

        Pair<K, V> entry = new Pair<>(key, value);

        int hash1 = hash1(key);
        int hash2 = hash2(key);

        int count1 = table[hash1] == null ? 0 : table[hash1].list.getCount();
        int count2 = table[hash2] == null ? 0 : table[hash2].list.getCount();

        if (count1 <= count2) {
            if (table[hash1] == null) {
                table[hash1] = new Entry<>();
            }

            table[hash1].list.put(entry);
        } else {
            if (table[hash2] == null) {
                table[hash2] = new Entry<>();
            }

            table[hash2].list.put(entry);
        }

        if (++elementsCount > table.length * OCCUPANCY) {
            rehash();
        }
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

        int hash1 = hash1(key);

        if (table[hash1] != null) {
            Node<Pair<K, V>> node = search(table[hash1].list, key);

            if (node != null) {
                result = node.getData().value;
                table[hash1].list.delete(node);
            }
        }

        int hash2 = hash2(key);

        if (table[hash2] != null && result == null) {
            Node<Pair<K, V>> node = search(table[hash2].list, key);

            if (node != null) {
                result = node.getData().value;
                table[hash2].list.delete(node);
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

        Node<Pair<K, V>> node = findItem(key);

        if (node != null) {
            result = node.getData().value;
        }

        return result;

    }

    /**
     * Find node based on key.
     *
     * @param key
     * @return
     */
    private Node<Pair<K, V>> findItem(K key) {
        Node<Pair<K, V>> result = null;

        int hash1 = hash1(key);

        if (table[hash1] != null) {
            Node<Pair<K, V>> node = search(table[hash1].list, key);

            if (node != null) {
                result = node;
            }
        }

        int hash2 = hash2(key);

        if (table[hash2] != null && result == null) {
            Node<Pair<K, V>> node = search(table[hash2].list, key);

            if (node != null) {
                result = node;
            }
        }

        return result;
    }

    /**
     * Search in linked list for a given key.
     *
     * @param list
     * @param key
     * @return
     */
    private Node<Pair<K, V>> search(
            BiDirectionalNullTerminatedTailedLinkedList<Pair<K, V>> list,
            K key) {

        for (Node<Pair<K, V>> i = list.getHead().getNext(); i != list.getTail();
             i = i.getNext()) {
            if (i.getData().key.equals(key)) {
                return i;
            }
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
        return Math.abs(HashFunctions.hashBasedOnRandom(key, ROUNDS_HASH_1,
                table.length));
    }

    /**
     * Calculates second hash
     *
     * @param key
     * @return
     */
    private int hash2(K key) {
        return Math.abs(HashFunctions.hashBasedOnRandom(key, ROUNDS_HASH_2,
                table.length));
    }

    /**
     * Rehash whole hash table
     */
    private void rehash() {
        Entry<K, V>[] old = table;
        allocate((int) (old.length * INC));

        for (int i = 0; i < old.length; ++i) {
            if (old[i] != null) {
                for (Node<Pair<K, V>> j = old[i].list.getHead().getNext();
                     j != old[i].list.getTail();
                     j = j.getNext()) {
                    put(j.getData().key, j.getData().value);
                }

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
