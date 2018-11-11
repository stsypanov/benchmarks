package com.luxoft.logeek.flush.saver;

import com.luxoft.logeek.flush.entity.SimpleEntity;

import java.util.List;

public interface SimpleSaver {
  List<SimpleEntity> bulkSave(int entityCount);

  List<SimpleEntity> bulkSaveUsingFlush(int entityCount);
}
