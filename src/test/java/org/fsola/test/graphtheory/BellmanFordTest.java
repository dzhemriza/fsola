/*
 * org.fsola
 *
 * File Name: BellmanFordTest.java
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

import org.fsola.datastructures.graph.EdgeList;
import org.fsola.datastructures.graph.Vertex;
import org.fsola.graphtheory.BellmanFord;
import org.fsola.introduction.CustomMath;
import org.fsola.test.utils.Graph;
import org.junit.Assert;
import org.junit.Test;

public class BellmanFordTest {

    @Test
    public void test1() {
        Vertex[] v = Graph.buildVertices(6);
        EdgeList<Vertex>
                graph = Graph.buildEdgeListGraphExample3(v);

        float[] distances = BellmanFord.bellmanFord(v, graph, v[5]);

        Assert.assertTrue(CustomMath.equalZero(distances[5]));

        Assert.assertTrue(CustomMath.equalsFloat(distances[1], 6.0f));
        Assert.assertTrue(CustomMath.equalsFloat(distances[2], 3.0f));
        Assert.assertTrue(CustomMath.equalsFloat(distances[3], 3.0f));
        Assert.assertTrue(CustomMath.equalsFloat(distances[4], 2.0f));
    }
}
