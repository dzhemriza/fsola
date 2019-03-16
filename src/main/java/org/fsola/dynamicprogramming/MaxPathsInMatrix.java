/*
 * org.fsola
 *
 * File Name: MaxPathsInMatrix.java
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
 * Given a matrix MxN find the count of all possible paths from upper left [0][0] corner
 * to bottom down corner [M - 1][N - 1] you have move to the right or to the down.
 * <p/>
 * Used Resources: README file resources section line 51.
 */
public class MaxPathsInMatrix {

    public static int solve1(int M, int N) {
        if (M == 0 && N == 0) {
            return 0;
        }

        if (M == 0 || N == 0) {
            return 1;
        }

        int x = solve1(M - 1, N);
        int y = solve1(M, N - 1);

        return x + y;
    }

    // Matrix:
    // [00] [01] [02]
    // [10] [11] [12]
    //
    // Recursion tree:
    //      [00]
    //   /        \
    // [10]       [01]
    //  |        /    \
    // [11]    [11]   [02]
    //  |       |      |
    // [12]    [12]   [12]
    //

    public static int solve2(int M, int N) {
        int[][] A = new int[M][N];

        A[0][0] = 0;
        for (int i = 1; i < M; ++i) {
            A[i][0] = 1;
        }
        for (int i = 1; i < N; ++i) {
            A[0][i] = 1;
        }

        for (int i = 1; i < M; ++i) {
            for (int j = 1; j < N; ++j) {
                A[i][j] = A[i - 1][j] + A[i][j - 1];
            }
        }

        // Debug:
        System.out.println("Matrix:");
        for (int i = 0; i < M; ++i) {
            for (int j = 0; j < N; ++j) {
                System.out.print(" " + A[i][j]);
            }
            System.out.println();
        }
        System.out.println();

        return A[M - 1][N - 1];
    }
}
