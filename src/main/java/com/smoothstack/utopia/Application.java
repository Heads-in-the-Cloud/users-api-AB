package com.smoothstack.utopia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@EnableAsync
@EnableZuulProxy
@SpringBootApplication
public class Application {

    private static final int MAX_POOL_SIZE = 100;
    private static final int CORE_POOL_SIZE = 75;
    private static final int QUEUE_CAPACITY = 100;

   public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
   }

  @Bean
  public Executor taskExecutor() {
    final ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    executor.setMaxPoolSize(MAX_POOL_SIZE);
    executor.setCorePoolSize(CORE_POOL_SIZE);
    executor.setQueueCapacity(QUEUE_CAPACITY);
    executor.setThreadNamePrefix("UsersService-");
    executor.initialize();
    return executor;
  }

}
