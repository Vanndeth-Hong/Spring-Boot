package org.example.itespringrestapi.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ErrorResponse<T> {
    @Builder.Default
    private LocalDateTime timestamp = LocalDateTime.now();
    private boolean status;
    private int code;
    private String message;
    private T errors;

    @Builder
    public record FieldErrorResponse(
            String field,
            String message
    ) {
    }
}
