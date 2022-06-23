package com.trepudox.rottenitaumatoes.core.usecase.impl;

import com.trepudox.rottenitaumatoes.core.exception.APIException;
import com.trepudox.rottenitaumatoes.core.usecase.ILogInUseCase;
import com.trepudox.rottenitaumatoes.dataprovider.dto.AccessTokenDTO;
import com.trepudox.rottenitaumatoes.dataprovider.dto.UserCredentialsDTO;
import com.trepudox.rottenitaumatoes.dataprovider.model.User;
import com.trepudox.rottenitaumatoes.dataprovider.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LogInUseCaseImpl implements ILogInUseCase {

    //TODO: Realizar login na API de segurança E CORRETAMENTE

    private final UserRepository userRepository;

    @Override
    public AccessTokenDTO logIn(UserCredentialsDTO userToBeLoggedIn) {
        APIException exception = new APIException("Login incorreto", "Verifique se usuário e senha estão corretos", 422);

        User user = userRepository.findById(userToBeLoggedIn.getUsername())
                .orElseThrow(() -> exception);

        if(userToBeLoggedIn.getPassword().equals(user.getPassword()))
            return new AccessTokenDTO("token");

        throw exception;
    }

}
