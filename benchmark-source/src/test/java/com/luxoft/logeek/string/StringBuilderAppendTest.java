package com.luxoft.logeek.string;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StringBuilderAppendTest {
  private String str = "abcd";
  private String наРусском = "абвгдеё";
  private String expected = "abcdбвгд";

  @Test
  public void appendCharSequence() {
    String string = new StringBuilder().append(str).append(наРусском, 1, 5).toString();

    assertEquals(expected, string);
  }

  @Test
  @SuppressWarnings("StringBufferReplaceableByString")
  public void appendSubString() {
    String substring = наРусском.substring(1, 5);
    String string = new StringBuilder().append(str).append(substring).toString();

    assertEquals(expected, string);
  }
}
