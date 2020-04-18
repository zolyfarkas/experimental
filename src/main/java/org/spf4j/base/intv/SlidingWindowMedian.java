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
 * https://leetcode.com/problems/sliding-window-median/submissions/
 *
 * this one uses the MedianFinder based on 2 priority queues.
 * however sorting the window and remove and inserting into it would work as well.
 * @author Zoltan Farkas
 */
public class SlidingWindowMedian {

  // based on https://leetcode.com/problems/find-median-from-data-stream/
  public static  class MedianFinder {

    private final PriorityQueue<Integer> lh;
    private final PriorityQueue<Integer> hh;

    /** initialize your data structure here. */
    public MedianFinder(int capacity) {
      int hc = capacity / 2 +1;
      lh = new PriorityQueue<>(hc, (x, y) -> -Integer.compare(x, y));
      hh = new PriorityQueue<>(hc, (x, y) -> Integer.compare(x, y));
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

    public void removeNum(int num) {
       Integer lm = lh.peek();
       if (lm == null) {
         if (!hh.remove(num)) {
           throw new IllegalStateException();
         }
         return;
       }
       if (num > lm) {
         if (!hh.remove(num)) {
           throw new IllegalStateException();
         }
         if (lh.size() - hh.size() > 1) {
           hh.add(lh.poll());
         }
       } else {
         if (!lh.remove(num)) {
           throw new IllegalStateException();
         }
         if (hh.size() - lh.size() >= 1) {
           lh.add(hh.poll());
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
        return  ((double) lh.peek() + (double) hh.peek()) / 2.0;
      } else if (ls > hs) {
        return lh.peek();
      } else {
        return hh.peek();
      }
    }
}

    public static double[] medianSlidingWindow(int[] nums, int k) {
      if (nums.length == 0 || k == 0) {
        return new double[] {};
      }
      MedianFinder mf = new MedianFinder(k);
      for (int i = 0; i < k; i++) {
        mf.addNum(nums[i]);
      }
      double[] result = new double[nums.length - k + 1];
      result[0] = mf.findMedian();
      for (int i = 1, j = 0; i < result.length; i++, j++) {
        mf.removeNum(nums[j]);
        mf.addNum(nums[j + k]);
        result[i] = mf.findMedian();
      }
      return result;
    }
}
