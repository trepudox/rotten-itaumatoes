package com.trepudox.rottenitaumatoes.core.usecase;

import com.trepudox.rottenitaumatoes.dataprovider.dto.AccessTokenDTO;
import com.trepudox.rottenitaumatoes.dataprovider.dto.UserCredentialsDTO;

public interface ILogInUseCase {

    AccessTokenDTO logIn(UserCredentialsDTO userToBeLoggedIn);

}
