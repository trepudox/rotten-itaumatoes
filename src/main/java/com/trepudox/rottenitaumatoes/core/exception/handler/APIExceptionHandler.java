package com.trepudox.rottenitaumatoes.core.exception.handler;

import com.trepudox.rottenitaumatoes.core.exception.APIException;
import com.trepudox.rottenitaumatoes.dataprovider.dto.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class APIExceptionHandler {

    @ExceptionHandler(APIException.class)
    public ResponseEntity<ErrorResponseDTO> apiExceptionHandler(APIException e) {
        HttpStatus status = HttpStatus.valueOf(e.getStatusCode());

        ErrorResponseDTO errorBody = ErrorResponseDTO.builder()
                .title(e.getTitle())
                .detail(e.getDetail())
                .status(e.getStatusCode())
                .dateTime(LocalDateTime.now())
                .build();

        return ResponseEntity.status(status).body(errorBody);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> exceptionExceptionHandler(Exception e) {
        String title = "Não foi possível realizar a operação desejada";

        ErrorResponseDTO errorBody = ErrorResponseDTO.builder()
                .title(title)
                .detail(e.getMessage())
                .status(500)
                .dateTime(LocalDateTime.now())
                .build();

        e.printStackTrace();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorBody);
    }

}
