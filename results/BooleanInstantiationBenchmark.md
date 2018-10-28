|                                 |      |      |     Java 8  |      Java 9  |     Java 10  |     Java 11  |      |
|---------------------------------|------|------|-------------|--------------|--------------|--------------|------|
| Benchmark                       | Mode |  Cnt |        Score|       Score  |       Score  |       Score  | Unit |
| valueOf                         | avgt |   30 |**3,7 ± 0,3**| **3,4 ± 0,0**| **3,6 ± 0,0**| **3,5 ± 0,0**| ns/op|
| constructor                     | avgt |   30 |**7,4 ± 1,8**| **5,0 ± 0,0**| **5,5 ± 0,1**| **5,9 ± 0,4**| ns/op|
| valueOf:·gc.alloc.rate.norm     |gcprof|   30 |   **≈0**    |   **≈0**     |   **≈0**     |   **≈0**     |  B/op|
| constructor:·gc.alloc.rate.norm |gcprof|   30 |   **16**    |   **16**     |   **16**     |   **16**     |  B/op|