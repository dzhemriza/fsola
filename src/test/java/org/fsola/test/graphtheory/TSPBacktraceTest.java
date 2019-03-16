/*
 * org.fsola
 *
 * File Name: TSPBacktraceTest.java
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
import org.fsola.graphtheory.TSPBacktrace;
import org.fsola.introduction.CustomMath;
import org.fsola.test.utils.Graph;
import org.junit.Assert;
import org.junit.Test;

import static org.fsola.graphtheory.TSPBacktrace.Result;

public class TSPBacktraceTest {

    @Test
    public void test1() {
        Vertex[] v = Graph.buildVertices(6);
        AdjacencyMatrix<Vertex> graph =
                Graph.buildAdjacencyMatrixGraphExample6(v);

        Result<Vertex> result = TSPBacktrace.hamilton(graph, v);

        System.out.println("Min Hamilton cycle cost: " + result.cost);

        System.out.print("Min Hamilton cycle: " + v[0].getNum());
        for (Vertex vx : result.minCycle) {
            System.out.print(" " + vx.getNum());
        }
        System.out.println();

        Assert.assertTrue(CustomMath.equalsFloat(28, result.cost));
    }
}
