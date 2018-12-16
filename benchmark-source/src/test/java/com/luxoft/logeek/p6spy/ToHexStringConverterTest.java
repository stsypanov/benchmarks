package com.luxoft.logeek.p6spy;

import org.junit.Before;
import org.junit.Test;
import org.junit.runners.Parameterized;

import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.*;

public class ToHexStringConverterTest {
  @Parameterized.Parameters
  public static Object[][] data() {
    return new Object[100000][0];
  }

  private String expected;
  private byte[] bytes;
  private ToHexStringConverter converter;

  @Before
  public void setUp() {
    converter = new ToHexStringConverter();
    bytes = freshBytes();
    expected = converter.toHexString(bytes);
  }

  @Test
  public void patched_toHexString() {
    String actual = converter.patched_toHexString(bytes);
    assertEquals(expected, actual);
  }

  @Test
  public void chars_toHexString() {
    String actual = converter.chars_toHexString(bytes);
    assertEquals(expected, actual);
  }

  private byte[] freshBytes() {
    ThreadLocalRandom random = ThreadLocalRandom.current();
    int length = random.nextInt(100500);

    byte[] bytes = new byte[length];
    random.nextBytes(bytes);
    return bytes;
  }
}