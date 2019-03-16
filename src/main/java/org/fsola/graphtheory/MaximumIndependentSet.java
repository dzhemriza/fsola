/*
 * org.fsola
 *
 * File Name: MaximumIndependentSet.java
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

import java.util.List;

/**
 * Find maximum independent set in graph.
 * <p/>
 * Used Resources: README file resources section line 1.
 */
public class MaximumIndependentSet {

    public static class Result1<V> {
        /**
         * Contains result of the maximum independent set.
         */
        public Vector<V> maxIndependentSet = new Vector<>();
    }

    public static <V> Result1<V> maxIndependentSet(V[] vertices, AdjacencyMatrix<V> graph) {
        Result1<V> r = new Result1<>();

        int inSetC = 0; // Number of items in set
        BitVector inSet = new BitVector(); // Vertices in max set

        int notInSetC = 0; // Number of items not in set
        BitVector notInSet = new BitVector(); // Vertices not in max set

        while (inSetC + notInSetC < vertices.length) {
            for (int i = 0; i < vertices.length; ++i) {
                if (!inSet.getBit(vertices[i].hashCode()) &&
                        !notInSet.getBit(vertices[i].hashCode())) {
                    // Add vertex in max independent set
                    inSetC++;
                    inSet.setTrue(vertices[i].hashCode());

                    // Add all adjacent vertices to vertices[i] in notInSet
                    List<V> neighbors = graph.neighbors(vertices[i]);
                    for (V neighbor : neighbors) {
                        if (!notInSet.getBit(neighbor.hashCode())) {
                            notInSet.setTrue(neighbor.hashCode());
                            notInSetC++;
                        }
                    }
                }
            }
        }

        for (int i = 0; i < vertices.length; ++i) {
            if (inSet.getBit(vertices[i].hashCode())) {
                r.maxIndependentSet.pushBack(vertices[i]);
            }
        }

        return r;
    }

    public static class Result2<V> {
        /**
         * Contains all maximum independent sets.
         */
        public Vector<Vector<V>> maxIndependentSets = new Vector<>();
    }

    private static <V> void result(BitVector inSet, V[] vertices, Result2<V> r) {
        Vector<V> res = new Vector<>();

        for (int i = 0; i < vertices.length; ++i) {
            if (inSet.getBit(vertices[i].hashCode())) {
                res.pushBack(vertices[i]);
            }
        }

        r.maxIndependentSets.pushBack(res);
    }

    private static <V> void maxIndependentSetsImpl(V[] vertices, AdjacencyMatrix<V> graph,
                                                   int last, int inSetC, BitVector inSet,
                                                   int notInSetC, BitVector notInSet,
                                                   Result2<V> r) {
        if (inSetC + notInSetC == vertices.length) {
            result(inSet, vertices, r);
        } else {
            for (int i = last; i < vertices.length; ++i) {
                if (!inSet.getBit(vertices[i].hashCode()) &&
                        !notInSet.getBit(vertices[i].hashCode())) {
                    inSet.setTrue(vertices[i].hashCode());
                    inSetC++;

                    Vector<Integer> initialState = new Vector<>();
                    List<V> neighbors = graph.neighbors(vertices[i]);
                    for (V neighbor : neighbors) {
                        if (!notInSet.getBit(neighbor.hashCode())) {
                            notInSet.setTrue(neighbor.hashCode());
                            notInSetC++;
                            initialState.pushBack(neighbor.hashCode());
                        }
                    }

                    maxIndependentSetsImpl(vertices, graph, i, inSetC, inSet,
                            notInSetC, notInSet, r);

                    for (int j = 0; j < initialState.size(); ++j) {
                        notInSetC--;
                        notInSet.setFalse(initialState.get(j));
                    }

                    --inSetC;
                    inSet.setFalse(vertices[i].hashCode());
                }
            }
        }
    }

    public static <V> Result2<V> maxIndependentSets(V[] vertices, AdjacencyMatrix<V> graph) {
        Result2<V> r = new Result2<>();

        int inSetC = 0; // Number of items in set
        BitVector inSet = new BitVector(); // Vertices in max set

        int notInSetC = 0; // Number of items not in set
        BitVector notInSet = new BitVector(); // Vertices not in max set

        maxIndependentSetsImpl(vertices, graph, 0, inSetC,
                inSet, notInSetC, notInSet, r);

        return r;
    }
}
