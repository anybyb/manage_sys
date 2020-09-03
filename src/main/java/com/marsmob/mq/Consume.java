package com.marsmob.mq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Consume  {
	@RabbitListener(queues = "sysMsg")
	@RabbitHandler  
	public void process(String message) {
	    
	}

}
