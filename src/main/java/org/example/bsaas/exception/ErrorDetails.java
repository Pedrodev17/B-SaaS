package org.example.bsaas.exception;

public class ErrorDetails extends RuntimeException {
    public ErrorDetails(String message) {
        super(message);
    }
}
