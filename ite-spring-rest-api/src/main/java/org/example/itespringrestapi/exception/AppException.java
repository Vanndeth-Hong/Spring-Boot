package org.example.itespringrestapi.exception;

import lombok.extern.slf4j.Slf4j;
import org.example.itespringrestapi.dto.ErrorResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
@Slf4j
public class AppException {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorResponse<?>> handleServiceException(ResponseStatusException e) {
        log.error("Service Exception happened: {}", e.getReason());

        ErrorResponse<?> errorResponse = ErrorResponse.builder()
                .status(false)
                .code(e.getStatusCode().value())
                .message("Service Exception errored")
                .errors(e.getReason())
                .build();

        return ResponseEntity.status(e.getStatusCode()).body(errorResponse);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse<?> handleValidationException(MethodArgumentNotValidException e) {
        log.error("Validation Exception happened");

        List<ErrorResponse.FieldErrorResponse> fields = new ArrayList<>();
        e.getFieldErrors().forEach(fieldError -> {
            ErrorResponse.FieldErrorResponse field = ErrorResponse.FieldErrorResponse.builder()
                    .field(fieldError.getField())
                    .message(fieldError.getDefaultMessage())
                    .build();
            fields.add(field);
        });


        return ErrorResponse.builder()
                .status(false)
                .code(HttpStatus.BAD_REQUEST.value())
                .message("Validation is errored")
                .errors(fields)
                .build();
    }
}
