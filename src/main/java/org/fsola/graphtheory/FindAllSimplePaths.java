/*
 * org.fsola
 *
 * File Name: FindAllSimplePaths.java
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

import java.util.ArrayList;
import java.util.List;

/**
 * Algorithm for finding all simple paths in given graph between two vertices
 * using DFS algorithm.
 * <p/>
 * Used Resources: README file resources section line 1
 */
public class FindAllSimplePaths {

    private static int MAX = 1024;
    private static int pathCount = 0;

    private static <V> void constructPath(V[] path, int count,
                                          List<List<V>> result) {
        ArrayList<V> simplePath = new ArrayList<>();

        for (int i = 0; i < count; ++i) {
            simplePath.add(path[i]);
        }

        result.add(simplePath);
    }

    private static <V> void dfsImpl(Graph<V> graph, V startVertex,
                                    V endVertex, BitVector visited,
                                    V[] path, List<List<V>> result) {
        if (visited.getBit(startVertex.hashCode())) {
            // This vertex is already visited so we just end of the recursion
            // here.
            return;
        }

        if (startVertex == endVertex) {
            // We find a simple path
            path[pathCount] = endVertex;
            constructPath(path, pathCount + 1, result);
        }

        // Mark as visited
        visited.setBit(startVertex.hashCode(), true);
        path[pathCount++] = startVertex;

        // Recursive call to all incident vertices
        List<V> neighbors = graph.neighbors(startVertex);

        for (V y : neighbors) {
            if (!visited.getBit(y.hashCode())) {
                dfsImpl(graph, y, endVertex, visited, path, result);
            }
        }

        // Un-mark as visited
        visited.setBit(startVertex.hashCode(), false);
        pathCount--;
    }

    public static <V> List<List<V>> solve(Graph<V> graph, V startVertex,
                                          V endVertex) {
        BitVector visited = new BitVector();
        List<List<V>> result = new ArrayList<>();
        V[] path = (V[]) new Object[MAX];

        for (int i = 0; i < path.length; ++i) {
            path[i] = null;
        }
        pathCount = 0;

        dfsImpl(graph, startVertex, endVertex, visited, path, result);

        return result;
    }
}
