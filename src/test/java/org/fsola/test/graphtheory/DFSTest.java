/*
 * org.fsola
 *
 * File Name: DFSTest.java
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
import org.fsola.graphtheory.DFS;
import org.fsola.graphtheory.VertexVisitor;
import org.fsola.test.utils.Graph;
import org.junit.Assert;
import org.junit.Test;

public class DFSTest {

    private int pos = 0;
    private int[] vertices;

    class TestVertexVisitor implements VertexVisitor<Vertex> {
        @Override
        public void visit(Vertex vertex) {
            System.out.println("Visiting Vertex: " + vertex.getNum());
            vertices[pos++] = vertex.getNum();
        }

        @Override
        public void processEdge(Vertex parent, Vertex child) {
            // Do nothing
        }
    }

    @Test
    public void test1() {
        Vertex[] v = Graph.buildVertices(15);
        AdjacencyList<Vertex>
                graph = Graph.buildAdjacencyListGraphExample2(v);

        pos = 0;
        vertices = new int[10];
        for (int i = 0; i < vertices.length; ++i) {
            vertices[i] = 0;
        }

        DFS.dfs(graph, v[1], new TestVertexVisitor());

        Assert.assertArrayEquals(new int[]{1, 2, 5, 7, 10, 11, 6, 12, 4, 3},
                vertices);
    }

    @Test
    public void test2() {
        Vertex[] v = Graph.buildVertices(15);
        AdjacencyList<Vertex>
                graph = Graph.buildAdjacencyListGraphExample2(v);

        pos = 0;
        vertices = new int[1];
        for (int i = 0; i < vertices.length; ++i) {
            vertices[i] = 0;
        }

        DFS.dfs(graph, v[0], new TestVertexVisitor());

        Assert.assertArrayEquals(new int[]{0}, vertices);
    }

    @Test
    public void test3() {
        Vertex[] v = Graph.buildVertices(15);
        AdjacencyList<Vertex>
                graph = Graph.buildAdjacencyListGraphExample2(v);

        pos = 0;
        vertices = new int[4];
        for (int i = 0; i < vertices.length; ++i) {
            vertices[i] = 0;
        }

        DFS.dfs(graph, v[8], new TestVertexVisitor());

        Assert.assertArrayEquals(new int[]{8, 9, 14, 13}, vertices);
    }
}
