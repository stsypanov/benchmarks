# Список бенчмарков с краткими пояснениями | Benchmarks list with brief comments

Scroll down to read English version


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

См. итоги измерений в файле [ArrayInstantiationBenchmark.txt](./results/ArrayInstantiationBenchmark.txt) # It works!

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
см. [ArithmeticTest](./benchmark-source/src/test/java/com/luxoft/logeek/ArithmeticTest.java)

Поэтому замена `1L` на `1` предпочтительна только для положительных чисел.