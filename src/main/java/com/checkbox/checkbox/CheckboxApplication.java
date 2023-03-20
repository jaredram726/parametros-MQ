package com.checkbox.checkbox;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


/**
 * Hemos agregado un bean para MessageConverter y lo hemos configurado para utilizar 
 * Jackson2JsonMessageConverter, que es una implementación de Spring que convierte los 
 * mensajes a JSON utilizando la biblioteca Jackson. Además, hemos actualizado la 
 * configuración de RabbitTemplate para utilizar el MessageConverter.
 */
@SpringBootApplication
public class CheckboxApplication {

	 // Configuración de RabbitMQ
	  @Bean
	  public ConnectionFactory connectionFactory() {
	    CachingConnectionFactory connectionFactory = new CachingConnectionFactory("localhost");
	    connectionFactory.setUsername("guest");
	    connectionFactory.setPassword("guest");
	    return connectionFactory;
	  }

	  @Bean
	  public RabbitTemplate rabbitTemplate() {
	    RabbitTemplate template = new RabbitTemplate(connectionFactory());
	    template.setExchange("user-exchange");
	    template.setRoutingKey("user-routing-key");
	    template.setMessageConverter(messageConverter());
	    return template;
	  }

	  @Bean
	  public MessageConverter messageConverter() {
			return new Jackson2JsonMessageConverter();
	  }

	  @Bean
	  public Queue userQueue() {
	    return new Queue("user-queue", true);
	  }

	  @Bean
	  public DirectExchange userExchange() {
	    return new DirectExchange("user-exchange");
	  }

	  @Bean
	  public Binding userBinding(Queue userQueue, DirectExchange userExchange) {
	    return BindingBuilder.bind(userQueue).to(userExchange).with("user-routing-key");
	  }

	public static void main(String[] args) {
		SpringApplication.run(CheckboxApplication.class, args);
	}

}
