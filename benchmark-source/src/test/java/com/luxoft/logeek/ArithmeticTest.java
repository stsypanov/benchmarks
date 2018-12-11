package com.luxoft.logeek;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ArithmeticTest {
  @Test
  public void test() {
    assertEquals(slow(Integer.MAX_VALUE, Integer.MIN_VALUE), fast(Integer.MAX_VALUE, Integer.MIN_VALUE));
    assertEquals(slow(Integer.MAX_VALUE, Integer.MAX_VALUE), fast(Integer.MAX_VALUE, Integer.MAX_VALUE));

    assertEquals(slow(Integer.MIN_VALUE, Integer.MIN_VALUE), fast(Integer.MIN_VALUE, Integer.MIN_VALUE));
    assertEquals(slow(Integer.MIN_VALUE, Integer.MAX_VALUE), fast(Integer.MIN_VALUE, Integer.MAX_VALUE));

    assertEquals(slow(Integer.MIN_VALUE, 0), fast(Integer.MIN_VALUE, 0));
    assertEquals(slow(Integer.MAX_VALUE, 0), fast(Integer.MAX_VALUE, 0));
  }

  int slow(int size, int initialCapacity) {
    return (int) ((initialCapacity + size - 1L) / size);
  }

  int fast(int size, int initialCapacity) {
    return (initialCapacity + size - 1) / size;
  }
}
