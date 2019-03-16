/*
 * org.fsola
 *
 * File Name: EulerCycle.java
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
import org.fsola.datastructures.stack.LinkedListStack;

import java.util.ArrayList;
import java.util.List;

/**
 * Finds Euler cycle in graph.
 * <p/>
 * Used Resources: README file resources section line 1.
 */
public class EulerCycle {

    public static <V> List<V> euler(AdjacencyMatrix<V> graph, V[] vertices) {
        List<V> result = new ArrayList<>();
        int[][] matrix = graph.cloneToUnweightedMatrix();

        if (EulerGraphTest.isEulerGraph(graph)) {
            LinkedListStack<V> stack = new LinkedListStack<>();
            LinkedListStack<V> cStack = new LinkedListStack<>();

            // Add random vertex in stack
            stack.push(vertices[0]);

            while (!stack.isEmpty()) {
                V topVertex = stack.top();
                int i = topVertex.hashCode();

                boolean removed = false;
                for (int j = 0; j < graph.getNumberOfVertices(); ++j) {
                    if (matrix[i][j] == 1) { // edge
                        // Remove the edge from graph
                        matrix[i][j] = 0;
                        removed = true;
                        // Save neighbor vertex and add it into stack below
                        i = j;
                        break;
                    }
                }

                if (removed) {
                    stack.push(vertices[i]);
                } else {
                    cStack.push(stack.pop());
                }
            }

            // Build result
            while (!cStack.isEmpty()) {
                result.add(cStack.pop());
            }
        }

        return result;
    }
}
