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

import org.fsola.datastructures.linkedlist.BiDirectionalNullTerminatedLinkedList;
import org.fsola.sorting.QuickSort;

import java.util.ArrayList;
import java.util.List;

import static org.fsola.datastructures.linkedlist.BiDirectionalNullTerminatedLinkedList.Node;

/**
 * Graph representation data structure - edge list.
 * <p/>
 * Used Resources: README file resources section line 1, line 32
 * <p/>
 * Complexity by operations: adjacent - O(n), neighbors - O(n), add - O(C),
 * delete - O(n).
 *
 * @param <V>
 */
public class EdgeList<V> implements Graph<V> {

    /**
     * List of edges in graph.
     */
    private BiDirectionalNullTerminatedLinkedList<Edge<V>> edges = new
            BiDirectionalNullTerminatedLinkedList<>();

    @Override
    public boolean adjacent(V x, V y) {
        if (x == null || y == null) {
            throw new IllegalArgumentException("x or y is null!");
        }

        Node<Edge<V>> edge = findEdge(x, y);

        return edge != null ? true : false;
    }

    @Override
    public List<V> neighbors(V x) {
        ArrayList<V> neighbors = new ArrayList<>();

        for (Node<Edge<V>> p = edges.getHead(); p != null; p = p.getNext()) {
            Edge<V> edge = p.getData();

            if (x.equals(edge.getX())) {
                neighbors.add(edge.getY());
            }
        }

        return neighbors;
    }

    @Override
    public void add(V x, V y, float weight) {
        // Check is there such relation
        Node<Edge<V>> node = findEdge(x, y);
        if (node != null) {
            // update only weight
            node.getData().setWeight(weight);
        } else {
            // Add new edge
            Edge<V> newEdge = new Edge<>(x, y, weight);
            edges.push(newEdge);
        }
    }

    @Override
    public void delete(V x, V y) {
        Node<Edge<V>> node = findEdge(x, y);
        if (node != null) {
            edges.delete(node);
        }
    }

    @Override
    public Edge<V> getEdge(V x, V y) {
        Edge<V> result = null;
        Node<Edge<V>> node = findEdge(x, y);

        if (node != null) {
            result = node.getData();
        }

        return result;
    }

    /**
     * Sort edges by weight and return the result in array.
     *
     * @return
     */
    public Edge<V>[] getSortedEdgeArray() {
        int c = 0; // find number of edges
        for (Node<Edge<V>> p = edges.getHead(); p != null; p = p.getNext()) {
            ++c;
        }

        Edge<V>[] edgeArr = new Edge[c];
        int i = 0;
        for (Node<Edge<V>> p = edges.getHead(); p != null; p = p.getNext(), ++i) {
            edgeArr[i] = p.getData();
        }

        QuickSort.quick1(edgeArr);

        return edgeArr;
    }

    /**
     * @return Copy of all edges
     */
    public BiDirectionalNullTerminatedLinkedList getAllEdges() {
        BiDirectionalNullTerminatedLinkedList<Edge<V>> result = new
                BiDirectionalNullTerminatedLinkedList<>();

        for (Node<Edge<V>> p = edges.getHead(); p != null; p = p.getNext()) {
            result.push(p.getData());
        }

        return result;
    }

    private Node<Edge<V>> findEdge(V x, V y) {
        for (Node<Edge<V>> p = edges.getHead(); p != null; p = p.getNext()) {
            Edge<V> edge = p.getData();

            if (x.equals(edge.getX()) && y.equals(edge.getY())) {
                return p;
            }
        }

        return null;
    }
}
