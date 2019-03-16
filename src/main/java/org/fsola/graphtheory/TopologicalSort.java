/*
 * org.fsola
 *
 * File Name: TopologicalSort.java
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
import org.fsola.introduction.SimpleAritmetics;

import java.util.ArrayList;
import java.util.List;

/**
 * Topological sort of a DAG using DFS backtrace.
 * <p/>
 * Used Resources: README file resources section line 1.
 */
public class TopologicalSort {

    public static <V> List<V> sort(AdjacencyMatrix<V> graph, V[] vertices) {
        ArrayList<V> result = new ArrayList<>();

        int[][] m = graph.cloneToUnweightedMatrix();
        ArrayList<Integer> r = new ArrayList<>();
        BitVector used = new BitVector();

        for (int i = 0; i < vertices.length; ++i) {
            if (!used.getBit(i)) {
                dfs(m, used, r, i);
            }
        }

        for (Integer i : r) {
            result.add(vertices[i]);
        }

        SimpleAritmetics.reverseArrayList(result);
        return result;
    }

    /**
     * Simple DFS in DAG for finding such vertex that has no outgoing edges.
     *
     * @param m
     * @param used
     * @param r
     * @param start
     */
    private static void dfs(int[][] m, BitVector used,
                            ArrayList<Integer> r, int start) {
        used.setTrue(start);

        for (int i = 0; i < m.length; ++i) {
            if (m[start][i] == 1 && !used.getBit(i)) {
                dfs(m, used, r, i);
            }
        }

        r.add(start);
    }
}
