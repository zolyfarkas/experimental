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

import java.math.BigInteger;

/**
 * @author Zoltan Farkas
 */
public class LastSubstringLexOrderSlow2 {
   public static String lastSubstring(String s) {
      if (s.isEmpty()) {
        return "";
      }
      int base = 'a' - 1;
      int maxIdx = s.length() - 1;
      BigInteger maxVal = BigInteger.valueOf(s.charAt(maxIdx) - base);
      BigInteger prevVal = maxVal;
      for (int i = s.length() -2, shift = 5; i >= 0; i--, shift += 5) {
        BigInteger curVal = BigInteger.valueOf(s.charAt(i) - base).shiftLeft(shift).add(prevVal);
        maxVal = maxVal.shiftLeft(5);
        if (curVal.compareTo(maxVal) > 0) {
          maxVal = curVal;
          maxIdx = i;
        }
        prevVal = curVal;
      }
      return s.substring(maxIdx);
    }
}
