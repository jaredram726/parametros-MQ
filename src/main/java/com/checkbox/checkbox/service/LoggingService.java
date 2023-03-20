package com.checkbox.checkbox.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Servicio de logging para registrar información relevante en la aplicación:
 */
@Service
public class LoggingService {

  private final Logger LOGGER = LoggerFactory.getLogger(LoggingService.class);

  public void log(String message) {
	  LOGGER.info(message);
  }

}
