package com.buying.back.application.quartz.config;

import java.util.List;
import org.quartz.Trigger;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.support.JobRegistryBeanPostProcessor;
import org.springframework.boot.autoconfigure.quartz.QuartzProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;

@Configuration
@EnableConfigurationProperties(QuartzProperties.class)
@EnableBatchProcessing
public class BatchConfig {

  @Bean
  public JobRegistryBeanPostProcessor jobRegistryBeanPostProcessor(JobRegistry jobRegistry) {
    JobRegistryBeanPostProcessor jobRegistryBeanPostProcessor = new JobRegistryBeanPostProcessor();
    jobRegistryBeanPostProcessor.setJobRegistry(jobRegistry);
    return jobRegistryBeanPostProcessor;
  }

  @Bean
  public Trigger[] registryTrigger(List<CronTriggerFactoryBean> cronTriggerFactoryBeans) {
    return cronTriggerFactoryBeans.stream()
      .map(CronTriggerFactoryBean::getObject)
      .toArray(Trigger[]::new);
  }
}
