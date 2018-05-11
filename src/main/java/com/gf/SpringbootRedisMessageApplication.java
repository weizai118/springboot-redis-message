package com.gf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.StringRedisTemplate;
import java.util.concurrent.CountDownLatch;

@SpringBootApplication
public class SpringbootRedisMessageApplication {

	private static final Logger logger = LoggerFactory.getLogger(SpringbootRedisMessageApplication.class);

	public static void main(String[] args) throws InterruptedException {
		ApplicationContext ctx = SpringApplication.run( SpringbootRedisMessageApplication.class, args );

		StringRedisTemplate template = ctx.getBean( StringRedisTemplate.class );
		CountDownLatch latch = ctx.getBean( CountDownLatch.class );
		logger.info( "Sending message ..." );
		template.convertAndSend( "chat" , "Hello from Redis" );
		latch.await();
		System.exit( 0 );
	}

}
