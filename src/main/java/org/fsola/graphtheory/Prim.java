/*
 * org.fsola
 *
 * File Name: Prim.java
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
import org.fsola.datastructures.graph.Edge;
import org.fsola.datastructures.vector.BitVector;
import org.fsola.introduction.CustomMath;

import java.util.ArrayList;

/**
 * Prim algorithm for finding minimum spanning tree.
 * <p/>
 * Used Resources: README file resources section line 1, line 58, line 59.
 */
public class Prim {

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

    private static final float INF = 100000.0f;

    public static <V> Result<V> prim(V[] vertices, AdjacencyMatrix<V> graph) {
        Result<V> r = new Result<>();
        // Vertices that are already visited (vertices included in the tree)
        BitVector inTree = new BitVector();
        int[] predecessor = new int[vertices.length];
        float[] dist = new float[vertices.length];
        float[][] A = graph.cloneMatrix();

        for (int i = 0; i < vertices.length; ++i) {
            dist[i] = INF;
            predecessor[i] = -1;
        }
        dist[0] = 0;

        for (int i = 0; i < vertices.length - 1; ++i) {
            int u = findMin(inTree, dist);

            // Mark this vertex as visited (part of MST)
            inTree.setTrue(u);

            // Search for vertex v such that has minimum weight
            for (int v = 0; v < vertices.length; ++v) {
                // Is there a edge (u, v)
                if (!CustomMath.equalZero(A[u][v])) {
                    // Is v is already visited (part of MST)
                    if (!inTree.getBit(v)) {
                        if (A[u][v] < dist[v]) {
                            dist[v] = A[u][v];
                            predecessor[v] = u;
                        }
                    }
                }
            }
        }

        // Collect result
        System.out.print("Debug:");
        for (int v = 0; v < vertices.length; ++v) {
            if (predecessor[v] != -1) {
                int u = predecessor[v];

                System.out.print(" (" + u + ", " + v + "),");

                Edge<V> edge = graph.getEdge(u, v);
                r.edgeList.add(edge);
                r.totalWeight += A[u][v];
            }
        }
        System.out.println();

        return r;
    }

    /**
     * Find vertex with minimum weight (this could be replaced with PQ).
     *
     * @param inTree
     * @param dist
     * @return
     */
    private static int findMin(BitVector inTree, float[] dist) {
        int minIndex = -1;
        float minDist = INF;

        for (int i = 0; i < dist.length; ++i) {
            // If this vertex is not in MST
            if (!inTree.getBit(i)) {
                if (dist[i] < minDist) {
                    minDist = dist[i];
                    minIndex = i;
                }
            }
        }

        return minIndex;
    }
}
