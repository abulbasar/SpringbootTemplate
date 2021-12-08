package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.security.concurrent.DelegatingSecurityContextExecutorService;

import java.util.concurrent.*;

@Configuration
@EnableAsync
public class AsyncConfiguration implements AsyncConfigurer {

    private ExecutorService getExecutorService(){
        final int cores = Runtime.getRuntime().availableProcessors() * 2;
        final int queueSize = 2048;
        final  ExecutorService executorService = new ThreadPoolExecutor(cores, cores,
                0, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(queueSize, true));

        return executorService;
    }

    @Override
    public Executor getAsyncExecutor() {
        final DelegatingSecurityContextExecutorService executorService =
                new DelegatingSecurityContextExecutorService(getExecutorService());
        return executorService;
    }

    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler(){
        ThreadPoolTaskScheduler threadPoolTaskScheduler
                = new ThreadPoolTaskScheduler();
        threadPoolTaskScheduler.setPoolSize(5);
        threadPoolTaskScheduler.setThreadNamePrefix(
                "ThreadPoolTaskScheduler");
        return threadPoolTaskScheduler;
    }
}