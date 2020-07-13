package com.gw.domain.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * @author zoujialiang
 */
@Configuration
public class TaskExecutorConfig implements AsyncConfigurer {

    /**
     * 线程池维护线程的最少数量，核心线程池
     */
    private static final int CORE_POOL_SIZE = 2;
    /**
     * 线程池维护线程的最大数量
     */
    private static final int MAX_POOL_SIZE = 10;
    /**
     * 线程池维护线程所允许的空闲时间
     */
    private final static int KEEP_ALIVE_TIME = 0;
    /**
     * 线程池所使用的缓冲队列大小
     */
    private static final int QUEUE_CAPACITY = 50;

    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(CORE_POOL_SIZE);
        taskExecutor.setMaxPoolSize(MAX_POOL_SIZE);
        taskExecutor.setKeepAliveSeconds(KEEP_ALIVE_TIME);
        taskExecutor.setQueueCapacity(QUEUE_CAPACITY);
        taskExecutor.initialize();
        return taskExecutor;
    }
}
