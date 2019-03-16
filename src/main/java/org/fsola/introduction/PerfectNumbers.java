/*
 * org.fsola
 *
 * File Name: PerfectNumbers.java
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

import java.util.ArrayList;
import java.util.List;

/**
 * Calculates first N perfect numbers.<p/> Example of perfect numbers: 6 = 1 + 2
 * + 3, 28 = 1 + 2 + 4 + 7 + 14, ...
 * <p/>
 * Used Resources: README file resources section line 1
 */
public class PerfectNumbers {

    private static int[] FIRST_10_MERSENNE_PRIMES =
            {2, 3, 5, 7, 13, 17, 19, 31, 61, 89};

    /**
     * Calculates Nth perfect number based on (2 ^ (p - 1)) * (2 ^ p - 1).
     *
     * @param n perfect number
     * @param p prime number
     * @return
     */
    public static CustomMath.BigNum perfect(int n, int p) {
        CustomMath.BigNum num = new CustomMath.BigNum();

        for (int i = 0; i < p; ++i) {
            num.doubleN(); // 2 ^ i
        }

        // The last number is always in {2, 4, 6, 8 }
        num.minusLastDigit();

        for (int i = 0; i < p - 1; ++i) {
            num.doubleN();
        }

        return num;
    }

    /**
     * Calculates first 10 perfect numbers.
     *
     * @return
     */
    public static List<String> first10PerfectNumbers() {
        final int MN = 10;
        ArrayList<String> result = new ArrayList<>();

        for (int i = 1; i <= MN; ++i) {
            CustomMath.BigNum perfect =
                    perfect(i, FIRST_10_MERSENNE_PRIMES[i - 1]);
            result.add(perfect.toString());
        }

        return result;
    }
}
