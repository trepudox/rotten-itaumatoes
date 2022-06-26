package com.trepudox.rottenitaumatoes.core.usecase.user;

import com.trepudox.rottenitaumatoes.dataprovider.dto.UserDTO;

public interface IViewProfileUseCase {

    UserDTO view(String token);

}
