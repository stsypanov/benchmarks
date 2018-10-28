package com.luxoft.logeek.report.repository;

import com.luxoft.logeek.report.ReportExampleConfig;
import com.luxoft.logeek.report.entity.ReportEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;

import static java.util.Objects.requireNonNull;
import static org.apache.commons.io.IOUtils.toByteArray;

@Commit
@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ReportExampleConfig.class)
public class ReportRepositoryTest {
  @Autowired
  ReportRepository reportRepository;

  @Test
  public void test() throws Exception {
    try (InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream("spy.properties")) {
      byte[] reportContent = toByteArray(requireNonNull(resourceAsStream));
      ReportEntity reportEntity = new ReportEntity(reportContent);
      reportRepository.save(reportEntity);
    }
  }
}