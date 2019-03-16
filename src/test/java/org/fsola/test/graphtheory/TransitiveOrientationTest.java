/*
 * org.fsola
 *
 * File Name: TransitiveOrientationTest.java
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
import org.fsola.graphtheory.TransitiveOrientation;
import org.fsola.test.utils.Graph;
import org.junit.Test;

public class TransitiveOrientationTest {

    @Test
    public void test1() {
        Vertex[] v = Graph.buildVertices(6);
        AdjacencyMatrix<Vertex> graph =
                Graph.buildAdjacencyMatrixGraphExample8(v);

        int[][] m = TransitiveOrientation.transitiveOrientation(graph, v);

        // Print the orientation
        for (int i = 0; i < m.length; ++i) {
            for (int j = 0; j < m[i].length; ++j) {
                System.out.print(" " + m[i][j]);
            }
            System.out.println();
        }
    }
}
