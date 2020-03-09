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

/**
 * https://leetcode.com/problems/best-sightseeing-pair/
 *
 * @author Zoltan Farkas
 */
public class MaxScoreSightSeeing {

  public static int maxScoreSightseeingPair(int[] A) {
    if (A.length < 2) {
      throw new IllegalArgumentException("Invalid argument " + A);
    }
    int max = A[0] + A[1] - 1;
    int mpval = Math.max(A[0] - 2, A[1] - 1);
    for (int i = 2; i < A.length; i++) {
      int cur = A[i];
      int nMax = mpval + cur;
      if (nMax > max) {
        max = nMax;
      }
      mpval = Math.max(mpval - 1, cur - 1);
    }
    return max;
  }

}
