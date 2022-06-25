package com.trepudox.rottenitaumatoes.entrypoint.filter;

import com.trepudox.rottenitaumatoes.dataprovider.enums.EnProfile;
import com.trepudox.rottenitaumatoes.util.SecurityFilterUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class AvancadoAuthFilter extends OncePerRequestFilter {

    private static final EnProfile REQUIRED_ROLE = EnProfile.AVANCADO;

    private final SecurityFilterUtil securityFilterUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        securityFilterUtil.validateTokenAndPermissions(request, REQUIRED_ROLE);
        filterChain.doFilter(request, response);
    }

}
