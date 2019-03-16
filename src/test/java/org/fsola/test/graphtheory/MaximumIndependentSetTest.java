/*
 * org.fsola
 *
 * File Name: MaximumIndependentSetTest.java
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
import org.fsola.graphtheory.MaximumIndependentSet;
import org.fsola.test.utils.Graph;
import org.junit.Assert;
import org.junit.Test;

public class MaximumIndependentSetTest {

    @Test
    public void test1() {
        int N = 6;
        Vertex[] vertices = Graph.buildVertices(N);
        AdjacencyMatrix<Vertex> graph = Graph.buildAdjacencyMatrixGraphExample13(vertices);
        MaximumIndependentSet.Result1<Vertex> r =
                MaximumIndependentSet.maxIndependentSet(vertices, graph);
        Assert.assertNotNull(r);

        System.out.print("MaximumIndependentSet");
        for (int i = 0; i < r.maxIndependentSet.size(); ++i) {
            System.out.print(" " + r.maxIndependentSet.get(i).hashCode());
        }
        System.out.println();
    }

    @Test
    public void test2() {
        int N = 7;
        Vertex[] vertices = Graph.buildVertices(N);
        AdjacencyMatrix<Vertex> graph = Graph.buildAdjacencyMatrixGraphExample12(vertices);
        MaximumIndependentSet.Result1<Vertex> r =
                MaximumIndependentSet.maxIndependentSet(vertices, graph);
        Assert.assertNotNull(r);

        System.out.print("MaximumIndependentSet");
        for (int i = 0; i < r.maxIndependentSet.size(); ++i) {
            System.out.print(" " + r.maxIndependentSet.get(i).hashCode());
        }
        System.out.println();
    }

    @Test
    public void test3() {
        int N = 6;
        Vertex[] vertices = Graph.buildVertices(N);
        AdjacencyMatrix<Vertex> graph = Graph.buildAdjacencyMatrixGraphExample13(vertices);
        MaximumIndependentSet.Result2<Vertex> r =
                MaximumIndependentSet.maxIndependentSets(vertices, graph);
        Assert.assertNotNull(r);

        for (int i = 0; i < r.maxIndependentSets.size(); ++i) {
            Vector<Vertex> v = r.maxIndependentSets.get(i);

            System.out.print("MaximumIndependentSet");
            for (int j = 0; j < v.size(); ++j) {
                System.out.print(" " + v.get(j).hashCode());
            }
            System.out.println();
        }
    }

    @Test
    public void test4() {
        int N = 7;
        Vertex[] vertices = Graph.buildVertices(N);
        AdjacencyMatrix<Vertex> graph = Graph.buildAdjacencyMatrixGraphExample12(vertices);
        MaximumIndependentSet.Result2<Vertex> r =
                MaximumIndependentSet.maxIndependentSets(vertices, graph);
        Assert.assertNotNull(r);

        for (int i = 0; i < r.maxIndependentSets.size(); ++i) {
            Vector<Vertex> v = r.maxIndependentSets.get(i);

            System.out.print("MaximumIndependentSet");
            for (int j = 0; j < v.size(); ++j) {
                System.out.print(" " + v.get(j).hashCode());
            }
            System.out.println();
        }
    }
}
