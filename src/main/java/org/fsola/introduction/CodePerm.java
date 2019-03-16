/*
 * org.fsola
 *
 * File Name: CodePerm.java
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
package org.fsola.introduction;

/**
 * Used Resources: README file resources section line 1
 */
public class CodePerm {

    /**
     * Codes given permutation to number.
     *
     * @param perm
     * @return
     */
    public static int codePerm(int[] perm) {
        int[] p = new int[perm.length];

        for (int i = 0; i < perm.length; ++i) {
            p[i] = i + 1;
        }

        int result = 0;

        for (int pos = 0; pos < perm.length; ++pos) {
            int r;
            for (r = 0; perm[pos] != p[r]; ++r)
                ;

            result = result * (perm.length - pos) + r;

            for (int i = r + 1; i < perm.length; ++i) {
                p[i - 1] = p[i];
            }
        }

        return result;
    }

    /**
     * Decodes permutation from given {@code code}.
     *
     * @param code
     * @param n
     * @return
     */
    public static int[] decodePerm(int code, int n) {
        int[] perm = new int[n];
        int[] p = new int[n];

        for (int i = 0; i < n; ++i) {
            p[i] = i + 1;
        }

        int k = n;
        do {
            int m = n - k + 1;

            perm[k - 1] = code % m;
            if (k > 1) {
                code /= m;
            }
        } while (--k > 0);

        k = 0;
        do {
            int m = perm[k];
            perm[k] = p[m];
            if (k < n) {
                for (int i = m + 1; i < n; ++i) {
                    p[i - 1] = p[i];
                }
            }
        } while (++k < n);

        return perm;
    }
}
