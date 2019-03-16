/*
 * org.fsola
 *
 * File Name: GraphConnectivityTest.java
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
import org.fsola.graphtheory.GraphConnectivity;
import org.fsola.test.utils.Graph;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class GraphConnectivityTest {

    @Test
    public void test1() {
        Vertex[] v = Graph.buildVertices(15);
        AdjacencyList<Vertex>
                graph = Graph.buildAdjacencyListGraphExample2(v);

        GraphConnectivity.Result r = GraphConnectivity.connectivity(graph, v);
        Assert.assertNotNull(r);

        System.out.println("All connected components in graph:");
        for (List<Integer> component : r.components) {

            System.out.print("Component:");
            for (Integer i : component) {
                System.out.print(" " + i);
            }
            System.out.println();
        }
    }
}
