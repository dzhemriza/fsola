/*
 * org.fsola
 *
 * File Name: PrimTest.java
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
import org.fsola.datastructures.graph.Edge;
import org.fsola.datastructures.graph.Vertex;
import org.fsola.graphtheory.Prim;
import org.fsola.introduction.CustomMath;
import org.fsola.test.utils.Graph;
import org.junit.Assert;
import org.junit.Test;

public class PrimTest {

    @Test
    public void test1() {
        final int V = 6;
        Vertex[] v = Graph.buildVertices(V);
        AdjacencyMatrix<Vertex> graph = Graph.buildAdjacencyMatrixGraphExample13(v);

        Prim.Result<Vertex> r = Prim.prim(v, graph);
        Assert.assertNotNull(r);

        System.out.println("Total weight of MST: " + r.totalWeight);
        System.out.print("MST edges: ");
        for (Edge<Vertex> edge : r.edgeList) {
            System.out.print("(" + edge.getX().hashCode()
                    + ", " + edge.getY().hashCode() + ") ");
        }
        System.out.println();

        Assert.assertTrue(CustomMath.equalsFloat(9.0f, r.totalWeight));
        int[][] edgesInMST = {
                {0, 1}, {1, 4}, {4, 3}, {5, 2}, {4, 5}
        };

        for (int i = 0; i < edgesInMST.length; ++i) {
            boolean found = false;

            for (Edge<Vertex> edge : r.edgeList) {
                if (edge.getX().hashCode() == edgesInMST[i][0] &&
                        edge.getY().hashCode() == edgesInMST[i][1]) {
                    found = true;
                    break;
                }
            }

            if (!found) {
                Assert.fail("Unable to find edge: (" + edgesInMST[i][0]
                        + ", " + edgesInMST[i][1] + ") ");
            }
        }
    }
}
