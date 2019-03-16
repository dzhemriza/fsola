/*
 * org.fsola
 *
 * File Name: ChainedHashTableUsingLinkedList.java
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

import static org.fsola.datastructures.linkedlist.BiDirectionalNullTerminatedTailedLinkedList.Node;

/**
 * Chained hash table implementation using linked list for collision handling.
 *
 * @param <K>
 * @param <V>
 */
public class ChainedHashTableUsingLinkedList<K extends Comparable<K>, V> {
    private static int INITIAL_TABLE_SIZE = 997;

    /**
     * Internal class used to represent key value pair.
     *
     * @param <K>
     * @param <V>
     */
    private static class Pair<K extends Comparable<K>, V> {
        K key;
        V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    /**
     * Hash table entry class.
     *
     * @param <K>
     * @param <V>
     */
    private static class Entry<K extends Comparable<K>, V> {
        BiDirectionalNullTerminatedTailedLinkedList<Pair<K, V>> list =
                new BiDirectionalNullTerminatedTailedLinkedList<>();
    }

    private Entry<K, V>[] table = new Entry[INITIAL_TABLE_SIZE];

    public ChainedHashTableUsingLinkedList() {
        // Initialize table
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
    private int place(K key, Entry[] table) {
        return Math.abs(key.hashCode()) % table.length;
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
            if (0 == i.getData().key.compareTo(key)) {
                return i;
            }
        }

        return null;
    }

    /**
     * Retrieve element from hash table.
     *
     * @param key
     * @return
     */
    public V get(K key) {
        V result = null;

        if (null == key) {
            throw new IllegalStateException("Null value for key is " +
                    "unsupported!");
        }

        int place = place(key, table);

        if (table[place] != null) {
            Node<Pair<K, V>> pair = search(table[place].list, key);

            if (pair != null) {
                result = pair.getData().value;
            }
        }

        return result;
    }

    /**
     * Add new element in pre-defined table.
     *
     * @param key
     * @param value
     * @param table
     */
    private void putImpl(K key, V value, Entry[] table) {
        int place = place(key, table);

        if (table[place] == null) {
            table[place] = new Entry();
        }

        Node<Pair<K, V>> pair = search(table[place].list, key);

        if (pair == null) {
            // Add new item in linked list
            table[place].list.put(new Pair(key, value));
        } else {
            // Element with same key exists updating the value of the pair
            pair.getData().value = value;
        }
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

        putImpl(key, value, table);
    }

    /**
     * Delete item from hash.
     *
     * @param key
     * @return
     */
    public V delete(K key) {
        V result = null;

        if (null == key) {
            throw new IllegalStateException("Null value for key is " +
                    "unsupported!");
        }

        int place = place(key, table);

        if (table[place] != null) {
            Node<Pair<K, V>> pair = search(table[place].list, key);

            if (pair != null) {
                result = pair.getData().value;

                table[place].list.delete(pair);
                if (table[place].list.isEmpty()) {
                    // Get rid of unused references
                    table[place] = null;
                }
            }
        }

        return result;
    }

    /**
     * Add items in new table that is used for rehashing.
     *
     * @param newTable
     * @param entry
     */
    private void addInNewTable(Entry[] newTable, Entry entry) {
        for (Node<Pair<K, V>> i = entry.list.getHead().getNext();
             i != entry.list.getTail();
             i = i.getNext()) {
            putImpl(i.getData().key, i.getData().value, newTable);
        }
    }

    /**
     * Resize hash table.
     *
     * @param newSize
     */
    public void resize(int newSize) {
        if (newSize <= 0) {
            throw new IllegalArgumentException("newSize is invalid: " +
                    newSize);
        }

        Entry[] newTable = new Entry[newSize];
        // Initialize table
        for (int i = 0; i < newTable.length; ++i) {
            newTable[i] = null;
        }

        for (int i = 0; i < table.length; ++i) {
            if (table[i] != null) {
                addInNewTable(newTable, table[i]);
            }
        }

        table = newTable;
    }
}
