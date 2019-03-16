/*
 * org.fsola
 *
 * File Name: BFS.java
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
import org.fsola.datastructures.linkedlist.BiDirectionalNullTerminatedTailedLinkedList;
import org.fsola.datastructures.vector.BitVector;

import java.util.List;

/**
 * Breadth-First-Search algorithm for graph traversal.
 * <p/>
 * Used Resources: README file resources section line 1
 */
public class BFS {

    public static <V> void bfs(Graph<V> g, V startVertex, V endVertex,
                               VertexVisitor<V> visitor) {
        bfsImpl(g, startVertex, endVertex, visitor);
    }

    public static <V> void bfs(Graph<V> g, V startVertex,
                               VertexVisitor<V> visitor) {
        bfsImpl(g, startVertex, null, visitor);
    }

    private static <V> void bfsImpl(Graph<V> g, V startVertex, V endVertex,
                                    VertexVisitor<V> visitor) {
        BiDirectionalNullTerminatedTailedLinkedList<V> queue = new
                BiDirectionalNullTerminatedTailedLinkedList<>();

        BitVector used = new BitVector();

        queue.put(startVertex);
        used.setBit(startVertex.hashCode(), true);
        while (!queue.isEmpty()) {
            V vertex = queue.pop();

            // Visit current vertex
            visitor.visit(vertex);

            // If endVertex is null bfsImpl iterates through paths in graph
            if (endVertex != null && endVertex.equals(vertex)) {
                // Ending vertex is found. Stop any processing.
                break;
            }

            List<V> neighbors = g.neighbors(vertex);

            // Add all neighbors for visiting (except if they are already
            // visited)
            for (V y : neighbors) {
                // If this vertex is already visited just ignore it
                if (!used.getBit(y.hashCode())) {
                    // Put this vertex in the queue and mark as used
                    queue.put(y);
                    used.setBit(y.hashCode(), true);

                    visitor.processEdge(vertex, y);
                }
            }
        }
    }
}
