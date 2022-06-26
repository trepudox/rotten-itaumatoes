package com.trepudox.rottenitaumatoes.entrypoint.controller;

import com.trepudox.rottenitaumatoes.core.usecase.user.IGiveModeradorUseCase;
import com.trepudox.rottenitaumatoes.core.usecase.user.IViewProfileUseCase;
import com.trepudox.rottenitaumatoes.dataprovider.dto.UserDTO;
import com.trepudox.rottenitaumatoes.dataprovider.dto.UsernameDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final IViewProfileUseCase viewProfileUseCase;
    private final IGiveModeradorUseCase giveModeradorUseCase;

    @GetMapping("/profile")
    public ResponseEntity<UserDTO> getUserProfileByToken(@RequestHeader("Authorization") String token) {
        UserDTO user = viewProfileUseCase.view(token);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @PostMapping("/give-mod")
    public ResponseEntity<UserDTO> giveModerador(@Valid @RequestBody UsernameDTO username) {
        UserDTO newModUser = giveModeradorUseCase.give(username);
        return ResponseEntity.status(HttpStatus.OK).body(newModUser);
    }

}
