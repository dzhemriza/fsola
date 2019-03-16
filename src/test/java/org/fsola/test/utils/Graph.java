/*
 * org.fsola
 *
 * File Name: Graph.java
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
package org.fsola.test.utils;

import org.fsola.datastructures.graph.*;
import org.junit.Assert;

/**
 * Graph related utility class.
 */
public class Graph {
    // Graph used in tests:
    // Example 1
    // ---------
    //
    //     [0]
    //   /     \
    // [1]  -  [2] - [4]
    //  \       /
    //   \     /
    //     [3]
    //
    // 5 vertices
    // 6 edges
    //
    //
    // Example 2
    // ---------
    //
    //    [1]            [0]
    //     |
    //    [2]-----[4]             [8]
    //   /  \      |               |
    //  /    \     |               |
    // [5]  [3]    |               |
    //  |     |   [12]            [9]
    //  |     |  /                / \
    // [7]----[6]                /   \
    //  |                      [14]--[13]
    // [10]--[11]
    //
    // 15 vertices
    // 15 edges
    //
    //
    // Example 3
    // ---------
    //
    //        3            4
    //  [1]<-------[2]<-----------
    //   | \        ^            |
    //   |  \       |           [5]
    //   |   \  3   |            |
    //  6|    ----- |            |
    //   |        | |1           |
    //   |        | |            |
    //   v    1   v |            |
    //  [ ]<------[ ]      2     |
    //  [3]   2   [4]<------------
    //  [ ]------>[ ]
    //
    // 5 vertices
    // 8 edges
    //
    //
    // Example 4
    // ---------
    //
    //      12             40
    // [0]---------->[1]----------->[2]
    //                |              ^
    //                |              |
    //             17 |           20 |
    //                |              |
    //       30       V     20       |
    // [3]---------->[4]----------->[5]
    //
    // 6 vertices
    // 6 edges
    //
    //
    // Example 5
    // ---------
    //
    // [0]------[1]--------[3]-----[4]
    //   \      /           \      /
    //    \    /             \    /
    //     \  /               \  /
    //     [2]-----------------[5]
    //
    // 6 vertices
    // 8 edges
    //
    //
    // Example 6
    // ---------
    //
    //                   7
    //  +-----------------------------------------+
    //  |        5               5                |
    // [0]--------------[1]--------------[2]      |
    //  |                                /|       |
    //  |                     6         / |       |
    //  |          +-------------------+ 5|       |
    //  |          |                      |       |
    //  |          |          3           |       |
    //  |         [3]--------------------[4]------+
    //  |          |                      |
    //  |          |                      |
    // 7|         3|                     5|
    //  |          |                      |
    //  |          |                      |
    //  +---------[5]---------------------+
    //
    // 6 vertices
    // 9 edges
    //
    //
    // Example 7
    // ---------
    //
    //       10                7
    //  +----------->[3]---------------+
    //  |                              |
    //  |                              |
    //  |     5              5         V
    // [0]---------->[1]------------->[5]
    //  |             |                ^
    //  |           4 |              8 |
    //  |     5       V     7          |
    //  +----------->[2]------------->[4]
    //
    // 6 vertices
    // 8 edges
    //
    //
    // Example 8
    //
    //  +----[0]-----+
    //  |            |
    //  |            |
    // [5]          [1]
    //  |            |
    //  |            |
    // [4]          [2]
    //  |            |
    //  |            |
    //  +----[3]-----+
    //
    // 6 vertices
    // 6 edges
    //
    //
    // Example 9 (acyclic)
    //
    // [0]------>[2]--------->[5]------------+
    //  ^         ^            |             |
    //  |         |            |             |
    //  |         |            v             v
    // [1]--------+           [3]<----------[4]
    //
    // 6 vertices
    // 7 edges
    //
    //
    // Example 10
    //
    //         10
    //   [5]<--------[2]
    //    ^ ^         ^
    //    |  \     10 |
    //    |   \ 20    |    55
    // 25 |    +-----[0]-------->[4]
    //    |           |           |
    //    |     6    / \          |
    //    |   +-----+   \ 20   31 |
    //    |  /           \        |
    //    | v     45      v       |
    //   [1]<--------------[3]<---+
    //
    // 6 vertices
    // 9 edges
    //
    //
    // Example 11
    //
    //                                   +---------------------+
    //                                   |                     v
    // [0]<-----------[2]<--------------[5]                   [6]<-----+
    //  |            ^ ^                 ^ \                   ^       |
    //  |    +-----+/  |                 | --------------------+       |
    //  |   /          |                 |                             |
    //  |  /           |  -------------+ |                             |
    //  v /            | /              >|                             |
    // [1]<-----------[3]               [4]<--------------------------[7]
    //                   <              /
    //                    +-------------
    //
    // 8 vertices
    // 13 edges
    //
    //
    // Example 12
    //
    // [0]--------------[1]--------------[2]
    //                   |                |
    //                   |                |
    //                   |                |
    // [6]--------------[5]--------------[3]--------------[4]
    //
    // 7 vertices
    // 7 edges
    //
    //
    // Example 13
    //
    //          1             6
    //  [0]------------[1]------------[2]
    //   |      5     / |      5     / |
    // 3 |  /--------/ 1|  /--------/  | 2
    //   | /            | /            |
    //  [3]------------[4]------------[5]
    //           1             4
    //
    // 6 vertices
    // 9 edges
    //
    //
    // Example 14
    //
    // [5]------------>[0]-------------->[1]---+
    //  ^              / \                ^    |
    //  |             /   \               |    |
    //  |            /     \              |    |
    //  |           /       \             |    |
    //  |          /         \            |    |
    //  |         |           |           |    |
    //  |         V           V           |    |
    // [3]<------[2]         [4]----------+    |
    //  ^                                      |
    //  |                                      |
    //  +--------------------------------------+
    //
    // 6 vertices
    // 8 edges
    //
    // Example 15
    //
    //                                    +------------+
    //                                    |            |
    //                                    v            |
    //  [4]<------>[5]<---------+        [0]<-----+   [1]<------[8]
    //              |           |         ^       |    |
    //              |           |         |       |    |
    //              |           |         |       |    |
    //              v           |         |       |    |
    //             [3]-------->[7]--------+      [2]<--+        [6]
    //
    // 9 vertices
    // 10 edges
    //
    //
    // Example 16
    //
    //     +--------->[0]------------+
    //     |                         |
    //     |                         V
    //    [5]           +---------->[1]
    //     ^            |            |
    //     |            |            V
    //    [4]<----------+   +------>[2]
    //     ^ \              |        |
    //     |  +-------------+        |
    //     |                         |
    //     +-----------[3]<----------+
    //
    //
    // 6 vertices
    // 9 edges
    //

    public static void buildExample16Graph(
            org.fsola.datastructures.graph.Graph<Vertex> graph, Vertex[] v) {
        Assert.assertEquals("Graph example 15 require only 9 vertices!", 6,
                v.length);

        // [0]
        graph.add(v[0], v[1], 1.0f);

        // [1]
        graph.add(v[1], v[2], 1.0f);
        graph.add(v[1], v[4], 1.0f);

        // [2]
        graph.add(v[2], v[3], 1.0f);

        // [3]
        graph.add(v[3], v[4], 1.0f);

        // [4]
        graph.add(v[4], v[1], 1.0f);
        graph.add(v[4], v[2], 1.0f);
        graph.add(v[4], v[5], 1.0f);

        // [5]
        graph.add(v[5], v[0], 1.0f);
    }

    /**
     * Builds a {@link org.fsola.datastructures.graph.AdjacencyMatrix} graph based on
     * example 16 image above.
     *
     * @param v
     * @return
     */
    public static AdjacencyMatrix<Vertex> buildAdjacencyMatrixGraphExample16(Vertex[] v) {
        AdjacencyMatrix<Vertex> graph = new AdjacencyMatrix<>(v.length);

        buildExample16Graph(graph, v);

        return graph;
    }

    public static void buildExample15Graph(
            org.fsola.datastructures.graph.Graph<Vertex> graph, Vertex[] v) {
        Assert.assertEquals("Graph example 15 require only 9 vertices!", 9,
                v.length);

        // [0]

        // [1]
        graph.add(v[1], v[0], 0.0f);
        graph.add(v[1], v[2], 0.0f);

        // [2]
        graph.add(v[2], v[0], 0.0f);

        // [3]
        graph.add(v[3], v[7], 0.0f);

        // [4]
        graph.add(v[4], v[5], 0.0f);

        // [5]
        graph.add(v[5], v[3], 0.0f);
        graph.add(v[5], v[4], 0.0f);

        // [6]

        // [7]
        graph.add(v[7], v[0], 0.0f);
        graph.add(v[7], v[5], 0.0f);

        // [8]
        graph.add(v[8], v[1], 0.0f);
    }

    /**
     * Builds a {@link org.fsola.datastructures.graph.AdjacencyMatrix} graph based on
     * example 15 image above.
     *
     * @param v
     * @return
     */
    public static AdjacencyMatrix<Vertex> buildAdjacencyMatrixGraphExample15(Vertex[] v) {
        AdjacencyMatrix<Vertex> graph = new AdjacencyMatrix<>(v.length);

        buildExample15Graph(graph, v);

        return graph;
    }

    public static void buildExample14Graph(
            org.fsola.datastructures.graph.Graph<Vertex> graph, Vertex[] v) {
        Assert.assertEquals("Graph example 14 require only 6 vertices!", 6,
                v.length);

        // [0]
        graph.add(v[0], v[1], 0.0f);
        graph.add(v[0], v[2], 0.0f);
        graph.add(v[0], v[4], 0.0f);

        // [1]
        graph.add(v[1], v[3], 0.0f);

        // [2]
        graph.add(v[2], v[3], 0.0f);

        // [3]
        graph.add(v[3], v[5], 0.0f);

        // [4]
        graph.add(v[4], v[1], 0.0f);

        // [5]
        graph.add(v[5], v[0], 0.0f);
    }

    /**
     * Builds a {@link org.fsola.datastructures.graph.AdjacencyMatrix} graph based on
     * example 14 image above.
     *
     * @param v
     * @return
     */
    public static AdjacencyMatrix<Vertex> buildAdjacencyMatrixGraphExample14(Vertex[] v) {
        AdjacencyMatrix<Vertex> graph = new AdjacencyMatrix<>(v.length);

        buildExample14Graph(graph, v);

        return graph;
    }

    public static void buildExample13Graph(
            org.fsola.datastructures.graph.Graph<Vertex> graph, Vertex[] v) {
        Assert.assertEquals("Graph example 13 require only 6 vertices!", 6,
                v.length);

        // [0]
        graph.add(v[0], v[1], 1.0f);
        graph.add(v[0], v[3], 3.0f);

        graph.add(v[1], v[0], 1.0f);
        graph.add(v[3], v[0], 3.0f);

        // [1]
        graph.add(v[1], v[2], 6.0f);
        graph.add(v[1], v[4], 1.0f);
        graph.add(v[1], v[3], 5.0f);

        graph.add(v[2], v[1], 6.0f);
        graph.add(v[4], v[1], 1.0f);
        graph.add(v[3], v[1], 5.0f);

        // [2]
        graph.add(v[2], v[4], 5.0f);
        graph.add(v[2], v[5], 2.0f);

        graph.add(v[4], v[2], 5.0f);
        graph.add(v[5], v[2], 2.0f);

        // [3]
        graph.add(v[3], v[4], 1.0f);

        graph.add(v[4], v[3], 1.0f);

        // [4]
        graph.add(v[4], v[5], 4.0f);

        graph.add(v[5], v[4], 4.0f);

        // [5]
    }

    /**
     * Builds a {@link org.fsola.datastructures.graph.EdgeList} graph based on
     * example 13 image above.
     *
     * @param v
     * @return
     */
    public static EdgeList<Vertex> buildEdgeListGraphExample13(Vertex[] v) {
        EdgeList<Vertex> graph = new EdgeList<>();

        buildExample13Graph(graph, v);

        return graph;
    }

    /**
     * Builds a {@link org.fsola.datastructures.graph.AdjacencyMatrix} graph based on
     * example 13 image above.
     *
     * @param v
     * @return
     */
    public static AdjacencyMatrix<Vertex> buildAdjacencyMatrixGraphExample13(Vertex[] v) {
        AdjacencyMatrix<Vertex> graph = new AdjacencyMatrix<>(v.length);

        buildExample13Graph(graph, v);

        return graph;
    }

    public static void buildExample12Graph(
            org.fsola.datastructures.graph.Graph<Vertex> graph, Vertex[] v) {
        Assert.assertEquals("Graph example 12 require only 7 vertices!", 7,
                v.length);
        // [0]
        graph.add(v[0], v[1], 0.0f);

        // [1]
        graph.add(v[1], v[0], 0.0f);
        graph.add(v[1], v[2], 0.0f);
        graph.add(v[1], v[5], 0.0f);

        // [2]
        graph.add(v[2], v[1], 0.0f);
        graph.add(v[2], v[3], 0.0f);

        // [3]
        graph.add(v[3], v[2], 0.0f);
        graph.add(v[3], v[4], 0.0f);
        graph.add(v[3], v[5], 0.0f);

        // [4]
        graph.add(v[4], v[3], 0.0f);

        // [5]
        graph.add(v[5], v[1], 0.0f);
        graph.add(v[5], v[3], 0.0f);
        graph.add(v[5], v[6], 0.0f);

        // [6]
        graph.add(v[6], v[5], 0.0f);
    }

    public static void buildExample11Graph(
            org.fsola.datastructures.graph.Graph<Vertex> graph, Vertex[] v) {
        Assert.assertEquals("Graph example 11 require only 8 vertices!", 8,
                v.length);
        // [0]
        graph.add(v[0], v[1], 0.0f);

        // [1]
        graph.add(v[1], v[2], 0.0f);

        // [2]
        graph.add(v[2], v[0], 0.0f);

        // [3]
        graph.add(v[3], v[1], 0.0f);
        graph.add(v[3], v[2], 0.0f);
        graph.add(v[3], v[4], 0.0f);

        // [4]
        graph.add(v[4], v[3], 0.0f);
        graph.add(v[4], v[5], 0.0f);

        // [5]
        graph.add(v[5], v[2], 0.0f);
        graph.add(v[5], v[6], 0.0f);

        // [6]
        graph.add(v[6], v[5], 0.0f);

        // [7]
        graph.add(v[7], v[4], 0.0f);
        graph.add(v[7], v[6], 0.0f);
    }

    public static void buildExample10Graph(
            org.fsola.datastructures.graph.Graph<Vertex> graph, Vertex[] v) {
        Assert.assertEquals("Graph example 10 require only 6 vertices!", 6,
                v.length);
        // [0]
        graph.add(v[0], v[1], 6.0f);
        graph.add(v[0], v[2], 10.0f);
        graph.add(v[0], v[3], 20.0f);
        graph.add(v[0], v[4], 55.0f);
        graph.add(v[0], v[5], 20.0f);

        // [1]
        graph.add(v[1], v[5], 25.0f);

        // [2]
        graph.add(v[2], v[5], 10.0f);

        // [3]
        graph.add(v[3], v[1], 45.0f);

        // [4]
        graph.add(v[4], v[3], 31.0f);

        // [5]
    }

    public static void buildExample9Graph(
            org.fsola.datastructures.graph.Graph<Vertex> graph, Vertex[] v) {
        Assert.assertEquals("Graph example 9 require only 6 vertices!", 6,
                v.length);
        // [0]
        graph.add(v[0], v[2], 0.0f);

        // [1]
        graph.add(v[1], v[0], 0.0f);
        graph.add(v[1], v[2], 0.0f);

        // [2]
        graph.add(v[2], v[5], 0.0f);

        // [3]

        // [4]
        graph.add(v[4], v[3], 0.0f);

        // [5]
        graph.add(v[5], v[3], 0.0f);
        graph.add(v[5], v[4], 0.0f);
    }

    public static void buildExample8Graph(
            org.fsola.datastructures.graph.Graph<Vertex> graph, Vertex[] v) {
        Assert.assertEquals("Graph example 8 require only 6 vertices!", 6,
                v.length);
        // [0]
        graph.add(v[0], v[1], 0.0f);
        graph.add(v[0], v[5], 0.0f);

        // [1]
        graph.add(v[1], v[0], 0.0f);
        graph.add(v[1], v[2], 0.0f);

        // [2]
        graph.add(v[2], v[1], 0.0f);
        graph.add(v[2], v[3], 0.0f);

        // [3]
        graph.add(v[3], v[2], 0.0f);
        graph.add(v[3], v[4], 0.0f);

        // [4]
        graph.add(v[4], v[3], 0.0f);
        graph.add(v[4], v[5], 0.0f);

        // [5]
        graph.add(v[5], v[0], 0.0f);
        graph.add(v[5], v[4], 0.0f);
    }

    public static void buildExample7Graph(
            org.fsola.datastructures.graph.Graph<Vertex> graph, Vertex[] v) {
        Assert.assertEquals("Graph example 7 require only 6 vertices!", 6,
                v.length);
        // [0]
        graph.add(v[0], v[1], 5.0f);
        graph.add(v[0], v[2], 5.0f);
        graph.add(v[0], v[3], 10.0f);

        // [1]
        graph.add(v[1], v[5], 5.0f);
        graph.add(v[1], v[2], 4.0f);

        // [2]
        graph.add(v[2], v[4], 7.0f);

        // [3]
        graph.add(v[3], v[5], 7.0f);

        // [4]
        graph.add(v[4], v[5], 8.0f);

        // [5]
    }

    /**
     * Builds a graph based on example 6 above.
     *
     * @param graph
     * @param v
     */
    public static void buildExample6Graph(
            org.fsola.datastructures.graph.Graph<Vertex> graph, Vertex[] v) {
        Assert.assertEquals("Graph example 6 require only 6 vertices!", 6,
                v.length);

        // [0]
        graph.add(v[0], v[1], 5.0f);
        graph.add(v[0], v[4], 7.0f);
        graph.add(v[0], v[5], 7.0f);

        // [1]
        graph.add(v[1], v[0], 5.0f);
        graph.add(v[1], v[2], 5.0f);

        // [2]
        graph.add(v[2], v[1], 5.0f);
        graph.add(v[2], v[3], 6.0f);
        graph.add(v[2], v[4], 5.0f);

        // [3]
        graph.add(v[3], v[2], 6.0f);
        graph.add(v[3], v[4], 3.0f);
        graph.add(v[3], v[5], 3.0f);

        // [4]
        graph.add(v[4], v[0], 7.0f);
        graph.add(v[4], v[2], 5.0f);
        graph.add(v[4], v[3], 3.0f);
        graph.add(v[4], v[5], 5.0f);

        // [5]
        graph.add(v[5], v[0], 7.0f);
        graph.add(v[5], v[3], 3.0f);
        graph.add(v[5], v[4], 5.0f);
    }

    /**
     * Builds a graph based on example 5 above.
     *
     * @param graph
     * @param v
     */
    public static void buildExample5Graph(
            org.fsola.datastructures.graph.Graph<Vertex> graph, Vertex[] v) {
        Assert.assertEquals("Graph example 5 require only 6 vertices!", 6,
                v.length);

        // [0]
        graph.add(v[0], v[1], 0);
        graph.add(v[0], v[2], 0);

        // [1]
        graph.add(v[1], v[0], 0);
        graph.add(v[1], v[2], 0);
        graph.add(v[1], v[3], 0);

        // [2]
        graph.add(v[2], v[0], 0);
        graph.add(v[2], v[1], 0);
        graph.add(v[2], v[5], 0);

        // [3]
        graph.add(v[3], v[1], 0);
        graph.add(v[3], v[4], 0);
        graph.add(v[3], v[5], 0);

        // [4]
        graph.add(v[4], v[3], 0);
        graph.add(v[4], v[5], 0);

        // [5]
        graph.add(v[5], v[2], 0);
        graph.add(v[5], v[3], 0);
        graph.add(v[5], v[4], 0);
    }

    /**
     * Builds a graph based on example 4 above.
     *
     * @param graph
     * @param v
     */
    public static void buildExample4Graph(
            org.fsola.datastructures.graph.Graph<Vertex> graph, Vertex[] v) {
        Assert.assertEquals("Graph example 4 require only 6 vertices!", 6,
                v.length);

        // [0]
        graph.add(v[0], v[1], 12);

        // [1]
        graph.add(v[1], v[2], 40);
        graph.add(v[1], v[4], 17);

        // [2]
        // No edges

        // [3]
        graph.add(v[3], v[4], 30);

        // [4]
        graph.add(v[4], v[5], 20);

        // [5]
        graph.add(v[5], v[2], 20);
    }

    /**
     * Builds a graph based on example 3 image above.
     *
     * @param graph
     * @param v
     * @return
     */
    public static void buildExample3Graph(
            org.fsola.datastructures.graph.Graph<Vertex> graph, Vertex[] v) {
        Assert.assertEquals("Graph example 3 require only 6 vertices!", 6,
                v.length);
        // 6 vertices because of v[0]

        // [1]
        graph.add(v[1], v[3], 6);
        graph.add(v[1], v[4], 3);

        // [2]
        graph.add(v[2], v[1], 3);

        // [3]
        graph.add(v[3], v[4], 2);

        // [4]
        graph.add(v[4], v[2], 1);
        graph.add(v[4], v[3], 1);

        // [5]
        graph.add(v[5], v[2], 4);
        graph.add(v[5], v[4], 2);
    }

    /**
     * Builds a {@link org.fsola.datastructures.graph.AdjacencyMatrix} graph based on
     * example 12 image above.
     *
     * @param v
     * @return
     */
    public static AdjacencyMatrix<Vertex> buildAdjacencyMatrixGraphExample12(Vertex[] v) {
        AdjacencyMatrix<Vertex> graph = new AdjacencyMatrix<>(v.length);

        buildExample12Graph(graph, v);

        return graph;
    }

    /**
     * Builds a {@link org.fsola.datastructures.graph.AdjacencyMatrix} graph based on
     * example 11 image above.
     *
     * @param v
     * @return
     */
    public static AdjacencyMatrix<Vertex> buildAdjacencyMatrixGraphExample11(Vertex[] v) {
        AdjacencyMatrix<Vertex> graph = new AdjacencyMatrix<>(v.length);

        buildExample11Graph(graph, v);

        return graph;
    }

    /**
     * Builds a {@link org.fsola.datastructures.graph.AdjacencyMatrix} graph based on
     * example 10 image above.
     *
     * @param v
     * @return
     */
    public static AdjacencyMatrix<Vertex> buildAdjacencyMatrixGraphExample10(Vertex[] v) {
        AdjacencyMatrix<Vertex> graph = new AdjacencyMatrix<>(v.length);

        buildExample10Graph(graph, v);

        return graph;
    }

    /**
     * Builds a {@link org.fsola.datastructures.graph.AdjacencyMatrix} graph based on
     * example 9 image above.
     *
     * @param v
     * @return
     */
    public static AdjacencyMatrix<Vertex> buildAdjacencyMatrixGraphExample9(Vertex[] v) {
        AdjacencyMatrix<Vertex> graph = new AdjacencyMatrix<>(v.length);

        buildExample9Graph(graph, v);

        return graph;
    }

    /**
     * Builds a {@link org.fsola.datastructures.graph.AdjacencyMatrix} graph based on
     * example 8 image above.
     *
     * @param v
     * @return
     */
    public static AdjacencyMatrix<Vertex> buildAdjacencyMatrixGraphExample8(Vertex[] v) {
        AdjacencyMatrix<Vertex> graph = new AdjacencyMatrix<>(v.length);

        buildExample8Graph(graph, v);

        return graph;
    }

    /**
     * Builds a {@link org.fsola.datastructures.graph.EdgeList} graph based on
     * example 3 image above.
     *
     * @param v
     * @return
     */
    public static EdgeList<Vertex> buildEdgeListGraphExample3(Vertex[] v) {
        EdgeList<Vertex> graph = new EdgeList<>();

        buildExample3Graph(graph, v);

        return graph;
    }

    /**
     * Builds a {@link org.fsola.datastructures.graph.AdjacencyMatrix} graph
     * based on example 4 image above.
     *
     * @param v
     * @return
     */
    public static AdjacencyMatrix<Vertex> buildAdjacencyMatrixGraphExample4(
            Vertex[] v) {
        AdjacencyMatrix<Vertex> graph = new AdjacencyMatrix<>(v.length);

        buildExample4Graph(graph, v);

        return graph;
    }

    /**
     * Builds a {@link org.fsola.datastructures.graph.AdjacencyMatrix} graph
     * based on example 6 image above.
     *
     * @param v
     * @return
     */
    public static AdjacencyMatrix<Vertex> buildAdjacencyMatrixGraphExample6(
            Vertex[] v) {
        AdjacencyMatrix<Vertex> graph = new AdjacencyMatrix<>(v.length);

        buildExample6Graph(graph, v);

        return graph;
    }

    /**
     * Builds a {@link org.fsola.datastructures.graph.AdjacencyMatrix} graph
     * based on example 5 image above.
     *
     * @param v
     * @return
     */
    public static AdjacencyMatrix<Vertex> buildAdjacencyMatrixGraphExample5(
            Vertex[] v) {
        AdjacencyMatrix<Vertex> graph = new AdjacencyMatrix<>(v.length);

        buildExample5Graph(graph, v);

        return graph;
    }

    /**
     * Builds a {@link org.fsola.datastructures.graph.AdjacencyMatrix} graph
     * based on example 3 image above.
     *
     * @param v
     * @return
     */
    public static AdjacencyMatrix<Vertex> buildAdjacencyMatrixGraphExample3(
            Vertex[] v) {
        AdjacencyMatrix<Vertex> graph = new AdjacencyMatrix<>(v.length);

        buildExample3Graph(graph, v);

        return graph;
    }

    /**
     * Builds a {@link org.fsola.datastructures.graph.AdjacencyList} graph based
     * on example 3 image above.
     *
     * @param v
     * @return
     */
    public static AdjacencyList<Vertex> buildAdjacencyListGraphExample3(
            Vertex[] v) {
        AdjacencyList<Vertex> graph = new AdjacencyList<>(v.length);

        buildExample3Graph(graph, v);

        return graph;
    }

    /**
     * Builds a graph based on example 2 image above.
     *
     * @param graph
     * @param v
     * @return
     */
    public static void buildExample2Graph(
            org.fsola.datastructures.graph.Graph<Vertex> graph, Vertex[] v) {
        Assert.assertEquals("Graph example 2 require only 15 vertices!", 15,
                v.length);

        // [0]
        // No edges on this vertex

        // [1]
        graph.add(v[1], v[2], 0);

        // [2]
        graph.add(v[2], v[1], 0);
        graph.add(v[2], v[3], 0);
        graph.add(v[2], v[4], 0);
        graph.add(v[2], v[5], 0);

        // [3]
        graph.add(v[3], v[2], 0);
        graph.add(v[3], v[6], 0);

        // [4]
        graph.add(v[4], v[2], 0);
        graph.add(v[4], v[12], 0);

        // [5]
        graph.add(v[5], v[2], 0);
        graph.add(v[5], v[7], 0);

        // [6]
        graph.add(v[6], v[3], 0);
        graph.add(v[6], v[7], 0);
        graph.add(v[6], v[12], 0);

        // [7]
        graph.add(v[7], v[5], 0);
        graph.add(v[7], v[6], 0);
        graph.add(v[7], v[10], 0);

        // [8]
        graph.add(v[8], v[9], 0);

        // [9]
        graph.add(v[9], v[8], 0);
        graph.add(v[9], v[13], 0);
        graph.add(v[9], v[14], 0);

        // [10]
        graph.add(v[10], v[7], 0);
        graph.add(v[10], v[11], 0);

        // [11]
        graph.add(v[11], v[10], 0);

        // [12]
        graph.add(v[12], v[4], 0);
        graph.add(v[12], v[6], 0);

        // [13]
        graph.add(v[13], v[9], 0);
        graph.add(v[13], v[14], 0);

        // [14]
        graph.add(v[14], v[9], 0);
        graph.add(v[14], v[13], 0);
    }

    /**
     * Builds a {@link org.fsola.datastructures.graph.AdjacencyMatrix} graph
     * based on example 1 image above.
     *
     * @param v
     * @return
     */
    public static AdjacencyMatrix<Vertex> buildAdjacencyMatrixGraphExample1(
            Vertex[] v) {
        AdjacencyMatrix<Vertex> graph = new AdjacencyMatrix<>(v.length);

        buildExample1Graph(graph, v);

        return graph;
    }

    /**
     * Builds a {@link org.fsola.datastructures.graph.IncidenceMatrix} graph
     * based on example 1 image above.
     *
     * @param v
     * @return
     */
    public static IncidenceMatrix<Vertex> buildIncidenceMatrixGraphExample1(
            Vertex[] v) {
        IncidenceMatrix<Vertex> graph = new IncidenceMatrix<>(v.length, 12);

        buildExample1Graph(graph, v);

        return graph;
    }

    /**
     * Builds a {@link org.fsola.datastructures.graph.EdgeList} graph based on
     * example 1 image above.
     *
     * @param v
     * @return
     */
    public static EdgeList<Vertex> buildEdgeListGraphExample1(Vertex[] v) {
        EdgeList<Vertex> graph = new EdgeList<>();

        buildExample1Graph(graph, v);

        return graph;
    }

    /**
     * Builds a {@link org.fsola.datastructures.graph.AdjacencyList} graph based
     * on example 1 image above.
     *
     * @param v
     * @return
     */
    public static AdjacencyList<Vertex> buildAdjacencyListGraphExample1(
            Vertex[] v) {
        AdjacencyList<Vertex> graph = new AdjacencyList<>(v.length);

        buildExample1Graph(graph, v);

        return graph;
    }

    /**
     * Builds a {@link org.fsola.datastructures.graph.EdgeList} graph based on
     * example 2 image above.
     *
     * @param v
     * @return
     */
    public static AdjacencyList<Vertex> buildAdjacencyListGraphExample2(
            Vertex[] v) {
        AdjacencyList<Vertex> graph = new AdjacencyList<>(v.length);

        buildExample2Graph(graph, v);

        return graph;
    }

    /**
     * Builds a graph based on example 1 image above.
     *
     * @param graph
     * @param v
     * @return
     */
    public static void buildExample1Graph(
            org.fsola.datastructures.graph.Graph<Vertex> graph, Vertex[] v) {
        Assert.assertEquals("Graph example 1 require only 5 vertices!", 5,
                v.length);

        // [0]
        graph.add(v[0], v[1], 0);
        graph.add(v[0], v[2], 0);

        // [1]
        graph.add(v[1], v[2], 0);
        graph.add(v[1], v[3], 0);
        graph.add(v[1], v[0], 0);

        // [2]
        graph.add(v[2], v[0], 0);
        graph.add(v[2], v[3], 0);
        graph.add(v[2], v[4], 0);
        graph.add(v[2], v[1], 0);

        // [3]
        graph.add(v[3], v[1], 0);
        graph.add(v[3], v[2], 0);

        // [4]
        graph.add(v[4], v[2], 0);
    }

    /**
     * Builds a {@link org.fsola.datastructures.graph.AdjacencyMatrix} graph
     * based on example 7 image above.
     *
     * @param v
     * @return
     */
    public static AdjacencyMatrix<Vertex> buildAdjacencyMatrixGraphExample7(
            Vertex[] v) {
        AdjacencyMatrix<Vertex> graph = new AdjacencyMatrix<>(v.length);

        buildExample7Graph(graph, v);

        return graph;
    }

    /**
     * Build an array of vertices.
     *
     * @param n
     * @return
     */
    public static Vertex[] buildVertices(int n) {
        Vertex[] result = new Vertex[n];

        for (int i = 0; i < n; ++i) {
            result[i] = new Vertex(i);
        }

        return result;
    }
}
