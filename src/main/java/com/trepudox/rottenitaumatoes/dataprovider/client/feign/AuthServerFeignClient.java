package com.trepudox.rottenitaumatoes.dataprovider.client.feign;

import com.trepudox.rottenitaumatoes.dataprovider.dto.JwtRequestDTO;
import com.trepudox.rottenitaumatoes.dataprovider.dto.JwtResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "AuthServerFeignClient", url = "${feign.config.authServer.url}")
public interface AuthServerFeignClient {

    @PostMapping("/auth/login")
    ResponseEntity<JwtResponseDTO> login(@RequestBody JwtRequestDTO credentials);

}
