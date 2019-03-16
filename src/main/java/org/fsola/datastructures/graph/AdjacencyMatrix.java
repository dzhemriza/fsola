/*
 * org.fsola
 *
 * File Name: AdjacencyMatrix.java
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
package org.fsola.datastructures.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * Graph representation data structure - Adjacency Matrix.
 * <p/>
 * Used Resources: README file resources section line 1, line 32
 * <p/>
 * Complexity by operations: adjacent - O(C), neighbors - O(n), add - O(C),
 * delete - O(C).
 *
 * @param <V>
 */
public class AdjacencyMatrix<V> implements Graph<V> {

    private static class InternalEdge<V> {
        boolean adjacent;
        float weight;
        V x;
        V y;

        InternalEdge(boolean adjacent, float weight, V x, V y) {
            this.adjacent = adjacent;
            this.weight = weight;
            this.x = x;
            this.y = y;
        }
    }

    private InternalEdge<V>[][] matrix;
    private int numberOfVertices;

    public AdjacencyMatrix(int numberOfVertices) {
        if (numberOfVertices <= 0) {
            throw new IllegalArgumentException("Invalid value for " +
                    "numberOfVertices" + numberOfVertices);
        }

        this.numberOfVertices = numberOfVertices;

        // Build the matrix
        matrix = new InternalEdge[this.numberOfVertices][this.numberOfVertices];
        for (int i = 0; i < numberOfVertices; ++i) {
            for (int j = 0; j < numberOfVertices; ++j) {
                matrix[i][j] = new InternalEdge<>(false, 0.0f, null, null);
            }
        }
    }

    @Override
    public boolean adjacent(V x, V y) {
        int X = x.hashCode();
        int Y = y.hashCode();
        return matrix[X][Y].adjacent;
    }

    /**
     * Returns the edge weight between two vertices.
     *
     * @param x
     * @param y
     * @return
     */
    public float getWeight(V x, V y) {
        int X = x.hashCode();
        int Y = y.hashCode();

        if (matrix[X][Y].adjacent) {
            return matrix[X][Y].weight;
        }

        return 0.0f;
    }

    @Override
    public List<V> neighbors(V x) {
        ArrayList<V> vertices = new ArrayList<>();

        int X = x.hashCode();
        int N = matrix.length; // Number of vertices

        // Check for each vertices in matrix
        for (int i = 0; i < N; ++i) {
            if (matrix[X][i].adjacent) {
                vertices.add(matrix[X][i].y);
            }
        }

        return vertices;
    }

    @Override
    public void add(V x, V y, float weight) {
        int X = x.hashCode();
        int Y = y.hashCode();

        matrix[X][Y].adjacent = true;
        matrix[X][Y].weight = weight;
        matrix[X][Y].x = x;
        matrix[X][Y].y = y;
    }

    @Override
    public void delete(V x, V y) {
        int X = x.hashCode();
        int Y = y.hashCode();

        matrix[X][Y].adjacent = false;
        matrix[X][Y].weight = 0.0f;
        matrix[X][Y].x = null;
        matrix[X][Y].y = null;
    }

    @Override
    public Edge<V> getEdge(V x, V y) {
        int X = x.hashCode();
        int Y = y.hashCode();

        return getEdge(X, Y);
    }

    public Edge<V> getEdge(int X, int Y) {
        Edge<V> result = null;

        if (matrix[X][Y].adjacent) {
            result = new Edge<>(matrix[X][Y].x, matrix[X][Y].y,
                    matrix[X][Y].weight);
        }

        return result;
    }

    /**
     * Clones matrix into {@code float[][]} form.
     *
     * @return
     */
    public float[][] cloneMatrix() {
        int N = matrix.length;
        float[][] result = new float[N][N];

        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                result[i][j] = 0.0f;

                if (matrix[i][j].adjacent) {
                    result[i][j] = matrix[i][j].weight;
                }
            }
        }

        return result;
    }

    /**
     * Cones matrix into {@code int[][]} form.
     *
     * @return
     */
    public int[][] cloneToUnweightedMatrix() {
        int N = matrix.length;
        int[][] result = new int[N][N];

        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                result[i][j] = 0;

                if (matrix[i][j].adjacent) {
                    result[i][j] = 1;
                }
            }
        }

        return result;
    }

    /**
     * @return Number of vertices
     */
    public int getNumberOfVertices() {
        return numberOfVertices;
    }
}
