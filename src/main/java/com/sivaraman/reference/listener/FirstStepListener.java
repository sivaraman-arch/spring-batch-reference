package com.sivaraman.reference.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class FirstStepListener implements StepExecutionListener {
    @Override
    public void beforeStep(StepExecution stepExecution) {
        log.info("before step");
        stepExecution.getExecutionContext().put("step_key", "step_val");
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        log.info("after step");
        return null;
    }
}
