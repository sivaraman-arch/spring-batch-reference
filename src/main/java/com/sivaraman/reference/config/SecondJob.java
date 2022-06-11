package com.sivaraman.reference.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecondJob {

    @Autowired
    JobBuilderFactory jobBuilderFactory;

    @Autowired
    StepBuilderFactory stepBuilderFactory;

    @Bean
    Job randomNumberGenerator(){
        return jobBuilderFactory.get("Random number generator").start(firstRandomStep()).build();
    }


    @Bean
    public Step firstRandomStep(){
        return stepBuilderFactory.get("First step").tasklet(firstRandomTasklet()).build();
    }

    @Bean
    public Tasklet firstRandomTasklet(){
        return (stepContribution, chunkContext) -> {

            System.out.println("First tasklet");
            System.out.println(Math.random()*100);
            return RepeatStatus.FINISHED;
        };
    }
}
