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

import java.io.IOException;
import java.io.UncheckedIOException;

/**
 *
 * @author Zoltan Farkas
 */
public class IntegerToEnglish {

  private static final String[] sd = {"Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine",
    "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen",
    "Twenty"};

  private static final String[] tens = {"", "Ten", "Twenty", "Thirty", "Forty",
    "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
  

 /**
   * https://leetcode.com/problems/integer-to-english-words/
   * @param num
   * @return
   */
  public static String numberToWords(int num) {
    StringBuilder sb = new StringBuilder(16);
    try {
      numberToWords(num, sb, false);
    } catch (IOException ex) {
      throw new UncheckedIOException(ex);
    }
    return sb.toString();
  }

  public static void numberToWords(int num, final Appendable sb, boolean cont) throws IOException {
    int n = num / 1000000000;
    if (n > 0) {
      numberToWords(n, sb, cont);
      sb.append(" Billion");
      cont = true;
    }
    num = num % 1000000000;
    n = num / 1000000;
    if (n > 0) {
      numberToWords(n, sb, cont);
      sb.append(" Million");
      cont = true;
    }
    num = num % 1000000;
    n = num / 1000;
    if (n > 0) {
      numberToWords(n, sb, cont);
      sb.append(" Thousand");
      cont = true;
    }
    num = num % 1000;
    n = num / 100;
    if (n > 0) {
      numberToWords(n, sb, cont);
      sb.append(" Hundred");
      cont = true;
    }
    num = num % 100;
    if (num > 0 && num < sd.length) {
      if (cont) {
        sb.append(' ');
      }
      sb.append(sd[num]);
      return;
    }
    n = num / 10;
    if (n > 0) {
      if (cont) {
        sb.append(' ');
      }
      sb.append(tens[n]);
      int nr = num % 10;
      if (nr > 0) {
        sb.append(' ');
        sb.append(sd[nr]);
      }
    } else {
      if (!cont) {
        sb.append(sd[num % 10]);
      }
    }
  }


}
