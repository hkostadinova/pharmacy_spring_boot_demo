package com.rewe.pharmacy.config.executors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnThreading;
import org.springframework.boot.autoconfigure.thread.Threading;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@Configuration
public class ExecutorServiceConfig {
    @Bean
    @ConditionalOnThreading(Threading.VIRTUAL)
    public ExecutorService virtualThreadExecutor() {
        log.info("Virtual Thread");
        return Executors.newVirtualThreadPerTaskExecutor();
    }

    @Bean
    @ConditionalOnThreading(Threading.PLATFORM)
    public ExecutorService platformThreadExecutor() {
        log.info("Platform Thread");
        return Executors.newCachedThreadPool();
    }
}
