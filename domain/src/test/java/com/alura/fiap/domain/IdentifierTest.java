package com.alura.fiap.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class IdentifierTest {

    // Concrete subclass for testing
    private static class TestIdentifier extends Identifier {
        private final Long value;

        public TestIdentifier(Long value) {
            this.value = value;
        }

        @Override
        public Long getValue() {
            return value;
        }
    }

    @Test
    public void testGetValue() {
        // Given
        Long expectedValue = 42L;

        // When
        Identifier identifier = new TestIdentifier(expectedValue);

        // Then
        assertEquals(expectedValue, identifier.getValue());
    }
}
