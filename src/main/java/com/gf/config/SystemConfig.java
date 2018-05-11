package com.gf.config;

import com.gf.message.Receiver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

import java.util.concurrent.CountDownLatch;
 /**
 * <p>Title: SystemConfig</p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author guofu
 * @version 1.0
 * @date 2018-04-09 10:09
 */
@Configuration
public class SystemConfig{


    @Bean
    CountDownLatch latch(){
        return new CountDownLatch( 1 );
    }

    @Bean
    Receiver receiver(CountDownLatch latch){
        return new Receiver( latch );
    }



    @Bean
    StringRedisTemplate template(RedisConnectionFactory connectionFactory){
        return new StringRedisTemplate( connectionFactory );
    }

    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory , MessageListenerAdapter listenerAdapter){
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory( connectionFactory );
        container.addMessageListener( listenerAdapter , new PatternTopic( "chat" ) );

        return container;
    }

    @Bean
    MessageListenerAdapter listenerAdapter(Receiver receiver){
        return new MessageListenerAdapter( receiver , "receiveMessage" );
    }

}
