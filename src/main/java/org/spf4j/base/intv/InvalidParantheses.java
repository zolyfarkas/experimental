/*
 * Copyright 2019 SPF4J.
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

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * https://leetcode.com/problems/remove-invalid-parentheses/
 * @author Zoltan Farkas
 */
public class InvalidParantheses {

  public static String remove(final String s, int[] except) {
    int l = s.length();
    StringBuilder sb = new StringBuilder(l - except.length);
    for (int i = 0, j = 0; i < l; i++) {
      if (j < except.length) {
        if (i == except[j]) {
          j++;
          continue;
        }
      }
      sb.append(s.charAt(i));
    }
    return sb.toString();
  }

  public static boolean validParan(final String s, int[] except) {
    int nrOpen = 0;
    for (int i = 0, l = s.length(), j = 0; i < l; i++) {
      if (j < except.length) {
        if (i == except[j]) {
          j++;
          continue;
        }
      }
      char c = s.charAt(i);
      switch (c) {
        case '(':
          nrOpen++;
          break;
        case ')':
          nrOpen--;
          if (nrOpen < 0) {
            return false;
          }
          break;
      }
    }
    return nrOpen == 0;
  }

  public static List<String> removeInvalidParentheses(final String s) {
    int l = s.length();
    if (l == 0) {
      return Arrays.asList(s);
    }
    if (validParan(s, new int[]{})) {
      return Arrays.asList(s);
    }
    Set<String> result = new HashSet<>(4);
    ArrayDeque<int[]> queue = new ArrayDeque<>(l * 3);
    addToQueue(s, queue, new int[]{});
    int minremovals = Integer.MAX_VALUE;
    if (queue.isEmpty()) {
      return Arrays.asList(s);
    }
    while (true) {
      int[] removals = queue.pollFirst();
      if (removals == null) {
        break;
      }
      if (validParan(s, removals)) {
        int rs = removals.length;
        if (rs > minremovals) {
          return new ArrayList<>(result);
        }
        minremovals = rs;
        result.add(remove(s, removals));
      }
      addToQueue(s, queue, removals);
    }
    return new ArrayList<>(result);
  }

  public static void addToQueue(final String s, Collection<int[]> queue, int[] additional) {
    for (int i = additional.length == 0 ? 0 : additional[additional.length - 1] + 1, l = s.length(); i < l; i++) {
      char c = s.charAt(i);
      switch (c) {
        case '(':
        case ')':
          int[] copyOf = Arrays.copyOf(additional, additional.length + 1);
          copyOf[additional.length] = i;
          queue.add(copyOf);
      }
    }
  }
}
