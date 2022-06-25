package com.trepudox.rottenitaumatoes.dataprovider.client;

import com.trepudox.rottenitaumatoes.dataprovider.dto.JwtRequestDTO;
import com.trepudox.rottenitaumatoes.dataprovider.dto.JwtResponseDTO;

public interface IAuthServerClient {

    JwtResponseDTO login(JwtRequestDTO credentials);

}
