/*
 * org.fsola
 *
 * File Name: PrintMatrixInSpiralForm.java
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
package org.fsola.misc;

import java.util.ArrayList;
import java.util.List;

/**
 * Prints given matrix in spiral form starting from (0, 0) coordinates.
 */
public class PrintMatrixInSpiralForm {

    public static List<Integer> spiral(int[][] matrix) {
        ArrayList<Integer> r = new ArrayList<>();

        int M = matrix.length;
        int N = matrix[0].length;

        int K = Math.min(N, M) / 2;

        int i = 0;
        int j = 0;
        int n = N - 1;
        int m = M - 1;

        for (int l = 0; l <= K; ++l, ++i, ++j, --n, --m) {
            square(i, n, j, m, matrix, r);
        }

        return r;
    }

    private static void square(int i, int n, int j, int m, int[][] matrix, List<Integer> r) {
        for (int k = i; k <= n; ++k) {
            r.add(matrix[j][k]);
        }
        if ((m - j) != 0) { // Check for only one row
            for (int k = j + 1; k <= m; ++k) {
                r.add(matrix[k][n]);
            }
            for (int k = n - 1; k >= i; --k) {
                r.add(matrix[m][k]);
            }
            for (int k = m - 1; k >= j + 1; --k) {
                r.add(matrix[k][i]);
            }
        }
    }
}
