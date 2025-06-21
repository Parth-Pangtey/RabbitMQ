package com.learning.spring_rabbitmq_producer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
@AllArgsConstructor 
public class CustomMessage {
	
	
	private String messageId;
	private String message;
	private String messageDate;
	

}
