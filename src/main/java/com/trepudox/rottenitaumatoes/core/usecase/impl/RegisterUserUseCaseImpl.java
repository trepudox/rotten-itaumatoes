package com.trepudox.rottenitaumatoes.core.usecase.impl;

import com.trepudox.rottenitaumatoes.core.exception.APIException;
import com.trepudox.rottenitaumatoes.core.usecase.IRegisterUserUseCase;
import com.trepudox.rottenitaumatoes.dataprovider.dto.UserCredentialsDTO;
import com.trepudox.rottenitaumatoes.dataprovider.enums.EnProfile;
import com.trepudox.rottenitaumatoes.dataprovider.model.User;
import com.trepudox.rottenitaumatoes.dataprovider.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RegisterUserUseCaseImpl implements IRegisterUserUseCase {

    //TODO: Realizar cadastro na API de segurança E CORRETAMENTE

    private final UserRepository userRepository;

    @Override
    public User register(UserCredentialsDTO userToBeRegistered) {
        if(userRepository.existsById(userToBeRegistered.getUsername()))
            throw new APIException("Não foi possível realizar o cadastro", "Este usuario já está cadastrado", 422);

        User newUser = User.builder()
                .username(userToBeRegistered.getUsername())
                .password(userToBeRegistered.getPassword())
                .profile(EnProfile.LEITOR.name())
                .score(0)
                .build();

        return userRepository.save(newUser);
    }
}
