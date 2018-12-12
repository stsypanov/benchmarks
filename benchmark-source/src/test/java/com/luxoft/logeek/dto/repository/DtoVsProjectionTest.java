package com.luxoft.logeek.dto.repository;

import com.luxoft.logeek.dto.DtoVsProjectionConfig;
import com.luxoft.logeek.dto.domain.HasIdAndName;
import com.luxoft.logeek.dto.domain.IdNameDto;
import com.luxoft.logeek.dto.entity.MyEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@Commit
@Transactional
//@ActiveProfiles("flush")
@ActiveProfiles("flush-postgres")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DtoVsProjectionConfig.class)
public class DtoVsProjectionTest {

  private final String name = "Иван";
  @Autowired
  private MyRepository myRepository;
  private List<Long> ids;
  private ThreadLocalRandom random = ThreadLocalRandom.current();

  @Before
  public void setUp() {
    List<MyEntity> entities = LongStream.range(1, 101)
            .boxed()
            .map(randomLong -> new MyEntity(randomLong, name))
            .collect(Collectors.toList());

    ids = myRepository.saveAll(entities).stream().map(MyEntity::getId).collect(Collectors.toList());
  }

  @Test
  public void findAllByName() {
    HasIdAndName names = myRepository.findProjectionById(ids.get(random.nextInt(100)));
    assertNotNull(names);
  }

  @Test
  public void findAllByNameIntoDto() {
    IdNameDto names = myRepository.findDtoById(ids.get(random.nextInt(100)));
    assertNotNull(names);
  }
}