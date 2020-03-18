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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * https://leetcode.com/problems/spiral-matrix/
 * @author Zoltan Farkas
 */
public class SpiralMatrix {

 public List<Integer> spiralOrder(int[][] matrix) {
        int m = matrix.length;
        if (matrix.length == 0) {
            return Collections.EMPTY_LIST;
        }
        int n = matrix[0].length;
        List<Integer> result = new ArrayList<>(m * n);
        for (int i = 0, l = Math.min((m + 1) / 2, (n + 1) / 2); i < l; i++) {
            int n1  =  n - i - 1;
            for (int j = i; j <= n1; j++) {
                result.add(matrix[i][j]);
            }
            int m1 = m - i - 1;
            for (int j = i + 1; j <= m1; j++) {
                result.add(matrix[j][n1]);
            }
            if (m1 > i) {
                for (int j = n1 - 1; j >= i; j--) {
                    result.add(matrix[m1][j]);
                }
            }
            if (i < n1) {
                for (int j = m1 - 1; j > i; j--) {
                    result.add(matrix[j][i]);
                }
            }
        }
        return result;
    }
}
