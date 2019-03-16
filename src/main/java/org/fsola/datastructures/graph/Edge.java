/*
 * org.fsola
 *
 * File Name: EdgeList.java
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
package org.fsola.datastructures.graph;

/**
 * Edge - pair between 2 vertices. Each vertex is represented as {@code int}.
 *
 * @param <V> Vertex class representation
 */
public class Edge<V> implements Comparable<Edge<V>> {
    private V x;
    private V y;
    private float weight;

    /**
     * @param x vertex
     * @param y vertex
     */
    public Edge(V x, V y, float weight) {
        set(x, y, weight);
    }

    /**
     * Set new values for {@code x} and {@code y} vertices.
     *
     * @param x
     * @param y
     */
    public void set(V x, V y, float weight) {
        this.x = x;
        this.y = y;
        this.weight = weight;
    }

    public V getX() {
        return x;
    }

    public V getY() {
        return y;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    /**
     * Comparison based on edge weight.
     */
    @Override
    public int compareTo(Edge<V> vEdge) {
        if (vEdge != null) {
            if (this.weight < vEdge.weight) {
                return -1;
            } else if (this.weight > vEdge.weight) {
                return 1;
            } else {
                return 0;
            }
        }
        return 1;
    }
}
