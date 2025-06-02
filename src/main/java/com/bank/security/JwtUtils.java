package com.bank.security;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.JwtParser;

@Component
public class JwtUtils {

    // Use a secure random 64-byte key for HS512
    private static final String SECRET = "K*kw$%0WV1FKX7L%2QT5KVQA^^VbDUA@CVdU1qcg&89g*UppTLW$N&Vffa6Tt7O0";
    private final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());
    private final long expirationMs = 1000 * 60 * 60; // 1 hour

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(key)
                .compact();
    }
    public String extractUsername(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateToken(String token) {
        try {
            JwtParser jwtParser = Jwts.parserBuilder().setSigningKey(key).build();
            jwtParser.parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}
