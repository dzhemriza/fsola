/*
 * org.fsola
 *
 * File Name: FullTopologicalSort.java
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
import org.fsola.datastructures.vector.BitVector;

import java.util.ArrayList;
import java.util.List;

/**
 * Topological sort of using back tracing.
 * <p/>
 * Used Resources: README file resources section line 1.
 */
public class FullTopologicalSort {

    public static class Result {
        public List<List<Integer>> sort = new ArrayList<>();
    }

    public static <V> Result fullSort(AdjacencyMatrix<V> graph, V[] vertices) {
        BitVector used = new BitVector();
        int[][] matrix = graph.cloneToUnweightedMatrix();

        Result r = new Result();
        int[] topSort = new int[vertices.length];

        fullSortImpl(r, matrix, used, topSort, 0);

        return r;
    }

    private static void fullSortImpl(Result r, int[][] matrix, BitVector used,
                                     int[] topSort, int count) {
        if (count == matrix.length) {
            printSort(r, topSort, count);
            return;
        }

        // Find all vertices without parent
        for (int i = 0; i < matrix.length; ++i) {
            if (!used.getBit(i)) {

                // Find such edge from j -> i
                boolean noParent = true;
                for (int j = 0; j < matrix.length; ++j) {
                    if (matrix[j][i] > 0) {
                        noParent = false;
                        break;
                    }
                }

                if (noParent) { // no parent
                    // save information for all neighbors
                    int saved[] = new int[matrix.length];

                    for (int k = 0; k < matrix.length; ++k) {
                        saved[k] = matrix[i][k]; // save neighbors

                        // remove neighbors from recursion
                        matrix[i][k] = 0;
                    }
                    used.setTrue(i); // mark as used

                    // add element in the stack
                    topSort[count] = i;

                    // find another solution
                    fullSortImpl(r, matrix, used, topSort, count + 1);

                    used.setFalse(i); // un-mark as used
                    // restore saved neighbors
                    for (int k = 0; k < matrix.length; ++k) {
                        matrix[i][k] = saved[k];
                    }
                }
            }
        }
    }

    private static void printSort(Result r, int[] topSort, int len) {
        ArrayList<Integer> x = new ArrayList<>();
        for (int i = 0; i < len; ++i) {
            x.add(topSort[i]);
        }
        r.sort.add(x);
    }
}
