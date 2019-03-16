/*
 * org.fsola
 *
 * File Name: AllCyclesTest.java
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
import org.fsola.graphtheory.AllCycles;
import org.fsola.test.utils.Graph;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class AllCyclesTest {

    @Test
    public void test1() {
        Vertex[] v = Graph.buildVertices(6);
        AdjacencyMatrix<Vertex> graph =
                Graph.buildAdjacencyMatrixGraphExample5(v);

        Assert.assertEquals(true, true);

        List<List<Integer>> cycles = AllCycles.allCycles(v, graph);

        System.out.println("All simple cycles:");
        for (List<Integer> c : cycles) {
            System.out.print("Cycle:");
            for (Integer i : c) {
                System.out.print(" " + i);
            }
            System.out.println();
        }

        int[][] expected = {{0, 1, 2}, {1, 2, 5, 3}, {4, 3, 5}};
        int i = 0;
        for (List<Integer> c : cycles) {
            int j = 0;
            for (Integer k : c) {
                Assert.assertEquals(Integer.valueOf(expected[i][j]), k);
                ++j;
            }
            ++i;
        }
    }
}
