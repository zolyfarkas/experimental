
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
import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/longest-string-chain/
 * @author Zoltan Farkas
 */
public class LongestStringChain {

  public static int longestStrChain(String[] words) {
    if (words.length <= 0) {
      return 0;
    }
    Arrays.sort(words, (a, b) -> Integer.compare(a.length(), b.length()));
    int[] linkUpTo = new int[words.length];
    Arrays.fill(linkUpTo, 1);
    Map<Integer, Integer> length2Idx = new HashMap<>();
    int k = 0;
    for (String s : words) {
      length2Idx.putIfAbsent(s.length(), k);
      k++;
    }
    int maxLength = 0;
    int i = 0;
    while (i < words.length) {
      int iLength = linkUpTo[i];
      String w1 = words[i];
      if (iLength > maxLength) {
        maxLength = iLength;
      }
      Integer next1 = length2Idx.get(w1.length() + 1);
      if (next1 != null) {
        int j = next1;
        while (j < words.length) {
          String w2 = words[j];
          int diff = w2.length() - w1.length();
          if (diff == 0) {
            throw new IllegalStateException();
          } else if (diff == 1) {
            if (isChain1Diff(w1, w2)) {
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

  public static boolean isChain(String ps1, String ps2) {
    int l1 = ps1.length();
    int l2 = ps2.length();
    if (l1 == l2 || Math.abs(l1 - l2) > 1) {
      return false;
    }
    String s1;
    String s2;
    if (l1 > l2) {
      s1 = ps2;
      s2 = ps1;
    } else {
      s1 = ps1;
      s2 = ps2;
    }
    return isChain1Diff(s1, s2);
  }

  public static boolean isChain1Diff(String s1, String s2) {
    boolean skipped = false;
    for (int i = 0, j = 0, ll2 = s2.length(), ll1 = s1.length(); i < ll2 && j < ll1;) {
      char c1 = s1.charAt(j);
      char c2 = s2.charAt(i);
      if (c1 != c2) {
        if (skipped) {
          return false;
        }
        i++;
        skipped = true;
      } else {
        i++;
        j++;
      }
    }
    return true;
  }

}
