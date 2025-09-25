package com.project.backend.services;

import org.springframework.stereotype.Service;

@Service
public class TokenService {

    public String generateToken(String username) {
        // TODO: implement real token generation (e.g., JWT)
        return "token_for_" + username;
    }

    public boolean validateToken(String token) {
        // TODO: implement real token validation
        return token != null && token.startsWith("token_for_");
    }
}
