/*
 * org.fsola
 *
 * File Name: BinomialCoefficient.java
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
 * Used Resources: README file resources section line 1, line 2
 */
public class BinomialCoefficient {

    /**
     * Calculates binomial coefficient based on Pascals triangle take a look at
     * http://en.wikipedia.org/wiki/Binomial_coefficient
     *
     * @param n
     * @param k
     */
    public static int binomialCoefficient(int n, int k) {
        final int MAX = 1000;

        if (n > MAX) {
            throw new IllegalArgumentException("n > 1000");
        }

        int[] lastLine = new int[MAX + 1];

        lastLine[0] = 1;
        for (int i = 1; i <= n; ++i) {
            lastLine[i] = 1;

            for (int j = i - 1; j >= 1; --j) {
                lastLine[j] += lastLine[j - 1];
            }
        }

        return lastLine[k];
    }

    /**
     * Calculates and prints Pascal's triangle in array.
     *
     * @param n
     */
    public static void printPascalTriangle(int n) {
        final int MAX = 1000;

        if (n > MAX) {
            throw new IllegalArgumentException("n > 1000");
        }

        int[][] pascalTriangle = new int[MAX + 1][MAX + 1];

        for (int i = 0; i < MAX + 1; ++i) {
            for (int j = 0; j < MAX + 1; ++j) {
                pascalTriangle[i][j] = 0;
            }
        }

        for (int i = 0; i <= n; ++i) {
            pascalTriangle[i][0] = 1;
            pascalTriangle[i][i] = 1;
        }

        for (int i = 2; i <= n; ++i) {
            for (int j = 1; j < i; ++j) {
                pascalTriangle[i][j] =
                        pascalTriangle[i - 1][j] + pascalTriangle[i - 1][j - 1];
            }
        }

        for (int i = 0; i <= n; ++i) {
            for (int j = 0; j <= i; ++j) {
                System.out.print(pascalTriangle[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * Calculates binomial coefficient based on multiplicative formula
     * http://en.wikipedia.org/wiki/Binomial_coefficient#Multiplicative_formula
     *
     * @param n
     * @param k
     * @return
     */
    public static int binomialCoefficient2(int n, int k) {
        if ((k < 0) || (k > n)) {
            return 0;
        }

        if ((k == 0) || (k == n)) {
            return 1;
        }

        int diff = n - k;

        if (k > diff) {
            k = diff;
        }

        int c = 1;
        for (int i = 0; i < k; ++i) {
            c = c * (n - i) / (i + 1);
        }

        return c;
    }
}
