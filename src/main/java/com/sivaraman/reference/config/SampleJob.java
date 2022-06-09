package com.sivaraman.reference.config;

import com.sivaraman.reference.listener.FirstJobListener;
import com.sivaraman.reference.listener.FirstStepListener;
import com.sivaraman.reference.service.SecondTasklet;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SampleJob {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private SecondTasklet secondTasklet;

    @Autowired
    private FirstJobListener firstJobListener;

    @Autowired
    private FirstStepListener firstStepListener;

    @Bean
    public Job firstJob(){
        return jobBuilderFactory.get("First job")
//                .incrementer(new RunIdIncrementer()) // comment to avoid multiple execution
                .listener(firstJobListener)
                .start(firstStep()).next(secondStep()).build();
    }

    @Bean
    public Step firstStep(){
        return stepBuilderFactory.get("First step").tasklet(firstTasklet()).listener(firstStepListener).build();
    }

    @Bean
    public Tasklet firstTasklet(){

        return (stepContribution, chunkContext) -> {
           System.out.println("First tasklet");
           return RepeatStatus.FINISHED;
        };
    }

    @Bean
    public Step secondStep(){
        return stepBuilderFactory.get("Second step").tasklet(secondTasklet).build();
    }

}
