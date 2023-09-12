package com.example.listner;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class firstJobListner implements JobExecutionListener{

	@Override
	public void beforeJob(JobExecution jobExecution) {
		System.out.println("Before Job"+jobExecution.getJobInstance().getJobName());
		System.out.println("Job Params"+jobExecution.getJobParameters());
		System.out.println("Context Job"+jobExecution.getExecutionContext());
		jobExecution.getExecutionContext().put("jec","jec value");
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		System.out.println("Before Job"+jobExecution.getJobInstance().getJobName());
		System.out.println("Job Params"+jobExecution.getJobParameters());
		System.out.println("Context Job"+jobExecution.getExecutionContext());
		
	}
	

}
