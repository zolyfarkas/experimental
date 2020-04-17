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
 * https://leetcode.com/problems/valid-parenthesis-string/
 * @author Zoltan Farkas
 */
public class ValidParanString {

  private static boolean isValidUnchanged(String s, int from, int to) {
    int nrOpen = 0;
    for (int i = from; i < to; i++) {
      char c = s.charAt(i);
          switch (c) {
        case '{':
          nrOpen++;
          break;
        case '}':
          nrOpen--;
          if (nrOpen < 0) {
            return false;
          }
          break;
        case '*':
          break;
        default:
          throw new IllegalStateException("Invalid String " + s);
      }
    }
    return nrOpen == 0;
  }


 public static  boolean checkValidString(String s) {
   return checkValidString(s, 0, s.length());
 }

 public static  boolean checkValidString(String s, int from, int to) {
      int nrStars = 0;
      int nrOpen = 0;
      int idxLastClosed = -1;
      for (int i = from; i < to; i++) {
        char c = s.charAt(i);
            switch (c) {
          case '(':
            nrOpen++;
            break;
          case ')':
            nrOpen--;
            if (nrOpen < 0) { // trring to close more
              if (nrStars > 0) {
                nrStars--;
                nrOpen = 0;
              } else {
                return false;
              }
            }
            break;
          case '*':
            nrStars++;
            break;
          default:
            throw new IllegalStateException("Invalid String " + s);
        }
        if (nrOpen == 0) {
          idxLastClosed = i;
        }
      }
      if (nrOpen == 0) {
        return true;
      } else { //nrOpen  >  0 means  {*{*{* or {{{*** or {{{{ or {{} or {{{}** will go the other way arround:
        nrOpen = 0;
        nrStars = 0;
        for (int i = to - 1; i > idxLastClosed; i--) {
          char c = s.charAt(i);
              switch (c) {
            case '(':
              nrOpen--;
              if (nrOpen < 0) { // tring to close more
                if (nrStars > 0) {
                  nrStars--;
                  nrOpen = 0;
                } else {
                  return false;
                }
              }
              break;
            case ')':
              nrOpen++;
              break;
            case '*':
              nrStars++;
              break;
            default:
              throw new IllegalStateException("Invalid String " + s);
          }
        }
        return nrOpen == 0;
      }
    }
}
