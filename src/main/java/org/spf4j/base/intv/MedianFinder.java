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

import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/find-median-from-data-stream/
 * @author Zoltan Farkas
 */
public class MedianFinder {

    private final PriorityQueue<Integer> lh;
    private final PriorityQueue<Integer> hh;

    /** initialize your data structure here. */
    public MedianFinder() {
      lh = new PriorityQueue<>((x, y) -> -Integer.compare(x, y));
      hh = new PriorityQueue<>((x, y) -> Integer.compare(x, y));
    }

    public void addNum(int num) {
      Integer lm = lh.peek();
      if (lm == null) {
        lh.add(num);
      } else {
        if (num > lm) {
          if (lh.size() <= hh.size()) {
            Integer rm = hh.peek();
            if (rm > num) {
              lh.add(num);
            } else {
              hh.remove();
              hh.add(num);
              lh.add(rm);
            }
          } else {
            hh.add(num);
          }
        } else { // (num <= lm)
          if (lh.size() <= hh.size()) {
            lh.add(num);
          } else {
            lh.remove();
            lh.add(num);
            hh.add(lm);
          }
        }
      }
    }

    public double findMedian() {
      int ls = lh.size();
      int hs = hh.size();
      if (ls == 0 && hs == 0) {
        throw new IllegalStateException("No median for empty bag");
      }
      if (ls == hs) {
        return ((double) (lh.peek() + hh.peek())) / 2.0;
      } else if (ls > hs) {
        return lh.peek();
      } else {
        return hh.peek();
      }
    }
}
