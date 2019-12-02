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

import java.util.BitSet;

/**
 * https://leetcode.com/problems/first-missing-positive/
 * @author Zoltan Farkas
 */
public class SmallersMissing {

  public static int firstMissingPositive(int[] nums) {
       int l = nums.length;
        if (l == 0) {
            return 1;
        }
        BitSet seen = new BitSet();
        for (int i = 0; i < l; i++) {
            int nr = nums[i];
            if (nr > 0 && nr <= l) {
              seen.set(nr);
            }
        }
        int j = seen.length();
        if (j == 0) {
          return 1;
        }
        for (int i = 1; i < j; i++) {
          if (!seen.get(i)) {
            return i;
          }
        }
        return j;
    }

}
