ArrayList

|                     |      |       |    Java 8 |        |   Java 9  |        |      |
|---------------------|------|-------|-----------|--------|-----------|--------|------|
| Benchmark           | Mode |  size |      Score|  Error |      Score|  Error | Unit |
| addAll              | avgt |    10 |   **57,9**|   1,0  |   **40,0**|    0,8 | ns/op|
| collectionsAddAll   | avgt |    10 |   **77,0**|   1,1  |   **53,4**|    0,8 | ns/op|
| addAll              | avgt |   100 |  **231,3**|   4,4  |  **220,8**|    5,4 | ns/op|
| collectionsAddAll   | avgt |   100 |  **723,0**|  12,8  |  **641,3**|    8,2 | ns/op|
| addAll              | avgt |  1000 | **1883,0**|  57,5  | **1708,3**|   18,7 | ns/op|
| collectionsAddAll   | avgt |  1000 | **7371,5**|  85,4  | **8033,2**|  279,4 | ns/op|

HashSet

|                     |      |       |     Java 8 |         |    Java 9  |        |      |
|---------------------|------|-------|------------|---------|------------|--------|------|
| Benchmark           | Mode |  size |       Score|   Error |       Score|  Error | Unit |
| addAll              | avgt |    10 |   **227,8**|    6,2  |   **170,4**|    2,7 | ns/op|
| collectionsAddAll   | avgt |    10 |   **208,2**|    3,0  |   **170,8**|    2,7 | ns/op|
| addAll              | avgt |   100 |  **2798,2**|   61,7  |  **2170,6**|   29,9 | ns/op|
| collectionsAddAll   | avgt |   100 |  **2150,4**|   29,3  |  **2179,3**|   36,9 | ns/op|
| addAll              | avgt |  1000 | **26212,2**|  410,0  | **20008,5**|  310,9 | ns/op|
| collectionsAddAll   | avgt |  1000 | **20960,9**|  396,5  | **19986,4**|  274,4 | ns/op|

ArrayDeque

|                     |      |       |    Java 8 |         |    Java 9 |       |      |
|---------------------|------|-------|-----------|---------|-----------|-------|------|
| Benchmark           | Mode |  size |      Score|   Error |      Score| Error | Unit |
| addAll              | avgt |    10 |   **39,9**|    0,7  |   **39,1**|   0,5 | ns/op|
| collectionsAddAll   | avgt |    10 |   **33,3**|    0,5  |   **24,8**|   0,4 | ns/op|
| addAll              | avgt |   100 |  **758,3**|   13,4  |  **245,9**|   5,3 | ns/op|
| collectionsAddAll   | avgt |   100 |  **607,7**|    7,7  |  **558,5**|   7,0 | ns/op|
| addAll              | avgt |  1000 | **6760,7**|  105,8  | **1976,9**|  37,2 | ns/op|
| collectionsAddAll   | avgt |  1000 | **5474,7**|   69,6  | **6821,2**|  92,3 | ns/op|

CopyOnWriteArrayList

|                     |      |       |      Java 8 |         |     Java 9  |        |      |
|---------------------|------|-------|-------------|---------|-------------|--------|------|
| Benchmark           | Mode |  size |        Score|   Error |        Score|  Error | Unit |
| addAll              | avgt |    10 |     **80,1**|     1,0 |     **32,9**|    0,4 | ns/op|
| collectionsAddAll   | avgt |    10 |    **516,5**|    16,2 |    **192,7**|    2,5 | ns/op|
| addAll              | avgt |   100 |    **259,6**|     3,3 |    **111,3**|    1,1 | ns/op|
| collectionsAddAll   | avgt |   100 |   **7559,0**|   109,2 |   **5509,7**|   59,3 | ns/op|
| addAll              | avgt |  1000 |   **1958,4**|    43,1 |   **1008,7**|   10,4 | ns/op|
| collectionsAddAll   | avgt |  1000 | **606132,5**| 11235,1 | **505670,8**| 5603,9 | ns/op|

ConcurrentLinkedDeque

|                     |      |       |     Java 8 |       |    Java 9  |       |      |
|---------------------|------|-------|------------|-------|------------|-------|------|
| Benchmark           | Mode |  size |       Score| Error |       Score| Error | Unit |
| addAll              | avgt |    10 |   **116,0**|   2,1 |    **88,4**|   1,2 | ns/op|
| collectionsAddAll   | avgt |    10 |   **210,3**|   2,3 |   **194,1**|   1,6 | ns/op|
| addAll              | avgt |   100 |   **820,1**|  10,0 |   **622,3**|   9,2 | ns/op|
| collectionsAddAll   | avgt |   100 |  **1914,3**|  13,1 |  **1782,0**|   9,3 | ns/op|
| addAll              | avgt |  1000 |  **7642,7**|  62,5 |  **6086,1**|  88,2 | ns/op|
| collectionsAddAll   | avgt |  1000 | **19024,5**| 323,8 | **17593,2**|  82,9 | ns/op|