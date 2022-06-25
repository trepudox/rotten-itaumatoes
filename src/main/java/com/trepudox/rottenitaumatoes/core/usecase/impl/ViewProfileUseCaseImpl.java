package com.trepudox.rottenitaumatoes.core.usecase.impl;

import com.trepudox.rottenitaumatoes.core.mapper.UserMapper;
import com.trepudox.rottenitaumatoes.core.usecase.IViewProfileUseCase;
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
        String jwt = token.replace("Bearer ", "");
        String username = jwtTokenUtil.getSubjectFromToken(jwt);
        UserModel userModel = userRepository.findById(username).orElseThrow();

        return UserMapper.INSTANCE.userModelToDTO(userModel);
    }
}
