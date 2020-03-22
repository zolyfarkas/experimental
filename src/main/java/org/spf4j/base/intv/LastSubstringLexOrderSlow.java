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

import java.nio.charset.StandardCharsets;

/**
 *
 * @author Zoltan Farkas
 */
public class LastSubstringLexOrderSlow {

   public static String lastSubstring(String s) {
      if (s.isEmpty()) {
        return "";
      }
      byte[] str = s.getBytes(StandardCharsets.US_ASCII);
      int maxIdx = 0;
      for (int i = 1, l = str.length; i < l; i++) {
        if (compareSpecial(l, str, maxIdx, i) < 0) {
          maxIdx = i;
        }
      }
      return s.substring(maxIdx);
    }

    public static int compareSpecial(int l,  byte[] str, int idx1, int idx2) {
      int result = 0;
      do {
        byte c1 = str[idx1];
        byte c2 = str[idx2];
        result =  c1 - c2;
        if (result != 0) {
          return result;
        }
        idx1++;
        idx2++;
        if (idx2 >= l) {
          return 1;
        }
      } while (true);
    }
}
