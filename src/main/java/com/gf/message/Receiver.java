package com.gf.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.CountDownLatch;

/**
 * <p>Title: Receiver</p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author guofu
 * @version 1.0
 * @date 2018-04-09 10:00
 */
public class Receiver {

    private static final Logger logger = LoggerFactory.getLogger( Receiver.class );

    private CountDownLatch latch;

    @Autowired
    public Receiver(CountDownLatch latch) {
        this.latch = latch;
    }

    public void receiveMessage(String message){
        logger.info( "received <" + message + ">" );
        latch.countDown();
    }

}
