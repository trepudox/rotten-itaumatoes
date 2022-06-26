package com.trepudox.rottenitaumatoes.core.usecase.user;

import com.trepudox.rottenitaumatoes.dataprovider.dto.UserDTO;
import com.trepudox.rottenitaumatoes.dataprovider.dto.UsernameDTO;

public interface IGiveModeradorUseCase {

    UserDTO give(UsernameDTO username);

}
