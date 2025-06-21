package com.learning.spring_rabbitmq_producer;


import java.util.UUID;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessagePublisher {

    private final TopicExchange exchange;
	
	@Autowired
	private RabbitTemplate template;

    MessagePublisher(TopicExchange exchange) {
        this.exchange = exchange;
    }
	
	@PostMapping("/publish")
	public String publishMessage(@RequestBody CustomMessage message) {
		message.setMessageId(UUID.randomUUID().toString());
		message.setMessageDate(new Date());
		template.convertAndSend(MQConfig.TopicExchange,MQConfig.RoutingKey,message);
		
		return "Message Published";
	}
		
	

}
