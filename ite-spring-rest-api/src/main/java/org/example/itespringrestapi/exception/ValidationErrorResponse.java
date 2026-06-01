package org.example.itespringrestapi.exception;

public record ValidationErrorResponse(
        String field,
        String message
) {
}
