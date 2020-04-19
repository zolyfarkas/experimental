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

import java.util.NoSuchElementException;
import java.util.PriorityQueue;

/**
 *
 * @author Zoltan Farkas
 */
public class KLargestElement {

  static final class TopNumbers {

    private PriorityQueue<Integer> topNrs;

    public TopNumbers(int nr) {
      this.topNrs = new PriorityQueue<>(nr);
    }

    public void add(int nr) {
      topNrs.add(nr);
    }

    public void accumulate(int nr) {
      Integer peek = topNrs.peek();
      if (nr > peek) {
        topNrs.remove();
        topNrs.add(nr);
      }
    }

    public int getMin() {
      return topNrs.peek();
    }

  }

  public static int findKthLargest(int[] nums, int k) {
    if (nums.length == 0) {
      throw new NoSuchElementException();
    }
    if (k > nums.length) {
      throw new IllegalArgumentException("Invalid input " + k + " > " + nums.length);
    }
    TopNumbers tnrs = new TopNumbers(k);
    for (int i = 0; i < k; i++) {
      tnrs.add(nums[i]);
    }
    for (int i = k; i < nums.length; i++) {
      tnrs.accumulate(nums[i]);
    }
    return tnrs.getMin();
  }

}
