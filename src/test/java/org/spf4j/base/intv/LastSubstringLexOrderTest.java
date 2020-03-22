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
public class LastSubstringLexOrderTest {

  @Test
  public void testLastSubString() {
    Assert.assertEquals("tcode", LastSubstringLexOrder.lastSubstring("leetcode"));
  }

  @Test
  public void testLastSubString2() {
    Assert.assertEquals("cb", LastSubstringLexOrder.lastSubstring("cacacb"));
  }

  @Test
  public void testLastSubString3() {
    StringBuilder testBuilder = new StringBuilder(400000);
    for (int i = 0; i < 400000; i++) {
      testBuilder.append('a');
    }
    String testStr = testBuilder.toString();
    Assert.assertEquals(testStr, LastSubstringLexOrder.lastSubstring(testStr));
  }




}
