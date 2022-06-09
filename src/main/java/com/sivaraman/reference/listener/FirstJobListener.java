package com.sivaraman.reference.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class FirstJobListener implements JobExecutionListener {
    @Override
    public void beforeJob(JobExecution jobExecution) {
        log.info("Before first job");
        log.info(jobExecution.getJobInstance().toString());
        jobExecution.getExecutionContext().put("my_key", "my_value");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        log.info("After first job");
        log.info(jobExecution.getJobInstance().toString());
        log.info(jobExecution.getExecutionContext().toString());
    }
}
