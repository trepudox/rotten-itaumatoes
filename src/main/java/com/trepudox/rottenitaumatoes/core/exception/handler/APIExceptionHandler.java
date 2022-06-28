package com.trepudox.rottenitaumatoes.core.exception.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trepudox.rottenitaumatoes.core.exception.APIException;
import com.trepudox.rottenitaumatoes.core.exception.APISecurityException;
import com.trepudox.rottenitaumatoes.dataprovider.dto.ArtifactDTO;
import com.trepudox.rottenitaumatoes.dataprovider.dto.ErrorResponseDTO;
import feign.FeignException;
import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class APIExceptionHandler {

    @ExceptionHandler(APIException.class)
    public ResponseEntity<ErrorResponseDTO> handleAPIException(APIException e) {
        ErrorResponseDTO error = buildError(e.getTitle(), e.getDetail(), e.getStatus());

        log.error("{} - {}: {}", e.getClass().getSimpleName(), e.getTitle(), e.getDetail());

        return ResponseEntity.status(HttpStatus.valueOf(e.getStatus())).body(error);
    }

    @ExceptionHandler(APISecurityException.class)
    public ResponseEntity<ErrorResponseDTO> handleAPISecurityException(APISecurityException e) {
        int status = e.getStatus();

        ErrorResponseDTO error = null;
        if(status == 403) {
            error = buildError(e.getTitle(), e.getDetail(), e.getStatus());
        }

        log.error("{} - {}: {}", e.getClass().getSimpleName(), e.getTitle(), e.getDetail());

        return ResponseEntity.status(HttpStatus.valueOf(status)).body(error);
    }

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<ErrorResponseDTO> handleJwtException(JwtException e) {
        int status = 401;

        log.error("Erro de token: {}", e.getMessage());

        return ResponseEntity.status(HttpStatus.valueOf(status)).build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String title = "Parâmetros inválidos";
        String detail = "Um ou mais parâmetros foram enviados de maneira errônea";
        List<ArtifactDTO> artifacts = new ArrayList<>();
        int status = 400;

        List<ObjectError> fieldErrors = e.getBindingResult().getAllErrors();
        for(ObjectError error : fieldErrors) {
            FieldError fieldError = (FieldError) error;
            String fieldName = fieldError.getField();
            String message = fieldError.getDefaultMessage();
            Object rejectedValue = fieldError.getRejectedValue();

            artifacts.add(new ArtifactDTO(fieldName, message, rejectedValue));
        }

        ErrorResponseDTO error = buildError(title, detail, artifacts, status);
        log.error("{}: {}", e.getClass().getSimpleName(), e.getMessage());

        return ResponseEntity.status(HttpStatus.valueOf(400)).body(error);
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

        ErrorResponseDTO error = buildError(title, detail, status);
        log.error(title.concat(": ").concat(e.request().toString()));

        return ResponseEntity.status(HttpStatus.valueOf(status)).body(error);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponseDTO> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        String title = "Não foi possível ler a requisição";
        String detail = "A requisição foi mal formada, possuindo erros de sintaxe";
        int status = 400;

        ErrorResponseDTO error = buildError(title, detail, status);
        log.error("HttpMessageNotReadableException: {}", e.getMessage());

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
                .artifacts(new ArrayList<>())
                .dateTime(LocalDateTime.now())
                .build();
    }

    private ErrorResponseDTO buildError(String title, String detail, List<ArtifactDTO> artifacts, int status) {
        return ErrorResponseDTO.builder()
                .title(title)
                .detail(detail)
                .artifacts(artifacts)
                .status(status)
                .dateTime(LocalDateTime.now())
                .build();
    }

}
