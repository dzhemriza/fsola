/*
 * org.fsola
 *
 * File Name: Utils.java
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
package org.fsola.test.utils;

import org.fsola.datastructures.linkedlist.BiDirectionalNullTerminatedTailedLinkedList;
import org.fsola.sorting.QuickSort;
import org.junit.Assert;

import java.util.Random;

import static org.fsola.datastructures.linkedlist.BiDirectionalNullTerminatedTailedLinkedList.Node;

public class Utils {
    public static Random RAND = new Random();
    private static final int MIN_NO_OF_CHARS = 5;
    private final static String CHARS =
            "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM1234567890";

    public static String[][] STRINGS_KV = {
            {"Aliens", "Gattaca"},
            {"Blade Runner", "A Space Odyssey"},
            {"Metropolis", "Close Encounters of the Third Kind"},
            {"Star Wars: Episode V: The Empire Strikes Back",
                    "Star Trek II: The Wrath of Khan"},
            {"The Terminator", "Planet of the Apes"},
            {"RoboCop", "Forbidden Planet"},
            {"Wall-E", "Logan's Run"},
            {"Akira", "Dark City"},
            {"The Fly", "Total Recall"},
            {"Tron", "The Thing"}
    };

    /**
     * Generates random int array
     *
     * @param size
     * @param limit
     * @return
     */
    public static int[] randomIntArray(int size, int limit) {
        int[] r = new int[size];

        for (int i = 0; i < size; ++i) {
            r[i] = RAND.nextInt(limit);
        }

        return r;
    }

    /**
     * Generates random int linked list
     *
     * @param size
     * @param limit
     * @return
     */
    public static BiDirectionalNullTerminatedTailedLinkedList<Integer> randomIntList(
            int size, int limit) {
        BiDirectionalNullTerminatedTailedLinkedList<Integer> r = new
                BiDirectionalNullTerminatedTailedLinkedList<>();

        for (int i = 0; i < size; ++i) {
            r.put(RAND.nextInt(limit));
        }

        return r;
    }

    /**
     * Populates data from given list {@code l} into given array {@code a}
     * please be careful with using this because it could throw {@link java
     * .lang.IndexOutOfBoundsException} in cases when the list has more elements
     * than array.
     *
     * @param l
     * @param a
     * @param <T>
     */
    public static <T> void populateArrayFromList(
            BiDirectionalNullTerminatedTailedLinkedList<T> l, T[] a) {
        int x = 0;
        for (Node<T> p = l.getHead().getNext(); p != l.getTail(); p = p
                .getNext()) {
            a[x++] = p.getData();
        }
    }

    /**
     * Generates random int array (contains negative numbers).
     *
     * @param size
     * @param limit
     * @return
     */
    public static int[] randomIntArrayWithNegatives(int size, int limit) {
        int[] r = new int[size];

        for (int i = 0; i < size; ++i) {
            r[i] = RAND.nextInt(limit);

            if (RAND.nextBoolean()) {
                r[i] = -r[i];
            }
        }

        return r;
    }

    /**
     * Generates random positive int array (without zero element).
     *
     * @param size
     * @param limit
     * @return
     */
    public static int[] randomPositiveIntArrayWithoutZero(int size,
                                                          int limit) {
        int[] r = new int[size];

        for (int i = 0; i < size; ++i) {
            int rand;
            while ((rand = RAND.nextInt(limit)) == 0) ;
            r[i] = rand;
        }

        // Verify array contains no zero element
        for (int i : r) {
            if (i == 0) {
                throw new IllegalStateException("Array contains zero.");
            }
        }

        return r;
    }

    /**
     * Verifies sorting in int array
     *
     * @param a
     */

    public static void verifySortedIntArrayAsc(int[] a) {
        for (int i = 1; i < a.length; ++i) {
            Assert.assertTrue(a[i - 1] <= a[i]);
        }
    }

    /**
     * Verifies sorting in int array
     *
     * @param a
     */
    public static void verifySortedIntArrayDesc(int[] a) {
        for (int i = 1; i < a.length; ++i) {
            Assert.assertTrue(a[i] <= a[i - 1]);
        }
    }

    /**
     * Generates random string
     *
     * @return
     */
    public static String generateRandomString() {
        int s = RAND.nextInt(CHARS.length());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < MIN_NO_OF_CHARS + s; ++i) {
            sb.append(CHARS.charAt(RAND.nextInt(CHARS.length())));
        }

        return sb.toString();
    }

    /**
     * Generates 2D string array used as STRINGS_KV
     *
     * @return
     */
    public static String[][] generateRandomKV(int max) {
        String[][] result = new String[max][2];

        for (int i = 0; i < max; ++i) {
            result[i][0] = Utils.generateRandomString();
            result[i][1] = Utils.generateRandomString();
        }

        return result;
    }

    /**
     * Converts given number to it's binary representations
     *
     * @param a
     * @return
     */
    public static String num2Bin(int a) {
        StringBuilder sb = new StringBuilder();
        for (int bitPow2 = 1, i = 0; i < Integer.SIZE; bitPow2 <<= 1,
                ++i) {
            if ((a & bitPow2) == bitPow2) {
                sb.append("1");
            } else {
                sb.append("0");
            }
        }
        return sb.reverse().toString();
    }

    /**
     * Swap 2 elements from given int array
     *
     * @param a
     * @param x
     * @param y
     */
    public static void swap(int[] a, int x, int y) {
        int tmp = a[x];
        a[x] = a[y];
        a[y] = tmp;
    }

    /**
     * Copy given int array and sort it using quick sort.
     *
     * @param a
     * @return
     */
    public static int[] copyAndSort(int[] a) {
        int[] result = new int[a.length];

        for (int i = 0; i < a.length; ++i) result[i] = a[i];

        QuickSort.quick1(result);

        return result;
    }

    /**
     * Prints matrix to the std out.
     *
     * @param m
     */
    public static void printMatrix(int[][] m) {
        for (int i = 0; i < m.length; ++i) {
            for (int j = 0; j < m[i].length; ++j) {
                System.out.print(" " + m[i][j]);
            }
            System.out.println();
        }
    }
}
