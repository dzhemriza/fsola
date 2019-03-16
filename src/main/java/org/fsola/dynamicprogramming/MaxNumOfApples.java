/*
 * org.fsola
 *
 * File Name: MaxNumOfApples.java
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
 * A table composed of N x M cells, each having a certain
 * quantity of apples, is given. You start from the
 * upper-left corner. At each step you can go down or
 * right one cell. Find the maximum number of apples you
 * can collect.
 * <p/>
 * Used Resources: README file resources section line 48.
 */
public class MaxNumOfApples {

    public static int apples(int A[][]) {
        int M = A.length;
        int N = A[0].length;
        int[][] S = new int[M][N];

        for (int i = 0; i < M; ++i) {
            for (int j = 0; j < N; ++j) {
                int t = 0;

                if (0 <= i - 1) {
                    t = S[i - 1][j];
                }

                int l = 0;

                if (0 <= j - 1) {
                    l = S[i][j - 1];
                }

                S[i][j] = A[i][j] + Math.max(t, l);
            }
        }

        return S[M - 1][N - 1];
    }
}
