package com.sivaraman.reference.service;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Service;

import java.util.stream.IntStream;

@Service
@Slf4j
public class SecondTasklet implements Tasklet {

  @SneakyThrows
  @Override
  public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) {
    log.info("Start of second tasklet");

    IntStream.range(0, 10)
        .forEach(
            x -> {
              double v = Math.random() * 100;
              log.info(String.valueOf(v));

                try {
                    Thread.sleep(1000);  // to simulate delay
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });


    log.info("Second tasklet completed");
    return RepeatStatus.FINISHED;
  }
}
