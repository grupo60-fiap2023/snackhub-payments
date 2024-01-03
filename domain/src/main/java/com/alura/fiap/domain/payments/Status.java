package com.alura.fiap.domain.payments;

public enum Status {

    APRO("approved"),
    OTHER("reject");

    private final String name;

    Status(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
