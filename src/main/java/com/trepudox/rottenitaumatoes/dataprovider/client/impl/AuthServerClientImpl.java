package com.trepudox.rottenitaumatoes.dataprovider.client.impl;

import com.trepudox.rottenitaumatoes.dataprovider.client.IAuthServerClient;
import com.trepudox.rottenitaumatoes.dataprovider.client.feign.AuthServerFeignClient;
import com.trepudox.rottenitaumatoes.dataprovider.dto.JwtResponseDTO;
import com.trepudox.rottenitaumatoes.dataprovider.dto.UserCredentialsDTO;
import com.trepudox.rottenitaumatoes.dataprovider.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthServerClientImpl implements IAuthServerClient {

    private final AuthServerFeignClient authServerFeignClient;

    @Override
    public JwtResponseDTO login(UserCredentialsDTO credentials) {
        return authServerFeignClient.login(credentials).getBody();
    }

    @Override
    public UserDTO register(UserCredentialsDTO credentials) {
        return authServerFeignClient.register(credentials).getBody();
    }

}
