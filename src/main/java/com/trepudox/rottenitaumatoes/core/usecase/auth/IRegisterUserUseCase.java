package com.trepudox.rottenitaumatoes.core.usecase.auth;

import com.trepudox.rottenitaumatoes.dataprovider.dto.UserCredentialsDTO;
import com.trepudox.rottenitaumatoes.dataprovider.dto.UserDTO;
import com.trepudox.rottenitaumatoes.dataprovider.model.UserModel;

public interface IRegisterUserUseCase {

    UserDTO register(UserCredentialsDTO userToBeRegistered);

}
