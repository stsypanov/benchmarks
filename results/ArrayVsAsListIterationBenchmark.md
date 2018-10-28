|                         |      |      |  Java 8 |       | Java 9  |       |      |
|-------------------------|------|------|---------|-------|---------|-------|------|
| Benchmark               | Mode | Cnt  |    Score| Error |    Score| Error | Unit |
| iterateOverArray        | avgt |   10 |  **9,3**|  0,6 |  **9,2**|   0,0 | ns/op|
| iterateOverArraysAsList | avgt |   10 | **16,8**|  0,2 |  **9,4**|   0,1 | ns/op|
|-------------------------|------|------|---------|------|---------|--------|------|
| iterateOverArray        | avgt |  100 | **45,2**|  3,0 | **40,5**|   0,1 | ns/op|
| iterateOverArraysAsList | avgt |  100 | **89,6**|  1,3 | **43,4**|   0,2 | ns/op|
|-------------------------|------|------|---------|------|----------|-------|------|
| iterateOverArray        | avgt | 1000 |**379,8**|  5,0 |**366,4**|   1,4 | ns/op|
| iterateOverArraysAsList | avgt | 1000 |**742,2**|  4,8 |**391,5**|   2,2 | ns/op|