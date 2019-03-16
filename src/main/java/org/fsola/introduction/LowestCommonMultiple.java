/*
 * org.fsola
 *
 * File Name: LowestCommonMultiple.java
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
public class LowestCommonMultiple {

    /**
     * Calculates Lowest Common Multiple.
     *
     * @param a
     * @return
     */
    public static int lcm(int[] a) {
        if (a.length == 2) {
            return (a[0] * a[1]) / GreatestCommonDivisor.gcdIter(a[0], a[1]);
        } else {
            // Create new array that we pass to recursion
            int[] tmp = new int[a.length - 1];
            for (int i = 0; i < a.length - 1; ++i) {
                tmp[i] = a[i];
            }

            int b = lcm(tmp);
            return (b * a[a.length - 1]) /
                    GreatestCommonDivisor.gcdIter(b, a[a.length - 1]);
        }
    }

    /**
     * Calculates Lowest Common Multiple using iteration.
     *
     * @param a
     * @return
     */
    public static int lcmIter(int[] a) {
        if (a.length < 2) {
            throw new IllegalArgumentException("a length: " + a.length);
        }

        int b = (a[0] * a[1]) / GreatestCommonDivisor.gcdIter(a[0], a[1]);

        for (int i = 2; i < a.length; ++i) {
            int tmp = (b * a[i]) / GreatestCommonDivisor.gcdIter(b, a[i]);
            b = tmp;
        }

        return b;
    }
}
