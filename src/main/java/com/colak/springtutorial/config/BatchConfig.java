package com.colak.springtutorial.config;

import com.colak.springtutorial.config.chunklistener.CustomChunkListener;
import com.colak.springtutorial.config.itemread.CustomItemReadListener;
import com.colak.springtutorial.config.joblistener.AnnotatedJobExecutionListener;
import com.colak.springtutorial.config.joblistener.CustomJobExecutionListener;
import com.colak.springtutorial.config.steplistener.CustomStepExecutionListener;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.DefaultJobParametersValidator;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.listener.JobListenerFactoryBean;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.List;

@Configuration
public class BatchConfig {

    @Bean
    public Job job(JobRepository jobRepository, Step step) {
        // Add an example validator which by default does nothing
        DefaultJobParametersValidator validator = new DefaultJobParametersValidator();

        return new JobBuilder("job", jobRepository)
                .validator(validator)
                .incrementer(new RunIdIncrementer())
                .listener(new CustomJobExecutionListener())
                .listener(JobListenerFactoryBean.getListener(new AnnotatedJobExecutionListener()))
                .listener(new CustomStepExecutionListener())


                .start(step)
                .build();
    }

    @Bean
    public Step step(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("step", jobRepository)
                .<Integer, Integer>chunk(1, transactionManager)
                .reader(itemReader())
                .processor(itemProcessor())
                .writer(itemWriter())
                .listener(new CustomChunkListener())
                .listener(new CustomStepExecutionListener())
                .listener(new CustomItemReadListener<>())
                .build();
    }

    @Bean
    public ListItemReader<Integer> itemReader() {
        return new ListItemReader<>(List.of(1, 2, 3, 4, 5));
    }

    @Bean
    public ItemProcessor<Integer, Integer> itemProcessor() {
        return item -> item * item; // Squaring the number
    }

    @Bean
    public ItemWriter<Integer> itemWriter() {
        return items -> {
            for (Integer item : items) {
                System.out.println("Writing item: " + item);
            }
        };
    }
}
