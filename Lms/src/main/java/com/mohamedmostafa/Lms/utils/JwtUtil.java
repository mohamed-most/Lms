package com.mohamedmostafa.Lms.utils;

import com.mohamedmostafa.Lms.entity.UserDetailsCustomized;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {


    private final String SECRET_KEY;

    private final long TOKEN_VALIDITY;

    public JwtUtil(@Value("${SECRET_KEY}") String securityKey, @Value("${TOKEN_VALIDITY}") long token_validity) {
        this.SECRET_KEY = securityKey;
        this.TOKEN_VALIDITY = token_validity;
    }

    public Integer extractUserId(String token) {
        return Integer.parseInt(this.extractAllClaims(token).getId());
    }

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    // Generate token with email as subject
    public String generateToken(UserDetailsCustomized userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", userDetails.getAuthorities());
        claims.put("id", userDetails.getUserId());
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername()) // email is subject
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_VALIDITY))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // Extract email (subject) from token
    public String extractEmail(String token) {
        return extractAllClaims(token).getSubject();
    }

    // Validate token
    public boolean validateToken(String token, UserDetails userDetails) {
        final String email = extractEmail(token);
        return (email.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    // Check if token is expired
    private boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }

    // Extract all claims
    private Claims extractAllClaims(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (JwtException e) {
            throw new RuntimeException("Invalid JWT token: " + e.getMessage());
        }
    }
}
