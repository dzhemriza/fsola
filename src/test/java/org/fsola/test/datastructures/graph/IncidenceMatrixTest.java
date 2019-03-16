/*
 * org.fsola
 *
 * File Name: IncidenceMatrixTest.java
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
package org.fsola.test.datastructures.graph;

import org.fsola.datastructures.graph.IncidenceMatrix;
import org.fsola.datastructures.graph.Vertex;
import org.fsola.test.utils.Graph;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class IncidenceMatrixTest {

    @Test
    public void test1() {
        Vertex[] v = Graph.buildVertices(5);
        IncidenceMatrix<Vertex>
                graph = Graph.buildIncidenceMatrixGraphExample1(v);

        // [0]
        Assert.assertTrue(graph.adjacent(v[0], v[1]));
        Assert.assertTrue(graph.adjacent(v[0], v[2]));

        // [1]
        Assert.assertTrue(graph.adjacent(v[1], v[2]));
        Assert.assertTrue(graph.adjacent(v[1], v[3]));
        Assert.assertTrue(graph.adjacent(v[1], v[0]));

        // [2]
        Assert.assertTrue(graph.adjacent(v[2], v[0]));
        Assert.assertTrue(graph.adjacent(v[2], v[3]));
        Assert.assertTrue(graph.adjacent(v[2], v[4]));
        Assert.assertTrue(graph.adjacent(v[2], v[1]));

        // [3]
        Assert.assertTrue(graph.adjacent(v[3], v[1]));
        Assert.assertTrue(graph.adjacent(v[3], v[2]));

        // [4]
        Assert.assertTrue(graph.adjacent(v[4], v[2]));
    }

    @Test
    public void test2() {
        Vertex[] v = Graph.buildVertices(5);
        IncidenceMatrix<Vertex>
                graph = Graph.buildIncidenceMatrixGraphExample1(v);

        graph.delete(v[0], v[1]);
        Assert.assertFalse(graph.adjacent(v[0], v[1]));
    }

    @Test
    public void test3() {
        Vertex[] v = Graph.buildVertices(5);
        IncidenceMatrix<Vertex>
                graph = Graph.buildIncidenceMatrixGraphExample1(v);

        List<Vertex> neighbors = graph.neighbors(v[0]);

        Assert.assertEquals(2, neighbors.size());
        Assert.assertEquals(1, neighbors.get(0).getNum());
        Assert.assertEquals(2, neighbors.get(1).getNum());
    }

    @Test
    public void test4() {
        Vertex[] v = Graph.buildVertices(5);
        IncidenceMatrix<Vertex>
                graph = Graph.buildIncidenceMatrixGraphExample1(v);

        Assert.assertNotNull(graph.getEdge(v[0], v[1]));
        Assert.assertNull(graph.getEdge(v[0], v[3]));
    }
}
