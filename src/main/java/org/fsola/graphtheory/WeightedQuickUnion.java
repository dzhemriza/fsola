/*
 * org.fsola
 *
 * File Name: WeightedQuickUnion.java
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
 * Weighted Quick Union Find using path compression.
 * <p/>
 * Used Resources: README file resources section line 57.
 */
public class WeightedQuickUnion {

    private final int[] V;
    private final int[] W;

    /**
     * @param N Number of vertices
     */
    public WeightedQuickUnion(int N) {
        V = new int[N];
        W = new int[N];

        for (int i = 0; i < V.length; ++i) {
            V[i] = i;
            W[i] = 1;
        }

        print();
    }

    /**
     * Find root of p.
     *
     * @param p
     * @return
     */
    public int findRoot(int p) {
        int i = p;
        while (V[i] != i) {
            V[i] = V[V[i]];
            i = V[i];
        }

        return i;
    }

    /**
     * Check is there a connection between p vertex and q vertex.
     *
     * @param p
     * @param q
     * @return
     */
    public boolean isConnected(int p, int q) {
        int P = findRoot(p);
        int Q = findRoot(q);
        return (P == Q);
    }

    /**
     * Union between p and q with outcome is both p and q belongs to
     * one connected component.
     *
     * @param p
     * @param q
     * @return
     */
    public boolean unionOperation(int p, int q) {
        int P = findRoot(p);
        int Q = findRoot(q);

        if (P != Q) {
            // Based on sub tree weights we decide to move
            // tree with lowest weight under tree with highest
            // weight.
            if (W[P] < W[Q]) {
                V[P] = Q;
                W[Q] += W[P];
            } else {
                V[Q] = P;
                W[P] += W[Q];
            }

            return true;
        }

        return false;
    }

    /**
     * Make a connection between p vertex and q vertex.
     *
     * @param p
     * @param q
     */
    public void union(int p, int q) {
        unionOperation(p, q);

        print();
    }

    private void print() {
        // Debug
        System.out.print("Debug V:");
        for (int i = 0; i < V.length; ++i) {
            System.out.print(" " + V[i]);
        }
        // Debug
        System.out.print(" | W:");
        for (int i = 0; i < W.length; ++i) {
            System.out.print(" " + W[i]);
        }
        System.out.println();
    }
}
