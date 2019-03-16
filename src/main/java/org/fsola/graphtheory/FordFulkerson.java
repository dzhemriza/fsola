/*
 * org.fsola
 *
 * File Name: FordFulkerson.java
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
import org.fsola.datastructures.queue.LinkedListQueue;
import org.fsola.datastructures.vector.BitVector;

/**
 * Ford Fulkerson method.
 * <p/>
 * Used Resources: README file resources section line 1, line 39, line 43.
 */
public class FordFulkerson {

    /**
     * Run BFS to find augmenting path between {@code source} and {@code sink}.
     *
     * @param matrix
     * @param source
     * @param sink
     * @param predecessors
     * @return
     */
    private static boolean bfs(float[][] matrix, int source,
                               int sink, int[] predecessors) {
        for (int i = 0; i < predecessors.length; ++i) {
            predecessors[i] = -1;
        }

        BitVector visited = new BitVector();
        LinkedListQueue<Integer> q = new LinkedListQueue<>();
        q.put(source);
        visited.setBit(source, true);

        while (!q.isEmpty()) {
            int u = q.get();

            for (int v = 0; v < matrix.length; ++v) {
                // If there is a path between u and v and if this path is not
                // visited yet
                if (matrix[u][v] > 0.0f && !visited.getBit(v)) {
                    visited.setBit(v, true);
                    q.put(v);
                    predecessors[v] = u;
                }
            }
        }

        return visited.getBit(sink);
    }

    public static <V> float[][] fordFulkerson(AdjacencyMatrix<V> graph,
                                              V[] vertices, int source, int sink) {
        float[][] F = new float[vertices.length][vertices.length];

        float[][] matrix = graph.cloneMatrix();
        int[] predecessors = new int[vertices.length];

        for (int i = 0; i < vertices.length; ++i) {
            for (int j = 0; j < vertices.length; ++j) {
                F[i][j] = 0.0f;
            }
        }

        // Find augmenting path
        while (bfs(matrix, source, sink, predecessors)) {
            // Update flow
            float pathFlow = Float.MAX_VALUE;

            // Find minimum residual capacity
            for (int v = sink; v != source; v = predecessors[v]) {
                int u = predecessors[v]; // parent
                pathFlow = Math.min(pathFlow, matrix[u][v]);
            }

            // Update residual capacities
            for (int v = sink; v != source; v = predecessors[v]) {
                int u = predecessors[v]; // parent

                matrix[u][v] -= pathFlow;
                matrix[v][u] += pathFlow;

                F[u][v] += pathFlow;
                F[v][u] -= pathFlow;
            }
        }

        return F;
    }
}
