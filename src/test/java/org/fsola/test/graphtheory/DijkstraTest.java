/*
 * org.fsola
 *
 * File Name: DijkstraTest.java
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
import org.fsola.graphtheory.Dijkstra;
import org.fsola.datastructures.graph.Vertex;
import org.fsola.introduction.CustomMath;
import org.fsola.test.utils.Graph;
import org.junit.Assert;
import org.junit.Test;

import static org.fsola.graphtheory.Dijkstra.Result;

public class DijkstraTest {

    @Test
    public void test1() {
        Vertex[] v = Graph.buildVertices(6);
        AdjacencyList<Vertex>
                graph = Graph.buildAdjacencyListGraphExample3(v);

        Result r = Dijkstra.dijkstra(v, graph, v[5]);

        Assert.assertTrue(CustomMath.equalZero(r.distances[5]));

        Assert.assertTrue(CustomMath.equalsFloat(r.distances[1], 6.0f));
        Assert.assertTrue(CustomMath.equalsFloat(r.distances[2], 3.0f));
        Assert.assertTrue(CustomMath.equalsFloat(r.distances[3], 3.0f));
        Assert.assertTrue(CustomMath.equalsFloat(r.distances[4], 2.0f));

        // Reconstruct the paths
        for (int i = 0; i < v.length; ++i) {
            // Ignore starting vertex
            if (i == v[5].hashCode()) {
                continue;
            }

            if (CustomMath.equalsFloat(r.distances[i], Dijkstra.MAX)) {
                System.out.println("No path from v[5] to v[" + i + "]!");
            } else {
                System.out.print("Shortest path from v[5] to v[" + i + "]:");
                printPath(r, v[5].hashCode(), i);
                System.out.println();
            }
        }
    }

    private void printPath(Result r, int start, int j) {
        if (r.predecessors[j] != start) {
            printPath(r, start, r.predecessors[j]);
        }
        System.out.print(" " + j);
    }
}
