/*
 * org.fsola
 *
 * File Name: MatrixChainMultiplication.java
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
package org.fsola.dynamicprogramming;

/**
 * Given a sequence of matrices, find the most efficient way to multiply
 * these matrices together. The problem is not actually to perform the
 * multiplications, but merely to decide in which order to perform the
 * multiplications.
 * <p/>
 * Used Resources: README file resources section line 1, line 39, line 54.
 */
public class MatrixChainMultiplication {

    public static int sol1(int[] dimensions, int i, int j) {
        if (i == j) {
            return 0;
        }

        int min = Integer.MAX_VALUE;
        for (int k = i; k < j; ++k) {
            int q = sol1(dimensions, i, k) +
                    sol1(dimensions, k + 1, j) +
                    dimensions[i - 1] * dimensions[k] * dimensions[j];

            if (q < min) {
                min = q;
            }
        }

        return min;
    }

    public static int sol2Impl(int[] dimensions, int i, int j, int[][] m) {
        if (i == j) {
            return 0;
        }

        if (m[i][j] != -1) {
            return m[i][j];
        }

        int min = Integer.MAX_VALUE;
        for (int k = i; k < j; ++k) {
            int q = sol2Impl(dimensions, i, k, m) +
                    sol2Impl(dimensions, k + 1, j, m) +
                    dimensions[i - 1] * dimensions[k] * dimensions[j];

            if (q < min) {
                min = q;
            }
        }

        m[i][j] = min;

        return m[i][j];
    }

    public static int sol2(int[] dimensions) {
        // cache used for already calculated data
        int[][] m = new int[dimensions.length][dimensions.length];

        for (int i = 0; i < dimensions.length; ++i) {
            m[i][i] = 0;
        }
        for (int i = 0; i < dimensions.length; ++i) {
            for (int j = 0; j < dimensions.length; ++j) {
                m[i][j] = -1;
            }
        }

        return sol2Impl(dimensions, 1, dimensions.length - 1, m);
    }

    public static int sol3(int[] dimensions) {
        // cache used for already calculated data
        int[][] m = new int[dimensions.length][dimensions.length];
        int[][] s = new int[dimensions.length][dimensions.length];

        for (int i = 0; i < dimensions.length; ++i) {
            m[i][i] = 0;
        }

        for (int L = 2; L < dimensions.length; ++L) {
            for (int i = 1; i < dimensions.length - L + 1; ++i) {

                int j = i + L - 1;

                m[i][j] = Integer.MAX_VALUE;
                for (int k = i; k < j; ++k) {
                    int q = m[i][k] + m[k + 1][j] +
                            dimensions[i - 1] * dimensions[k] * dimensions[j];

                    if (q < m[i][j]) {
                        m[i][j] = q;
                        s[i][j] = k;
                    }
                }
            }
        }

        print(s, 1, dimensions.length - 1);
        System.out.println();

        return m[1][dimensions.length - 1];
    }

    private static void print(int[][] s, int i, int j) {
        if (i == j) {
            System.out.print("A" + i);
        } else {
            System.out.print("(");
            print(s, i, s[i][j]);
            print(s, s[i][j] + 1, j);
            System.out.print(")");
        }
    }
}
