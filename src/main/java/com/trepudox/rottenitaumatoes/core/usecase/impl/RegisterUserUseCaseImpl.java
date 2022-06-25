package com.trepudox.rottenitaumatoes.core.usecase.impl;

import com.trepudox.rottenitaumatoes.core.usecase.IRegisterUserUseCase;
import com.trepudox.rottenitaumatoes.dataprovider.client.IAuthServerClient;
import com.trepudox.rottenitaumatoes.dataprovider.dto.UserCredentialsDTO;
import com.trepudox.rottenitaumatoes.dataprovider.dto.UserDTO;
import com.trepudox.rottenitaumatoes.util.CustomPasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RegisterUserUseCaseImpl implements IRegisterUserUseCase {

    private final CustomPasswordEncoder customPasswordEncoder;
    private final IAuthServerClient authServerClient;

    @Override
    public UserDTO register(UserCredentialsDTO userToBeRegistered) {
        String encodedPassword = customPasswordEncoder.encode(userToBeRegistered.getPassword());
        UserCredentialsDTO newUserCredentials = new UserCredentialsDTO(userToBeRegistered.getUsername(), encodedPassword);

        return authServerClient.register(newUserCredentials);
    }
}
