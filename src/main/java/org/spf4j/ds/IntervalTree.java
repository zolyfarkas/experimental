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
 * Derived from the example in Section 15.3 of CLR.
 * https://en.wikipedia.org/wiki/Interval_tree
 */
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

public class IntervalTree<I, T> extends RBTree<T, IntervalNode<I, T>> {

  private final Comparator<I> endpointComparator;

  /**
   * This constructor takes only one comparator: one which operates upon the endpoints of the Intervals this tree will
   * store. It constructs an internal "interval comparator" out of this one.
   */
  public IntervalTree(Comparator<I> endpointComparator) {
    super(new IntervalComparator<>(endpointComparator));
    this.endpointComparator = endpointComparator;
  }

  public void insert(Interval<I> interval, T data) {
    IntervalNode<I, T> node = new IntervalNode<>(interval, endpointComparator, data);
    insertNode(node);
  }

  /**
   * Returns a List&lt;IntervalNode&gt; indicating which nodes' intervals were intersected by the given query interval.
   * It is guaranteed that these nodes will be returned sorted by increasing low endpoint.
   */
  public List<IntervalNode<I, T>> findAllNodesIntersecting(Interval<I> interval) {
    List<IntervalNode<I, T>> retList = new ArrayList<>();
    searchForIntersectingNodesFrom((IntervalNode) getRoot(), interval, retList::add);
    return retList;
  }

  public void print() {
    printOn(System.out);
  }

  public void printOn(PrintStream tty) {
    printFromNode(getRoot(), tty, 0);
  }


  protected void verify() {
    super.verify();
    verifyFromNode(getRoot());
  }

  //----------------------------------------------------------------------
  // Internals only below this point
  //
  private void verifyFromNode(RBNode node) {
    if (node == null) {
      return;
    }

    // We already know that the red/black structure has been verified.
    // What we need to find out is whether this node has been updated
    // properly -- i.e., whether its notion of the maximum endpoint is
    // correct.
    IntervalNode intNode = (IntervalNode) node;
    if (!intNode.getMaxEndpoint().equals(intNode.computeMaxEndpoint())) {
      throw new RuntimeException("Node's max endpoint was not updated properly");
    }
    if (!intNode.getMinEndpoint().equals(intNode.computeMinEndpoint())) {
      throw new RuntimeException("Node's min endpoint was not updated properly");
    }

    verifyFromNode(node.getLeft());
    verifyFromNode(node.getRight());
  }

  static class IntervalComparator<I, T> implements Comparator<IntervalNode<I, T>> {

    private Comparator<I> endpointComparator;

    public IntervalComparator(Comparator<I> endpointComparator) {
      this.endpointComparator = endpointComparator;
    }

    public int compare(IntervalNode<I, T> o1, IntervalNode<I, T> o2) {
      return endpointComparator.compare(o1.getInterval()
              .getLowEndpoint(), o2.getInterval().getLowEndpoint());
    }
  }

  private void searchForIntersectingNodesFrom(IntervalNode<I, T> node,
          Interval<I> interval,
          Consumer<IntervalNode<I, T>> resultList) {
    // Inorder traversal (very important to guarantee sorted order)
    // Check to see whether we have to traverse the left subtree
    IntervalNode<I, T> left = (IntervalNode) node.getLeft();
    if ((left != null)
            && (endpointComparator.compare(left.getMaxEndpoint(),
                    interval.getLowEndpoint()) > 0)) {
      searchForIntersectingNodesFrom(left, interval, resultList);
    }

    // Check for intersection with current node
    if (node.getInterval().overlaps(interval, endpointComparator)) {
      resultList.accept(node);
    }

    // Check to see whether we have to traverse the left subtree
    IntervalNode<I, T> right = (IntervalNode) node.getRight();
    if ((right != null)
            && (endpointComparator.compare(interval.getHighEndpoint(),
                    right.getMinEndpoint()) > 0)) {
      searchForIntersectingNodesFrom(right, interval, resultList);
    }
  }

  /**
   * Debugging
   */
  private void printFromNode(RBNode node, PrintStream tty, int indentDepth) {
    for (int i = 0; i < indentDepth; i++) {
      tty.print(" ");
    }

    tty.print("-");
    if (node == null) {
      tty.println();
      return;
    }

    tty.println(" " + node
            + " (min " + ((IntervalNode) node).getMinEndpoint()
            + ", max " + ((IntervalNode) node).getMaxEndpoint() + ")"
            + ((node.getColor() == RBNode.RBColor.RED) ? " (red)" : " (black)"));
    if (node.getLeft() != null) {
      printFromNode(node.getLeft(), tty, indentDepth + 2);
    }
    if (node.getRight() != null) {
      printFromNode(node.getRight(), tty, indentDepth + 2);
    }
  }



}
