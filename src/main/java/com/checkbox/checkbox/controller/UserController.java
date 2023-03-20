package com.checkbox.checkbox.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.checkbox.checkbox.entity.UserPolicy;

/**
*Controlador que maneja la solicitud POST para registrar la información del 
*usuario y enviarla por RabbitMQ
**/
@RestController
public class UserController {

	private final RabbitTemplate rabbitTemplate;
	private final MessageConverter messageConverter;
	private final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	public UserController(RabbitTemplate rabbitTemplate, MessageConverter messageConverter) {
		this.rabbitTemplate = rabbitTemplate;
		this.messageConverter = messageConverter;
	}
	
	/**
	 * Hemos inyectado el MessageConverter y lo usamos para convertir el objeto 
	 * User a un Message de RabbitMQ antes de enviarlo mediante el método send() 
	 * del RabbitTemplate. La conversión a JSON se realiza automáticamente gracias 
	 * a la configuración del MessageConverter por defecto.
	 * @param user
	 * @return
	 */
	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@RequestBody UserPolicy user) {
		LOGGER.info("Received request to register user: {}", user);
		LOGGER.info("User logged: userId={}, policyNumber={}, checkbox1={}, checkbox2={}, checkbox3={}",
                user.getUserId(), user.getPolicyNumber(), user.isCheckbox1(),
                user.isCheckbox2(), user.isCheckbox3());
		Message message = messageConverter.toMessage(user, new MessageProperties());
		rabbitTemplate.send("user-exchange", "user-routing-key", message);
		LOGGER.info("User registration successful");
		return ResponseEntity.ok("User registration successful");
	}

}
