package com.luxoft.logeek.string;

import com.luxoft.logeek.utils.RandomStringGenerator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

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
    RandomStringGenerator generator = new RandomStringGenerator();

    for (int i = 0; i < arrayLength; i++) {
      String latinStr = generator.randomString("abcdefghijklmnopqrstuvwxyz", arrayLength);
      String nonLatinStr = generator.randomString("абвгдеёжзиклмнопрстуфхцчшщьыъэюя", arrayLength);

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

}