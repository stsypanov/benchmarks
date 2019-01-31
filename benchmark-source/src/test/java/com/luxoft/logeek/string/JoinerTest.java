package com.luxoft.logeek.string;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class JoinerTest {

  private String[] stringArray;

  private boolean latin;
  private int arrayLength;

  public JoinerTest(boolean latin, int arrayLength) {
    this.latin = latin;
    this.arrayLength = arrayLength;
  }

  @Parameterized.Parameters
  public static Collection<Object[]> data() {
    return Arrays.asList(
            new Object[][]{
                    {true, 10}, {true, 100}, {true, 1000},
                    {false, 10}, {false, 100}, {false, 1000},
            }
    );
  }

  @Before
  public void setUp() {
    String[] latinStrArray = new String[arrayLength];
    String[] nonLatinStrArray = new String[arrayLength];

    for (int i = 0; i < arrayLength; i++) {
      String latinStr = randomString("abcdefghijklmnopqrstuvwxyz", arrayLength);
      String nonLatinStr = randomString("абвгдеёжзиклмнопрстуфхцчшщьыъэюя", arrayLength);

      latinStrArray[i] = latinStr;
      nonLatinStrArray[i] = nonLatinStr;
    }

    stringArray = latin ? latinStrArray : nonLatinStrArray;
  }

  @Test
  public void join() {
    String str2 = Joiner.joinWithStringJoiner(stringArray);
    String str1 = Joiner.joinWithStringBuilder(stringArray);

    assertEquals(str1, str2);
  }

  private String randomString(String alphabet, int length) {
    char[] chars = alphabet.toCharArray();

    ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();

    StringBuilder sb = new StringBuilder(length);
    for (int i = 0; i < length; i++) {
      char c = chars[threadLocalRandom.nextInt(chars.length)];
      sb.append(c);
    }

    return sb.toString();
  }
}