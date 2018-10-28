# Список бенчмарков с краткими пояснениями

Бенчмарки, связанные с enterprise-технологиями (Spring, Hibernate), пока реализованы только для JDK8 (ветка master).
Ветка master-java-9 включает бенчмарки, использующие только Java Core. Ветка настроена на JDK9.
Для замеров на JDK10/11 можно использовать ветку master-java-9. Всё, что нужно сделать - выставить для проекта нужный JDK.

### ArrayVsAsListIterationBenchmark
Показывает разницу между проходом по "голому" массиву

```java
Integer[] array = holder.array;
for (Integer integer : array) {
  bh.consume(integer);
}
```
и проходом по массиву, завёрнутому в `Arrays.asList`
```java
List<Integer> integers = Arrays.asList(array);
for (Integer integer : integers) {
  bh.consume(integer);
}
```

Итоги замеров см. в results/ArrayVsAsListIterationBenchmark.md

Также см. [IDEA-184240 Unnecessary array-to-collection wrapping should be detected](https://youtrack.jetbrains.com/issue/IDEA-184240)

### SubListToArrayBenchmark
Показывает разницу между 

```java
ArrayList.toArray(new T[0])
``` 
и
```java
ArrayList.subList(0, size()).toArray(new T[0])
```

Несмотря на равный объём перемещаемых данных, производительность второго способа значительно хуже.

См. [JDK-8196207 Inefficient ArrayList.subList().toArray()](https://bugs.openjdk.java.net/browse/JDK-8196207)

### BulkOperationBenchmark
Показывает разницу между тремя способами заполнения коллекции:

1\.
```java
Collection<Integer> newCollection = new ArrayList<>();
for (Integer item : items) {
    newCollection.add(item);
}
```
2\.
```java
Collection<Integer> newCollection = new ArrayList<>();
newCollection.addAll(items);
```
3\.
```java
Collection<Integer> newCollection = new ArrayList(items);
```

См. [IDEA-165942 Inspection to replace method call in a loop with bulk operation](https://youtrack.jetbrains.com/issue/IDEA-165942)

### SubArrayBenchmark
Показывает разницу между 

```java
Arrays.asList(array).subList(0, to).toArray(new Integer[0])
```
и
```java
Arrays.copyOf(array, to);
```

Итоги замеров см. в results/SubArrayBenchmark.md

Также см. [IDEA-182206 Simplification for Arrays.asList().sublist().toArray()](https://youtrack.jetbrains.com/issue/IDEA-182206)

### LazyTransactionBenchmark
Показывает разницу между

```java
public long createCashFlow(CashFlowDto dto) {
  boolean isInvalid = dto.getNumber() % 2 != 0;
  if (isInvalid) {
    return -1L;
  }

  return cashFlowServiceLocal.createCashFlow(dto);
}
```

и

```java
@Transactional(readOnly = true)
public long createCashFlow(CashFlowDto dto) {
  boolean isInvalid = dto.getNumber() % 2 != 0;
  if (isInvalid) {
    return -1L;
  }

  return cashFlowServiceLocal.createCashFlow(dto);
}
```

Иными словами, показывает стоимость "холостой" транзакции.

### IteratorFromStreamBenchmark
Показывает разницу между тремя способами перебора элементов стрима

1\)
```java
Iterator<String> iterator = items.stream()
  .map(Object::toString)
  .collect(toList())//todo add case for toSet()
  .iterator();

while (iterator.hasNext())
  bh.consume(iterator.next());
```
2\)
```java
Iterator<String> iterator = items.stream()
  .map(Object::toString)
  .iterator();

while (iterator.hasNext())
  bh.consume(iterator.next());
```
3\)
```java
items.stream()
  .map(Object::toString)
  .forEach(bh::consume);
```

Итоги замеров см. в results/iteratorFromStream.txt

### AllMatchVsContainsAllBenchmark
Показывает разницу между 

```java
set.stream().allMatch(data.collection::contains);
```
и
```java
collection.containsAll(data.set);
```

Итоги замеров см. в results/ArrayVsAsListIterationBenchmark.md

Также см. [IDEA-187623 Arrays.stream.anyMatch can be simplified](https://youtrack.jetbrains.com/issue/IDEA-187623)

### LoopVsSingleCallBenchmark
Бенчмарк, показывающий разницу между вызовом `JpaRepository.findOne` в цикле и единичным вызовом `JpaRepository.findAll`

См. [IDEA-165730 Warn about inefficient usages of JpaRepository](https://youtrack.jetbrains.com/issue/IDEA-165730)

### RemoveFromArrayListBenchmark
Показывает разницу между удалением из `ArrayList-а` тремя способами

- прямой проход (наименее производительный)
```java
for (int i = from; i < to; i++) {
  arrayList.remove(from);
}
```

- обратный проход (движение в сторону меньших индексов)
```java
for (int i = to - 1; i >= from; i--) {
  arrayList.remove(i);
}
```

- обратный проход с разворотом списка (позволяет сохранить порядок удаления и даёт доступ к порядковому номеру удаляемого элемента)
```java
public removeInReverseOrder() {
  reverse(arrayList);
  for (int i = to - 1; i >= from; i--) {
    arrayList.remove(i);
  }
}

private static <T> void reverse(ArrayList<T> list) {
  int i = 0;
  int j = list.size() - 1;

  while (i < j) {
    swap(list, i, j);
    i++;
    j--;
  }
}

private static <T> void swap(ArrayList<T> list, int i, int j) {
  T tmp = list.get(i);
  list.set(i, list.get(j));
  list.set(j, tmp);
}
```

- очистка подсписка (наиболее производительный)
```java
arrayList.subList(from, to).clear();
```

См. [IDEA-177466 Detect list.remove(int index) called in a loop](https://youtrack.jetbrains.com/issue/IDEA-177466)

### FormatterBenchmark
Показывает разницу между преобразованием в строку `java.util.Date` и `java.time.LocalDate`,
а также их сранвнение с промежуточным способом: превращение `java.util.Date` в `java.time.LocalDate`
и преобразование `java.time.LocalDate` с помощью `java.time.format.DateTimeFormatter`

### BoxingBenchmark

Показывает проседание кода, использующего обёртку там, где достаточно обойтись простым типом
```java
Long accumulator = 0L;
for (Long aLong : data.longs) {
  accumulator += aLong == null ? 0 : aLong;
}
return accumulator;
```
в цикле проиходит создание ненужных сущностей `java.lang.Long` при вычислении суммы.
Лучше так:
```java
long accumulator = 0L;
for (Long aLong : data.longs) {
  accumulator += aLong == null ? 0 : aLong;
}
return accumulator;
```

Итоги замеров см. в results/BoxingBenchmark.md

Также см. [IDEA-189336 New inspection 'Wrapper type may be primitive'](https://youtrack.jetbrains.com/v2/issue/IDEA-189336)

### AnyMatchVsContainsBenchmark

Показывает разницу между вызовом
```java
Arrays.asList().stream().anyMatch()
```
и 
```java
Arrays.asList().contains();
```

Итоги замеров см. в results/AnyMatchVsContainsAllBenchmark.md.md

См. [Arrays.stream.anyMatch can be simplified](https://youtrack.jetbrains.com/issue/IDEA-187623)