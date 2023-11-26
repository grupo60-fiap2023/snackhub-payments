package com.alura.fiap.domain.exceptions;

import com.alura.fiap.domain.validation.Error;

import java.util.Collection;
import java.util.List;

public class DomainException extends RuntimeException {

    protected final List<Error> errors;

    protected DomainException(final String aMessage, final List<Error> anErrors) {
        super(aMessage);
        this.errors = anErrors;
    }

    public static DomainException with(final Error anErrors) {
        return new DomainException(anErrors.message(), List.of(anErrors));
    }

    public static DomainException with(final List<Error> anErrors) {
        return new DomainException("", anErrors);
    }

    public Collection<? extends Error> getErrors() {
        return errors;
    }
}