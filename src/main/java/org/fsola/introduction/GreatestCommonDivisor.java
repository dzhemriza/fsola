/*
 * org.fsola
 *
 * File Name: GreatestCommonDivisor.java
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
package org.fsola.introduction;

/**
 * Used Resources: README file resources section line 1
 */
public class GreatestCommonDivisor {

    /**
     * Iterative approach for finding greatest common divisor using Euclid's
     * algorithm.
     *
     * @param a
     * @param b
     * @return
     */
    public static int gcdIter(int a, int b) {
        while (b > 0) {
            int swap = b;
            b = a % b;
            a = swap;
        }

        return a;
    }

    /**
     * Recursive approach for finding greatest common divisor using Euclid's
     * algorithm.
     *
     * @param a
     * @param b
     * @return
     */
    public static int gcdRec(int a, int b) {
        return (b == 0) ? a : gcdRec(b, a % b);
    }
}
