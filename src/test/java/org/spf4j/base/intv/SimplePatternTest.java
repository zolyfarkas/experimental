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

import org.junit.Assert;
import org.junit.Test;


/**
 *
 * @author zoly
 */
public class SimplePatternTest {
  
  @Test
  public void testSimplePattern() {
    SimplePattern pattern = SimplePattern.compile("i18n");
    Assert.assertTrue(pattern.match("internationalization"));
    Assert.assertFalse(pattern.match("internationaization"));
    Assert.assertFalse(pattern.match("internatixxonaization"));
  }
  
  
  @Test
  public void testSimplePattern2() {
    SimplePattern pattern = SimplePattern.compile("");
    Assert.assertTrue(pattern.match(""));
    Assert.assertFalse(pattern.match("internationaization"));
  }
  
  
  @Test
  public void testSimplePattern3() {
    SimplePattern pattern = SimplePattern.compile("a3bcd");
    Assert.assertTrue(pattern.match("axxxbcd"));
    Assert.assertFalse(pattern.match("axxxb4cd"));
    Assert.assertFalse(pattern.match("axxbcd"));
    Assert.assertFalse(pattern.match("axxxbcdx"));
    Assert.assertFalse(pattern.match("xaxxxbcd"));
  }  
  
  @Test
  public void testSimplePattern4() {
    SimplePattern pattern = SimplePattern.compile("5");
    Assert.assertTrue(pattern.match("xxxxx"));
    Assert.assertFalse(pattern.match("x"));
    Assert.assertFalse(pattern.match("xxxxxxx"));
  }
  
  @Test
  public void testSimplePattern5() {
    SimplePattern pattern = SimplePattern.compile("\\55");
    Assert.assertTrue(pattern.match("5xxxxx"));
    Assert.assertFalse(pattern.match("x"));
    Assert.assertFalse(pattern.match("xxxxxxx"));
  }
  
  
}
