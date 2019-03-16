/*
 * org.fsola
 *
 * File Name: EulerCycleTest.java
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
import org.fsola.graphtheory.EulerCycle;
import org.fsola.test.utils.Graph;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class EulerCycleTest {

    @Test
    public void test1() {
        Vertex[] v = Graph.buildVertices(6);
        AdjacencyMatrix<Vertex> graph =
                Graph.buildAdjacencyMatrixGraphExample6(v);

        List<Vertex> cycle = EulerCycle.euler(graph, v);

        System.out.print("Euler cycle:");
        for (Vertex vertex : cycle) {
            System.out.print(" " + vertex.getNum());
        }
        System.out.println();

        int[] expected = {0, 1, 0, 4, 0, 5, 3, 2, 1,
                2, 3, 4, 2, 4, 3, 5, 4, 5, 0};

        int i = 0;
        for (Vertex vertex : cycle) {
            Assert.assertEquals(expected[i++], vertex.getNum());
        }
    }

    @Test
    public void test2() {
        Vertex[] v = Graph.buildVertices(6);
        AdjacencyMatrix<Vertex> graph =
                Graph.buildAdjacencyMatrixGraphExample4(v);

        List<Vertex> cycle = EulerCycle.euler(graph, v);

        // No Euler cycle in this graph
        Assert.assertTrue(cycle.isEmpty());
    }
}
