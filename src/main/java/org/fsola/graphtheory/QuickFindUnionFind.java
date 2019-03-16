/*
 * org.fsola
 *
 * File Name: QuickFindUnitFind.java
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

/**
 * Union Find - Quick Find algorithm.
 * <p/>
 * Used Resources: README file resources section line 57.
 */
public class QuickFindUnionFind {
    private final int[] V;

    /**
     * @param N Number of vertices
     */
    public QuickFindUnionFind(int N) {
        V = new int[N];

        for (int i = 0; i < V.length; ++i) {
            V[i] = i;
        }

        print();
    }

    /**
     * Check is there a connection between p vertex and q vertex.
     *
     * @param p
     * @param q
     * @return
     */
    public boolean isConnected(int p, int q) {
        return (V[p] == V[q]);
    }

    /**
     * Make a connection between p vertex and q vertex.
     *
     * @param p
     * @param q
     */
    public void union(int p, int q) {
        int P = V[p];
        int Q = V[q];

        for (int i = 0; i < V.length; ++i) {
            if (V[i] == P) {
                V[i] = Q;
            }
        }

        print();
    }

    private void print() {
        // Debug
        System.out.print("Debug:");
        for (int i = 0; i < V.length; ++i) {
            System.out.print(" " + V[i]);
        }
        System.out.println();
    }
}
