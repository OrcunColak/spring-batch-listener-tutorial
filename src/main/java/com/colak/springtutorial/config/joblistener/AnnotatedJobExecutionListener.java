package com.colak.springtutorial.config.joblistener;

import org.springframework.batch.core.annotation.AfterJob;
import org.springframework.batch.core.annotation.BeforeJob;
import org.springframework.stereotype.Component;

@Component
public class AnnotatedJobExecutionListener {

    @BeforeJob
    public void beforeJob() {
        System.out.println("Job is about to start using annotations");
    }

    @AfterJob
    public void afterJob() {
        System.out.println("Job has completed using annotations");
    }
}
