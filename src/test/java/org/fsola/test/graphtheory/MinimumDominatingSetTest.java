/*
 * org.fsola
 *
 * File Name: MinimumDominatingSetTest.java
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
import org.fsola.datastructures.vector.Vector;
import org.fsola.graphtheory.MinimumDominatingSet;
import org.fsola.test.utils.Graph;
import org.junit.Assert;
import org.junit.Test;

public class MinimumDominatingSetTest {

    @Test
    public void test1() {
        int N = 6;
        Vertex[] vertices = Graph.buildVertices(N);
        AdjacencyMatrix<Vertex> graph = Graph.buildAdjacencyMatrixGraphExample14(vertices);
        Assert.assertNotNull(graph);

        MinimumDominatingSet.Result<Vertex> r = MinimumDominatingSet.solve(graph, vertices);
        Assert.assertNotNull(r);

        for (int i = 0; i < r.mimimumDominatingSet.size(); ++i) {
            Vector<Vertex> v = r.mimimumDominatingSet.get(i);

            System.out.print("MinimumDominatingSet:");
            for (int j = 0; j < v.size(); ++j) {
                System.out.print(" " + v.get(j).hashCode());
            }
            System.out.println();
        }
    }
}
