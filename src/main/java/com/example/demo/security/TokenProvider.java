package com.example.demo.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.demo.model.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class TokenProvider {
    private static final String SECRET_KEY = "NMA8JPctFuna59f5";

    public String create(UserEntity userEntity) {
        // 기한은 지금부터 1일로 설정
        Date expiryDate = Date.from(
                Instant.now().plus(1, ChronoUnit.DAYS)
        );


        /*
        { // header
            "alg":"HS512"
        }.
        { // payload
            "sub":"40288093784915d201784916a40c0001",
            "iss": "demo app",
            "iat":1595733657,
            "exp":1596597657
        }.
        // SECRET_KEY를 이용해 서명한 부분
        Nn4d1MOVLZg79sfFACTIpCPKqWmpZMZQsbNrXdJJNWkRv50_l7bPLQPwhMobT4vBOG6Q3JYjhDrKFlBSaUxZOg
        */

        // JWT Token 생성
        Map<String, Object> headerClaims = new HashMap();
        headerClaims.put("typ", "JWT");
        headerClaims.put("alg", "HS512");

        Map<String, Object> payloadClaims = new HashMap<>();
        payloadClaims.put("sub", userEntity.getId());
        payloadClaims.put("iss", "demo app");
        payloadClaims.put("iat", new Date());
        payloadClaims.put("exp", expiryDate);

        return JWT.create()
                .withHeader(headerClaims)
                .withPayload(payloadClaims)
                .sign(Algorithm.HMAC512(SECRET_KEY));
    }

    public String validateAndGetUserId(String token) {
        // 위변조 확인, 위조되면 예외를 날림
        // 우리는 userId가 필요하므로 페이로드의 subject값 리턴
        JWTVerifier verifier =  JWT.require(Algorithm.HMAC512(SECRET_KEY)).build();
        return verifier.verify(token).getSubject();
    }
}
