/*
 * org.fsola
 *
 * File Name: GraphCycleTest.java
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

import org.fsola.datastructures.graph.Graph;
import org.fsola.datastructures.vector.BitVector;

import java.util.List;

/**
 * Algorithm for testing for cycle in a given graph using DFS algorithm.
 * <p/>
 * Used Resources: README file resources section line 1
 */
public class GraphCycleTest {

    private static BitVector visited;
    private static boolean cycleDetected = false;

    private static <V> void dfsImpl(Graph<V> graph, V startVertex) {
        if (visited.getBit(startVertex.hashCode())) {
            // This vertex is already visited so we just end of the recursion
            // here.
            return;
        }

        // Mark as visited
        visited.setBit(startVertex.hashCode(), true);

        // Recursive call to all incident vertices
        List<V> neighbors = graph.neighbors(startVertex);

        for (V y : neighbors) {
            if (visited.getBit(y.hashCode()) && y != startVertex) {
                cycleDetected = true;
            } else if (!visited.getBit(y.hashCode())) {
                dfsImpl(graph, y);
            }
        }
    }

    public static <V> boolean hasCycle(Graph<V> g, V startVertex) {
        // Clear visited vertices bit vector
        visited = new BitVector();
        cycleDetected = false;

        dfsImpl(g, startVertex);

        return cycleDetected;
    }
}
