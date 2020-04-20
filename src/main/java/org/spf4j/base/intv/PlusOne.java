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

import java.util.ArrayDeque;
import java.util.Collection;

/**
 * https://leetcode.com/problems/plus-one/
 * @author Zoltan Farkas
 */
public class PlusOne {

    static int[] toArray(Collection<Integer> col) {
      int[] result = new int[col.size()];
      int i = 0;
      for (int val : col) {
        result[i++] = val;
      }
      return result;
    }

    public static int[] plusOne(int[] digits) {
        int carry = 1;
        int i = digits.length - 1;
        ArrayDeque<Integer> result = new ArrayDeque<>(digits.length + 1);
        while (i >= 0) {
          int digit = digits[i];
          digit += carry;
          if (digit <= 9) {
            carry = 0;
            result.addFirst(digit);
          } else {
            result.addFirst(digit - 10);
          }
          i--;
        }
        if (carry > 0) {
          result.addFirst(1);
        }
        return toArray(result);
    }
}
