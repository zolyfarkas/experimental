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

/**
 * https://leetcode.com/problems/valid-number/
 * @author Zoltan Farkas
 */
public class ValidNumber {

  private static final java.util.regex.Pattern NR_PATTERN =
          java.util.regex.Pattern.compile(" *[-+]?(([0-9]+(\\.[0-9]*)?)|(\\.[0-9]+))([eE][-+]?[0-9]+)? *");
  public static boolean isNumber(String s) {
    return NR_PATTERN.matcher(s).matches();
  }
}
