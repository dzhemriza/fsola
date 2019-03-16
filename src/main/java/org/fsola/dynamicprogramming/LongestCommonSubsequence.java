/*
 * org.fsola
 *
 * File Name: LongestCommonSubsequence.java
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
package org.fsola.dynamicprogramming;

/**
 * Longest Common Subsequence problem.
 * <p/>
 * Used Resources: README file resources section line 55, line 56.
 */
public class LongestCommonSubsequence {

    public static int lcs1impl(int[] A, int[] B, int I, int J) {
        if (A.length <= I || B.length <= J) {
            return 0;
        }

        int max = 0;
        for (int i = I; i < A.length; ++i) {
            for (int j = J; j < B.length; ++j) {
                if (A[i] == B[j]) {
                    int c = lcs1impl(A, B, i + 1, j + 1) + 1;

                    if (max < c) {
                        max = c;
                    }
                }
            }
        }

        return max;
    }

    public static int lcs1(int[] A, int[] B) {
        return lcs1impl(A, B, 0, 0);
    }

    public static int lcs2(int[] A, int[] B, int m, int n) {
        if (m == 0 || n == 0)
            return 0;
        if (A[m - 1] == B[n - 1])
            return 1 + lcs2(A, B, m - 1, n - 1);
        else
            return Math.max(lcs2(A, B, m, n - 1), lcs2(A, B, m - 1, n));
    }

    public static int lcs3impl(int[] A, int[] B, int[][] M, int[] S, int I, int J) {
        if (A.length <= I || B.length <= J) {
            return 0;
        }

        if (M[I][J] != -1) {
            return M[I][J];
        }

        int max = 0;
        for (int i = I; i < A.length; ++i) {
            for (int j = J; j < B.length; ++j) {
                if (A[i] == B[j]) {
                    int c = lcs3impl(A, B, M, S, i + 1, j + 1) + 1;

                    if (max < c) {
                        max = c;
                        S[i] = I - 1;
                    }
                }
            }
        }

        M[I][J] = max;

        return max;
    }

    public static int lcs3(int[] A, int[] B) {
        int[][] M = new int[A.length][B.length];
        int[] S = new int[Math.max(A.length, B.length)];

        for (int i = 0; i < A.length; ++i) {
            for (int j = 0; j < B.length; ++j) {
                M[i][j] = -1;
            }
        }

        for (int i = 0; i < S.length; ++i) {
            S[i] = -1;
        }

        int r = lcs3impl(A, B, M, S, 0, 0);

        System.out.println("M:");
        for (int i = 0; i < A.length; ++i) {
            for (int j = 0; j < B.length; ++j) {
                System.out.print(" " + M[i][j]);
            }
            System.out.println();
        }

        // Print the LCS
        int start = -1;
        for (int i = S.length - 1; i > 0; --i) {
            if (S[i] != -1) {
                start = i;
                break;
            }
        }

        if (start != -1) {
            System.out.print("LCS:");
            for (int x = start; x >= 0; x = S[x]) {
                System.out.print(" " + A[x]);
            }
            System.out.println();
        }

        return r;
    }

    private static void traceBackLcs4(int[][] M, int[] A, int[] B, int i, int j) {
        if (i == 0 || j == 0) {
            return;
        } else if (A[i - 1] == B[j - 1]) {
            System.out.print(" " + A[i - 1]);
            traceBackLcs4(M, A, B, i - 1, j - 1);
        } else {
            if (M[i][j - 1] > M[i - 1][j]) {
                traceBackLcs4(M, A, B, i, j - 1);
            } else {
                traceBackLcs4(M, A, B, i - 1, j);
            }
        }
    }

    public static int lcs4(int[] A, int[] B) {
        int[][] M = new int[A.length + 1][B.length + 1];

        for (int i = 0; i < A.length; ++i) {
            M[i][0] = 0;
        }
        for (int j = 0; j < B.length; ++j) {
            M[0][j] = 0;
        }

        for (int i = 1; i <= A.length; ++i) {
            for (int j = 1; j <= B.length; ++j) {
                if (A[i - 1] == B[j - 1]) {
                    M[i][j] = M[i - 1][j - 1] + 1;
                } else {
                    M[i][j] = Math.max(M[i - 1][j], M[i][j - 1]);
                }
            }
        }

        System.out.println();
        System.out.print("Traceback:");
        traceBackLcs4(M, A, B, A.length, B.length);
        System.out.println();

        return M[A.length][B.length];
    }
}
