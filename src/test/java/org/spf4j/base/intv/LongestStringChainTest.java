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
public class LongestStringChainTest {


  @Test
  public void testIsChain() {
    Assert.assertTrue(LongestStringChain.isChain("a", ""));
    Assert.assertFalse(LongestStringChain.isChain("", ""));
    Assert.assertFalse(LongestStringChain.isChain("a", "a"));
    Assert.assertFalse(LongestStringChain.isChain("", "ab"));
    Assert.assertFalse(LongestStringChain.isChain("a", "de"));
    Assert.assertFalse(LongestStringChain.isChain("de", "a"));
    Assert.assertTrue(LongestStringChain.isChain("ab", "a"));
  }

  @Test
  public void testGetMaxChain() {
    Assert.assertEquals(4,
            LongestStringChain.longestStrChain(new String[] {"a","b","ba","bca","bda","bdca"}));
  }

  @Test
  public void testGetMaxChain2() {
    Assert.assertEquals(9,
            LongestStringChain.longestStrChain(new String[] {"czgxmxrpx","lgh","bj","cheheex","jnzlxgh","nzlgh","ltxdoxc","bju","srxoatl","bbadhiju","cmpx","xi","ntxbzdr","cheheevx","bdju","sra","getqgxi","geqxi","hheex","ltxdc","nzlxgh","pjnzlxgh","e","bbadhju","cmxrpx","gh","pjnzlxghe","oqlt","x","sarxoatl","ee","bbadju","lxdc","geqgxi","oqltu","heex","oql","eex","bbdju","ntxubzdr","sroa","cxmxrpx","cmrpx","ltxdoc","g","cgxmxrpx","nlgh","sroat","sroatl","fcheheevx","gxi","gqxi","heheex"}));
  }


}
