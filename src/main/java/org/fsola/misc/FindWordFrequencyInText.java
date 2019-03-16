/*
 * org.fsola
 *
 * File Name: FindWordFrequencyInText.java
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

import org.fsola.datastructures.hashtable.RobinHoodHashTable;

/**
 * Problem: Find word frequency in given text separated by space symbol.
 * <p/>
 * Used Resources: README file resources section line 1
 */
public class FindWordFrequencyInText {

    /**
     * Class represents word and it's frequency.
     */
    public static class Word {
        private String word;
        private int frequency;

        public Word(String word, int frequency) {
            this.word = word;
            this.frequency = frequency;
        }

        public String getWord() {
            return word;
        }

        public int getFrequency() {
            return frequency;
        }

        public void increaseFrequency() {
            frequency++;
        }
    }

    /**
     * Solves the problem and returns {@link org.fsola.datastructures
     * .hashtable.RobinHoodHashTable} that contains all words and it's
     * frequencies.
     *
     * @param text
     * @return
     */
    public static RobinHoodHashTable<String, Word> solve(String text) {
        RobinHoodHashTable<String, Word> result = new RobinHoodHashTable<>();

        String[] words = text.split("\\s+"); // split string by space

        for (String strWord : words) {
            Word word = result.get(strWord);

            if (word == null) {
                word = new Word(strWord, 1);
            } else {
                word.increaseFrequency();
            }

            result.put(strWord, word);
        }

        return result;
    }
}
