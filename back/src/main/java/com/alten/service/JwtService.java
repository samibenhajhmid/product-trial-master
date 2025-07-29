package com.alten.service;


import com.alten.domain.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

@Service
public class JwtService {
    private final String secretKey = "your-secret-key";

    // Génération du token JWT
    public String generateToken(User user) {
        return Jwts.builder()
                .setSubject(user.getEmail())
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    // Extraire le nom d'utilisateur depuis le token
    public String extractUsername(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // Vérification de la validité du token
    public boolean isTokenValid(String token, User user) {
        return user.getEmail().equals(extractUsername(token));
    }
}
