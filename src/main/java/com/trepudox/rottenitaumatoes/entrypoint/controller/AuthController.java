package com.trepudox.rottenitaumatoes.entrypoint.controller;

import com.trepudox.rottenitaumatoes.core.usecase.ILogInUseCase;
import com.trepudox.rottenitaumatoes.core.usecase.IRegisterUserUseCase;
import com.trepudox.rottenitaumatoes.dataprovider.dto.AccessTokenDTO;
import com.trepudox.rottenitaumatoes.dataprovider.dto.JwtResponseDTO;
import com.trepudox.rottenitaumatoes.dataprovider.dto.UserCredentialsDTO;
import com.trepudox.rottenitaumatoes.dataprovider.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final IRegisterUserUseCase registerUserUseCase;
    private final ILogInUseCase logInUseCase;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody UserCredentialsDTO userToBeRegistered) {
        User newUser = registerUserUseCase.register(userToBeRegistered);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponseDTO> login(@RequestBody UserCredentialsDTO userToBeLoggedIn) {
        JwtResponseDTO token = logInUseCase.logIn(userToBeLoggedIn);
        return ResponseEntity.status(HttpStatus.OK).body(token);
    }

}
