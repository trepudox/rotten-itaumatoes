package com.trepudox.rottenitaumatoes.util;

import com.trepudox.rottenitaumatoes.core.exception.APISecurityException;
import com.trepudox.rottenitaumatoes.dataprovider.enums.EnProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SecurityFilterUtil {

    private final JwtTokenUtil jwtTokenUtil;

    public void validateTokenAndPermissions(HttpServletRequest request, EnProfile profile) {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(authHeader == null || authHeader.isBlank() || !authHeader.startsWith("Bearer "))
            throw new APISecurityException("Token inválido", "Token foi enviado nulo, ou não Bearer", 401);

        String jwt = authHeader.replace("Bearer ", "").trim();

        List<String> roles = jwtTokenUtil.getTokenRoles(jwt);
        if(!roles.contains(profile.name()))
            throw new APISecurityException("Acesso negado", "Usuário não possui permissão necessária para acessar esse recurso", 403);
    }

}
