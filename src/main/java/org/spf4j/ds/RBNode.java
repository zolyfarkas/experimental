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
package org.spf4j.ds;

/**
 * @author Zoltan Farkas
 */
public class RBNode<T> {

  private T data;
  private RBNode left;
  private RBNode right;
  private RBNode parent;
  private RBColor color;

  /**
   * Newly-created nodes are colored red
   */
  public RBNode(T data) {
    this.data = data;
    color = RBColor.RED;
  }

  public T getData() {
    return data;
  }

  /**
   * Must copy all user-defined fields from the given node. For example, the base implementation copies the "data"
   * field. However, it does not (and must not) copy the link fields (parent, left child, right child). It also does not
   * need to copy any computed information for the node, as the node will be updated when necessary. Subclasses must be
   * careful to call the superclass implementation.
   */
  public void copyFrom(RBNode<T> arg) {
    this.data = arg.data;
  }

  /**
   * This is called by the base RBTree's insertion and deletion methods when necessary. Subclasses can use this to
   * update any computed information based on the information in their left or right children. For multi-node updates it
   * is guaranteed that this method will be called in the correct order. This should return true if an update actually
   * occurred, false if not.
   */
  public boolean update() {
    return false;
  }

  public RBColor getColor() {
    return color;
  }

  public void setColor(RBColor color) {
    this.color = color;
  }

  public RBNode<T> getParent() {
    return parent;
  }

  public void setParent(RBNode<T> parent) {
    this.parent = parent;
  }

  /**
   * Access to left child
   */
  public RBNode<T> getLeft() {
    return left;
  }

  public void setLeft(RBNode<T> left) {
    this.left = left;
  }

  /**
   * Access to right child
   */
  public RBNode<T> getRight() {
    return right;
  }

  public void setRight(RBNode<T> right) {
    this.right = right;
  }

  public enum RBColor {
    RED("red"),
    BLACK("black");

    private String name;

    private RBColor(final String name) {
      this.name = name;
    }

    public String getName() {
      return name;
    }
  }

}
