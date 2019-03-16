/*
 * org.fsola
 *
 * File Name: WarshallTest.java
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
import org.fsola.graphtheory.Warshall;
import org.fsola.test.utils.Graph;
import org.fsola.test.utils.Utils;
import org.junit.Assert;
import org.junit.Test;

public class WarshallTest {

    @Test
    public void test1() {
        int N = 6;

        Vertex[] v = Graph.buildVertices(N);
        AdjacencyMatrix<Vertex>
                graph = Graph.buildAdjacencyMatrixGraphExample3(v);

        int[][] matrix = Warshall.warshall(v, graph);

        Utils.printMatrix(matrix);

        for (int i = 1; i < N; ++i) {
            for (int j = 1; j < N - 1; ++j) {
                Assert.assertEquals(1, matrix[i][j]);
            }
        }

        for (int i = 0; i < N; ++i) {
            // There is no path from vertex 0 to all others
            Assert.assertEquals(0, matrix[i][0]);
            Assert.assertEquals(0, matrix[0][i]);

            // There is no path from all others to vertex 5
            Assert.assertEquals(0, matrix[i][5]);
        }
    }
}
