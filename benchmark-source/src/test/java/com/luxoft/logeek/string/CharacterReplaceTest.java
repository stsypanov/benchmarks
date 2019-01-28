package com.luxoft.logeek.string;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CharacterReplaceTest {

  @Test
  public void test() {
    Class<String> stringClass = String.class;

    String foo = CharacterReplacer.ineffective(stringClass);
    String bar = CharacterReplacer.effective(stringClass);

    assertEquals(foo, bar);
  }

}
