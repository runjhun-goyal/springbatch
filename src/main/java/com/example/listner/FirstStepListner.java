package com.example.listner;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class FirstStepListner implements StepExecutionListener {

	@Override
	public void beforeStep(StepExecution stepExecution) {
		System.out.println("Before Step"+stepExecution.getStepName());
		System.out.println("Before Step"+stepExecution.getExecutionContext());
		System.out.println("Before step"+stepExecution.getExecutionContext());
		stepExecution.getExecutionContext().put("sec","sec value");
	}

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		System.out.println("After Step"+stepExecution.getStepName());
		System.out.println("After Step"+stepExecution.getExecutionContext());
		System.out.println("After step"+stepExecution.getExecutionContext());
		return null;
	}

}
