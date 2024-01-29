package com.alura.fiap.application.execeptions;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class HandlerExceptionTest {
    @Test
    void handleException_ShouldLogErrorAndReturnInternalServerError() {
        // Arrange
        Logger mockLogger = mock(Logger.class);
        HandlerException.setLogger(mockLogger);

        Exception mockException = new RuntimeException("Test Exception");
        Long id = 123L;
        String topic = "TestTopic";
        String errorMessage = "Test Error Message";

        // Act
        ResponseEntity<Object> responseEntity = HandlerException.handleException(mockException, id, topic, errorMessage);

        assert responseEntity != null;
        assert HttpStatus.INTERNAL_SERVER_ERROR.equals(responseEntity.getStatusCode());
        assert errorMessage.equals(responseEntity.getBody());
        verify(mockLogger, Mockito.never()).error("{} ID: {}, Topic: {}", errorMessage, id, topic, mockException);
    }

}