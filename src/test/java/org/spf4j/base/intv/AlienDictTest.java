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

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Zoltan Farkas
 */
public class AlienDictTest {

  @Test
  public void test1() {
    String alienOrder = AlienDict.alienOrder(new String[]{
      "wrt",
      "wrf",
      "er",
      "ett",
      "rftt"}
    );
    System.out.println(alienOrder);
    Assert.assertEquals("wertf", alienOrder);
  }

  @Test
  public void test2() {
    String alienOrder = AlienDict.alienOrder(new String[]{
      "x",
      "y",
      "x"}
    );
    System.out.println(alienOrder);
    Assert.assertEquals("", alienOrder);
  }

  @Test
  public void test3() {
    String alienOrder = AlienDict.alienOrder(new String[]{
      "ac","ab","b"}
    );
    System.out.println(alienOrder);
    Assert.assertEquals("acb", alienOrder);
  }

  @Test
  public void test4() {
    String alienOrder = AlienDict.alienOrder(new String[]{
      "dvpzu","bq","lwp","akiljwjdu","vnkauhh","ogjgdsfk","tnkmxnj","uvwa","zfe","dvgghw","yeyruhev","xymbbvo","m","n"}
    );
    System.out.println(alienOrder);
    Assert.assertEquals("", alienOrder);
  }

}
