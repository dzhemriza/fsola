/*
 * org.fsola
 *
 * File Name: BellmanFord.java
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

import org.fsola.datastructures.graph.Edge;
import org.fsola.datastructures.graph.EdgeList;
import org.fsola.datastructures.linkedlist.BiDirectionalNullTerminatedLinkedList;

import static org.fsola.datastructures.linkedlist.BiDirectionalNullTerminatedLinkedList.Node;

/**
 * Bellman-Ford algorithm for finding single source shortest path to all other
 * vertices. This algorithm also is capable of detecting a negative paths.
 * <p/>
 * Used Resources: README file resources section line 1, line 33, line 34, line
 * 35.
 */
public class BellmanFord {

    /**
     * Marked indicating infinite number.
     */
    public static float INF = 100000.0f;

    public static class GraphContainsNegativeEdgesException extends
            RuntimeException {
    }

    public static <V> float[] bellmanFord(V[] vertices, EdgeList<V> graph,
                                          V startingVertex) {
        float[] distances = new float[vertices.length];

        for (int i = 0; i < vertices.length; ++i) {
            distances[i] = INF;
        }
        distances[startingVertex.hashCode()] = 0.0f;

        BiDirectionalNullTerminatedLinkedList<Edge<V>> edges = graph
                .getAllEdges();

        // Find single source shortest path
        for (int i = 0; i < vertices.length - 1; ++i) {
            for (Node<Edge<V>> p = edges.getHead(); p != null;
                 p = p.getNext()) {
                relaxEdge(p.getData(), distances);
            }
        }

        // Check for negative edges
        for (Node<Edge<V>> p = edges.getHead(); p != null; p = p.getNext()) {
            negativeCheck(p.getData(), distances);
        }

        return distances;
    }

    private static <V> void relaxEdge(Edge<V> edge, float[] distances) {
        float W = edge.getWeight();
        V u = edge.getX();
        V v = edge.getY();
        int U = u.hashCode();
        int V = v.hashCode();

        if (distances[V] > distances[U] + W) {
            distances[V] = distances[U] + W;
        }
    }

    private static <V> void negativeCheck(Edge<V> edge, float[] distances) {
        float W = edge.getWeight();
        V u = edge.getX();
        V v = edge.getY();
        int U = u.hashCode();
        int V = v.hashCode();

        if (distances[V] > distances[U] + W) {
            throw new GraphContainsNegativeEdgesException();
        }
    }
}
