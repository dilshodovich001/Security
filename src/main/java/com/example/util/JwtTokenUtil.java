package com.example.util;


import com.example.dto.JwtDTO;
import com.example.enums.ProfileRole;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.IOException;

import java.util.Date;

public class JwtTokenUtil {
    private static final String secretKey = readSecretKeyFromFile();
    private static final int tokenLiveTime = 1000 * 3600 * 24; // 1-day


    public static String encode(String phone, ProfileRole role) {
        JwtBuilder jwtBuilder = Jwts.builder();
        buildCommonClaims(jwtBuilder, phone, role);
        return jwtBuilder.compact();
    }

    public static JwtDTO decode(String token) {
        JwtParser jwtParser = Jwts.parser();
        Jws<Claims> jws = jwtParser.parseClaimsJws(token);
        Claims claims = jws.getBody();
        String phone = (String) claims.get("phone");
        String role = (String) claims.get("role");
        ProfileRole profileRole = ProfileRole.valueOf(role);
        return new JwtDTO(phone, profileRole);
    }

    public static Integer decodeForEmailVerification(String token) {
        JwtParser jwtParser = Jwts.parser();
        Jws<Claims> jws = jwtParser.parseClaimsJws(token);
        Claims claims = jws.getBody();
        return (Integer) claims.get("id");
    }

    private static void buildCommonClaims(JwtBuilder jwtBuilder, String phone, ProfileRole role) {
        jwtBuilder.setIssuedAt(new Date());
        jwtBuilder.signWith(SignatureAlgorithm.HS512, secretKey);
        jwtBuilder.claim("phone", phone);
        jwtBuilder.claim("role", role);
        jwtBuilder.setExpiration(new Date(System.currentTimeMillis() + tokenLiveTime));
        jwtBuilder.setIssuer("YouTube");
    }

    private static String readSecretKeyFromFile() {
        try {

            return "mazgi123xaxa";
        } catch (IOException e) {
            throw new RuntimeException("Kalitni o'qib olishda xatolik", e);
        }
    }
}