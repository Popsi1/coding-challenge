package com.seerbit.codingchallenge.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.naming.TimeLimitExceededException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@ControllerAdvice
public class GlobalResponseExceptionHandler {

        private static final Logger log = LoggerFactory.getLogger(GlobalResponseExceptionHandler.class);

        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<?> notValid(MethodArgumentNotValidException ex) {

                BindingResult bindingResult = ex.getBindingResult();

                Map<String, String> fieldErrors = new HashMap<>();
                for (FieldError fieldError : bindingResult.getFieldErrors()) {
                        String fieldName = fieldError.getField();
                        String errorMessage = fieldError.getDefaultMessage();
                        fieldErrors.put(fieldName, errorMessage);
                }

                return ResponseEntity.badRequest().body(fieldErrors);
        }

        @ExceptionHandler(IOException.class)
        public ResponseEntity<Object> ioException(IOException e) {
                return ResponseEntity.unprocessableEntity().build();
        }

        @ExceptionHandler(TimeLimitExceededException.class)
        public ResponseEntity<Object> timeLimitExceededException(TimeLimitExceededException e) {
                return ResponseEntity.noContent().build();
        }

        @ExceptionHandler(TimeExceededException.class)
        public ResponseEntity<Object> timeExceededException(TimeExceededException e) {
                return ResponseEntity.unprocessableEntity().build();
        }

}
