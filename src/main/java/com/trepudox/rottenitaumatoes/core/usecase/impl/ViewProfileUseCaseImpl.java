package com.trepudox.rottenitaumatoes.core.usecase.impl;

import com.trepudox.rottenitaumatoes.core.usecase.IViewProfileUseCase;
import com.trepudox.rottenitaumatoes.dataprovider.model.User;
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
    public User view(String token) {
        String jwt = token.replace("Bearer ", "");
        String username = jwtTokenUtil.getSubjectFromToken(jwt);
        return userRepository.findById(username).orElseThrow();
    }
}
