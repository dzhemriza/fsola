/*
 * org.fsola
 *
 * File Name: TSPBacktrace.java
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
 * Calculate min Hamilton cycle - Traveling Salesman Problem.
 * <p/>
 * Used Resources: README file resources section line 1
 */
public class TSPBacktrace {

    public static class Result<V> {
        public List<V> minCycle = new ArrayList<>();
        public float cost = 0.0f;
    }

    public static <V> Result<V> hamilton(AdjacencyMatrix<V> graph,
                                         V[] vertices) {
        Result<V> result = new Result<>();
        result.cost = Float.MAX_VALUE;
        Result<V> current = new Result<>();
        BitVector used = new BitVector();

        dfs(graph, vertices, used, current, result, vertices[0], 0);

        return result;
    }

    private static <V> void dfs(AdjacencyMatrix<V> graph,
                                V[] vertices, BitVector used,
                                Result<V> current, Result<V> result, V start,
                                int level) {

        // If graph contains a Hamilton cycle this means that vertex 0 is
        // definitely in the cycle and if this is not the first time when we
        // call backtrace.
        if (start.equals(vertices[0]) && level > 0) {
            // If the level is equals to the number of vertices this means
            // that a min Hamilton cycle is detected.
            if (level == vertices.length) {
                // Update min cost
                result.cost = current.cost;
                // Cleanup previous result and fill it with the new min value
                result.minCycle.clear();
                for (V p : current.minCycle) {
                    result.minCycle.add(p);
                }
            }
        }

        // If we already process this vertex exit processing further
        if (used.getBit(start.hashCode())) {
            return;
        }

        // Mark as used in cycle
        used.setBit(start.hashCode(), true);

        List<V> neighbors = graph.neighbors(start);
        for (V neighbor : neighbors) {
            current.cost += graph.getWeight(start, neighbor);
            current.minCycle.add(neighbor);

            // Stop processing vertices that are not optimal. This is similar
            // to Alpha–beta pruning.
            if (current.cost < result.cost) {
                dfs(graph, vertices, used, current, result, neighbor,
                        level + 1);
            }

            current.minCycle.remove(current.minCycle.size() - 1);
            current.cost -= graph.getWeight(start, neighbor);
        }

        // Un-mark
        used.setBit(start.hashCode(), false);
    }
}
