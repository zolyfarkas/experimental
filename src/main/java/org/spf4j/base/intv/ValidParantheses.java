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

import java.util.ArrayDeque;

/**
 * https://leetcode.com/problems/valid-parentheses/
 * @author Zoltan Farkas
 */
public class ValidParantheses {
    public static boolean isValid(String s) {
      ArrayDeque<Character> parstack = new ArrayDeque<>(4);
      for (int i = 0, l = s.length(); i < l; i++) {
        char c = s.charAt(i);
        switch (c) {
          case '{':
          case '[':
          case '(':
            parstack.addLast(c);
            break;
          case '}':
            Character lp = parstack.pollLast();
            if (lp == null || lp != '{') {
              return false;
            }
            break;
          case ']':
            lp = parstack.pollLast();
            if (lp == null || lp != '[') {
              return false;
            }
            break;
          case ')':
            lp = parstack.pollLast();
            if (lp == null || lp != '(') {
              return false;
            }
            break;
        }
      }
      return parstack.isEmpty();
    }
}
