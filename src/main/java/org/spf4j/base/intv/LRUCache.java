package org.spf4j.base.intv;


public class LRUCache {

    private static class Node {

        private int key;

        private int value;

        private Node nextRecent;

        private Node prevRecent;

    }


    private final int capacity;

    private Node head;
    private Node tail;

    private Node[] table;

    private int size;

    public LRUCache(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Invalid capacity");
        }
        this.capacity = capacity;
        this.table = new Node[capacity + capacity / 2];
    }

    public int get(int key) {
        int hi = key % table.length;
        Node n = findNode(hi, key);
        if (n == null || n.key != key) {
          return -1;
        }
        moveToRecent(n);
        return n.value;
    }

  private void moveToRecent(Node n) {
    if (head != n) {
      if (n.nextRecent == null) {
        n.nextRecent = head;
        n.prevRecent.nextRecent = null;
        tail = n.prevRecent;
        n.prevRecent = null;
        head.prevRecent = n;
        head = n;
      } else {
        n.nextRecent.prevRecent = n.prevRecent;
        n.prevRecent.nextRecent = n.nextRecent;
        n.prevRecent = null;
        n.nextRecent = head;
        head.prevRecent = n;
        head = n;
      }
    }
  }

  private Node findNode(int hi, int key) {
    int i = hi;
    while (true) {
      Node n = this.table[i];
      if (n != null && n.key == key) {
        return n;
      }
      i++;
      if (i >= table.length) {
        i = 0;
      }
      if (i == hi) {
        return n;
      }
    }
  }

  private int findNodeIdx(int hi, int key) {
    int i = hi;
    while (true) {
      Node n = this.table[i];
      if (n != null && n.key == key) {
        return i;
      }
      i++;
      if (i >= table.length) {
        i = 0;
      }
      if (i == hi) {
        break;
      }
    }
    throw new IllegalStateException();
  }

  private int findFreeIdx(int hi, int key) {
    int i = hi;
    while (true) {
      Node n = this.table[i];
      if (n == null) {
        return i;
      }
      if (n.key == key) {
        return i;
      }
      i++;
      if (i >= table.length) {
        i = 0;
      }
      if (i == hi) {
        return i;
      }
    }
  }

    private void removeLeastRecentlyUsed() {
        Node n = tail;
        if (n.prevRecent != null) {
            n.prevRecent.nextRecent = null;
            tail = n.prevRecent;
        } else {
            head = null;
            tail = null;
        }
        int hi = n.key % table.length;
        int nidx = findNodeIdx(hi, n.key);
        table[nidx] = null;
        size--;
    }

    public void put(int key, int value) {
        int hi = key % table.length;
        Node n;
        while (true) {
            int fIdx = findFreeIdx(hi, key);
            n = this.table[fIdx];
            if (n == null) {
                if (size >= capacity) {
                  removeLeastRecentlyUsed();
                }
                n = new Node();
                n.key = key;
                n.value = value;
                this.table[fIdx] = n;
                size++;
                addToRecent(n);
                return;
            } else {
                if (n.key == key) {
                  n.value = value;
                  moveToRecent(n);
                  return;
                } else {
                   removeLeastRecentlyUsed();
                }
            }
       }
    }

  public void addToRecent(Node n) {
    if (tail == null) {
      head = n;
      tail = n;
    } else {
      head.prevRecent = n;
      n.nextRecent = head;
      head = n;
    }
  }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */