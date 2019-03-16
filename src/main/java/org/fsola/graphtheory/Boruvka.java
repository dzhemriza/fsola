/*
 * org.fsola
 *
 * File Name: Boruvka.java
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

import java.util.ArrayList;

import static org.fsola.datastructures.linkedlist.BiDirectionalNullTerminatedLinkedList.Node;

/**
 * Borůvka algorithm for finding minimum spanning tree.
 * <p/>
 * Used Resources: README file resources section line 60, line 61.
 */
public class Boruvka {

    public static class Result<V> {
        /**
         * Edges that are part of minimum spanning tree.
         */
        public ArrayList<Edge<V>> edgeList = new ArrayList<>();

        /**
         * Total weight of minimum spanning tree.
         */
        public float totalWeight = 0.0f;
    }

    public static <V> Result<V> boruvka(V[] vertices, EdgeList<V> graph) {
        Result<V> r = new Result<>();
        Edge<V>[] minEdges = new Edge[vertices.length];
        WeightedQuickUnion uf = new WeightedQuickUnion(vertices.length);

        for (int t = 1;
             t < vertices.length && r.edgeList.size() < vertices.length - 1;
             t = t + t) {

            for (int i = 0; i < minEdges.length; ++i) {
                minEdges[i] = null;
            }

            // Prepare minimum weight edges
            BiDirectionalNullTerminatedLinkedList<Edge<V>> edges = graph.getAllEdges();
            for (Node<Edge<V>> p = edges.getHead(); p != null; p = p.getNext()) {
                Edge<V> edge = p.getData();

                int i = uf.findRoot(edge.getX().hashCode());
                int j = uf.findRoot(edge.getY().hashCode());

                if (i != j) {
                    // i and j doesn't belong to one connected component
                    if (minEdges[i] == null || edge.getWeight() < minEdges[i].getWeight()) {
                        minEdges[i] = edge;
                    }

                    if (minEdges[j] == null || edge.getWeight() < minEdges[j].getWeight()) {
                        minEdges[j] = edge;
                    }
                }
            }

            // Connect components
            for (int i = 0; i < minEdges.length; ++i) {
                if (minEdges[i] != null) {
                    Edge<V> edge = minEdges[i];

                    if (!uf.isConnected(
                            edge.getX().hashCode(),
                            edge.getY().hashCode())) {

                        uf.union(edge.getX().hashCode(),
                                edge.getY().hashCode());

                        r.edgeList.add(edge);
                        r.totalWeight += edge.getWeight();
                    }
                }
            }
        }

        return r;
    }
}
