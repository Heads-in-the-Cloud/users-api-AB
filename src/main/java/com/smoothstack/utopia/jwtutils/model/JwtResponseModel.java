package com.smoothstack.utopia.jwtutils.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class JwtResponseModel implements Serializable {

   private static final long serialVersionUID = 1L;

   @Getter
   private final String token;

}
