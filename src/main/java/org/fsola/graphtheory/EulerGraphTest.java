/*
 * org.fsola
 *
 * File Name: EulerGraphTest .java
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
package org.fsola.graphtheory;

import org.fsola.datastructures.graph.AdjacencyMatrix;

/**
 * Check is graph contains an Euler cycle.
 * <p/>
 * Used Resources: README file resources section line 1
 */
public class EulerGraphTest {

    public static <V> boolean isEulerGraph(AdjacencyMatrix<V> graph) {
        int[][] matrix = graph.cloneToUnweightedMatrix();

        for (int i = 0; i < graph.getNumberOfVertices(); ++i) {
            int din = 0;
            int dout = 0;

            for (int j = 0; j < graph.getNumberOfVertices(); ++j) {
                if (matrix[i][j] == 1) {
                    din++;
                }
                if (matrix[j][i] == 1) {
                    dout++;
                }

                if (din != dout) {
                    return false;
                }
            }
        }

        return true;
    }
}
