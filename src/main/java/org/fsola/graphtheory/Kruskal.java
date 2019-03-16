/*
 * org.fsola
 *
 * File Name: Kruskal.java
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

import java.util.ArrayList;

/**
 * Kruskal algorithm for finding minimum spanning tree using
 * {@link org.fsola.datastructures.graph.EdgeList}.
 * <p/>
 * Used Resources: README file resources section line 1, line 57, line 58.
 */
public class Kruskal {

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

    public static <V> Result<V> kruskal(V[] vertices, EdgeList<V> graph) {
        WeightedQuickUnion uf = new WeightedQuickUnion(vertices.length);
        Result<V> r = new Result<>();

        Edge<V>[] edges = graph.getSortedEdgeArray();

        for (Edge<V> edge : edges) {
            if (uf.unionOperation(edge.getX().hashCode(), edge.getY().hashCode())) {
                r.edgeList.add(edge);
                r.totalWeight += edge.getWeight();
            }
        }

        return r;
    }
}
