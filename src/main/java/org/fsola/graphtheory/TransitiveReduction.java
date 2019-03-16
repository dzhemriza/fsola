/*
 * org.fsola
 *
 * File Name: TransitiveReduction.java
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
package org.fsola.graphtheory;

/**
 * Transitive reduction or reverse Warshall algorithm.
 * Finds given graph reachability matrix to adjacency matrix
 * with minimum number of edges.
 * Graph must be acyclic!
 * <p/>
 * Used Resources: README file resources section line 1, line 42.
 */
public class TransitiveReduction {

    public static int[][] transitiveReduction(int[][] W) {
        int N = W.length;

        int[][] A = new int[N][N];

        // copy matrix
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                A[i][j] = W[i][j];
            }
        }

        for (int k = 0; k < N; ++k) {
            for (int i = 0; i < N; ++i) {
                if (A[i][k] == 1) {
                    for (int j = 0; j < N; ++j) {
                        if (A[i][j] == 1 && W[k][j] == 1) {
                            A[i][j] = 0;
                        }
                    }
                }
            }
        }

        return A;
    }
}
