package com.ibm.medid.handler;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ibm.medid.utility.ResponseUtil;

import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {
    private final ResponseUtil responseUtil;

    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity<?> handleConstraintViolation(ConstraintViolationException exception) {
        List<String> errors = exception.getConstraintViolations().stream()
                .map(e -> e.getPropertyPath() + ": " + e.getMessage()).toList();

        return responseUtil.errorResponse("Validation Failed", errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = BadCredentialsException.class)
    public ResponseEntity<?> handleRuntimeException(BadCredentialsException exception) {
        return responseUtil.errorResponse("Login Failed", exception.getMessage(), HttpStatus.UNAUTHORIZED);
    }
    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<?> handleRuntimeException(RuntimeException exception) {
        return responseUtil.errorResponse("Register Failed", exception.getMessage(), HttpStatus.UNAUTHORIZED);
    }
}
