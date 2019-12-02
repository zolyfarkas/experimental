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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * https://leetcode.com/problems/expression-add-operators/
 * @author Zoltan Farkas
 */
public class ExpressionAddOperators {

  private static final int MAX_DIGITS = Integer.toUnsignedString(Integer.MAX_VALUE).length();

  public static Integer tryParse(String nr) {
    if (nr.length() > MAX_DIGITS) {
      return null;
    }
    try {
      return Integer.valueOf(nr);
    } catch (NumberFormatException ex) {
      return null;
    }
  }

   public static boolean hasExtraZero(String s) {
      return s.length() > 1 && s.charAt(0) == '0';
   }

   public static List<String> addOperators(String num, int target) {
      if (num.isEmpty()) {
        return target == 0 ?  Arrays.asList("") : Collections.EMPTY_LIST;
      }

      List<String> result = new ArrayList<>(4);
      Integer n = tryParse(num);
      if (n != null && !hasExtraZero(num) && n == target) {
        result.add(num);
      }
      int nl = num.length();
      for (int i = nl - 1; i > 0; i--) {
         String snr = num.substring(i, nl);
         if (hasExtraZero(snr)) {
             continue;
         }
         Integer nr = tryParse(snr);
         if (nr == null) {
           break;
         }
         String restStr = num.substring(0, i);
         List<String> rest = addOperators(restStr, target - nr); // +
         addAll(snr, '+', rest, result);
         rest = addOperators(restStr, nr + target); // -
         addAll(snr, '-', rest, result);
        rest = addOperatorsMultiply(restStr, target, nr); // /
        addAll(snr, '*', rest, result);
      }
      return result;
    }


    public static List<String> addOperatorsMultiply(String num, int target, int multiply) {
      if (num.isEmpty()) {
        return target == 0 ?  Arrays.asList("") : Collections.EMPTY_LIST;
      }

      List<String> result = new ArrayList<>(4);
      Integer n = tryParse(num);
      if (n != null && !hasExtraZero(num) && n * multiply == target) {
        result.add(num);
      }
      int nl = num.length();
      for (int i = nl - 1; i > 0; i--) {
         String snr = num.substring(i, nl);
         if (hasExtraZero(snr)) {
             continue;
         }
         Integer nr = tryParse(snr);
         if (nr == null) {
           break;
         }
         String restStr = num.substring(0, i);
         List<String> rest = addOperators(restStr, target - nr * multiply); // +
         addAll(snr, '+', rest, result);
         rest = addOperators(restStr, nr * multiply + target); // -
         addAll(snr, '-', rest, result);
        rest = addOperatorsMultiply(restStr, target, nr * multiply); // /
        addAll(snr, '*', rest, result);
      }
      return result;
    }

    public static void addAll(String nr, char op, List<String> rest, List<String> addTo) {
      for (String s : rest) {
        StringBuilder sb = new StringBuilder(nr.length() + s.length() + 1);
        addTo.add(sb.append(s).append(op).append(nr).toString());
      }
    }

}
