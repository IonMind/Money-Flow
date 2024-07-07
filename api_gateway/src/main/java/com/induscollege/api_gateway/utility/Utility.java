package com.induscollege.api_gateway.utility;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.web.servlet.function.ServerRequest;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Utility {

    // private static ObjectMapper mapper;

    public static String extractJWTToken(ServerRequest request) {
        String authHeader = Optional.ofNullable(request.headers().firstHeader("Authorization"))
                .orElseThrow(() -> new NoSuchElementException("Autherization Header not present"));

        if (!authHeader.startsWith("Bearer ")) {
            throw new NoSuchElementException("Bearer Token Format incorrect");// return false;
        }

        return authHeader.substring(7);
    }

    public static String extractUserIdFromJWT(ServerRequest request) {
        String token = extractJWTToken(request);
        String payload = token.substring(token.indexOf(".") + 1, token.lastIndexOf("."));
        String decodedPayload = new String(Base64.getDecoder().decode(payload), StandardCharsets.UTF_8);
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(decodedPayload);
            String uid = jsonNode.get("userId").asText();
            return uid;
        } catch (Exception e) {
            throw new NoSuchElementException(e.getMessage());
        }
        
    }

}
