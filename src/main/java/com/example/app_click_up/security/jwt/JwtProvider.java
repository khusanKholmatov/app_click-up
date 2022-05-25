package com.example.app_click_up.security.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtProvider {
    private final String secretKey = "secretKeyOfTheProject";

    public String generateToken(String username) {
        Date expirationDate = new Date(System.currentTimeMillis() + 1000 * 60 * 5);

        String token = Jwts
                .builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
        return token;
    }

    public String getUsernameFromToken(String token) {
        try {
            String email = Jwts
                    .parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token)
                    .getBody().getSubject();
            return email;
        }catch (Exception e){
            return null;
        }
    }

}
