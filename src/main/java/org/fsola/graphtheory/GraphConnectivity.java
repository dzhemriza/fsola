/*
 * org.fsola
 *
 * File Name: GraphConnectivity.java
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

import org.fsola.datastructures.graph.AdjacencyList;
import org.fsola.datastructures.queue.LinkedListQueue;
import org.fsola.datastructures.vector.BitVector;

import java.util.ArrayList;
import java.util.List;

/**
 * Find all connected components in graph.
 * <p/>
 * Used Resources: README file resources section line 1.
 */
public class GraphConnectivity {

    public static class Result {
        public List<List<Integer>> components = new ArrayList<>();
    }

    public static <V> Result connectivity(AdjacencyList<V> graph, V[] vertices) {
        Result r = new Result();
        BitVector used = new BitVector();

        for (V vertex : vertices) {
            if (!used.getBit(vertex.hashCode())) {
                List<Integer> cluster = new ArrayList<>();

                bfs(graph, used, vertex, cluster);

                r.components.add(cluster);
            }
        }

        return r;
    }

    private static <V> void bfs(AdjacencyList<V> graph, BitVector used,
                                V start, List<Integer> cluster) {
        LinkedListQueue<V> queue = new LinkedListQueue<>();

        queue.put(start);
        while (!queue.isEmpty()) {
            V vertex = queue.get();

            if (used.getBit(vertex.hashCode())) {
                // This vertex is already processed
                continue;
            }

            // Mark as used
            used.setTrue(vertex.hashCode());

            // Add vertex in current cluster
            cluster.add(vertex.hashCode());

            List<V> neighbors = graph.neighbors(vertex);
            for (V neighbor : neighbors) {

                if (!used.getBit(neighbor.hashCode())) {
                    // If this vertex is not used yet
                    // add into queue for later processing
                    queue.put(neighbor);
                }
            }
        }
    }
}
