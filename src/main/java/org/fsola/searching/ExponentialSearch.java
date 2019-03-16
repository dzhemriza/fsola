/*
 * org.fsola
 *
 * File Name: ExponentialSearch.java
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
package org.fsola.searching;

/**
 * Implementation of binary search algorithm.
 * <p/>
 * Used Resources: README file resources section line 67
 */
public class ExponentialSearch {

    public static int search(int[] array, int key) {
        if (array.length == 0) {
            // Nothing to search for
            return -1;
        }

        int bound = 1;
        while (bound < array.length && array[bound] <= key) {
            bound *= 2;
        }

        return BinarySearch.binarySearchIter(array, key, bound / 2, Math.min(array.length, bound) - 1);
    }
}
