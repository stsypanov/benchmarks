package com.luxoft.logeek;

import org.junit.Test;

import javax.tools.*;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static org.junit.Assert.*;

public class ClassFullNameCheckerTest {

  @Test
  public void test() throws Exception {
    ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
    java.lang.reflect.Field ClassLoader_classes_field = ClassLoader.class.getDeclaredField("classes");
    ClassLoader_classes_field.setAccessible(true);
    Vector<Class> classes = (Vector<Class>) ClassLoader_classes_field.get(contextClassLoader);

    for (Class aClass : new ArrayList<>(classes)) {
      boolean r1 = ClassFullNameChecker.effectiveStartsWith(aClass, "com");
      boolean r2 = ClassFullNameChecker.ineffectiveStartsWith(aClass, "com");
      assertEquals(r1, r2);
    }
  }

}