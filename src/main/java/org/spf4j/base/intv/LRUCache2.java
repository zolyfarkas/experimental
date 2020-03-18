package org.spf4j.base.intv;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/lru-cache/
 * @author Zoltan Farkas
 */
public class LRUCache2 {

  private final LinkedHashMap<Integer, Integer> map;

  public LRUCache2(int capacity) {
    if (capacity <= 0) {
      throw new IllegalArgumentException("Invalid capacity");
    }
    this.map = new LinkedHashMap<Integer, Integer>(capacity + capacity / 4 + 1, 0.75f, true) {
      @Override
      protected boolean removeEldestEntry(Map.Entry<Integer, Integer> arg0) {
        return size() > capacity;
      }
    };
  }

  public int get(int key) {
    Integer res = map.get(key);
    if (res == null) {
      return -1;
    }
    return res;
  }

  public void put(int key, int value) {
    map.put(key, value);
  }

}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
