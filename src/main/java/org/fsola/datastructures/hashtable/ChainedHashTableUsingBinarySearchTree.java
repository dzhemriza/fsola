/*
 * org.fsola
 *
 * File Name: ChainedHashTableUsingBinarySearchTree.java
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

import org.fsola.datastructures.tree.SimpleBinaryTree;
import org.fsola.datastructures.tree.SimpleBinaryTree.TreeNode;

/**
 * Hash table implementation using binary search tree for collision handling.
 *
 * @param <K>
 * @param <V>
 */
public class ChainedHashTableUsingBinarySearchTree<K extends Comparable<K>, V> {
    private static int INITIAL_TABLE_SIZE = 997;

    /**
     * Hash table entry class.
     *
     * @param <K>
     * @param <V>
     */
    private static class Entry<K extends Comparable<K>, V> {
        SimpleBinaryTree<K, V> tree = new SimpleBinaryTree<>();
    }

    private Entry<K, V>[] table = new Entry[INITIAL_TABLE_SIZE];

    public ChainedHashTableUsingBinarySearchTree() {
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
     * Add new element in pre-defined table.
     *
     * @param key
     * @param value
     * @param table
     */
    private void putImpl(K key, V value, Entry<K, V>[] table) {
        int place = place(key, table);

        if (table[place] == null) {
            table[place] = new Entry();
        }

        TreeNode<K, V> node = table[place].tree.search(key);

        if (node == null) {
            table[place].tree.insert(key, value);
        } else {
            node.setValue(value);
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
            TreeNode<K, V> node = table[place].tree.search(key);

            if (node != null) {
                result = node.getValue();
            }
        }

        return result;
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
            result = table[place].tree.deleteKey(key);

            if (table[place].tree.getRoot() == null) {
                // Tree is empty
                table[place] = null;
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
    private void addInNewTable(Entry<K, V>[] newTable, Entry<K, V> entry) {
        while (entry.tree.getRoot() != null) {
            K key = entry.tree.getRoot().getKey();
            V value = entry.tree.getRoot().getValue();
            entry.tree.deleteKey(entry.tree.getRoot().getKey());
            putImpl(key, value, newTable);
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

        Entry<K, V>[] newTable = new Entry[newSize];
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
