package com.trepudox.rottenitaumatoes.dataprovider.client.impl;

import com.trepudox.rottenitaumatoes.dataprovider.client.IAuthServerClient;
import com.trepudox.rottenitaumatoes.dataprovider.client.feign.AuthServerFeignClient;
import com.trepudox.rottenitaumatoes.dataprovider.dto.JwtRequestDTO;
import com.trepudox.rottenitaumatoes.dataprovider.dto.JwtResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthServerClientImpl implements IAuthServerClient {

    private final AuthServerFeignClient authServerFeignClient;

    @Override
    public JwtResponseDTO login(JwtRequestDTO credentials) {
        return authServerFeignClient.login(credentials).getBody();
    }

}
