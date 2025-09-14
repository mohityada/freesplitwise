package com.mohit.freesplitwise.Configuration;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
    private String SECRET_KEY = "yyoyoyoyoyoyoyoyoyyoyoyoyoyoyoyoyoyoyRRROOROROR";

    private Key getSignKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }


    public String generateToken(String email){
        return Jwts.builder()
            .setSubject(email)
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + 1000*60*60))
            .signWith(getSignKey(), SignatureAlgorithm.HS256)
            .compact();
    }

    public boolean validateToken(String token, String username){
        return isTokenValid(token) && getUserEmail(token).equals(username);
    }

    public String getUserEmail(String token){
        return extractAllClaims(token).getSubject();
    }

    public boolean isTokenValid(String token){
        return extractAllClaims(token).getExpiration().after(new Date(System.currentTimeMillis()));
    }

    private Claims extractAllClaims(String token){
        return Jwts.parserBuilder()
            .setSigningKey(getSignKey())
            .build()
            .parseClaimsJws(token)
            .getBody();
    }
}
