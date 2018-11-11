package com.luxoft.logeek.flush.saver;

import com.luxoft.logeek.flush.entity.SimpleEntity;
import com.luxoft.logeek.flush.repository.SimpleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

@Transactional
@RequiredArgsConstructor
public class SimpleSaverImpl implements SimpleSaver {
  private final SimpleRepository repository;

  @Override
  public List<SimpleEntity> bulkSave(int entityCount) {
    return LongStream.range(0, entityCount)
            .mapToObj(operand -> new SimpleEntity())
            .peek(repository::save)
            .collect(Collectors.toList());
  }

  @Override
  public List<SimpleEntity> bulkSaveUsingFlush(int entityCount) {
    return LongStream.range(0, entityCount)
            .mapToObj(operand -> new SimpleEntity())
            .peek(repository::saveAndFlush)
            .collect(Collectors.toList());
  }

}
