package com.example.demo_shop.service;

import com.example.demo_shop.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Claims;
import java.security.Key;
import java.util.Date;

@Service
public class JwtService {
    //64 символи
    private final String secreteKey = "PE46ZT3FJCK23LA2NX8R3873A24X525KPE46ZT3FJCK23LA2NX8R3873A24X525K";
    private final UserRepository userRepository;

    public JwtService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 60 * 60 * 1000)) //1 hour
                .signWith(SignatureAlgorithm.HS256, secreteKey)
                .compact();
    }
    // Метод для отримання ключа підпису
    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secreteKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // Метод для валідації токена та отримання username
    public String validateTokenAndGetUsername(String token) {
        // Валідація та парсинг токена
        Claims claims = Jwts.parser()
                .setSigningKey(getSignInKey())  // Вказуємо ключ для валідації
                .build()
                .parseClaimsJws(token)  // Парсимо JWT токен
                .getBody();  // Отримуємо payload

        // Витягуємо 'username' з payload
        return claims.get("username", String.class);  // Має бути правильний ключ для username в payload
    }
}
