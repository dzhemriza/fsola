/*
 * org.fsola
 *
 * File Name: StronglyConnectedComponents.java
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
 * Find all strongly connected components in graph.
 * <p/>
 * Used Resources: README file resources section line 1
 */
public class StronglyConnectedComponents {

    public static class Result {
        public List<List<Integer>> components = new ArrayList<>();
    }

    private static int postnum[];
    private static int count = 0;

    public static <V> Result components(AdjacencyMatrix<V> graph, V[] vertices) {
        Result r = new Result();
        int matrix[][] = graph.cloneToUnweightedMatrix();

        postnum = new int[vertices.length];
        count = 0;

        BitVector used = new BitVector();

        for (int i = 0; i < vertices.length; ++i) {
            if (!used.getBit(i)) {
                dfs(matrix, used, i);
            }
        }

        used = new BitVector();

        while (true) {
            int max = -1; // find max postnum un-used yet
            for (int i = 0; i < vertices.length; ++i) {
                if (!used.getBit(i)) {
                    if (max == -1) {
                        max = i;
                    }

                    if (postnum[max] < postnum[i]) {
                        max = i;
                    }
                }
            }

            if (max == -1) {
                break;
            }

            List<Integer> c = new ArrayList<>();
            backDfs(matrix, used, max, c);

            r.components.add(c);
        }

        return r;
    }

    private static void backDfs(int[][] matrix, BitVector used, int start, List<Integer> r) {
        used.setTrue(start);
        r.add(start);
        for (int i = 0; i < matrix.length; ++i) {
            if (!used.getBit(i) && matrix[i][start] == 1) {
                backDfs(matrix, used, i, r);
            }
        }
    }

    private static void dfs(int[][] matrix, BitVector used, int start) {
        used.setTrue(start);
        for (int i = 0; i < matrix.length; ++i) {
            if (matrix[start][i] == 1 && !used.getBit(i)) {
                dfs(matrix, used, i);
            }
        }
        postnum[start] = count++;
    }
}
