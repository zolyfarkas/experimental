/*
 * Copyright 2025 SPF4J.
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
 * leet code 408 Valid Word Abbreviation.
 */
public class SimplePattern {
  
  private static class Matcher {
    private boolean isCharMatch;
    private int characterOrCount;
    Matcher (final boolean isCharMatch, final int characterOrCount) {
      this.isCharMatch = isCharMatch;
      this.characterOrCount = characterOrCount;
    }
  }
  private List<Matcher> matchers;
  
  private SimplePattern(final List<Matcher> matchers) {
    this.matchers = matchers;
  }
  
  public static SimplePattern compile(final String pattern) {
    int offset = 0, strLen = pattern.length();
    if (strLen == 0) {
      return new SimplePattern(Collections.EMPTY_LIST);
    }
    StringBuilder currentNumber = new StringBuilder();
    List<Matcher> matchers = new ArrayList<>();
    boolean isEscape = false;
    while (offset < strLen) {
      int curChar = pattern.codePointAt(offset);
      if (!isEscape && curChar == '\\') {
          isEscape = true;
      } else if (!isEscape && Character.isDigit(curChar)) {
        currentNumber.appendCodePoint(curChar);
      } else {
        if (currentNumber.length() != 0) {
          int count = Integer.parseInt(currentNumber.toString());
          matchers.add(new Matcher(false, count));
          currentNumber.setLength(0);
        }
        matchers.add(new Matcher(true, curChar));
        isEscape = false;
      }
      offset += Character.charCount(curChar);
    }
    if (currentNumber.length() != 0) {
      int count = Integer.parseInt(currentNumber.toString());
      matchers.add(new Matcher(false, count));
    }
    
    return new SimplePattern(matchers);
  }
  
  
  public boolean match(final String str) {
    if (this.matchers.isEmpty()) {
      return str.isEmpty();
    }
    int at = 0;
    for (int k = 0, l = matchers.size(); k < l ; k++) {
      Matcher matcher = matchers.get(k);
      int curChar = str.codePointAt(at);
      if (matcher.isCharMatch) {
        if (matcher.characterOrCount != curChar) {
          return false;
        }
      } else {
        for (int i = 0; i < matcher.characterOrCount - 1; i++) {
          at += Character.charCount(curChar);
          if (at >= str.length()) {
            return false;
          }
          curChar = str.codePointAt(at);
        }
      }

      at += Character.charCount(curChar);
      if (at >= str.length()) {
        return k == (l -1);
      }   
    }
    return at == str.length();
  }
}
