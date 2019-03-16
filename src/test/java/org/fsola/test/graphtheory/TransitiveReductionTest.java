/*
 * org.fsola
 *
 * File Name: TransitiveReductionTest.java
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
import org.fsola.graphtheory.TransitiveReduction;
import org.fsola.graphtheory.Warshall;
import org.fsola.test.utils.Graph;
import org.fsola.test.utils.Utils;
import org.junit.Test;

public class TransitiveReductionTest {

    @Test
    public void test1() {
        int N = 6;

        Vertex[] v = Graph.buildVertices(N);
        AdjacencyMatrix<Vertex>
                graph = Graph.buildAdjacencyMatrixGraphExample9(v);

        int[][] matrix = Warshall.warshall(v, graph);

        System.out.println("Reachability matrix:");
        Utils.printMatrix(matrix);

        int[][] newMatrix = TransitiveReduction.transitiveReduction(matrix);
        System.out.println("Adjacency matrix:");
        Utils.printMatrix(newMatrix);
    }
}
