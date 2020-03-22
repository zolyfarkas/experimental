/*
 * Copyright 2020 SPF4J.
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
package org.spf4j.base.intv;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * this is the hacker rank variant.
 * @author Zoltan Farkas
 */
public class LongestStringChain2 {

   /*
     * Complete the 'longestChain' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts STRING_ARRAY words as parameter.
     */

    public static int longestChain(List<String> words) {
        int nrWords = words.size();
        if (nrWords < 1 || nrWords > 50000) {
            throw new IllegalArgumentException("Invalid number of words: " + nrWords);
        }
        Collections.sort(words, (a, b) -> Integer.compare(a.length(), b.length()));
        Map<Integer, Integer> length2Idx = new HashMap<>();
        int k = 0;
        for (String s : words) {
          length2Idx.putIfAbsent(s.length(), k);
          k++;
        }
        int [] linkUpTo = new int[nrWords];
        Arrays.fill(linkUpTo, 1);
        int maxLength = 0;
        int i =  0;
        while (i < nrWords) {
            int iLength = linkUpTo[i];
            String w1 = words.get(i);
            if (iLength > maxLength) {
                maxLength = iLength;
            }
            Integer next1 = length2Idx.get(w1.length() + 1);
            if (next1 != null) {
              int j = next1;
              while (j < nrWords) {
                  String w2 = words.get(j);
                  int diff = w2.length() - w1.length();
                  if (diff == 0) {
                      throw new IllegalStateException();
                  } else if (diff == 1) {
                      if (isChainX(w1, w2)) {
                          linkUpTo[j] = Math.max(linkUpTo[j], iLength + 1);
                      }
                      j++;
                  } else {
                      break;
                  }
              }
            }
            i++;
        }
        return maxLength;
    }

    // verifies if w1 and w2 can form a chain.
    // it is known that w2.length() - w1.length() == 1
    static boolean isChainX(String w1, String w2) {
        boolean skipped = false;
        for (int i = 0, j = 0, l1 = w1.length(), l2 = w2.length(); i < l1 && j < l2;j++) {
            char c1 = w1.charAt(i);
            char c2 = w2.charAt(j);
            if (c1 != c2) {
                if (skipped) {
                    return false;
                }
                skipped = true;
            } else {
                i++;
            }
        }
        return true;
    }


}
