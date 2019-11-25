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

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Zoltan Farkas
 */
public class Mws {

  private static class Range {

    public Range(int start, int end) {
      this.start = start;
      this.end = end;
    }

    private int start;
    private int end;

    public boolean smallerThan(Range other) {
      if (other == null) {
        return true;
      }
      return end - start < other.end - other.start;
    }
  }

  private static class WindowState {
    private int nrFound;
    private int expected;

    public WindowState() {
      this.expected = 1;
      this.nrFound = 0;
    }

    public void incExpected() {
      this.expected++;
    }

    public boolean incFound() {
      this.nrFound++;
      return this.nrFound == this.expected;
    }

    public boolean decFound() {
      boolean result = this.nrFound == this.expected;
      this.nrFound--;
      return result;
    }

  }

  public static String minWindow(String s, String t) {
    int l = t.length();
    if (l == 0) {
      return "";
    }
    int sl = s.length();
    if (sl == 0) {
      return "";
    }
    Map<Character, WindowState> wMap = new HashMap<>((int) (1.33 * l) + 1);
    for (int i = 0; i < l; i++) {
      Character c = t.charAt(i);
      WindowState ci = wMap.get(c);
      if (ci == null) {
        wMap.put(c, new WindowState());
      } else {
        ci.incExpected();
      }
    }
    int eLength = wMap.size();

    Range minRange = null;

    int ws = 0;
    int we = 0;
    int length = 0;
    do {
      if (length < eLength) {
        if (we >= sl) {
          break;
        }
        char c = s.charAt(we);
        we++;
        WindowState wc = wMap.get(c);
        if (wc != null) {
          if (wc.incFound()) {
            length++;
          }
        }
      } else {
        char c = s.charAt(ws);
        WindowState wc = wMap.get(c);
        if (wc != null) {
          if (wc.decFound()) {
            Range nr = new Range(ws, we);
            if (nr.smallerThan(minRange)) {
              minRange = nr;
            }
            length--;
          }
        }
        ws++;
      }
    } while (true);
    if (minRange == null) {
      return "";
    } else {
      return s.substring(minRange.start, minRange.end);
    }
  }


}
