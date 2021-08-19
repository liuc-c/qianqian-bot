package com.choool.qianqianbot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * 线程池配置文件
 *
 * @author <a href="https://github.com/liuc-c">choool</a>  on 2021/5/25 23:09
 */
@Configuration
@EnableAsync
public class ThreadPoolConfig {
    @Bean
    public TaskExecutor getAsyncThread() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 设置核心线程数，线程池维护线程的最少数量
        executor.setCorePoolSize(20);
        // 设置最大线程数，线程池维护线程的最大数量,只有在缓冲队列满了之后才会申请超过核心线程数的线程
        executor.setMaxPoolSize(50);
        // 设置队列容量，缓存队列
        executor.setQueueCapacity(100);
        // 设置线程活跃时间（秒），允许的空闲时间,当超过了核心线程出之外的线程在空闲时间到达之后会被销毁
        executor.setKeepAliveSeconds(60);
        // 设置默认线程名称,线程名称前缀
        executor.setThreadNamePrefix("qianqian-bot-thread-");
        // 设置拒绝策略,线程池对拒绝任务（无线程可用）的处理策略，目前只支持AbortPolicy、CallerRunsPolicy；默认为后者
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 等待所有任务结束后再关闭线程池
        executor.setWaitForTasksToCompleteOnShutdown(true);
        // 等待时长
        executor.setAwaitTerminationSeconds(60);
        executor.initialize();
        return executor;
    }
}