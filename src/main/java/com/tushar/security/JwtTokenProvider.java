package com.tushar.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {

    private static final String JWT_SECRET =
            "mY0xg8D3G8X5p9VbKZk8eY4xFzL9V0A2rW5cQ1kN6oM=";

    private static final long JWT_EXPIRATION = 86400000; // 1 day

    private final Key key;

    public JwtTokenProvider() {
        this.key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(JWT_SECRET));
    }

    public String generateToken(Long userId, Long tenantId) {
        return Jwts.builder()
                .setSubject(String.valueOf(userId))
                .claim("tenantId", tenantId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
