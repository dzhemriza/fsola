/*
 * org.fsola
 *
 * File Name: MinimumDominatingSet.java
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

import org.fsola.datastructures.graph.AdjacencyMatrix;
import org.fsola.datastructures.vector.Vector;

/**
 * Find minimum dominating set in graph.
 * <p/>
 * Used Resources: README file resources section line 1.
 */
public class MinimumDominatingSet {

    public static class Result<V> {
        public Vector<Vector<V>> mimimumDominatingSet = new Vector<>();
    }

    private static <V> void print(int[] T, V[] vertices, Result<V> r) {
        Vector<V> sr = new Vector<>();
        for (int i = 0; i < T.length; ++i) {
            if (T[i] > 0) {
                sr.pushBack(vertices[i]);
            }
        }

        r.mimimumDominatingSet.pushBack(sr);
    }

    private static boolean isOk(int[][] A, int[] T, int[] cover) {
        for (int i = 0; i < T.length; ++i) {
            if (T[i] > 0) {
                if (cover[i] == 0) {
                    // Check is the cover is still exists after removing i vertex
                    continue;
                }

                int j;
                for (j = 0; j < T.length; ++j) {
                    if (cover[j] > 0) {
                        if (!(cover[j] - 1 > 0) && !(T[i] > 0)) {
                            // There is uncovered vertex
                            break;
                        }
                    }
                }

                if (j == T.length) {
                    return false;
                }
            }
        }
        return true;
    }

    private static <V> void solveImpl(int[][] A, V[] vertices, int[] T,
                                      int[] cover, int last, Result<V> r) {
        // Check is there is already a solution
        int i;
        for (i = 0; i < vertices.length; ++i) {
            if (!(T[i] > 0) && !(cover[i] > 0)) {
                break;
            }
        }

        if (i == vertices.length) {
            print(T, vertices, r);
            return;
        }

        // Construct minimum dominating set
        for (i = last; i < vertices.length; ++i) {
            // Mark
            T[i] = 1;
            for (int j = 0; j < vertices.length; ++j) {
                if (A[i][j] > 0) {
                    cover[j]++;
                }
            }

            if (isOk(A, T, cover)) {
                solveImpl(A, vertices, T, cover, i + 1, r);
            }

            // Un-mark
            for (int j = 0; j < vertices.length; ++j) {
                if (A[i][j] > 0) {
                    cover[j]--;
                }
            }
            T[i] = 0;
        }
    }

    public static <V> Result<V> solve(AdjacencyMatrix<V> graph, V[] vertices) {
        Result<V> r = new Result<>();

        int[][] A = graph.cloneToUnweightedMatrix();
        int[] T = new int[vertices.length];
        int[] cover = new int[vertices.length];

        for (int i = 0; i < vertices.length; ++i) {
            T[i] = 0;
            cover[i] = 0;
        }

        solveImpl(A, vertices, T, cover, 0, r);

        return r;
    }
}
