package com.trepudox.rottenitaumatoes.core.usecase;

import com.trepudox.rottenitaumatoes.dataprovider.dto.UserDTO;
import com.trepudox.rottenitaumatoes.dataprovider.dto.UsernameDTO;

public interface IGiveModeradorUseCase {

    UserDTO give(UsernameDTO username);

}
