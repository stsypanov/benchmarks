package com.luxoft.logeek;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class ArithmeticTest {

  private int shift;

  public ArithmeticTest(int shift) {
    this.shift = shift;
  }

  @Parameterized.Parameters
  public static Collection<Object[]> data() {
    return Arrays.asList(new Object[][]
                    {
                            {0},
                            {1},
                            {2},
                            {3},
                            {4},
                            {5},
                            {6},
                            {7},
                            {8},
                            {9},
                            {10},
                            {11},
                            {12},
                            {13},
                            {14},
                            {15},
                            {16},
                    }
            );
  }

  @Test
  public void test() {
    final int initialCapacity = Integer.MAX_VALUE - 65536;
    final int size = 1 << shift;
    assertEquals(slow(initialCapacity, size), fast(initialCapacity, size));
  }

  int slow(int initialCapacity, int size) {
    return (int) ((initialCapacity + size - 1L) / size);
  }

  int fast(int initialCapacity, int size) {
    return (initialCapacity + size - 1) / size;
  }
}
