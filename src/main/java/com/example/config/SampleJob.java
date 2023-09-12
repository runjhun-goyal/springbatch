package com.example.config;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.example.listner.FirstStepListner;
import com.example.listner.firstJobListner;
import com.example.processor.FirstItemProcessor;
import com.example.reader.FirstItemReader;
import com.example.service.SecondTasklet;
import com.example.writer.FirstItemWriter;

@SuppressWarnings("removal")
@Configuration
public class SampleJob {
	
	@SuppressWarnings("removal")
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@SuppressWarnings("removal")
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	private SecondTasklet secondTasklet;
	@Autowired
	private firstJobListner firstjoblistner;

	@Autowired
	private FirstStepListner firstStepListner;
	
	@Autowired
	private FirstItemReader firstItemReader;
	
	@Autowired
	private FirstItemProcessor firstItemProcessor;
	
	@Autowired
	private FirstItemWriter firstItemWriter;
	
	@SuppressWarnings({ "deprecation" })
	@Bean
	public Job firstJob() {
		return jobBuilderFactory.get("First Job")
		.start(firstStep())
		.next(secondStep())
		.listener(firstjoblistner)
		.build();
	}
	@SuppressWarnings({ "removal", "deprecation" })
	private Step firstStep() {
		return stepBuilderFactory.get("First Step")
		.tasklet(firstTask())
		.listener(firstStepListner)
		.build();
	}
	
	private Tasklet firstTask() {
		return new Tasklet() {
			
			public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
				System.out.println("this is first tasklet step");
				return RepeatStatus.FINISHED;
			}
		};
	}
	private Step secondStep() {
		return stepBuilderFactory.get("Second Step")
		.tasklet(secondTasklet)
		.build();
	}
//	private Tasklet secondTask() {
//		return new Tasklet() {
//
//			@Override
//			public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
//				System.out.println("this is second tasklet step");
//				return RepeatStatus.FINISHED;
//			}
//			
//		};
//	}
	@Bean
	public Job secondJob() {
		return jobBuilderFactory.get("Second Job")
				.incrementer(new RunIdIncrementer())
				.start(firstChunkStep())
				.next(secondStep())
				.build();
	}
	
	private Step firstChunkStep() {
		return stepBuilderFactory.get("First Chunk Step")
				.<Integer,Long>chunk(4)
				.reader(firstItemReader)
				.processor(firstItemProcessor)
				.writer(firstItemWriter)
				.build();
	}
}
