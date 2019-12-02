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

import java.util.List;
import org.junit.Test;

/**
 *
 * @author Zoltan Farkas
 */
public class FindAllAnagramsTest {

  @Test
  public void testSomeMethod() {
    List<Integer> findAnagrams = FindAllAnagrams.findAnagrams("cbaebabacd", "abc");
    System.out.println(findAnagrams);
  }

  @Test
  public void testSomeMethod2() {
    List<Integer> findAnagrams = FindAllAnagrams.findAnagrams("aaa", "a");
    System.out.println(findAnagrams);
  }


}
