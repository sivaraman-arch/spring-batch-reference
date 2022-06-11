package com.sivaraman.reference;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableBatchProcessing
@EnableAsync
public class BatchProcessorApplication {

	public static void main(String[] args) {
		SpringApplication.run(BatchProcessorApplication.class, args);
	}

}
