/*
 * org.fsola
 *
 * File Name: CenterRadius.java
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
import org.fsola.introduction.CustomMath;

/**
 * Find center and radius of the graph.
 * <p/>
 * Used Resources: README file resources section line 1.
 */
public class CenterRadius {

    public static class Result {
        public float radius = 0.0f;

        public int center = -1;
    }

    public static <V> Result solve(AdjacencyMatrix<V> graph) {
        Result r = new Result();
        float[][] A = graph.cloneMatrix();

        floyd(A);
        findCenter(A, r);

        return r;
    }

    /**
     * Find center of the graph.
     *
     * @param A
     * @param r
     */
    private static void findCenter(float[][] A, Result r) {
        float min = Float.MAX_VALUE;
        int center = -1;

        for (int i = 0; i < A.length; ++i) {
            float max = A[i][0] + A[0][i];

            for (int j = 0; j < A.length; ++j) {
                if ((i != j) && (A[i][j] + A[j][i] > max)) {
                    max = A[i][j] + A[j][i];
                }
            }

            if (min > max) {
                min = max;
                center = i;
            }
        }

        r.radius = min;
        r.center = center;
    }

    /**
     * Find all pairs shortest path
     *
     * @param A
     */
    private static void floyd(float[][] A) {
        for (int i = 0; i < A.length; ++i) {
            for (int j = 0; j < A.length; ++j) {
                if (CustomMath.equalZero(A[i][j])) {
                    A[i][j] = Float.MAX_VALUE;
                }
            }
        }

        for (int k = 0; k < A.length; ++k) {
            for (int i = 0; i < A.length; ++i) {
                for (int j = 0; j < A.length; ++j) {
                    if (A[i][j] > A[i][k] + A[k][j]) {
                        A[i][j] = A[i][k] + A[k][j];
                    }
                }
            }
        }

        for (int i = 0; i < A.length; ++i) {
            A[i][i] = 0.0f;
        }
    }
}
