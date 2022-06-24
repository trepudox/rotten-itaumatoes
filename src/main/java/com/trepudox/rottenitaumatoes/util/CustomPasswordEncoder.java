package com.trepudox.rottenitaumatoes.util;

import com.trepudox.rottenitaumatoes.core.exception.APIException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.spec.KeySpec;

@Component
public class CustomPasswordEncoder {

    @Value("${security.hash.loops}")
    private Integer hashLoops;

    @Value("${security.hash.keyLength}")
    private Integer keyLength;

    @Value("${security.hash.salt}")
    private byte[] salt;

    @Value("${security.hash.algorithm}")
    private String algorithm;

    public String encode(String password) {
        try {
            KeySpec keySpec = new PBEKeySpec(password.toCharArray(), salt, hashLoops, keyLength);
            SecretKeyFactory factory = SecretKeyFactory.getInstance(algorithm);
            byte[] hash = factory.generateSecret(keySpec).getEncoded();

            return new String(hash);
        } catch(Exception e) {
            throw new APIException("Não foi possível realizar a operação", "Houve um erro ao criptografar a senha", 500);
        }
    }

}
