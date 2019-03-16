/*
 * org.fsola
 *
 * File Name: FindPathInMazeTest.java
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
package org.fsola.test.misc;

import org.fsola.misc.FindShortestPathInMaze;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class FindShortestPathInMazeTest {

    private static void print(List<FindShortestPathInMaze.Point> p) {
        System.out.print("Path:");
        if (p.isEmpty()) {
            System.out.print(" no path!");
        } else {
            for (FindShortestPathInMaze.Point x : p) {
                System.out.print(" (" + x.getX() + ", " + x.getY() + ")");
            }
        }
        System.out.println();
    }

    @Test
    public void test1() {
        boolean[][] maze = new boolean[][]{
                {true, true, true, true},
                {true, true, true, true},
                {false, true, true, true},
                {true, true, true, true}
        };

        List<FindShortestPathInMaze.Point> r = FindShortestPathInMaze.solve(maze);
        print(r);
    }

    @Test
    public void test2() {
        boolean[][] maze = new boolean[][]{
                {true, false, true, true},
                {true, false, true, true},
                {true, false, true, true},
                {true, true, true, true}
        };

        List<FindShortestPathInMaze.Point> r = FindShortestPathInMaze.solve(maze);
        print(r);
    }

    @Test
    public void test3() {
        boolean[][] maze = new boolean[][]{
                {true, true, true, false},
                {true, false, true, true},
                {true, false, false, true},
                {true, false, true, true}
        };

        List<FindShortestPathInMaze.Point> r = FindShortestPathInMaze.solve(maze);
        print(r);
    }

    @Test
    public void test4() {
        boolean[][] maze = new boolean[][]{
                {true, false, true, false},
                {true, false, true, true},
                {true, false, false, true},
                {true, false, true, true}
        };

        List<FindShortestPathInMaze.Point> r = FindShortestPathInMaze.solve(maze);
        print(r);
        Assert.assertTrue(r.isEmpty());
    }
}
