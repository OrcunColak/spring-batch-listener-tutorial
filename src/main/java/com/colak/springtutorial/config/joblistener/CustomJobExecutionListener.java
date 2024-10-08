package com.colak.springtutorial.config.joblistener;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

// During the course of the execution of a Job, it may be useful to be notified of various events in its lifecycle so
// that custom code can be run.
@Component
public class CustomJobExecutionListener implements JobExecutionListener {

    // afterJob method is called regardless of the success or failure of the Job
    @Override
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus().isUnsuccessful()) {
            System.out.println("Job failed!");
        } else {
            System.out.println("Job completed successfully!");
        }
        // Another way is :
        // if (jobExecution.getStatus() == BatchStatus.COMPLETED ) {
        //     //job success
        // }
        // else if (jobExecution.getStatus() == BatchStatus.FAILED) {
        //     //job failure
        // }
    }
}
