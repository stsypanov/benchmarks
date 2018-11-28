package com.luxoft.logeek.flush.saver;

import com.luxoft.logeek.flush.SaveAndFlushExampleConfig;
import com.luxoft.logeek.flush.entity.SimpleEntity;
import com.luxoft.logeek.flush.repository.SimpleRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;

@Commit
@Transactional
//@ActiveProfiles("flush")
@ActiveProfiles("flush-postgres")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SaveAndFlushExampleConfig.class)
public class SimpleSaverImplTest {

  private final int entityCount = 2000;

  @Autowired
  private SimpleSaver simpleSaver;
  @Autowired
  private SimpleRepository repository;

  @Test
  public void bulkSave() {
    simpleSaver.bulkSave(entityCount);
  }

  @Test
  public void bulkSaveUsingFlush() {
    simpleSaver.bulkSaveUsingFlush(entityCount);
  }

  @AfterTransaction
  public void tearDown() {
    List<SimpleEntity> all = repository.findAll();
    assertEquals(entityCount, all.size());

    repository.deleteAllInBatch();
  }
}