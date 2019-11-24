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
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author Zoltan Farkas
 */
public class MwsSlow {
private static class Range {

    public Range(int start, int end) {
      this.start = start;
      this.end = end;
    }

    private int start;
    private int end;

    public boolean smallerThan(Range other) {
      return end - start < other.end - other.start;
    }
  }

  private static Range getCurrentRange(Map<Character, CharInfo> map) {
    int start = Integer.MAX_VALUE;
    int end = -1;
    Deque<Integer> sDq = null;
    for (CharInfo w : map.values()) {
      Iterator<Integer> it = w.occurrences.iterator();
      for (int i = 0; i < w.expectedOccurrences; i++) {
        if (!it.hasNext()) {
          return null;
        }
        Integer loc = it.next();
        if (loc < start) {
          start = loc;
          sDq = w.occurrences;
        }
        if (loc > end) {
          end = loc;
        }
      }
    }
    sDq.removeFirst();
    return new Range(start, end);
  }

  private static class CharInfo {

    public CharInfo() {
      this.expectedOccurrences = 1;
      this.occurrences = new ArrayDeque<Integer>(4);
    }

    public void incExpected() {
      this.expectedOccurrences++;
    }

    public void addOccurrence(int pos) {
      occurrences.addLast(pos);
    }

    private int expectedOccurrences;
    private ArrayDeque<Integer> occurrences;
  }

  public static String minWindow(String s, String t) {
    int l = t.length();
    Map<Character, CharInfo> map = new HashMap<>((int) (1.33 * l) + 1);
    for (int i = 0; i < l; i++) {
      Character c = t.charAt(i);
      CharInfo ci = map.get(c);
      if (ci == null) {
        ci = new CharInfo();
        map.put(c, ci);
      } else {
        ci.incExpected();
      }
    }
    for (int i = 0, sl = s.length(); i < sl; i++) {
      char c = s.charAt(i);
      CharInfo locations = map.get(c);
      if (locations != null) {
        locations.addOccurrence(i);
      }
    }

    Range minRange = null;
    Range r;
    while ((r = getCurrentRange(map)) != null) {
      if (minRange == null || r.smallerThan(minRange)) {
        minRange = r;
      }
    }
    if (minRange == null) {
      return "";
    } else {
      return s.substring(minRange.start, minRange.end + 1);
    }

  }
}
