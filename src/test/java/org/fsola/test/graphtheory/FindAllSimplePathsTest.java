/*
 * org.fsola
 *
 * File Name: FindAllSimplePathsTest.java
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

import org.fsola.datastructures.graph.AdjacencyList;
import org.fsola.datastructures.graph.Vertex;
import org.fsola.graphtheory.FindAllSimplePaths;
import org.fsola.test.utils.Graph;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class FindAllSimplePathsTest {

    @Test
    public void test1() {
        Vertex[] v = Graph.buildVertices(15);
        AdjacencyList<Vertex>
                graph = Graph.buildAdjacencyListGraphExample2(v);

        List<List<Vertex>> result = FindAllSimplePaths.solve(graph, v[1],
                v[10]);

        for (List<Vertex> path : result) {
            System.out.print("Path:");
            for (Vertex vertex : path) {
                System.out.print(" " + vertex.getNum());
            }
            System.out.println();
        }

        int[][] expected = new int[][]{
                {1, 2, 5, 7, 10},
                {1, 2, 4, 12, 6, 7, 10},
                {1, 2, 3, 6, 7, 10}
        };

        Assert.assertEquals(expected.length, result.size());
        for (int i = 0; i < result.size(); ++i) {
            Assert.assertEquals(expected[i].length, result.get(i).size());

            for (int j = 0; j < result.get(i).size(); ++j) {
                Assert.assertEquals(expected[i][j],
                        result.get(i).get(j).getNum());
            }
        }
    }
}
