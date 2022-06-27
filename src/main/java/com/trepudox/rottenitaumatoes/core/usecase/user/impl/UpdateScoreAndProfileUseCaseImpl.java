package com.trepudox.rottenitaumatoes.core.usecase.user.impl;

import com.trepudox.rottenitaumatoes.core.exception.APIException;
import com.trepudox.rottenitaumatoes.core.usecase.user.IUpdateScoreAndProfileUseCase;
import com.trepudox.rottenitaumatoes.dataprovider.enums.EnProfile;
import com.trepudox.rottenitaumatoes.dataprovider.model.UserModel;
import com.trepudox.rottenitaumatoes.dataprovider.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UpdateScoreAndProfileUseCaseImpl implements IUpdateScoreAndProfileUseCase {

    private final UserRepository userRepository;

    @Override
    public void update(String username) {
        UserModel userModel = userRepository.findById(username)
                .orElseThrow(() -> new APIException("Não foi possível atualizar o score e profile do usuario", "Usuario não encontrado", 500));

        userModel.setScore(userModel.getScore() + 1);

        updateProfile(userModel);

        userRepository.saveAndFlush(userModel);
    }

    private void updateProfile(final UserModel userModel) {
        switch(userModel.getProfile().name()) {
            case "LEITOR":
                if(userModel.getScore() >= EnProfile.BASICO.getScore())
                    userModel.setProfile(EnProfile.BASICO);
                break;
            case "BASICO":
                if(userModel.getScore() >= EnProfile.AVANCADO.getScore())
                    userModel.setProfile(EnProfile.AVANCADO);
                break;
            case "AVANCADO":
                if(userModel.getScore() >= EnProfile.MODERADOR.getScore())
                    userModel.setProfile(EnProfile.MODERADOR);
                break;
            case "MODERADOR":
                break;
            default:
                log.warn("Usuario {} possui perfil não mapeado: {}", userModel.getUsername(), userModel.getProfile());
                break;
        }
    }
}
