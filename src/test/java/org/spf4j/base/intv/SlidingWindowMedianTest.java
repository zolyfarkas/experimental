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

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Zoltan Farkas
 */
public class SlidingWindowMedianTest {



  @Test
  public void test1() {
    double[] results = SlidingWindowMedian.medianSlidingWindow(new int [] {1,3,-1,-3,5,3,6,7}, 3);
    Assert.assertArrayEquals(new double[] {1,-1,-1,3,5,6}, results, 0.0000001);
  }

  @Test
  public void test2() {
    double[] results = SlidingWindowMedian.medianSlidingWindow(new int [] {2147483647,2147483647}, 2);
    Assert.assertArrayEquals(new double[] {2147483647}, results, 0.0000001);
  }

  @Test
  public void test3() {
    double[] results = SlidingWindowMedian.medianSlidingWindow(new int [] {2147483647,1,2,3,4,5,6,7,2147483647}, 2);
  }



}
