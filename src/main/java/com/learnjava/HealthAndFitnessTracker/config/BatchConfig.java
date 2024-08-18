package com.learnjava.HealthAndFitnessTracker.config;

import com.learnjava.HealthAndFitnessTracker.model.User;
import com.learnjava.HealthAndFitnessTracker.repisitory.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
public class BatchConfig {
    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;
    private final UserRepository repository;
    @Bean
    public FlatFileItemReader<User> itemReader (){
        FlatFileItemReader<User> reader = new FlatFileItemReader<>();
        reader.setResource(new FileSystemResource("src/main/resources/users.csv"));
        reader.setName("csvReader");
        reader.setLinesToSkip(1);
        reader.setLineMapper(lineMapper());
        return reader;
    }
    @Bean
    public UserProcessor processor(){
        return new UserProcessor();
    }
    @Bean
    public RepositoryItemWriter<User> writer(){
        RepositoryItemWriter<User> writer = new RepositoryItemWriter<>();
        writer.setRepository(repository);
        writer.setMethodName("save");
        return writer;
    }
    @Bean
    public Step importStep(){
        return new StepBuilder("csvImport", jobRepository)
                .<User,User>chunk(10, transactionManager)
                .reader(itemReader())
                .processor(processor())
                .writer(writer())
                .build();

    }
    @Bean
    public Job runJob(){
        return new JobBuilder("ImportStudent", jobRepository)
                .start(importStep())
                .build();
    }

    private LineMapper<User> lineMapper() {
        DefaultLineMapper<User> lineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(",");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames("firstName","lastName","Age");

        BeanWrapperFieldSetMapper<User> fieldWrapper = new BeanWrapperFieldSetMapper<>();
        fieldWrapper.setTargetType(User.class);

        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(fieldWrapper);

        return lineMapper;
    }
    @Bean
    public TaskExecutor taskExecutor(){
        SimpleAsyncTaskExecutor asyncExecutor = new SimpleAsyncTaskExecutor();
        asyncExecutor.setConcurrencyLimit(10);
        return asyncExecutor;
    }
}
