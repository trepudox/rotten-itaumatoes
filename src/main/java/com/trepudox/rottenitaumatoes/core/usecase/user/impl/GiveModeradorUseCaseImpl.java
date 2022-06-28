package com.trepudox.rottenitaumatoes.core.usecase.user.impl;

import com.trepudox.rottenitaumatoes.core.exception.APIException;
import com.trepudox.rottenitaumatoes.core.mapper.UserMapper;
import com.trepudox.rottenitaumatoes.core.usecase.user.IGiveModeradorUseCase;
import com.trepudox.rottenitaumatoes.dataprovider.dto.UserDTO;
import com.trepudox.rottenitaumatoes.dataprovider.dto.UsernameDTO;
import com.trepudox.rottenitaumatoes.dataprovider.enums.EnProfile;
import com.trepudox.rottenitaumatoes.dataprovider.model.UserModel;
import com.trepudox.rottenitaumatoes.dataprovider.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GiveModeradorUseCaseImpl implements IGiveModeradorUseCase {

    private final UserRepository userRepository;

    @Override
    public UserDTO give(UsernameDTO username) {
        UserModel userModel = retrieveUserModel(username.getUsername());

        checkIfUserAlreadyIsModerador(userModel);

        userModel.setProfile(EnProfile.MODERADOR);
        userRepository.saveAndFlush(userModel);

        return UserMapper.INSTANCE.userModelToDTO(userModel);
    }

    private UserModel retrieveUserModel(String username) {
        return userRepository.findById(username)
                .orElseThrow(() -> new APIException("Solicitação não atendida", "O usuario não existe", 422));
    }

    private void checkIfUserAlreadyIsModerador(UserModel userModel) {
        if(userModel.getProfile().equals(EnProfile.MODERADOR))
            throw new APIException("Usuário já é moderador", "A solicitação não foi atendida, o usuário já é moderador", 422);
    }
}
