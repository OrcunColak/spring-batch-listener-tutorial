package com.colak.springtutorial.config.joblistener;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class CustomJobExecutionListener implements JobExecutionListener {

    @Override
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus().isUnsuccessful()) {
            System.out.println("Job failed!");
        } else {
            System.out.println("Job completed successfully!");
        }
    }
}
