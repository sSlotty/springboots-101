package com.thanathip.training.backend.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.thanathip.training.backend.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    @Value("${app.token.secret}")
    private String secret;

    @Value("${app.token.issuer}")
    private String issuer;

    public String tokenize(User user, Date expireTime) {


        return JWT.create()
                .withIssuer(issuer)
                .withClaim("principal", user.getId())
                .withClaim("role", "USER")
                .withExpiresAt(expireTime)
                .withIssuedAt(new Date())
                .sign(algorithm());
    }

    public DecodedJWT verify(String token) {

        if (token == null || token.isEmpty()) {
            return null;
        }

        try {

            JWTVerifier verifier = JWT.require(algorithm())
                    .withIssuer(issuer)
                    .build();
            return verifier.verify(token);

        } catch (Exception e) {
            return null;
        }

    }


    private Algorithm algorithm() {
        return Algorithm.HMAC256(secret);
    }
}
