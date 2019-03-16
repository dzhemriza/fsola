/*
 * org.fsola
 *
 * File Name: ShortestPathUnweightedGraphTest.java
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
import org.fsola.datastructures.linkedlist.OneDirectionalNullTerminatedLinkedList;
import org.fsola.graphtheory.ShortestPathUnweightedGraph;
import org.fsola.test.utils.Graph;
import org.junit.Assert;
import org.junit.Test;

import static org.fsola.datastructures.linkedlist.OneDirectionalNullTerminatedLinkedList.Node;

public class ShortestPathUnweightedGraphTest {

    @Test
    public void test1() {
        Vertex[] v = Graph.buildVertices(15);
        AdjacencyList<Vertex>
                graph = Graph.buildAdjacencyListGraphExample2(v);

        OneDirectionalNullTerminatedLinkedList<Vertex> path =
                ShortestPathUnweightedGraph.shortestPath(graph, v[1], v[10]);

        int pos = 0;
        int[] expectedPath = new int[]{1, 2, 5, 7, 10};
        for (Node<Vertex> p = path.getHead(); p != null; p = p.getNextNode()) {
            Assert.assertEquals(expectedPath[pos++], p.getData().getNum());
        }
    }

    @Test
    public void test2() {
        Vertex[] v = Graph.buildVertices(15);
        AdjacencyList<Vertex>
                graph = Graph.buildAdjacencyListGraphExample2(v);

        OneDirectionalNullTerminatedLinkedList<Vertex> path =
                ShortestPathUnweightedGraph.shortestPath(graph, v[0], v[10]);

        Assert.assertTrue(path.isEmpty());
    }
}
