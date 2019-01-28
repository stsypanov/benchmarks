package com.luxoft.logeek.string;

public class CharacterReplacer {

  public static String ineffective(Class<?> currentClass) {
    StringBuilder stringBuilder = new StringBuilder();
    String name = currentClass.getName();
    int nameLength = name.length();
    for (int i = 0; i < nameLength; ++i) {
      char car = name.charAt(i);
      stringBuilder.append(car == '.' ? '/' : car);
    }
    return stringBuilder.toString();
  }

  public static String effective(Class<?> currentClass) {
    return currentClass.getName().replace('.', '/');
  }
}
