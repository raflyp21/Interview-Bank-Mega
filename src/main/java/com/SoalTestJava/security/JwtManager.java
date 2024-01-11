package com.SoalTestJava.security;

import io.jsonwebtoken.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class JwtManager {
    private String SECRET_KEY = "bikin-baru-aja-lah";

    private Claims getClaims(String token) {
        JwtParser parser = Jwts.parser().setSigningKey(SECRET_KEY);
        Jws<Claims> signatureAndClaims = parser.parseClaimsJws(token);
        Claims claims = signatureAndClaims.getBody();
        return claims;
    }

    public String getUsername(String token) {
        Claims claims = getClaims(token);
        return claims.get("username", String.class);
    }

    private Date getIssueDate() {
        var now = LocalDateTime.now();
        var systemTime = now.atZone(ZoneId.systemDefault()).toInstant();
        var issueDate = Date.from(systemTime);
        return issueDate;
    }

    private Date getExpiredDate(LocalDateTime startTime, Integer minutes) {
        var expireAt = startTime.plusMinutes(minutes);
        var systemTime = expireAt.atZone(ZoneId.systemDefault()).toInstant();
        var expireDate = Date.from(systemTime);
        return expireDate;
    }

    public String generateToken(String subject, String username, String secretkey, String audience) {
        JwtBuilder builder = Jwts.builder();
        var issueDate = getIssueDate();
        var expireDate = getExpiredDate(LocalDateTime.now(), 180);
        builder = builder.setSubject(subject)
                .claim("username", username)
                .setIssuer("http://localhost:7788")
                .setAudience(audience)
                .setIssuedAt(issueDate)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS256, secretkey);
        return builder.compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        Claims claims = getClaims(token);
        String user = claims.get("username", String.class);
        String audience = claims.getAudience();
        return (user.equals(userDetails.getUsername()));
    }
}