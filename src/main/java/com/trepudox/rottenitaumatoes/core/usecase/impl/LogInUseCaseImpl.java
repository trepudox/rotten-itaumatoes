package com.trepudox.rottenitaumatoes.core.usecase.impl;

import com.trepudox.rottenitaumatoes.core.usecase.ILogInUseCase;
import com.trepudox.rottenitaumatoes.dataprovider.client.IAuthServerClient;
import com.trepudox.rottenitaumatoes.dataprovider.dto.JwtResponseDTO;
import com.trepudox.rottenitaumatoes.dataprovider.dto.UserCredentialsDTO;
import com.trepudox.rottenitaumatoes.util.CustomPasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LogInUseCaseImpl implements ILogInUseCase {

    private final CustomPasswordEncoder customPasswordEncoder;
    private final IAuthServerClient authServerClient;

    @Override
    public JwtResponseDTO logIn(UserCredentialsDTO userToBeLoggedIn) {
        String encodedPassword = customPasswordEncoder.encode(userToBeLoggedIn.getPassword());
        UserCredentialsDTO credentials = new UserCredentialsDTO(userToBeLoggedIn.getUsername(), encodedPassword);

        return authServerClient.login(credentials);
    }

}
