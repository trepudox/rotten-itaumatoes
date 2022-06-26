package com.trepudox.rottenitaumatoes.entrypoint.controller;

import com.trepudox.rottenitaumatoes.core.usecase.auth.ILogInUseCase;
import com.trepudox.rottenitaumatoes.core.usecase.auth.IRegisterUserUseCase;
import com.trepudox.rottenitaumatoes.dataprovider.dto.JwtResponseDTO;
import com.trepudox.rottenitaumatoes.dataprovider.dto.UserCredentialsDTO;
import com.trepudox.rottenitaumatoes.dataprovider.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final IRegisterUserUseCase registerUserUseCase;
    private final ILogInUseCase logInUseCase;

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@Valid @RequestBody UserCredentialsDTO userToBeRegistered) {
        UserDTO newUser = registerUserUseCase.register(userToBeRegistered);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponseDTO> login(@Valid @RequestBody UserCredentialsDTO userToBeLoggedIn) {
        JwtResponseDTO token = logInUseCase.logIn(userToBeLoggedIn);
        return ResponseEntity.status(HttpStatus.OK).body(token);
    }

}
