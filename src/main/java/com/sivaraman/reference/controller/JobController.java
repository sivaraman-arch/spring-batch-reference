package com.sivaraman.reference.controller;

import com.sivaraman.reference.model.JobExecutionRequest;
import com.sivaraman.reference.service.JobExecutionLauncher;
import lombok.SneakyThrows;
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
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/job")
@Slf4j
public class JobController {

  @Autowired JobLauncher jobLauncher;
  @Autowired JobExecutionLauncher jobExecutionLauncher;

  @Autowired Job firstJob;

  @GetMapping("/start/{jobName}")
  public String start(@PathVariable String jobName) {
    log.info("Received request to launch job : {}", jobName);
    Map<String, JobParameter> paramMap = new HashMap<>();

    paramMap.put("executionTime", new JobParameter(Instant.now().toString()));

    JobParameters params = new JobParameters(paramMap);
    try {
      jobLauncher.run(firstJob, params);
    } catch (JobExecutionAlreadyRunningException
        | JobRestartException
        | JobInstanceAlreadyCompleteException
        | JobParametersInvalidException e) {
      e.printStackTrace();
      log.info("Error while executing job : {}", e.getMessage());
      return jobName + "failed ...";
    }
    return jobName + " started ...";
  }

  @SneakyThrows
  @PostMapping("/start/{jobName}")
  public String startExecution(
      @PathVariable String jobName,
      @RequestBody List<JobExecutionRequest> jobExecutionRequestList) {
    Map<String, JobParameter> requestMap = new HashMap<>();

    jobExecutionRequestList.forEach(
        jobExecutionRequest -> {
          requestMap.put(
              jobExecutionRequest.getKey(), new JobParameter(jobExecutionRequest.getValue()));
        });

    jobExecutionLauncher.run(jobName, new JobParameters(requestMap)); // this is async

//    jobLauncher.run(firstJob, new JobParameters(requestMap));
    return "Job started ...";
  }
}
