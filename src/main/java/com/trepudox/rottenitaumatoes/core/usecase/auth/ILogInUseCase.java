package com.trepudox.rottenitaumatoes.core.usecase.auth;

import com.trepudox.rottenitaumatoes.dataprovider.dto.JwtResponseDTO;
import com.trepudox.rottenitaumatoes.dataprovider.dto.UserCredentialsDTO;

public interface ILogInUseCase {

    JwtResponseDTO logIn(UserCredentialsDTO userToBeLoggedIn);

}
