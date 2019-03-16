/*
 * org.fsola
 *
 * File Name: FloydWarshall.java
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
import org.fsola.introduction.CustomMath;

/**
 * Floyd-Warshall algorithm for finding all pairs shortest path.
 * <p/>
 * Used Resources: README file resources section line 1, line 36, line 37, line
 * 38.
 */
public class FloydWarshall {

    /**
     * Marked indicating infinite number.
     */
    public static float INF = 100000.0f;

    public static <V> float[][] floydWarshall(V[] vertices,
                                              AdjacencyMatrix<V> graph) {
        float[][] matrix = graph.cloneMatrix();
        int N = matrix.length;
        float[][] distances = new float[N][N];

        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                if (i == j) {
                    distances[i][j] = 0.0f;
                } else {
                    distances[i][j] = matrix[i][j];

                    if (CustomMath.equalZero(distances[i][j])) {
                        distances[i][j] = INF;
                    }
                }
            }
        }

        for (int k = 0; k < vertices.length; ++k) {
            for (int i = 0; i < vertices.length; ++i) {
                for (int j = 0; j < vertices.length; ++j) {
                    if (distances[i][j] > distances[i][k] + distances[k][j]) {
                        distances[i][j] = distances[i][k] + distances[k][j];
                    }
                }
            }
        }

        return distances;
    }
}
