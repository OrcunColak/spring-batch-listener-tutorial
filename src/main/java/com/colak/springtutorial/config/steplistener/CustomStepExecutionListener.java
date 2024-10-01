package com.colak.springtutorial.config.steplistener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CustomStepExecutionListener implements StepExecutionListener {

    @Override
    public void beforeStep(StepExecution stepExecution) {
        log.info("Starting step: {}", stepExecution.getStepName());
    }

    // afterStep method returns ExitStatus. This exitStatus will be populated in BATCH_STEP_EXECUTION table
    // after step is executed.
    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        log.info("Finished step: {}", stepExecution.getStepName());
        return stepExecution.getExitStatus();
    }
}

