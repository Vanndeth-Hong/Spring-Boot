package org.example.itespringrestapi.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class AppException {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleException(MethodArgumentNotValidException e){

        List<ValidationErrorResponse> errorList = new ArrayList<>();

        e.getFieldErrors().forEach(fieldError -> {
            errorList.add(new ValidationErrorResponse(
                    fieldError.getField(),
                    fieldError.getDefaultMessage()
            ));
        });

        Map<String, Object> response = new HashMap<>();

        response.put("status", "false");
        response.put("code", "400");
        response.put("message", "validation is errored");
        response.put("error", errorList);


        return ResponseEntity.badRequest().body(response);
    }
}