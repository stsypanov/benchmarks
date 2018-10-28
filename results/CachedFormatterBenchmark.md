|                                 |      |      | Java 8  |       | Java 9  |       |      |
|---------------------------------|------|------|---------|-------|---------|-------|------|
| Benchmark                       | Mode | Cnt  | Score   |  Error| Score   | Error | Unit |
| dateTimeFormatter               | avgt | 1000 |**204,1**|   2,9 |**142,7**|   3,0 | ns/op|
| dateTimeFormatter_dateConverted | avgt | 1000 |**426,0**|  31,7 |**228,3**|   2,6 | ns/op|
| simpleDateFormat                | avgt | 1000 |**290,2**|  23,6 |**266,2**|  11,5 | ns/op|