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
public class SerDeserTest {

  @Test
  public void testSD1() {
    SerDeser.TreeNode node = null;
    String serialize = SerDeser.serialize(node);
    SerDeser.TreeNode deserialize = SerDeser.deserialize(serialize);
    Assert.assertEquals(node, deserialize);
  }

  @Test
  public void testSD2() {
    SerDeser.TreeNode node = new SerDeser.TreeNode(6);
    String serialize = SerDeser.serialize(node);
    System.out.println(serialize);
    SerDeser.TreeNode deserialize = SerDeser.deserialize(serialize);
    Assert.assertEquals(node, deserialize);
  }

  @Test
  public void testSD3() {
    SerDeser.TreeNode node = new SerDeser.TreeNode(6);
    node.left = new SerDeser.TreeNode(9);
    node.left.right = new SerDeser.TreeNode(10);
    String serialize = SerDeser.serialize(node);
    System.out.println(serialize);
    SerDeser.TreeNode deserialize = SerDeser.deserialize(serialize);
    Assert.assertEquals(node, deserialize);
  }

}
