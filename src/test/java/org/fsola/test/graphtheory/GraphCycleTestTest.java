/*
 * org.fsola
 *
 * File Name: GraphCycleTestTest.java
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
import org.fsola.graphtheory.GraphCycleTest;
import org.fsola.test.utils.Graph;
import org.junit.Assert;
import org.junit.Test;

public class GraphCycleTestTest {

    @Test
    public void test1() {
        Vertex[] v = Graph.buildVertices(15);
        AdjacencyList<Vertex>
                graph = Graph.buildAdjacencyListGraphExample2(v);

        Assert.assertTrue(GraphCycleTest.hasCycle(graph, v[1]));
    }

    @Test
    public void test2() {
        Vertex[] v = Graph.buildVertices(15);
        AdjacencyList<Vertex>
                graph = Graph.buildAdjacencyListGraphExample2(v);

        Assert.assertFalse(GraphCycleTest.hasCycle(graph, v[0]));
    }
}
