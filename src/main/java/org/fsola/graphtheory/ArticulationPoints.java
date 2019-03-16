/*
 * org.fsola
 *
 * File Name: ArticulationPoints.java
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

import java.util.ArrayList;
import java.util.List;

/**
 * Find all articulation points in graph.
 * <p/>
 * Used Resources: README file resources section line 1, line 44
 */
public class ArticulationPoints {

    private static int[] prenum;
    private static int[] lowest;
    private static int num;

    public static <V> List<Integer> articulationPoints(AdjacencyMatrix<V> graph, V[] vertices) {
        List<Integer> points = new ArrayList<>();

        int[][] matrix = graph.cloneToUnweightedMatrix();
        prenum = new int[vertices.length];
        lowest = new int[vertices.length];
        num = 0;

        for (int i = 0; i < prenum.length; ++i) {
            prenum[i] = 0;
        }

        // Skip graph connectivity test

        // Build DFS tree and label each vertex using in postnum order
        dfs(matrix, 0);

        postOrder(matrix, 0);

        // Check if root has more than 1 child then it's a articulation point
        int count = 0;
        for (int i = 0; i < matrix.length; ++i) {
            if (matrix[0][i] == 2) {
                count++;
            }
        }

        if (count > 1) {
            points.add(0);
        }

        for (int i = 1 /*skip root*/; i < matrix.length; ++i) {
            for (int j = 0; j < matrix.length; ++j) {
                if (matrix[i][j] == 2 && lowest[j] >= prenum[i]) {
                    points.add(i);
                    break;
                }
            }
        }

        return points;
    }

    private static void postOrder(int[][] matrix, int start) {
        // Traverse DFS tree in post order to update lowest
        for (int i = 0; i < matrix.length; ++i) {
            if (matrix[start][i] == 2) {
                postOrder(matrix, i);
            }
        }

        lowest[start] = prenum[start];

        for (int i = 0; i < matrix.length; ++i) {
            if (matrix[start][i] == 1) {
                lowest[start] = Math.min(lowest[start], prenum[i]);
            }
        }

        for (int i = 0; i < matrix.length; ++i) {
            if (matrix[start][i] == 2) {
                lowest[start] = Math.min(lowest[start], lowest[i]);
            }
        }
    }

    private static void dfs(int[][] matrix, int start) {
        prenum[start] = ++num;

        for (int i = 0; i < matrix.length; ++i) {
            if (matrix[start][i] == 1 && prenum[i] == 0) {
                matrix[start][i] = 2; // This edge is part of DFS tree
                dfs(matrix, i);
            }
        }
    }
}
