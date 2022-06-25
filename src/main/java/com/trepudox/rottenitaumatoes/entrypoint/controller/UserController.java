package com.trepudox.rottenitaumatoes.entrypoint.controller;

import com.trepudox.rottenitaumatoes.core.usecase.IViewProfileUseCase;
import com.trepudox.rottenitaumatoes.dataprovider.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final IViewProfileUseCase viewProfileUseCase;

    @GetMapping("/profile")
    public ResponseEntity<User> getUserProfile(@RequestHeader("Authorization") String token) {
        User user = viewProfileUseCase.view(token);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

//    @PostMapping("/give-mod")
//    public ResponseEntity<User> giveModerador(@RequestBody userToBeModerador) {
//        TODO: dar moderador e retornar novo perfil do usuario
//        return ResponseEntity.status(HttpStatus.OK).body(user);
//    }

}
