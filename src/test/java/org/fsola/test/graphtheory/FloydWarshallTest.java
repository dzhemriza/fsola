/*
 * org.fsola
 *
 * File Name: FloydWarshallTest.java
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
package org.fsola.test.graphtheory;

import org.fsola.datastructures.graph.AdjacencyMatrix;
import org.fsola.datastructures.graph.EdgeList;
import org.fsola.datastructures.graph.Vertex;
import org.fsola.graphtheory.BellmanFord;
import org.fsola.graphtheory.FloydWarshall;
import org.fsola.introduction.CustomMath;
import org.fsola.test.utils.Graph;
import org.junit.Assert;
import org.junit.Test;

public class FloydWarshallTest {

    @Test
    public void test1() {
        int N = 6;

        Vertex[] v = Graph.buildVertices(N);
        AdjacencyMatrix<Vertex>
                graph = Graph.buildAdjacencyMatrixGraphExample3(v);

        float[][] distances = FloydWarshall.floydWarshall(v, graph);

        // Print the distances
        System.out.println("Distances:");
        for (int i = 0; i < distances.length; ++i) {
            for (int j = 0; j < distances[i].length; ++j) {
                if (CustomMath
                        .equalsFloat(distances[i][j], FloydWarshall.INF)) {
                    System.out.print(" INF");
                } else {
                    System.out.print(" " + distances[i][j]);
                }
            }
            System.out.println();
        }

        // Test the solution using Bellman-Ford algorithm
        for (int i = 0; i < v.length; ++i) {
            Vertex[] vBF = Graph.buildVertices(N);
            EdgeList<Vertex>
                    graphBF = Graph.buildEdgeListGraphExample3(v);

            float[] distancesBF = BellmanFord.bellmanFord(vBF, graphBF, vBF[i]);

            for (int j = 0; j < v.length; ++j) {
                Assert.assertTrue(CustomMath.equalsFloat(distancesBF[j],
                        distances[i][j]));
            }
        }
    }
}
