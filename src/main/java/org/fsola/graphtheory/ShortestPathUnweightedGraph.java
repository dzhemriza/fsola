/*
 * org.fsola
 *
 * File Name: ShortestPathUnweightedGraph.java
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
import org.fsola.datastructures.linkedlist.OneDirectionalNullTerminatedLinkedList;

/**
 * Unweighted graph shortest path algorithm based on BFS.
 * <p/>
 * Used Resources: README file resources section line 1
 */
public class ShortestPathUnweightedGraph {
    private static int MAX = 1024;

    private static class ShortestPathVertexVisitor<V> implements
            VertexVisitor<V> {

        private V[] previousVertex;

        public ShortestPathVertexVisitor(V[] previousVertex) {
            this.previousVertex = previousVertex;
        }

        @Override
        public void visit(V vertex) {
            // Do nothing
        }

        @Override
        public void processEdge(V parent, V child) {
            previousVertex[child.hashCode()] = parent;
        }
    }

    /**
     * Find shortest path in unweighted graph and return a list of vertices the
     * length of the path is {@code list.size() - 1}.
     *
     * @param g
     * @param startVertex
     * @param endVertex
     * @param <V>
     * @return
     */
    public static <V> OneDirectionalNullTerminatedLinkedList<V> shortestPath(
            Graph<V> g, V startVertex,
            V endVertex) {
        // previousVertex hash could be replaced with hash table.
        V[] previousVertex = (V[]) new Object[MAX];

        for (int i = 0; i < previousVertex.length; ++i) {
            previousVertex[i] = null;
        }

        VertexVisitor<V> visitor = new ShortestPathVertexVisitor<V>
                (previousVertex);

        BFS.bfs(g, startVertex, endVertex, visitor);

        OneDirectionalNullTerminatedLinkedList<V> result =
                new OneDirectionalNullTerminatedLinkedList<>();

        // Building the path could be replaced with recursive algorithm. In
        // current implementation is using simple stack (implemented using
        // linked list)
        V v = endVertex;
        for (; (v != startVertex) && (v != null);
             v = previousVertex[v.hashCode()]) {
            result.push(v);
        }

        if (v == startVertex) {
            result.push(startVertex);
        } else {
            // No such path so it's better to clear the stack
            result.clear();
        }

        return result;
    }
}
