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

import java.util.Arrays;

/**
 * https://leetcode.com/problems/next-permutation/
 * @author Zoltan Farkas
 */
public class NextPermutation {
    public void nextPermutation(int[] nums) {
        int m1 = -1;
        int m2 = -1;
        for (int i = nums.length - 1; i > 0; i--) {
            for (int j = i - 1; j >=0; j--) {
                if (nums[i] > nums[j]) {
                    if (j > m1) {
                        m1 = j;
                        m2 = i;
                    }
                }
            }
        }
        if (m1 >= 0)   {
            int tmp = nums[m1];
            nums[m1] = nums[m2];
            nums[m2] = tmp;
            Arrays.sort(nums, m1 + 1, nums.length);
        } else {
            Arrays.sort(nums);
        }
    }
}
