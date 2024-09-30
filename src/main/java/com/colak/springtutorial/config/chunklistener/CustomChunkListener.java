package com.colak.springtutorial.config.chunklistener;

import org.springframework.batch.core.ChunkListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.scope.context.StepContext;

public class CustomChunkListener implements ChunkListener {

    @Override
    public void beforeChunk(ChunkContext chunkContext) {
        StepContext stepContext = chunkContext.getStepContext();
        System.out.println("Before Chunk: " + stepContext.getStepName() +
                           ", JobInstanceId: " + stepContext.getJobInstanceId());
    }

    @Override
    public void afterChunk(ChunkContext chunkContext) {
        StepContext stepContext = chunkContext.getStepContext();
        System.out.println("After Chunk: " + stepContext.getStepName() +
                           ", JobInstanceId: " + stepContext.getJobInstanceId());
    }

    @Override
    public void afterChunkError(ChunkContext chunkContext) {
        System.err.println("Error during chunk processing: " + chunkContext.getStepContext().getStepName());
    }
}
