/*
 * org.fsola
 *
 * File Name: CompanyControlTest.java
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
package org.fsola.test.misc;

import org.fsola.datastructures.graph.AdjacencyMatrix;
import org.fsola.datastructures.graph.Vertex;
import org.fsola.misc.CompanyControl;
import org.fsola.test.utils.Graph;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class CompanyControlTest {

    @Test
    public void test1() {
        int N = 6;

        Vertex[] v = Graph.buildVertices(N);
        AdjacencyMatrix<Vertex>
                graph = Graph.buildAdjacencyMatrixGraphExample10(v);

        ArrayList<Integer> result = CompanyControl.solve(graph, v, 0);

        Assert.assertNotNull(result);

        System.out.print("Result:");
        for (Integer i : result) {
            System.out.print(" " + i);
        }
        System.out.println();

        Assert.assertEquals(Integer.valueOf(1), result.get(0));
        Assert.assertEquals(Integer.valueOf(3), result.get(1));
        Assert.assertEquals(Integer.valueOf(4), result.get(2));
    }
}
