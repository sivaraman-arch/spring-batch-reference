package com.sivaraman.reference.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class JobExecutionScheduler {

    @Autowired
    JobLauncher launcher;

    @Autowired
    Job randomNumberGenerator;


    @Scheduled(cron = "0 0/1 * 1/1 * ?")
    public void runJob(){

        log.info("Scheduler started at : {}", System.currentTimeMillis());

        Map<String, JobParameter> requestMap = new HashMap<>();
        requestMap.put("startTime", new JobParameter(Instant.now().toString()));
        JobParameters parameters = new JobParameters(requestMap);
        this.run(parameters);
    }



    private void run(JobParameters parameters){
        try {
            launcher.run(randomNumberGenerator, parameters);
        } catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException | JobParametersInvalidException e) {
            e.printStackTrace();
        }
    }



}
