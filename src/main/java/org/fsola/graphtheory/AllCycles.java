/*
 * org.fsola
 *
 * File Name: AllCycles.java
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

import java.util.LinkedList;
import java.util.List;

/**
 * Finds all simple cycles in undirected graph. First find a random spanning
 * tree then add new edge to tree.
 * <p/>
 * Used Resources: README file resources section line 1.
 */
public class AllCycles {

    private static BitVector used;
    private static int[][] matrix;
    private static List<Integer> cycle;
    private static List<List<Integer>> result;

    public static <V> List<List<Integer>> allCycles(V[] vertices,
                                                    AdjacencyMatrix<V> graph) {
        matrix = graph.cloneToUnweightedMatrix();
        used = new BitVector();
        result = new LinkedList<>();

        // Find random spanning tree using dfs
        dfs1(graph, vertices[0]);

        for (int i = 0; i < graph.getNumberOfVertices(); ++i) {
            for (int j = i + 1; j < graph.getNumberOfVertices(); ++j) {
                // Find a edge which is not part of the spanning tree
                if (matrix[i][j] == 1) {
                    used = new BitVector();
                    cycle = new LinkedList<>();

                    cycle.add(i);
                    dfs2(graph, vertices[i], vertices[j]);
                }
            }
        }

        return result;
    }

    private static <V> void dfs2(AdjacencyMatrix<V> graph, V start, V end) {
        if (start.equals(end)) {
            // Cycle is detected
            List<Integer> c = new LinkedList<>();
            for (Integer i : cycle) {
                c.add(i);
            }
            result.add(c);
        }

        used.setBit(start.hashCode(), true);
        List<V> neighbors = graph.neighbors(start);
        for (V neighbor : neighbors) {
            if (!used.getBit(neighbor.hashCode())) {
                int X = start.hashCode();
                int Y = neighbor.hashCode();

                // Does this edge is part of the spanning tree
                if (matrix[X][Y] == 2) {
                    cycle.add(neighbor.hashCode());
                    dfs2(graph, neighbor, end);
                    cycle.remove(cycle.size() - 1);
                }
            }
        }
    }

    private static <V> void dfs1(AdjacencyMatrix<V> graph, V startingVertex) {
        used.setBit(startingVertex.hashCode(), true);

        List<V> neighbors = graph.neighbors(startingVertex);
        for (V neighbor : neighbors) {
            if (!used.getBit(neighbor.hashCode())) {
                int X = startingVertex.hashCode();
                int Y = neighbor.hashCode();

                // Add this edge in spanning tree
                matrix[X][Y] = 2;
                matrix[Y][X] = 2;
                dfs1(graph, neighbor);
            }
        }
    }
}
