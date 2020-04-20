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
 * https://leetcode.com/problems/generate-parentheses/
 * @author Zoltan Farkas
 */
public class GenerateParantheses {

    /**
     * parans = (left)right
     *
     * @param n
     * @return
     */
    public static List<String> generateParenthesis(int n) {
        if (n == 0) {
          return Collections.singletonList("");
        }
        if (n == 1) {
          return Collections.singletonList("()");
        }
        List<String> result = new ArrayList<>(n * 2);
        List<String> sp = generateParenthesis(n - 1);
        for (String s : sp) {
          result.add("()" + s);
          result.add('(' + s + ')');
        }
        for (int i = 1, l = n - 1; i < l; i++) {
          for (String partL : generateParenthesis(i)) {
             for (String partR : generateParenthesis(n - i - 1)) {
               result.add('(' + partL + ')' + partR);
             }
          }
        }
        return result;
    }


}
