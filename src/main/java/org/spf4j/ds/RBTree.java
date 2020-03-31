
package org.spf4j.ds;

/**
 * <
 * P>
 * This class implements a Red-Black tree as described in Cormen, Leiserson, Rivest, <I>Introduction to Algorithms</I>,
 * MIT Press: Cambridge, MA, 1990. </P>
 *
    <P>
 * A property of this implementation is that it is designed to allow straightforward augmentation of the data structure.
 * A valid augmentation is one in which a node contains auxiliary information that can be computed by examining only
 * this node and its left and right children (see CLR, section 15.2). </P>
 *
    <P>
 * An RBTree is a structure of RBNodes, each of which contains a user data element. When the user inserts a piece of
 * data into the tree, a new RBNode is constructed around it. </P>
 *
    <P>
 * An RBTree takes a Comparator as argument to its constructor which is used internally to order the nodes in the tree.
 * The comparator's arguments are obtained by calling the routine "getNodeData" on two nodes; the default implementaion
 * returns the node data. This Comparator is also used to perform the generic "find" operation, which returns the RBNode
 * containing user data precisely equalling the query data. Different types of user data will typically require
 * different types of traversals as well as additional comparison operations; these are left to the RBTree subclass.
 * </P>
 *
 */
import java.io.PrintStream;
import java.util.Comparator;
import org.spf4j.ds.RBNode.RBColor;

public class RBTree<T, N extends RBNode<T>> {

  private RBNode<T> root;
  private Comparator<RBNode<T>> comparator;

  public RBTree(final Comparator<N> comparator) {
    this.comparator = (Comparator<RBNode<T>>) comparator;
  }

  public N getRoot() {
    return (N) root;
  }

  public void insertNode(N px) {
    RBNode<T> x = px;
    treeInsert(x);

    x.setColor(RBColor.RED);
    boolean shouldPropagate = x.update();
    RBNode<T> propagateStart = x;

    // Loop invariant: x has been updated.
    while ((x != root)) {
      RBNode<T> xParent = x.getParent();
      if (xParent.getColor() != RBColor.RED) {
        break;
      }
      RBNode<T> xParentParent = xParent.getParent();
      if (xParent == xParentParent.getLeft()) {
        RBNode<T> y = xParentParent.getRight();
        if ((y != null) && (y.getColor() == RBColor.RED)) {
          xParent.setColor(RBColor.BLACK);
          y.setColor(RBColor.BLACK);
          xParentParent.setColor(RBColor.RED);
          xParent.update();
          x = xParentParent;
          shouldPropagate = x.update();
          propagateStart = x;
        } else {
          if (x == xParent.getRight()) {
            x = xParent;
            leftRotate(x);
            xParent = x.getParent();
            xParentParent = xParent.getParent();
          }
          xParent.setColor(RBColor.BLACK);
          xParentParent.setColor(RBColor.RED);
          shouldPropagate = rightRotate(xParentParent);
          propagateStart = xParent;
        }
      } else {
        // Same as then clause with "right" and "left" exchanged
        RBNode<T> y = xParentParent.getLeft();
        if ((y != null) && (y.getColor() == RBColor.RED)) {
          xParent.setColor(RBColor.BLACK);
          y.setColor(RBColor.BLACK);
          xParentParent.setColor(RBColor.RED);
          xParent.update();
          x = xParentParent;
          shouldPropagate = x.update();
          propagateStart = x;
        } else {
          if (x == xParent.getLeft()) {
            x = xParent;
            rightRotate(x);
            xParent = x.getParent();
            xParentParent = xParent.getParent();
          }
          xParent.setColor(RBColor.BLACK);
          xParentParent.setColor(RBColor.RED);
          shouldPropagate = leftRotate(xParentParent);
          propagateStart = xParent;
        }
      }
    }
    while (shouldPropagate && (propagateStart != root)) {
      propagateStart = propagateStart.getParent();
      shouldPropagate = propagateStart.update();
    }
    root.setColor(RBColor.BLACK);
  }

  /**
   * FIXME: this does not work properly yet for augmented
   * red-black trees since it doesn't update nodes. Need to figure
   * out exactly from which points we need to propagate updates upwards.
   */
  public void deleteNode(N z) {
    // This routine splices out a node. Note that we may not actually
    // delete the RBNode z from the tree, but may instead remove
    // another node from the tree, copying its contents into z.

    // y is the node to be unlinked from the tree
    final RBNode<T> y;
    if ((z.getLeft() == null) || (z.getRight() == null)) {
      y = z;
    } else {
      y = treeSuccessor(z);
    }
    // y is guaranteed to be non-null at this point
    RBNode<T> x;
    if (y.getLeft() != null) {
      x = y.getLeft();
    } else {
      x = y.getRight();
    }
    // x is the potential child of y to replace it in the tree.
    // x may be null at this point
    RBNode<T> xParent;
    if (x != null) {
      x.setParent(y.getParent());
      xParent = x.getParent();
    } else {
      xParent = y.getParent();
    }
    if (y.getParent() == null) {
      root = x;
    } else {
      if (y == y.getParent().getLeft()) {
        y.getParent().setLeft(x);
      } else {
        y.getParent().setRight(x);
      }
    }
    if (y != z) {
      z.copyFrom(y);
    }
    if (y.getColor() == RBColor.BLACK) {
      deleteFixup(x, xParent);
    }
  }


  public void printOn(PrintStream tty) {
    printFromNode(root, tty, 0);
  }

  /**
   * Verify invariants are preserved
   */
  protected void verify() {
    verifyFromNode(root);
  }

  //----------------------------------------------------------------------
  // Internals only below this point
  //
  //
  // Vanilla binary search tree operations
  //
  private void treeInsert(RBNode<T> z) {
    RBNode<T> y = null;
    RBNode<T> x = root;

    while (x != null) {
      y = x;
      if (comparator.compare(z, x) < 0) {
        x = x.getLeft();
      } else {
        x = x.getRight();
      }
    }
    z.setParent(y);
    if (y == null) {
      root = z;
    } else {
      if (comparator.compare(z, y) < 0) {
        y.setLeft(z);
      } else {
        y.setRight(z);
      }
    }
  }

  private RBNode treeSuccessor(RBNode x) {
    if (x.getRight() != null) {
      return treeMinimum(x.getRight());
    }
    RBNode y = x.getParent();
    while ((y != null) && (x == y.getRight())) {
      x = y;
      y = y.getParent();
    }
    return y;
  }

  private RBNode treeMinimum(RBNode x) {
    while (x.getLeft() != null) {
      x = x.getLeft();
    }
    return x;
  }

  //
  // Insertion and deletion helpers
  //
  /**
   * Returns true if updates must continue propagating up the tree
   */
  private boolean leftRotate(RBNode x) {
    // Set y.
    RBNode y = x.getRight();
    // Turn y's left subtree into x's right subtree.
    x.setRight(y.getLeft());
    if (y.getLeft() != null) {
      y.getLeft().setParent(x);
    }
    // Link x's parent to y.
    y.setParent(x.getParent());
    if (x.getParent() == null) {
      root = y;
    } else {
      if (x == x.getParent().getLeft()) {
        x.getParent().setLeft(y);
      } else {
        x.getParent().setRight(y);
      }
    }
    // Put x on y's left.
    y.setLeft(x);
    x.setParent(y);
    // Update nodes in appropriate order (lowest to highest)
    boolean res = x.update();
    res = y.update() || res;
    return res;
  }

  /**
   * Returns true if updates must continue propagating up the tree
   */
  private boolean rightRotate(RBNode<T> y) {
    // Set x.
    RBNode<T> x = y.getLeft();
    // Turn x's right subtree into y's left subtree.
    y.setLeft(x.getRight());
    if (x.getRight() != null) {
      x.getRight().setParent(y);
    }
    // Link y's parent into x.
    x.setParent(y.getParent());
    if (y.getParent() == null) {
      root = x;
    } else {
      if (y == y.getParent().getLeft()) {
        y.getParent().setLeft(x);
      } else {
        y.getParent().setRight(x);
      }
    }
    // Put y on x's right.
    x.setRight(y);
    y.setParent(x);
    // Update nodes in appropriate order (lowest to highest)
    boolean res = y.update();
    res = x.update() || res;
    return res;
  }

  /**
   * Restores red-black property to tree after splicing out node during deletion. Note that x may be null, which is why
   * xParent must be specified.
   */
  private void deleteFixup(RBNode<T> x, RBNode<T> xParent) {
    while ((x != root) && ((x == null) || (x.getColor() == RBColor.BLACK))) {
      if (x == xParent.getLeft()) {
        // NOTE: the text points out that w can not be null. The
        // reason is not obvious from simply examining the code; it
        // comes about because of properties of the red-black tree.
        RBNode w = xParent.getRight();
          if (w == null) {
            throw new IllegalStateException("x's sibling should not be null: " + xParent);
          }
        if (w.getColor() == RBColor.RED) {
          // Case 1
          w.setColor(RBColor.BLACK);
          xParent.setColor(RBColor.RED);
          leftRotate(xParent);
          w = xParent.getRight();
        }
        if (((w.getLeft() == null) || (w.getLeft().getColor() == RBColor.BLACK))
                && ((w.getRight() == null) || (w.getRight().getColor() == RBColor.BLACK))) {
          // Case 2
          w.setColor(RBColor.RED);
          x = xParent;
          xParent = x.getParent();
        } else {
          if ((w.getRight() == null) || (w.getRight().getColor() == RBColor.BLACK)) {
            // Case 3
            w.getLeft().setColor(RBColor.BLACK);
            w.setColor(RBColor.RED);
            rightRotate(w);
            w = xParent.getRight();
          }
          // Case 4
          w.setColor(xParent.getColor());
          xParent.setColor(RBColor.BLACK);
          if (w.getRight() != null) {
            w.getRight().setColor(RBColor.BLACK);
          }
          leftRotate(xParent);
          x = root;
          xParent = x.getParent();
        }
      } else {
        // Same as clause above with "right" and "left"
        // exchanged

        // NOTE: the text points out that w can not be null. The
        // reason is not obvious from simply examining the code; it
        // comes about because of properties of the red-black tree.
        RBNode w = xParent.getLeft();
        if (w == null) {
          throw new IllegalStateException("x's sibling should not be null: " + xParent);
        }
        if (w.getColor() == RBColor.RED) {
          // Case 1
          w.setColor(RBColor.BLACK);
          xParent.setColor(RBColor.RED);
          rightRotate(xParent);
          w = xParent.getLeft();
        }
        if (((w.getRight() == null) || (w.getRight().getColor() == RBColor.BLACK))
                && ((w.getLeft() == null) || (w.getLeft().getColor() == RBColor.BLACK))) {
          // Case 2
          w.setColor(RBColor.RED);
          x = xParent;
          xParent = x.getParent();
        } else {
          if ((w.getLeft() == null) || (w.getLeft().getColor() == RBColor.BLACK)) {
            // Case 3
            w.getRight().setColor(RBColor.BLACK);
            w.setColor(RBColor.RED);
            leftRotate(w);
            w = xParent.getLeft();
          }
          // Case 4
          w.setColor(xParent.getColor());
          xParent.setColor(RBColor.BLACK);
          if (w.getLeft() != null) {
            w.getLeft().setColor(RBColor.BLACK);
          }
          rightRotate(xParent);
          x = root;
          xParent = x.getParent();
        }
      }
    }
    if (x != null) {
      x.setColor(RBColor.BLACK);
    }
  }

  // Returns the number of black children along all paths to all
  // leaves of the given node
  private int verifyFromNode(RBNode node) {
    // Bottoms out at leaf nodes
    if (node == null) {
      return 1;
    }

    // Each node is either red or black
    if (!((node.getColor() == RBColor.RED)
            || (node.getColor() == RBColor.BLACK))) {
      throw new IllegalStateException("Verify failed (1): " + node);
    }

    // Every leaf (null) is black
    if (node.getColor() == RBColor.RED) {
      // Both its children are black
      if (node.getLeft() != null) {
        if (node.getLeft().getColor() != RBColor.BLACK) {
          throw new IllegalStateException("Verify failed (2):  " + node);
        }
      }
      if (node.getRight() != null) {
        if (node.getRight().getColor() != RBColor.BLACK) {
          throw new IllegalStateException("Verify failed (3): " + node);
        }
      }
    }

    // Every simple path to a leaf contains the same number of black
    // nodes
    int i = verifyFromNode(node.getLeft());
    int j = verifyFromNode(node.getRight());
    if (i != j) {
      throw new IllegalStateException("Verify failed (4) (left black count = "
              + i + ", right black count = " + j + ")");
    }

    return i + ((node.getColor() == RBColor.RED) ? 0 : 1);
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

    tty.println(" " + node.getData()
            + ((node.getColor() == RBColor.RED) ? " (red)" : " (black)"));
    printFromNode(node.getLeft(), tty, indentDepth + 2);
    printFromNode(node.getRight(), tty, indentDepth + 2);
  }


}
