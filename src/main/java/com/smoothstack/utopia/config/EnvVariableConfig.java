package com.smoothstack.utopia.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Component
public class EnvVariableConfig {
    @Value("${JWT_SECRET}")
    private String jwtSecret;
}
