package com.trepudox.rottenitaumatoes.dataprovider.client;

import com.trepudox.rottenitaumatoes.dataprovider.dto.JwtResponseDTO;
import com.trepudox.rottenitaumatoes.dataprovider.dto.UserCredentialsDTO;
import com.trepudox.rottenitaumatoes.dataprovider.dto.UserDTO;

public interface IAuthServerClient {

    JwtResponseDTO login(UserCredentialsDTO credentials);

    UserDTO register(UserCredentialsDTO credentials);

}
