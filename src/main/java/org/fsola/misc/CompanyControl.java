/*
 * org.fsola
 *
 * File Name: CompanyControl.java
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
package org.fsola.misc;

import org.fsola.datastructures.graph.AdjacencyMatrix;
import org.fsola.datastructures.vector.BitVector;

import java.util.ArrayList;

/**
 * Find all companies which are managed by parent company. Such companies
 * are that parent company has more than 50% stocks.
 * Companies are represented as graph.
 * <p/>
 * Used Resources: README file resources section line 1.
 */
public class CompanyControl {

    public static <V> ArrayList<Integer> solve(AdjacencyMatrix<V> graph, V[] vertices, int startVertex) {
        ArrayList<Integer> result = new ArrayList<>();
        BitVector used = new BitVector();
        float[] control = new float[vertices.length]; // control in % for each company
        float[][] matrix = graph.cloneMatrix();

        for (int i = 0; i < control.length; ++i) {
            control[i] = matrix[startVertex][i];
        }

        for (int k = 0; k < vertices.length; ++k) {

            for (int i = 0; i < vertices.length; ++i) {

                // If company has control more than 50%
                // and it's not visited yet add all shares
                // of on child companies
                if (control[i] > 50 && !used.getBit(i)) {
                    used.setBit(i, true); // used now

                    for (int j = 0; j < vertices.length; ++j) {
                        control[j] += matrix[i][j];
                    }
                }
            }
        }

        for (int i = 0; i < control.length; ++i) {
            if (control[i] > 50) {
                result.add(i);
            }
        }

        return result;
    }
}
