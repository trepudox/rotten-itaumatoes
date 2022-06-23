package com.trepudox.rottenitaumatoes.core.usecase;

import com.trepudox.rottenitaumatoes.dataprovider.dto.UserCredentialsDTO;
import com.trepudox.rottenitaumatoes.dataprovider.model.User;

public interface IRegisterUserUseCase {

    User register(UserCredentialsDTO userToBeRegistered);

}
