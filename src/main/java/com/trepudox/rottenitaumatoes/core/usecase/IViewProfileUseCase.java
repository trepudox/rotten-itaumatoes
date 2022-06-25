package com.trepudox.rottenitaumatoes.core.usecase;

import com.trepudox.rottenitaumatoes.dataprovider.model.User;

public interface IViewProfileUseCase {

    User view(String token);

}
