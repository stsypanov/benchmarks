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
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import static org.junit.Assert.assertEquals;

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

  @Before
  public void setUp() {
    List<MyEntity> entities = LongStream.range(1, 101)
            .boxed()
            .map(randomLong -> new MyEntity(randomLong, name))
            .collect(Collectors.toList());

    myRepository.saveAll(entities);
  }

  @Test
  public void findAllByName() {
    List<HasIdAndName> names = myRepository.findAllByName(name);
    assertEquals(100, names.size());
  }

  @Test
  public void findAllByNameIntoDto() {
    List<IdNameDto> names = myRepository.findAllByNameIntoDto(name);
    assertEquals(100, names.size());
  }
}