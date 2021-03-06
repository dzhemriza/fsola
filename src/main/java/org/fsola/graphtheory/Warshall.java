/*
 * org.fsola
 *
 * File Name: Warshall.java
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

/**
 * Warshall algorithm for graph reachability.
 * <p/>
 * Used Resources: README file resources section line 1, line 42.
 */
public class Warshall {

    public static <V> int[][] warshall(V[] vertices, AdjacencyMatrix<V> graph) {
        int[][] matrix = graph.cloneToUnweightedMatrix();

        for (int k = 0; k < vertices.length; ++k) {
            for (int i = 0; i < vertices.length; ++i) {
                if (matrix[i][k] == 1) {
                    for (int j = 0; j < vertices.length; ++j) {
                        if (matrix[k][j] == 1) {
                            matrix[i][j] = 1;
                        }
                    }
                }
            }
        }

        return matrix;
    }
}
