package com.trepudox.rottenitaumatoes.core.usecase.user.impl;

import com.trepudox.rottenitaumatoes.core.exception.APIException;
import com.trepudox.rottenitaumatoes.core.mapper.UserMapper;
import com.trepudox.rottenitaumatoes.core.usecase.user.IViewProfileUseCase;
import com.trepudox.rottenitaumatoes.dataprovider.dto.UserDTO;
import com.trepudox.rottenitaumatoes.dataprovider.model.UserModel;
import com.trepudox.rottenitaumatoes.dataprovider.repository.UserRepository;
import com.trepudox.rottenitaumatoes.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ViewProfileUseCaseImpl implements IViewProfileUseCase {

    private final UserRepository userRepository;
    private final JwtTokenUtil jwtTokenUtil;

    @Override
    public UserDTO view(String token) {
        String username = getUsernameFromToken(token);
        UserModel userModel = getUserModel(username);

        return UserMapper.INSTANCE.userModelToDTO(userModel);
    }

    private String getUsernameFromToken(String token) {
        String jwt = token.replace("Bearer ", "");
        return jwtTokenUtil.getSubjectFromToken(jwt);
    }

    private UserModel getUserModel(String username) {
        return userRepository.findById(username)
                .orElseThrow(() -> new APIException("Solicitação não atendida", "O usuario da requisição não existe", 500));
    }

}
