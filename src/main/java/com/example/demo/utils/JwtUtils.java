package com.example.demo.utils;

import com.example.demo.model.UserDetailsImpl;
import io.jsonwebtoken.*;
import net.minidev.json.JSONValue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtils {

    private String jwtSecret = "bezKoderSecretKey";

    private int jwtExpirationMs = 8640000;

    public String generateJwtToken(Authentication authentication) {

        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
        Map obj = new HashMap();
        obj.put("user_id",userPrincipal.getId());
        obj.put("user_name",userPrincipal.getUsername());
        obj.put("user_email",userPrincipal.getEmail());
        obj.put("role", userPrincipal.getRole());
        obj.put("iat", (new Date().getTime()));
        obj.put("exp", (new Date((new Date()).getTime() + jwtExpirationMs)).getTime());
        String payload = JSONValue.toJSONString(obj);

        return Jwts.builder()
                .setPayload(payload)
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            System.out.println("Invalid JWT signature: " + e.getMessage());
        } catch (MalformedJwtException e) {
            System.out.println("Invalid JWT token: " + e.getMessage());
        } catch (ExpiredJwtException e) {
            System.out.println("JWT token is expired: " + e.getMessage());
        } catch (UnsupportedJwtException e) {
            System.out.println("JWT token is unsupported: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("JWT claims string is empty: " + e.getMessage());
        }

        return false;
    }
}

