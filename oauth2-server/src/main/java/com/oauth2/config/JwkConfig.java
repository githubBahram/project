package com.oauth2.config;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.oauth2.property.OAuth2ConfigProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.KeyPair;
import java.security.interfaces.RSAPublicKey;

@Configuration
public class JwkConfig {


    @Bean
    public JWKSet jwkSet(KeyPair keyPair, OAuth2ConfigProperties oAuth2ConfigProperties) {
        RSAKey.Builder builder = new RSAKey.Builder((RSAPublicKey) keyPair.getPublic())
                .algorithm(JWSAlgorithm.RS256)
                .keyID(oAuth2ConfigProperties.getKeyId());

        return new JWKSet(builder.build());
    }

}


