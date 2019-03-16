/*
 * org.fsola
 *
 * File Name: FordFulkersonTest.java
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
import org.fsola.datastructures.graph.Vertex;
import org.fsola.graphtheory.FordFulkerson;
import org.fsola.introduction.CustomMath;
import org.fsola.test.utils.Graph;
import org.junit.Assert;
import org.junit.Test;

public class FordFulkersonTest {

    @Test
    public void test1() {
        Vertex[] v = Graph.buildVertices(6);
        AdjacencyMatrix<Vertex> graph =
                Graph.buildAdjacencyMatrixGraphExample7(v);

        int source = 0;
        int sink = 5;

        float[][] F = FordFulkerson.fordFulkerson(graph, v, source, sink);

        for (int i = 0; i < v.length; ++i) {
            for (int j = 0; j < v.length; ++j) {
                System.out.print(F[i][j] + " ");
            }
            System.out.println();
        }

        float maxFlow = 0.0f;
        for (int i = 0; i < v.length; ++i) {
            maxFlow += F[i][sink];
        }
        System.out.println("Max flow: " + maxFlow);

        Assert.assertTrue(CustomMath.equalsFloat(17.0f, maxFlow));
    }
}
