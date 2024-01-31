package com.alura.fiap.application.execeptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class HandlerException {

    private static Logger logger = LoggerFactory.getLogger(HandlerException.class);

    // Private constructor to prevent instantiation
    private HandlerException() {
        throw new AssertionError("Utility class should not be instantiated");
    }

    public static ResponseEntity<Object> handleException(Exception e, Long id, String topic, String errorMessage) {
        if (logger != null) {
            logger.error("{} ID: {}, Topic: {}", errorMessage, id, topic, e);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
    }

    public static void setLogger(Logger mockLogger) {
        // document why this method is empty
    }
}
