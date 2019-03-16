/*
 * org.fsola
 *
 * File Name: IncidenceMatrix.java
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
 * Graph representation data structure - Incidence Matrix.
 * <p/>
 * Used Resources: README file resources section line 1, line 32
 * <p/>
 * Complexity by operations: adjacent - O(Ne), neighbors - O(Ne), add - O(Ne),
 * delete - O(Ne).
 *
 * @param <V>
 */
public class IncidenceMatrix<V> implements Graph<V> {

    /**
     * Direction of the vertices represented in incidence matrix
     */
    private static enum Direction {
        START, END
    }

    /**
     * Internal structure that holds the information for incidence.
     *
     * @param <V>
     */
    private static class InnerEdge<V> {
        boolean adjacent;
        float weight;
        V v;
        Direction dir;

        InnerEdge(boolean adjacent, float weight, V v, Direction dir) {
            this.adjacent = adjacent;
            this.weight = weight;
            this.v = v;
            this.dir = dir;
        }
    }

    private final int vertices;
    private final int edges;
    private InnerEdge<V> matrix[][];

    public IncidenceMatrix(int vertices, int edges) {
        if (vertices <= 0 || edges <= 0) {
            throw new IllegalArgumentException("vertices or edges is zero.");
        }

        this.vertices = vertices;
        this.edges = edges;

        // Create incidence matrix
        matrix = new InnerEdge[this.vertices][this.edges];
        for (int i = 0; i < vertices; ++i) {
            for (int j = 0; j < edges; ++j) {
                matrix[i][j] =
                        new InnerEdge<>(false, 0.0f, null, Direction.START);
            }
        }
    }

    @Override
    public boolean adjacent(V x, V y) {
        int edge = findEdge(x, y);

        if (edge != -1) {
            return true;
        }

        return false;
    }

    @Override
    public List<V> neighbors(V x) {
        ArrayList<V> result = new ArrayList<>();
        int X = x.hashCode();

        for (int e = 0; e < edges; ++e) {
            if (matrix[X][e].adjacent && matrix[X][e].dir == Direction.START) {
                for (int v = 0; v < vertices; ++v) {
                    if (v == X) continue;

                    if (matrix[v][e].adjacent && matrix[v][e].v != null) {
                        result.add(matrix[v][e].v);
                    }
                }
            }
        }

        return result;
    }

    @Override
    public void add(V x, V y, float weight) {
        int X = x.hashCode();
        int Y = y.hashCode();

        int edge = findEdge(x, y);

        if (edge != -1) {
            // Update weight on both vertices
            matrix[X][edge].weight = weight;
            matrix[Y][edge].weight = weight;
        } else {
            edge = findFirstEmptyEdge();

            matrix[X][edge].adjacent = true;
            matrix[X][edge].dir = Direction.START;
            matrix[X][edge].v = x;
            matrix[X][edge].weight = weight;

            matrix[Y][edge].adjacent = true;
            matrix[Y][edge].dir = Direction.END;
            matrix[Y][edge].v = y;
            matrix[Y][edge].weight = weight;
        }


    }

    @Override
    public void delete(V x, V y) {
        int X = x.hashCode();
        int Y = y.hashCode();

        int edge = findEdge(x, y);

        if (edge != -1) {
            matrix[X][edge].adjacent = false;
            matrix[X][edge].dir = Direction.START;
            matrix[X][edge].v = null;
            matrix[X][edge].weight = 0.0f;

            matrix[Y][edge].adjacent = false;
            matrix[Y][edge].dir = Direction.START;
            matrix[Y][edge].v = null;
            matrix[Y][edge].weight = 0.0f;
        }
    }

    @Override
    public Edge<V> getEdge(V x, V y) {
        int X = x.hashCode();

        int edge = findEdge(x, y);

        Edge<V> result = null;

        if (edge != -1) {
            result = new Edge<>(x, y, matrix[X][edge].weight);
        }

        return result;
    }

    private int findFirstEmptyEdge() {
        for (int j = 0; j < edges; ++j) {
            boolean empty = true;

            for (int i = 0; i < vertices; ++i) {
                if (matrix[i][j].adjacent) {
                    empty = false;
                }
            }

            if (empty) {
                return j;
            }
        }

        throw new IllegalStateException("Unable to find a empty edge. Matrix " +
                "resize is required");
    }

    private int findEdge(V x, V y) {
        int X = x.hashCode();
        int Y = y.hashCode();

        for (int i = 0; i < edges; ++i) {
            boolean dir = ((matrix[X][i].dir == Direction.START) &&
                    (matrix[Y][i].dir == Direction.END));
            boolean adjacent = (matrix[X][i].adjacent && matrix[Y][i].adjacent);

            if (adjacent && dir) {
                return i;
            }
        }

        return -1;
    }
}
