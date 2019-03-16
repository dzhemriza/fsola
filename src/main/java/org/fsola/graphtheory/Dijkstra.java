/*
 * org.fsola
 *
 * File Name: Dijkstra.java
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
import org.fsola.datastructures.graph.Edge;
import org.fsola.datastructures.vector.BitVector;

import java.util.List;

/**
 * Dijkstra algorithm for finding single source shortest path to all other
 * vertices. This algorithm also is NOT capable of detecting a negative paths.
 * <p/>
 * Used Resources: README file resources section line 1, line 21, line 39, line
 * 40, line 41.
 */
public class Dijkstra {

    public static float MAX = 1000.0f;

    public static class Result {
        public float[] distances;
        public int[] predecessors;
    }

    public static <V> Result dijkstra(V[] vertices, AdjacencyList<V> graph,
                                      V startingVertex) {
        Result r = new Result();
        r.distances = new float[vertices.length];
        r.predecessors = new int[vertices.length];
        BitVector inTree = new BitVector();

        for (int i = 0; i < r.distances.length; ++i) {
            r.distances[i] = MAX;
            r.predecessors[i] = -1;
        }
        r.distances[startingVertex.hashCode()] = 0.0f;

        V v = startingVertex;

        while (!inTree.getBit(v.hashCode())) {
            // Remove element for further processing
            inTree.setBit(v.hashCode(), true);

            List<V> neighbors = graph.neighbors(v);
            for (V neighbor : neighbors) {
                Edge<V> edge = graph.getEdge(v, neighbor);

                // Relax the edge
                float W = edge.getWeight();
                V x = edge.getX();
                V y = edge.getY();
                int U = x.hashCode();
                int V = y.hashCode();
                if (r.distances[V] > r.distances[U] + W) {
                    r.distances[V] = r.distances[U] + W;
                    r.predecessors[V] = v.hashCode();
                }
            }

            // Find next vertex based on minimum cost
            // The best optimization is to use Fibonacci Heap as priority queue
            float d = MAX;
            for (int i = 0; i < vertices.length; ++i) {
                if (!inTree.getBit(vertices[i].hashCode())) {
                    if (d > r.distances[i]) {
                        d = r.distances[i];
                        v = vertices[i];
                    }
                }
            }
        }

        return r;
    }
}
