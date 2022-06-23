package com.trepudox.rottenitaumatoes.core.exception;

import com.trepudox.rottenitaumatoes.dataprovider.dto.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class APIExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> exceptionExceptionHandler(Exception e) {
        String title = "Não foi possível realizar a operação desejada";
        ErrorResponseDTO body = new ErrorResponseDTO(title, e.getMessage(), 500, LocalDateTime.now());
        e.printStackTrace();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }

}
