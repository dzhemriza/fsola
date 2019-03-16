/*
 * org.fsola
 *
 * File Name: AdjacencyList.java
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
package org.fsola.datastructures.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * Graph representation data structure - Adjacency List.
 * <p/>
 * Used Resources: README file resources section line 1, line 21, line 32
 * <p/>
 * Complexity by operations: adjacent - O(Ne), neighbors - O(Ne), add - O(Ne),
 * delete - O(Ne).
 *
 * @param <V>
 */
public class AdjacencyList<V> implements Graph<V> {

    /**
     * Edge node in {@link org.fsola.datastructures.graph.AdjacencyList
     * .AdjacencyListInternal}.
     *
     * @param <V>
     */
    public static class EdgeNode<V> {
        /**
         * Position of the vertex from array of vertices.
         */
        private int y;

        /**
         * Weight of this edge.
         */
        private float weight;

        /**
         * Next edge node
         */
        private EdgeNode<V> next;

        public int getY() {
            return y;
        }

        public float getWeight() {
            return weight;
        }
    }

    /**
     * Internal data structure used to represent adjacency list.
     *
     * @param <V>
     */
    public static class AdjacencyListInternal<V> {
        /**
         * Array of vertices
         */
        private ArrayList<V> vertices = new ArrayList<>();

        /**
         * Array of edges mapped to {@link #vertices}.
         */
        private ArrayList<EdgeNode<V>> edges = new ArrayList<>();

        /**
         * Flag indicating that this graph is directed or not.
         */
        private boolean directed;

        /**
         * Outdegree of each vertex.
         */
        private ArrayList<Integer> degree = new ArrayList<>();

        public AdjacencyListInternal(boolean directed) {
            this.directed = directed;
        }

        public boolean isDirected() {
            return directed;
        }
    }

    private AdjacencyListInternal<V> adjacencyList = new
            AdjacencyListInternal<>(true);

    public AdjacencyList(int numberOfVertices) {
        for (int i = 0; i < numberOfVertices; ++i) {
            adjacencyList.vertices.add(null);
            adjacencyList.edges.add(null);
            adjacencyList.degree.add(0);
        }
    }

    @Override
    public boolean adjacent(V x, V y) {
        int X = x.hashCode();
        int Y = y.hashCode();

        V vertex = adjacencyList.vertices.get(X);
        if (vertex != null) {
            for (EdgeNode<V> edge = adjacencyList.edges.get(X); edge != null;
                 edge = edge.next) {

                if (edge.y == Y) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public List<V> neighbors(V x) {
        ArrayList<V> result = new ArrayList<>();

        int X = x.hashCode();
        V vertex = adjacencyList.vertices.get(X);
        if (vertex != null) {
            for (EdgeNode<V> edge = adjacencyList.edges.get(X); edge != null;
                 edge = edge.next) {
                result.add(adjacencyList.vertices.get(edge.y));
            }
        }

        return result;
    }

    @Override
    public void add(V x, V y, float weight) {
        int X = x.hashCode();
        int Y = y.hashCode();

        V vertex = adjacencyList.vertices.get(X);

        if (vertex == null) {
            adjacencyList.vertices.set(X, x);
            int deg = adjacencyList.degree.get(X);
            adjacencyList.degree.set(X, deg + 1);
        }

        EdgeNode<V> edge = adjacencyList.edges.get(X);
        for (; edge != null; edge = edge.next) {

            if (edge.y == Y) {
                // Update existing edge and return
                edge.weight = weight;
                return;
            }
        }

        // Add new edge
        EdgeNode<V> newEdge = new EdgeNode<>();
        newEdge.y = Y;
        newEdge.weight = weight;

        edge = adjacencyList.edges.get(X);
        newEdge.next = edge;
        adjacencyList.edges.set(X, newEdge);
    }

    @Override
    public void delete(V x, V y) {
        int X = x.hashCode();
        int Y = y.hashCode();

        V vertex = adjacencyList.vertices.get(X);
        if (vertex != null) {
            int deg = adjacencyList.degree.get(X);

            if (deg > 0) {
                adjacencyList.degree.set(X, deg - 1);
            }

            for (EdgeNode<V> edge = adjacencyList.edges.get(X),
                         prev = null; edge != null; prev = edge,
                         edge = edge.next) {
                if (edge.y == Y) {
                    // Delete this node
                    if (prev != null) {
                        prev.next = edge.next;
                    } else {
                        // Update head element
                        adjacencyList.edges.set(X, edge.next);
                    }
                }
            }
        }
    }

    @Override
    public Edge<V> getEdge(V x, V y) {
        int X = x.hashCode();
        int Y = y.hashCode();

        V vertex = adjacencyList.vertices.get(X);
        if (vertex != null) {
            for (EdgeNode<V> edge = adjacencyList.edges.get(X); edge != null;
                 edge = edge.next) {

                if (edge.y == Y) {
                    return new Edge<>(x, y, edge.weight);
                }
            }
        }

        return null;
    }
}
