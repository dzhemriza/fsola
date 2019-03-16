/*
 * org.fsola
 *
 * File Name: KthPowerOfAdjacencyMatrix.java
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
import org.fsola.introduction.SimpleAritmetics;

/**
 * Computes K-th power of Adjacency Matrix. After computation of the K-th power
 * resulting matrix will contains the number of paths with length K.
 * <p/>
 * Used Resources: README file resources section line 1.
 */
public class KthPowerOfAdjacencyMatrix {

    public static <V> int[][] compute(AdjacencyMatrix<V> graph, int k) {
        int[][] original = graph.cloneToUnweightedMatrix();

        int[][] x = SimpleAritmetics.matrixPower(original, k);

        return x;
    }
}
