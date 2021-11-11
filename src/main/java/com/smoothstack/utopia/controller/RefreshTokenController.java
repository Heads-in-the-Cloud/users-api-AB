package com.smoothstack.utopia.controller;

import com.smoothstack.utopia.exception.NotFoundException;
import com.smoothstack.utopia.entity.User;
import com.smoothstack.utopia.service.UserService;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/token_refresh")
public class RefreshTokenController {

    private final UserService service;

    private static final long EXPIRATION_TIMEOUT_ACCESS = 10 * 60 * 1000;

    @GetMapping
    public void refreshToken(
        final HttpServletRequest request,
        final HttpServletResponse response
    ) throws IOException {
        final String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try {
                final String refreshToken = authorizationHeader.substring("Bearer ".length());
                final Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                final JWTVerifier verifier = JWT.require(algorithm).build();
                final DecodedJWT decodedJWT = verifier.verify(refreshToken);
                final String username = decodedJWT.getSubject();
                final User user = service.selectByUsername(username).orElseThrow(NotFoundException::new);
                final String accessToken = JWT.create()
                    .withSubject(user.getUsername())
                    .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIMEOUT_ACCESS))
                    .withIssuer(request.getRequestURL().toString())
                    .withClaim("role", user.getRole().getName())
                    .sign(algorithm);
                Map<String, String> tokens = new HashMap<>();
                tokens.put("access_token", accessToken);
                tokens.put("refresh_token", refreshToken);
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);
            } catch(final Exception exception) {
                response.setHeader("error_message", exception.getMessage());
                response.setStatus(HttpStatus.FORBIDDEN.value());
            }
        } else {
            throw new RuntimeException("Refresh token is missing");
        }
    }
}
