package com.colak.springtutorial.config.steplistener;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

public class CustomStepExecutionListener implements StepExecutionListener {

    @Override
    public void beforeStep(StepExecution stepExecution) {
        System.out.println("Step is about to start for Step Name: " + stepExecution.getStepName());
    }
    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        System.out.println("Step has completed with status: " + stepExecution.getStatus());
        // You can customize the ExitStatus based on your logic
        return ExitStatus.COMPLETED;
    }
}
