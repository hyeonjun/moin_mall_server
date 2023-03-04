package com.buying.back.application.quartz.job;

import com.buying.back.application.quartz.service.BatchService;
import lombok.RequiredArgsConstructor;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.PersistJobDataAfterExecution;

@RequiredArgsConstructor
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class CheckAccountBirthDayJob implements Job {

  private final BatchService batchService;

  @Override
  public void execute(JobExecutionContext context) {
    batchService.batchCheckNormalAccountBirthDay();
  }
}
