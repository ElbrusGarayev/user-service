package com.userservice.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JwtUtil {


    public String generate(String id, String username) {
        long now = System.currentTimeMillis();
        return Jwts.builder()
                .setId(id)
                .setSubject(username)
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + 100 * 1000))  // in milliseconds
                .signWith(SignatureAlgorithm.HS512, "111".getBytes(StandardCharsets.UTF_8))
                .compact();
    }

    public JwtUser validate(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey("111".getBytes(StandardCharsets.UTF_8))
                .parseClaimsJws(token)
                .getBody();
        if (claims.getExpiration().before(new Date())) {
            throw new RuntimeException();
        }
        return new JwtUser(Long.parseLong(claims.getId()), claims.getSubject());
    }

    public String getTokenFromAuthorizationHeader(String authorizationHeader) {
        return authorizationHeader.replace("Bearer ", "");
    }
}