/*
 * org.fsola
 *
 * File Name: LongestPathInDAG.java
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

import org.fsola.datastructures.graph.AdjacencyMatrix;
import org.fsola.datastructures.linkedlist.OneDirectionalNullTerminatedLinkedList;
import org.fsola.introduction.CustomMath;

import java.util.List;

/**
 * Calculates longest path in a given weighted DAG (Directed Acyclic Graph).
 * <p/>
 * Used Resources: README file resources section line 1.
 */
public class LongestPathInDAG {

    public static class Result {
        public float max;
        public OneDirectionalNullTerminatedLinkedList<Integer> path =
                new OneDirectionalNullTerminatedLinkedList<>();
    }

    private static float[] maxDist;
    private static int[] path;

    public static <V> Result longestPath(AdjacencyMatrix<V> graph,
                                         V[] vertices) {
        Result r = new Result();

        maxDist = new float[graph.getNumberOfVertices()];
        path = new int[graph.getNumberOfVertices()];

        for (int i = 0; i < graph.getNumberOfVertices(); ++i) {
            maxDist[i] = 0.0f;
            path[i] = -1;
        }

        for (int i = 0; i < graph.getNumberOfVertices(); ++i) {
            if (CustomMath.equalsFloat(0.0f, maxDist[i])) {
                dfs(graph, vertices[i]);
            }
        }

        // Find maximum in maxDist array
        int max = 0;
        for (int i = 1; i < graph.getNumberOfVertices(); ++i) {
            if (CustomMath.isGreaterThan(maxDist[i], maxDist[max])) {
                max = i;
            }
        }

        r.max = maxDist[max];
        for (; path[max] >= 0; max = path[max]) {
            r.path.push(max);
        }
        r.path.push(max);

        // Reverse the list as all elements are inserted in the front
        r.path.reverse();

        return r;
    }

    private static <V> void dfs(AdjacencyMatrix<V> graph, V startingVertex) {
        if (CustomMath.isGreaterThan(maxDist[startingVertex.hashCode()],
                0.0f)) {
            return;
        }

        float max = maxDist[startingVertex.hashCode()];

        List<V> neighbors = graph.neighbors(startingVertex);
        for (V neighbor : neighbors) {
            dfs(graph, neighbor);

            float dist = maxDist[neighbor.hashCode()] + graph.getEdge
                    (startingVertex, neighbor).getWeight();

            if (dist > max) {
                max = dist;
                path[startingVertex.hashCode()] = neighbor.hashCode();
            }
        }

        maxDist[startingVertex.hashCode()] = max;
    }
}
