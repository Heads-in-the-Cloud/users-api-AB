package com.smoothstack.utopia.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final EnvVariableConfig envConfig;
    private final AuthenticationManager authenticationManager;
    private static final long EXPIRATION_TIMEOUT_ACCESS = 240 * 60 * 1000;
    private static final long EXPIRATION_TIMEOUT_REFRESH = 120 * 60 * 1000;

    @Override
    public Authentication attemptAuthentication(
        final HttpServletRequest request,
        final HttpServletResponse response
    ) throws AuthenticationException {
        final String username = request.getParameter("username");
        final String password = request.getParameter("password");
        final UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        return authenticationManager.authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(
        final HttpServletRequest request,
        final HttpServletResponse response,
        final FilterChain chain,
        final Authentication authentication
    ) throws IOException {
        final User user = (User) authentication.getPrincipal();
        final Algorithm algorithm = Algorithm.HMAC512(envConfig.getJwtSecret().getBytes());
        final String accessToken = JWT.create()
            .withSubject(user.getUsername())
            .withExpiresAt(new Date(new Date().getTime() + EXPIRATION_TIMEOUT_ACCESS))
            .withIssuer(request.getRequestURL().toString())
            .withClaim("role", user.getAuthorities().iterator().next().getAuthority())
            .sign(algorithm);
        final String refreshToken = JWT.create()
            .withSubject(user.getUsername())
            .withExpiresAt(new Date(new Date().getTime() + EXPIRATION_TIMEOUT_REFRESH))
            .withIssuer(request.getRequestURL().toString())
            .sign(algorithm);
        final Map<String, String> tokens = new HashMap<>();
        tokens.put("access_token", accessToken);
        tokens.put("refresh_token", refreshToken);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), tokens);
    }
}

