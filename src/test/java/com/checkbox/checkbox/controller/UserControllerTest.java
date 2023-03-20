package com.checkbox.checkbox.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import static org.mockito.ArgumentMatchers.eq;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.checkbox.checkbox.entity.UserPolicy;

/**
 *Utilizamos las anotaciones @RunWith(SpringRunner.class) y 
 *@SpringBootTest para ejecutar las pruebas en un contexto de Spring y 
 *tener acceso a los beans de la aplicación.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {

  @Autowired
  private UserController userController;

  @MockBean
  private RabbitTemplate rabbitTemplate;

  /**
   * simulamos una solicitud HTTP para el método registerUser() del UserController y 
   * verificamos que la respuesta sea correcta. Luego, utilizamos un objeto 
   * ArgumentCaptor para capturar el mensaje que se envía por RabbitMQ y verificamos 
   * que el contenido del mensaje sea el objeto User que hemos enviado.
   * @throws Exception
   */
  @Test
  void testRegisterUser() throws Exception {
    UserPolicy user = new UserPolicy("John Doe", "12345678", true, false, true);

    ResponseEntity<String> response = userController.registerUser(user);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals("User registration successful", response.getBody());

    ArgumentCaptor<Message> argument = ArgumentCaptor.forClass(Message.class);
    verify(rabbitTemplate).send(eq("user-exchange"), eq("user-routing-key"), argument.capture());
  }

  /**
   * Se crea explícitamente un objeto Message utilizando MessageBuilder de Spring AMQP. 
   * El objeto Message se configura con la cadena JSON y se establece el tipo de 
   * contenido del mensaje en "application/json". Luego, se pasa este objeto Message 
   * al método send de rabbitTemplate.
   * Finalmente, se verifica que el objeto Message se haya enviado correctamente a la 
   * cola de RabbitMQ utilizando la función eq para comparar los valores esperados con 
   * los valores actuales.
   */
  @Test
  void testSendMessageToRabbitMQ() {
      String json = "{\"username\": \"john\", \"policyNumber\": \"123456\", \"option1\": true, \"option2\": false, \"option3\": true}";
      Message message = MessageBuilder
          .withBody(json.getBytes())
          .setContentType(MediaType.APPLICATION_JSON_VALUE)
          .build();
      rabbitTemplate.send("user-exchange", "user-routing-key", message);

      verify(rabbitTemplate).send(
          eq("user-exchange"),
          eq("user-routing-key"),
          eq(message)
      );
  }
  
}
