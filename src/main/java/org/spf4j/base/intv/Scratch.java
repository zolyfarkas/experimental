package org.spf4j.base.intv;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Scratch {

  /**
   * https://leetcode.com/problems/merge-intervals/
   * @param intervals
   * @return
   */
  public int[][] merge(int[][] intervals) {
    if (intervals.length == 0) {
      return intervals;
    }
    Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
    List<int[]> result = new ArrayList<>(intervals.length);
    int[] intv = intervals[0];
    for (int i = 1; i < intervals.length; i++) {
      int[] other = intervals[i];
      int i1 = intv[1];
      if (i1 >= other[0]) {
        int o1 = other[1];
        if (o1 > i1) {
          intv[1] = o1;
        }
      } else {
        result.add(intv);
        intv = other;
      }
    }
    result.add(intv);
    return result.toArray(new int[result.size()][]);
  }

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
