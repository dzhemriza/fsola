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

import java.util.List;

/**
 * Interface used to represent a graph data structure.
 * <p/>
 * Used Resources: README file resources section line 32
 *
 * @param <V> Vertex class representation
 */
public interface Graph<V> {

    /**
     * Tests whether there is an edge from node x to node y.
     *
     * @param x
     * @param y
     * @return
     */
    public boolean adjacent(V x, V y);

    /**
     * Lists all nodes y such that there is an edge from x to y.
     *
     * @param x
     * @return Empty list if they are no neighbors.
     */
    public List<V> neighbors(V x);

    /**
     * Adds to G the edge from x to y, if it is not there.
     *
     * @param x
     * @param y
     * @param weight
     */
    public void add(V x, V y, float weight);

    /**
     * Removes the edge from x to y, if it is there.
     *
     * @param x
     * @param y
     */
    public void delete(V x, V y);

    /**
     * Retrieve the edge between 2 vertices.
     *
     * @param x
     * @param y
     */
    public Edge<V> getEdge(V x, V y);
}
