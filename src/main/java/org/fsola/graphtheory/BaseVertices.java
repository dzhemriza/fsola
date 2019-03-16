/*
 * org.fsola
 *
 * File Name: BaseVertices.java
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
package org.fsola.graphtheory;

import org.fsola.datastructures.graph.AdjacencyMatrix;
import org.fsola.datastructures.vector.BitVector;
import org.fsola.datastructures.vector.Vector;

/**
 * Find base vertices in graph such vertices are those that has
 * reach all other vertices in graph.
 * <p/>
 * Used Resources: README file resources section line 1.
 */
public class BaseVertices {

    public static class Result<V> {
        public Vector<V> base = new Vector<>();
    }

    public static <V> Result findBase(V[] vertices, AdjacencyMatrix<V> graph) {
        Result<V> r = new Result<>();
        BitVector used = new BitVector();
        BitVector base = new BitVector();

        for (int i = 0; i < vertices.length; ++i) {
            base.setTrue(i);
        }

        int[][] A = graph.cloneToUnweightedMatrix();

        for (int i = 0; i < vertices.length; ++i) {
            if (base.getBit(i)) {
                // Cleanup used bit vector
                for (int j = 0; j < vertices.length; ++j) {
                    used.setFalse(j);
                }
                dfs(A, used, base, i);
            }
        }

        for (int i = 0; i < vertices.length; ++i) {
            if (base.getBit(i)) {
                r.base.pushBack(vertices[i]);
            }
        }

        return r;
    }

    private static void dfs(int[][] A, BitVector used, BitVector base, int v) {
        used.setTrue(v);

        for (int i = 0; i < A.length; ++i) {
            if (A[v][i] > 0 && !used.getBit(i)) {
                base.setFalse(i);
                dfs(A, used, base, i);
            }
        }
    }
}
