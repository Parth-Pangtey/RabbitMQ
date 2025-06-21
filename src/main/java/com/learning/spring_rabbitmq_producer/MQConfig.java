package com.learning.spring_rabbitmq_producer;


import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class MQConfig {
	
	// Defining the variables
	public static final String Queue ="message_queue";
	public static final String TopicExchange ="message_exchange";
	public static final String RoutingKey ="message_routingKey";
	
	
	@Bean
	// Creating QUEUE method
	public Queue queue() {
		
		return new Queue(Queue);
	}
	
	@Bean
	// Creating EXCHANGE method
	public org.springframework.amqp.core.TopicExchange exchange() {
		
		return new org.springframework.amqp.core.TopicExchange("TopicExchange");
	}	
	
	@Bean
	// Creating BINDING method
	// Binding the queue to exchange with the help of routingKey
	public Binding binding(Queue queue, org.springframework.amqp.core.TopicExchange exchange ) {
		return BindingBuilder
				.bind(queue).to(exchange).with(RoutingKey);
		
	}
	@Bean
	//adding message converter to covert it in JSON and to send in particular queue
	public org.springframework.amqp.support.converter.MessageConverter messageCoverter() {
		
		return new Jackson2JsonMessageConverter();
	}
	
	
	@Bean
	//creating rabbitMQ template
	public AmqpTemplate template (ConnectionFactory connectionFactory) {
		RabbitTemplate template = new RabbitTemplate(connectionFactory);
		template.setMessageConverter(messageCoverter());
		return template;
	}
	
		
	}

