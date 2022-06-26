package com.trepudox.rottenitaumatoes.core.exception.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trepudox.rottenitaumatoes.core.exception.APIException;
import com.trepudox.rottenitaumatoes.core.exception.APISecurityException;
import com.trepudox.rottenitaumatoes.dataprovider.dto.ErrorResponseDTO;
import feign.FeignException;
import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Slf4j
@RestControllerAdvice
public class APIExceptionHandler { //TODO: HANDLER DO SPRING VALIDATION

    @ExceptionHandler(APIException.class)
    public ResponseEntity<ErrorResponseDTO> handleAPIException(APIException e) {
        ErrorResponseDTO error = buildError(e.getTitle(), e.getDetail(), e.getStatus());

        return ResponseEntity.status(HttpStatus.valueOf(e.getStatus())).body(error);
    }

    @ExceptionHandler(APISecurityException.class)
    public ResponseEntity<ErrorResponseDTO> handleAPISecurityException(APISecurityException e) {
        int status = e.getStatus();

        ErrorResponseDTO error = null;
        if(status == 403) {
            error = buildError(e.getTitle(), e.getDetail(), e.getStatus());
        }

        return ResponseEntity.status(HttpStatus.valueOf(status)).body(error);
    }

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<ErrorResponseDTO> handleJwtException(JwtException e) {
        int status = 401;

        return ResponseEntity.status(HttpStatus.valueOf(status)).build();
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

        String title = "Erro de integração com API externa";
        String detail = "Não foi possível estabelecer uma comunicação estável com serviços externos";
        int status = e.status() == -1 ? 504 : e.status();

        log.error(title.concat(" - ").concat(e.request().toString()));

        ErrorResponseDTO error = buildError(title, detail, status);

        return ResponseEntity.status(HttpStatus.valueOf(status)).body(error);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorResponseDTO> handleNoSuchElementException(NoSuchElementException e) {
        int status = 422;

        ErrorResponseDTO error = buildError("Não foi possível realizar a operação", "Entidade não encontrada", status);

        return ResponseEntity.status(HttpStatus.valueOf(status)).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleGenericException(Exception e) {
        if(e instanceof APISecurityException)
            return this.handleAPISecurityException((APISecurityException) e);

        if(e instanceof JwtException)
            return this.handleJwtException((JwtException) e);

        String title = "Não foi possível realizar a operação desejada";
        int status = 500;

        ErrorResponseDTO error = buildError(title, e.getMessage(), status);

        e.printStackTrace();

        return ResponseEntity.status(HttpStatus.valueOf(status)).body(error);
    }

    private ErrorResponseDTO buildError(String title, String detail, int status) {
        return ErrorResponseDTO.builder()
                .title(title)
                .detail(detail)
                .status(status)
                .dateTime(LocalDateTime.now())
                .build();
    }

}
