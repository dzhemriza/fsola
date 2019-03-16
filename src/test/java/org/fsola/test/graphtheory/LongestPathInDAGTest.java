/*
 * org.fsola
 *
 * File Name: LongestPathInDAGTest.java
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
import org.fsola.graphtheory.LongestPathInDAG;
import org.fsola.introduction.CustomMath;
import org.fsola.test.utils.Graph;
import org.junit.Assert;
import org.junit.Test;

import static org.fsola.datastructures.linkedlist.OneDirectionalNullTerminatedLinkedList.Node;

public class LongestPathInDAGTest {

    @Test
    public void test1() {
        Vertex[] v = Graph.buildVertices(6);
        AdjacencyMatrix<Vertex> graph =
                Graph.buildAdjacencyMatrixGraphExample4(v);

        LongestPathInDAG.Result r = LongestPathInDAG.longestPath(graph, v);
        Assert.assertTrue(CustomMath.equalsFloat(70.0f, r.max));

        System.out.print("Path:");
        for (Node<Integer> p = r.path.getHead(); p != null;
             p = p.getNextNode()) {
            System.out.print(" " + p.getData());
        }
    }
}
