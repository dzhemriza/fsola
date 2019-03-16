/*
 * org.fsola
 *
 * File Name: FindPathInMaze.java
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

import org.fsola.datastructures.queue.LinkedListQueue;

import java.util.ArrayList;
import java.util.List;

/**
 * Find shortest path from (0, 0) to (N-1, N-1) maze described as {@code boolean[][] matrix}, where
 * {@code true} you can move to that position {@code false} you can't move on that position.
 * Allowed only left, up, down and right movement.
 */
public class FindShortestPathInMaze {

    public static class Point {
        private final int x;
        private final int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }

    private static final Point[] MOVES = new Point[]{
            new Point(1, 0), new Point(0, 1), new Point(-1, 0), new Point(0, -1)
    };

    public static List<Point> solve(boolean[][] maze) {
        ArrayList<Point> path = new ArrayList<>();
        int M = maze.length;
        int N = maze[0].length;

        bfs(maze, new Point(0, 0), new Point(N - 1, M - 1), path);

        return path;
    }

    private static int point2int(Point x, int N) {
        return x.getX() * N + x.getY();
    }

    private static Point int2point(int x, int N) {
        return new Point(x / N, x % N);
    }

    private static void bfs(boolean[][] maze, Point start, Point end, List<Point> path) {
        int M = maze.length;
        int N = maze[0].length;
        int[] P = new int[M * N];

        for (int i = 0; i < P.length; ++i) {
            P[i] = -1;
        }

        LinkedListQueue<Point> q = new LinkedListQueue<>();
        boolean[][] visited = new boolean[maze.length][maze[0].length];

        for (int i = 0; i < visited.length; ++i) {
            for (int j = 0; j < visited[0].length; ++j) {
                visited[i][j] = false;
            }
        }

        q.put(start);

        while (!q.isEmpty()) {
            Point p = q.get();

            if (visited[p.getY()][p.getX()]) {
                // If it's already visited just ignore it
                continue;
            }

            // visit point
            visited[p.getY()][p.getX()] = true;

            if (p.getX() == end.getX() && p.getY() == end.getY()) {
                // found the shortest path in maze
                for (int pc = point2int(p, N); pc != -1; pc = P[pc]) {
                    path.add(int2point(pc, N));
                }
                return;
            }

            // Find child moves
            for (int i = 0; i < MOVES.length; ++i) {
                int x = p.getX() + MOVES[i].getX();
                int y = p.getY() + MOVES[i].getY();

                if ((0 <= x) && (x < N) && (0 <= y) && (y < M)) {
                    if (maze[y][x] && !visited[y][x]) {
                        Point u = new Point(x, y);
                        q.put(u);

                        int uc = point2int(u, N);
                        int pc = point2int(p, N);
                        P[uc] = pc;
                    }
                }
            }
        }
    }
}
