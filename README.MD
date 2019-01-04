# Список бенчмарков с краткими пояснениями | Benchmarks list with brief comments

Scroll down to read English version (not implemented yet)


##ArrayInstantiationBenchmark

Показывает разницу между созданием массива с помощью конструктора и вызовом платформенно-зависимого метода `Array::newInstance`:

```java
@Benchmark
public Object newInstance() {
  return Array.newInstance(Object.class, length);
}

@Benchmark
public Object constructor() {
  return new Object[length];
}
```

В коде, скомпилированном С2 между ними нет особой разницы, однако в режиме интерпретатора и С1 конструктор отрабатывает значительно быстрее.

Интересен больша разница во времени при создании небольшого массива и режиме С1:
```
             (length)  Mode  Cnt  Score  Error  Units
constructor        10  avgt   30    6,5 ±  0,1  ns/op
newInstance        10  avgt   30   68,3 ±  4,4  ns/op

constructor       100  avgt   30   34,8 ±  0,8  ns/op
newInstance       100  avgt   30   84,9 ±  4,1  ns/op
```

Платформенно-зависимый метод работает почти в 10 раз медленнее, чем конструктор, при создании массива из 10 объектов.
При создании же массива из 100 объектов разница между двумя способами составляет всего 2,4 раза.

Это поведение наблюдается только для С1.

См. итоги измерений в файле [./results/ArrayInstantiationBenchmark.txt](./results/ArrayInstantiationBenchmark.txt)

##ArithmeticSimplificationBenchmark

Показывает разницу между выражением с избыточным приведением и без него:

```java
@Benchmark
public int slow(Data data) {
  int size = data.size;
  int initialCapacity = data.initialCapacity;
  return (int) ((initialCapacity + size - 1L) / size);
}

@Benchmark
public int fast(Data data) {
  int size = data.size;
  int initialCapacity = data.initialCapacity;
  return (initialCapacity + size - 1) / size;
}
```

ВАЖНО! Способ, описанный в методе `fast` не всегда работает правильно, 
См. [ArithmeticTest](./benchmark-source/src/test/java/com/luxoft/logeek/ArithmeticTest.java)

Поэтому замена `1L` на `1` предпочтительна только для положительных чисел.

См. итоги измерений в файле [./results/ArithmeticSimplificationBenchmark.txt](./results/ArithmeticSimplificationBenchmark.txt)

##SubListToArrayBenchmark

Показывает проседание производительности при вызове `java.util.ArrayList$SubList::toArray`:

```java
@Benchmark
public Integer[] list(Data holder) {
  return holder.list.toArray(new Integer[0]);
}

@Benchmark
public Integer[] subList(Data holder) {
  return holder.list.subList(0, holder.size).toArray(new Integer[0]);
}
```

Несмотря на одинаковый объём передаваемых данных, производителньость второго метода значительно хуже первого.

См. обсуждение http://mail.openjdk.java.net/pipermail/core-libs-dev/2018-January/051102.html

Задача: https://bugs.openjdk.java.net/browse/JDK-8196207

Исправлено в JDK 11.

См. итоги измерений в файле [./results/SubListToArrayBenchmark.txt](./results/SubListToArrayBenchmark.txt)

##ZeroingEliminationBenchmark

Несколько странный пример, показывающий ненужность ручного обнуления содрежимого массива,
т.к. согласно спецификации его отдельные ячейки и так заполнены значениями по умолчанию.
Т.е. для целых чисел это 0.

См. итоги измерений в файле [./results/ZeroingEliminationBenchmark.txt](./results/ZeroingEliminationBenchmark.txt)

##BoxingBenchmark

Пример, показывающий значительное проседание производительности при использовании обёртки (`Long`) для
сложения чисел.

В боевом коде проблема была обнаружена в `org.springframework.data.jpa.repository.support.SimpleJpaRepository`.

См. https://github.com/spring-projects/spring-data-jpa/pull/269

Начиная с издания 2018.2 "Идея" умеет обнаруживать подобный код (см. https://youtrack.jetbrains.com/issue/IDEA-189336).

См. итоги измерений в файле [./results/BoxingBenchmark.txt](./results/BoxingBenchmark.txt)

##CollectionsAddAllVsAddAllBenchmark

Пример, показывающий неправдивость документации к методу `java.util.Collections::addAll` для коллекций, основанных на массиве
или предназначенных для использования в многопоточной среде.

Рассмотрим пример:
```java
@Benchmark
public boolean collectionsAddAll(Data data) {
  Collection<Integer> collection = data.freshCollection();
  Integer[] array = data.array;
  return Collections.addAll(collection, array);
}

@Benchmark
public boolean addAll(Data data) {
  Collection<Integer> collection = data.freshCollection();
  List<Integer> arrayAsList = Arrays.asList(data.array);
  return collection.addAll(arrayAsList);
}
```

В современных изданиях JDK метод `collectionsAddAll` работает быстрее только для `java.util.HashSet`, при это разница очень незначительна.

Для прочих коллекций `collectionsAddAll` работает значительно медленнее, хотя документация утверждает обратное.

См. задачу https://bugs.openjdk.java.net/browse/JDK-8193031

Предлагаемое изменение: http://cr.openjdk.java.net/~martin/webrevs/jdk/Collections-addAll/

Обсуждение: http://mail.openjdk.java.net/pipermail/core-libs-dev/2018-January/051197.html

См. итоги измерений в файлах для соответсвующих типов данных:
 - [./results/CollectionsAddAllVsAddAllBenchmark_HashSet.txt](./results/CollectionsAddAllVsAddAllBenchmark_HashSet.txt)
 - [./results/CollectionsAddAllVsAddAllBenchmark_COWList.txt](./results/CollectionsAddAllVsAddAllBenchmark_COWList.txt)
 - [./results/CollectionsAddAllVsAddAllBenchmark_ArrayList.txt](./results/CollectionsAddAllVsAddAllBenchmark_ArrayList.txt)
