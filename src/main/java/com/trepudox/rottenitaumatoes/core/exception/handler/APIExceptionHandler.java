package com.trepudox.rottenitaumatoes.core.exception.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trepudox.rottenitaumatoes.core.exception.APIException;
import com.trepudox.rottenitaumatoes.dataprovider.dto.ErrorResponseDTO;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@Slf4j
@RestControllerAdvice
public class APIExceptionHandler {

    @ExceptionHandler(APIException.class)
    public ResponseEntity<ErrorResponseDTO> apiExceptionHandler(APIException e) {
        HttpStatus status = HttpStatus.valueOf(e.getStatus());

        ErrorResponseDTO errorBody = ErrorResponseDTO.builder()
                .title(e.getTitle())
                .detail(e.getDetail())
                .status(e.getStatus())
                .dateTime(LocalDateTime.now())
                .build();

        return ResponseEntity.status(status).body(errorBody);
    }

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<ErrorResponseDTO> handleFeignException(FeignException e) {
        try {
            ObjectMapper om = new ObjectMapper().findAndRegisterModules();
            ErrorResponseDTO error = om.readValue(e.contentUTF8(), ErrorResponseDTO.class);
            return ResponseEntity.status(HttpStatus.valueOf(error.getStatus())).body(error);
        } catch(Exception exception) {
            log.warn("Não foi possível converter o JSON no metodo handleFeignException()");
        }

        int status = e.status();

        ErrorResponseDTO error = ErrorResponseDTO.builder()
                .title("Erro de integração com API externa")
                .detail(e.getLocalizedMessage().replace('"', '\''))
                .status(status)
                .dateTime(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.valueOf(status)).body(error);
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
