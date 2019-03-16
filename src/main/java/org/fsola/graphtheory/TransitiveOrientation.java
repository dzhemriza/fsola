/*
 * org.fsola
 *
 * File Name: TransitiveOrientation.java
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
 * Transitive orientation of graph.
 * <p/>
 * Puneli, Lempel, Even Algorithm
 * <br/>
 * Rules:
 * R1: If i->j, j-k, i=/k, orient j-k: k->j
 * R2: If i->j, i-k, j=/k, orient i-k: i->k
 * <br/>
 * 1. Select random non oriented edge and orient it. Mark this edge
 * in order to avoid re-selection of the same edge.
 * 2. Apply rules R1 and R2
 * 3. Check is all edges are oriented and exit if not remove oriented
 * edged from graph
 * <p/>
 * Used Resources: README file resources section line 1
 */
public class TransitiveOrientation {
    private static int MARK_POSITIVE_PATH = 2;
    private static int MARK_NEGATIVE_PATH = -2;
    private static int NO_PATH = 0;
    private static int ELIMINATED_POSITIVE_EDGE = -3;
    private static int ELIMINATED_NEGATIVE_EDGE = -4;

    /**
     * Indicates that graph is non transitive orientable.
     */
    public static class NonOrientableGraphException extends RuntimeException {
    }

    public static <V> int[][] transitiveOrientation(AdjacencyMatrix<V> graph, V[] vertices) {
        int[][] matrix = graph.cloneToUnweightedMatrix();

        // Calculate number of edges
        int edges = 0;

        for (int i = 0; i < vertices.length - 1; ++i) {
            for (int j = i + 1; j < vertices.length; ++j) {
                if (matrix[i][j] == 1) {
                    ++edges;
                }
            }
        }

        int eliminatedEdges = 0;
        do {
            // Find an edge and orient i -> j
            // matrix[i][j] = 2 => i -> j
            // matrix[j][i] = -2 => negative path
            for (int i = 0; i < vertices.length; ++i) {
                boolean found = false;
                for (int j = 0; j < vertices.length; ++j) {
                    if (1 == matrix[i][j]) {
                        matrix[i][j] = MARK_POSITIVE_PATH;
                        matrix[j][i] = MARK_NEGATIVE_PATH;
                        found = true;
                        break;
                    }
                }
                if (found) {
                    break;
                }
            }

            boolean flag;

            do {
                flag = false;

                for (int i = 0; i < vertices.length; ++i) {
                    for (int j = 0; j < vertices.length; ++j) {
                        if (matrix[i][j] == MARK_POSITIVE_PATH) {
                            // i -> j

                            for (int k = 0; k < vertices.length; ++k) {
                                if (i == k || j == k) {
                                    // Skip k equals to i or j vertices
                                    continue;
                                }

                                // No path i->k or it's already been removed
                                if (NO_PATH == matrix[i][k] || matrix[i][k] < MARK_NEGATIVE_PATH) {

                                    if (MARK_POSITIVE_PATH == matrix[j][k]) {
                                        throw new NonOrientableGraphException();
                                    }

                                    if (1 == matrix[j][k]) {
                                        // k->j
                                        matrix[k][j] = MARK_POSITIVE_PATH;
                                        matrix[j][k] = MARK_NEGATIVE_PATH;
                                        flag = true;
                                    }
                                }

                                // No path j->k or it's already been removed
                                if (NO_PATH == matrix[j][k] || matrix[j][k] < MARK_NEGATIVE_PATH) {

                                    if (MARK_POSITIVE_PATH == matrix[k][i]) {
                                        throw new NonOrientableGraphException();
                                    }

                                    if (1 == matrix[i][k]) {
                                        // i->k
                                        matrix[i][k] = MARK_POSITIVE_PATH;
                                        matrix[k][i] = MARK_NEGATIVE_PATH;
                                        flag = true;
                                    }
                                }
                            }
                        }
                    }
                }
            } while (flag);

            // Eliminate oriented edges
            for (int i = 0; i < vertices.length; ++i) {
                for (int j = 0; j < vertices.length; ++j) {
                    if (MARK_POSITIVE_PATH == matrix[i][j]) {
                        eliminatedEdges++;
                        matrix[i][j] = ELIMINATED_POSITIVE_EDGE;
                        matrix[j][i] = ELIMINATED_NEGATIVE_EDGE;
                    }
                }
            }

        } while (eliminatedEdges < edges);

        // Reformat matrix
        for (int i = 0; i < vertices.length; ++i) {
            for (int j = 0; j < vertices.length; ++j) {
                if (matrix[i][j] == ELIMINATED_POSITIVE_EDGE) {
                    matrix[i][j] = 1;
                } else if (matrix[i][j] == ELIMINATED_NEGATIVE_EDGE) {
                    matrix[i][j] = -1;
                }
            }
        }

        return matrix;
    }
}
