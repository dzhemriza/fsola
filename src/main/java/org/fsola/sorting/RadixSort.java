/*
 * org.fsola
 *
 * File Name: RadixSort.java
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
package org.fsola.sorting;

import org.fsola.datastructures.linkedlist.BiDirectionalNullTerminatedTailedLinkedList;

import static org.fsola.datastructures.linkedlist.BiDirectionalNullTerminatedTailedLinkedList.Node;

/**
 * Implementation of radix sort algorithm. Please be aware that this
 * implementation works only with positive integers.
 * <p/>
 * Used Resources: README file resources section line 1, 23
 */
public class RadixSort {
    private static int BASE = 10;
    private static int MAX_DIGIT_COUNT = 8;

    public static void sort(int[] a) {
        BiDirectionalNullTerminatedTailedLinkedList<Integer>[] bucket = new
                BiDirectionalNullTerminatedTailedLinkedList[BASE];

        // Initialize bucket
        for (int i = 0; i < BASE; ++i) {
            bucket[i] = new BiDirectionalNullTerminatedTailedLinkedList<>();
        }

        for (int i = 1, exp = 1; i <= MAX_DIGIT_COUNT; ++i, exp *= BASE) {
            // Make a pass and reorganize numbers in buckets
            for (int j = 0; j < a.length; ++j) {
                int pos = (a[j] / exp) % BASE;

                bucket[pos].put(a[j]);
            }

            // Reorder array
            for (int j = 0, k = 0; j < bucket.length; ++j) {
                for (Node<Integer> p = bucket[j].getHead().getNext(); p !=
                        bucket[j].getTail();
                     p = p.getNext()) {
                    a[k++] = p.getData().intValue();
                }

                // Clear the bucket for the next pass
                bucket[j].clear();
            }
        }
    }
}
